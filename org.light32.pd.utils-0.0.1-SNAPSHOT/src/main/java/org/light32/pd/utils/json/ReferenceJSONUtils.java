package org.light32.pd.utils.json;

import org.light32.pd.utils.StringUtils;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This is my own implementation of a JSON serializer/deserializer.
 * It uses scans for public accessor methods to determine what goes in or out
 * of the method.
 * 
 * @author jwhitt
 *
 */
class ReferenceJSONUtils implements JSONUtils {

	private final int MAX_DEPTH = 5;

	private final boolean ENABLE_CACHING = true;
	
	// methods that return types in this Set will not be serialized.
	private List<Class<?>> returnTypeBlacklist= new ArrayList<Class<?>>();
	
	public ReferenceJSONUtils() { 
		super();
		returnTypeBlacklist.add(java.lang.String.class);
		returnTypeBlacklist.add(java.lang.Integer.class);
		returnTypeBlacklist.add(java.lang.Double.class);
		returnTypeBlacklist.add(java.lang.Long.class);
		returnTypeBlacklist.add(java.lang.Short.class);
		returnTypeBlacklist.add(java.lang.Byte.class);
		returnTypeBlacklist.add(java.lang.Boolean.class);
		returnTypeBlacklist.add(java.lang.Float.class);
		returnTypeBlacklist.add(java.lang.Class.class);
		
	}
	
	/* (non-Javadoc)
	 * @see org.light32.pd.utils.json.JSONUtils#toJSON(java.lang.Object)
	 */
	@Override
	public String toJSON(Object obj) {
		try {
			return handleObject(new StringBuffer(""),obj,MAX_DEPTH);
		} catch (IllegalAccessException 
				| IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.light32.pd.utils.json.JSONUtils#fromJSON(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> T fromJSON(Class<T> klass, String jsonStr) throws InstantiationException, IllegalAccessException {
		
		// first build the method map
		// returnTypeMap: variablename -> returnType
		// setterMap: variableName -> setter method
		// constructorMap: variableName -> 
		Map<String,Class<?>> returnTypeMap = new HashMap<String,Class<?>>();
		Map<String,Method> setterMap = new HashMap<String,Method>();
		Map<String,Constructor<?>> constructorMap = new HashMap<String,Constructor<?>>();
		
		// cache the method maps by class
		if(ENABLE_CACHING) { 
//			classReturnTypeCache.put(klass, returnTypeMap);
//			classSetterCache.put(klass, setterMap);
//			classConstructorCache.put(klass, constructorMap);	
		}
		
		T newObj = klass.newInstance();
        // use the JEE7 Json parser
        JsonParser parser = Json.createParser(new StringReader(jsonStr));
		String fieldName = "";
		List<Object> arrayHolder = new ArrayList<Object>();
		boolean inArray = false;
		
		while(parser.hasNext()) { 
			Event event = parser.next();
			try {
			switch(event) {
			case END_ARRAY:
				setterMap.get(fieldName).invoke(newObj,arrayHolder.toArray());
				fieldName = "";
				inArray = false;
				break;
			case END_OBJECT:
				break;
			case KEY_NAME:
				fieldName = parser.getString();
				break;
			case START_ARRAY:
				arrayHolder.clear();
				inArray = true;
				break;
			case START_OBJECT:
				
				break;
			case VALUE_FALSE:
				setterMap.get(fieldName).invoke(newObj,false);
				fieldName = "";
				break;
			case VALUE_NULL:
				setterMap.get(fieldName).invoke(newObj,(Object[])null);
				fieldName = "";
				break;
			case VALUE_NUMBER:
				setterMap.get(fieldName).invoke(newObj,parser.getInt());
				fieldName = "";
				break;
			case VALUE_STRING:
				setterMap.get(fieldName).invoke(newObj,parser.getString());
				fieldName = "";
				break;
			case VALUE_TRUE:
				setterMap.get(fieldName).invoke(newObj,true);
				fieldName = "";
				break;
			default:
				break; 
			
			}
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return newObj;
	}

	/**
	 * converts an Object to a JsonObject
	 * 
	 * This scans the class for public methods,
	 * and if they fit the JavaBean accessor pattern,
	 * calls the method and appends its value.
	 * will recurse for accessors that return getters 
	 * 
	 * @param obj
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	String handleObject(StringBuffer outBuffer, Object obj, int maxDepth) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException { 

		// if at max depth, just return the toString() name of the class of the object to be serialized.
		if(maxDepth == 0) { 
			return obj.getClass().toString();
		}
		
		outBuffer.append("{");
		
		// get public methods
		Method[] methods = obj.getClass().getMethods();
		for(Method method : methods) { 	
			// honor transient, ignore abstract
			if(methodIsEligible(method)) { 
				handleMethod(outBuffer, method, obj, maxDepth, method.getName());
			}
		}

		// delete last ,
		outBuffer.deleteCharAt(outBuffer.length()-1);
		outBuffer.append("}");
		return outBuffer.toString();
	}

	private String handleMethod(StringBuffer outBuffer, Method method, Object obj, int maxDepth, String methodName) 
			throws IllegalAccessException, InvocationTargetException {
	
		Class<?> returnType = method.getReturnType();
			
			// convert method name to variable and append
			handleFieldName(outBuffer, methodName);
			outBuffer.append(":");
			
			Object returnValue = method.invoke(obj);

			if (returnType.isArray()) { 
				// need special logic to handle arrays since
				// the reflection API is not consistent with them
				handleArray(outBuffer, returnValue, returnType.getComponentType());
			}
			else {
				// the method returns a single object.  
				// enums will use .name() to get the exact name of the enum constant
				if (returnType.isEnum()) { 
					handleValueEnum(outBuffer, returnValue);		
				} 
				// check for deep serialization.
				// if the return type has public getters and isn't on the list of 
				// types not to deep serialize, call recursively to serialize the object
				else if (returnType.isAssignableFrom(Map.class)) { 
					handleMap(outBuffer, returnValue);
				}
				else if (hasPublicGetters(returnType) 
						&& !returnTypeBlacklist.contains(returnType)) {
						handleObject(outBuffer, returnValue, maxDepth-1);
				}
				else {
					handleValue(outBuffer, returnValue);
				}
			}
			// comma after value
			outBuffer.append(",");
		
		return outBuffer.toString();
	}

	@SuppressWarnings("unchecked")
	private void handleMap(StringBuffer outBuffer, Object returnValue) {
		Map<Object,Object> theMap = (Map<Object,Object>)returnValue;
	
		outBuffer.append("{");

		for(Map.Entry<Object, Object> entry : theMap.entrySet()){
			outBuffer.append("\"")
				.append(entry.getKey())
				.append("\":\"")
				.append(entry.getValue())
				.append("\",");
		}
		// add entry for class
		outBuffer.append("\"class\":\"")
		.append(theMap.getClass().toString())
		.append("\",");

		outBuffer.deleteCharAt(outBuffer.length()-1)
			.append("}");
		
	}

	private void handleFieldName(StringBuffer outBuffer, String methodName) {
		outBuffer.append("\"")
			.append(StringUtils.m2var(methodName))
			.append("\"");
	}

	private boolean methodIsEligible(Method method) { 
		return !Modifier.isTransient(method.getModifiers()) 
				&& !Modifier.isAbstract(method.getModifiers()) 
				&& StringUtils.methodIsGetter(method.getName());
	}

	private void handleValueEnum(StringBuffer outBuffer, Object methodOut) {
		outBuffer.append("\"")
		.append(((Enum<?>)methodOut).name())
		.append("\"");
	}

	private void handleValue(StringBuffer outBuffer,
			Object methodOut) {
		// implicit toString()
		outBuffer.append("\"")
			.append(methodOut)
			.append("\"");
	}

	private boolean hasPublicGetters(Class<?> returnType) {
		Method[] methods = returnType.getMethods();
		for(Method method : methods) { 			
			String name = method.getName();		
			if(StringUtils.methodIsGetter(name) && !name.equals("getClass")) { 
				return true;
			}
		}
			return false;
	}

	private String handleArray(StringBuffer outBuffer, Object methodOut, Class<?> componentType) {

		outBuffer.append("[");
		if (componentType == Integer.TYPE) {
			for(int ret : (int[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Double.TYPE) {
			for(double ret : (double[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Float.TYPE) {
			for(float ret : (float[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Boolean.TYPE) {
			for(boolean ret : (boolean[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Long.TYPE) {
			for(long ret : (long[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Byte.TYPE) {
			for(byte ret : (byte[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Short.TYPE) {
			for(short ret : (short[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else if (componentType == Character.TYPE) {
			for(char ret : (char[])methodOut) { 
				outBuffer.append("\"").append(ret).append("\",");
			}
		}
		else {
			for(Object ret : (Object[])methodOut) { 
				outBuffer.append("\"").append(componentType.cast(ret)).append("\",");
			}
		}
        // whack trailing comma
        outBuffer.deleteCharAt(outBuffer.length()-1).append("]");
		return outBuffer.toString();
	}
}
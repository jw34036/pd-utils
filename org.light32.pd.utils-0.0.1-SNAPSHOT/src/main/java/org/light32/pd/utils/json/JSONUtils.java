package org.light32.pd.utils.json;

public interface JSONUtils {

	// instances supporting various APIs
	public static final JSONUtils NULL = new NullJSONUtils();
	public static final JSONUtils JACKSON = new JacksonJSONUtils();
	public static final JSONUtils REFERENCE = new ReferenceJSONUtils();

	
	public abstract String toJSON(Object obj);	
	public abstract <T> T fromJSON(Class<T> klass, String jsonStr) throws InstantiationException, IllegalAccessException;
	
}

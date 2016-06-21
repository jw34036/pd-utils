package org.light32.pd.utils;

/**
 * String Utils
 * 
 * @author jwhitt 05/25/2015
 *
 */
public class StringUtils {

	public static final String nvl(String value, String defaultVal) { 
		return (value == null) ? defaultVal : value;
	}

	public static final String nevl(String value, String defaultVal) { 
		return (value == null || value.equals("")) ? defaultVal : value;
	}
	
	public static final String m2var(String name) {
		if(name.startsWith("get") || name.startsWith("set")) { 
			name = name.substring(3);			
		}
		else if (name.startsWith("is")) { 
			name = name.substring(2);						
		}
		char fc = Character.toLowerCase(name.charAt(0));
		return fc + name.substring(1);
	}
	
	public static final boolean methodIsAccessor(String name) { 
		boolean out = false;
		if (name.startsWith("get") || name.startsWith("set") || name.startsWith("is")) { 
			out = true;
		}
		return out;
	}
	
	public static final boolean methodIsGetter(String name) { 
		boolean out = false;
		if (name.startsWith("get") || name.startsWith("is")) { 
			out = true;
		}
		return out;
	}
}
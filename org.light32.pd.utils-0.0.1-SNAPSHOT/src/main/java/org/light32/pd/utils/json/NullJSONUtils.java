package org.light32.pd.utils.json;

/**
 * @author jwhitt
 *
 */
class NullJSONUtils implements JSONUtils {

	@Override
	public String toJSON(Object obj) {
		return null;
	}

	@Override
	public <T> T fromJSON(Class<T> klass, String jsonStr) {
		return null;
	}

}

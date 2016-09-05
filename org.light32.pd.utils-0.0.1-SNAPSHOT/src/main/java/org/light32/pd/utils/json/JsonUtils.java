package org.light32.pd.utils.json;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Jackson implementation of JSONUtils
 * 
 * @author jwhitt
 *
 */
public class JsonUtils {

	private final ObjectMapper defaultMapper;
    private ObjectMapper providedMapper = null;

    public JsonUtils() {
        defaultMapper = new ObjectMapper();
		defaultMapper.enable(Feature.WRITE_NUMBERS_AS_STRINGS);
		defaultMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

    public String toJSON(Object obj) {
		try {
			return mapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T> T fromJSON(Class<T> klass, String jsonStr) {
		try {
			return mapper().readValue(jsonStr, klass);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private ObjectMapper mapper() { 
		if(providedMapper != null) { 
			return providedMapper;
		}
		return defaultMapper;
	}
	
	public void useMapper(ObjectMapper mapper) {
		this.providedMapper = mapper;
	}
}

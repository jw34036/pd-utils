package org.light32.pd.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson implementation of JSONUtils
 * 
 * @author jwhitt
 *
 */
public class JacksonJSONUtils implements JSONUtils {

	private ObjectMapper providedMapper = null;
	private final ObjectMapper defaultMapper;
	
	public JacksonJSONUtils() { 
		super();
		defaultMapper = new ObjectMapper();
		defaultMapper.enable(Feature.WRITE_NUMBERS_AS_STRINGS);
		defaultMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	@Override
	public String toJSON(Object obj) {
		try {
			return mapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
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

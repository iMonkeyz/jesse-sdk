package com.imonkeyz.jpa.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class JsonAttributeConverter implements AttributeConverter<Object, String> {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to convert attribute value to Json", e);
		}
	}

	@Override
	public Object convertToEntityAttribute(String s) {
		try {
			return mapper.readValue(s, Object.class);
		} catch (IOException e) {
			throw new RuntimeException("Failed to parse Json to attribute value", e);
		}
	}
}

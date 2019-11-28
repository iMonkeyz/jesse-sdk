package com.imonkeyz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorUtils implements ApplicationContextAware {


	public static class Group {
		interface First{}
		interface Second{}
		interface Third{}
	}

	private static Validator validator;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ValidatorUtils.validator = (Validator) applicationContext.getBean("validator");
	}

	public static void validate(Object obj, Class<?>... groups)  {
		Set<ConstraintViolation<Object>> results = validator.validate(obj, groups);
		if ( !CollectionUtils.isEmpty(results) ) {
			throw new ConstraintViolationException(results);
		}
	}

}

package com.imonkeyz.validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumCheck.Validator.class)
public @interface EnumCheck {

	/**
	 * 是否必填 默认是必填的
	 * @return
	 */
	boolean required() default true;

	/**
	 * 验证失败的消息
	 * @return
	 */
	String message() default "无效的枚举值";

	/**
	 * 分组的内容
	 * @return
	 */
	Class<?>[] groups() default {};

	/**
	 * 错误验证的级别
	 * @return
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * 枚举的Class
	 * @return
	 */
	Class<? extends Enum<?>> enumClass();

	/**
	 * 枚举中的验证方法
	 * @return
	 */
	String enumMethod() default "validation";

	class Validator implements ConstraintValidator<EnumCheck, Object> {

		private EnumCheck enumCheck;

		@Override
		public void initialize(EnumCheck enumCheck) {
			this.enumCheck = enumCheck;
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

			if ( null == value ) {
				return !this.enumCheck.required();
			}

			//获取参数的数据类型
			Class<?> valueClass = value.getClass();
			try {
				Method method = this.enumCheck.enumClass().getMethod(this.enumCheck.enumMethod(), valueClass);

				if (!Boolean.TYPE.equals(method.getReturnType()) && !Boolean.class.equals(method.getReturnType())) {
					throw new RuntimeException(String.format("%s method return is not boolean type in the %s class", method, enumCheck.enumClass()));
				}

				if(!Modifier.isStatic(method.getModifiers())) {
					throw new RuntimeException(String.format("%s method is not static method in the %s class", method, enumCheck.enumClass()));
				}

				Object result = method.invoke(null, value);
				return Boolean.TRUE.equals(result);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(String.format("This %s(%s) method does not exist in the %s", enumCheck.enumMethod(), valueClass, enumCheck.enumClass()), e);
			}
		}
	}
}

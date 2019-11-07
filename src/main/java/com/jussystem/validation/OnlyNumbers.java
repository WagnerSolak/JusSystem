package com.jussystem.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;



@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "[0-9]{3,}")
public @interface OnlyNumbers {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{com.jussystem.constraints.OnlyNumbers.message}";
	
	Class<?>[]groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}

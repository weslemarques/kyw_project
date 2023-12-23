package br.com.kyw.project_kyw.application.services.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserRegisterValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRegisterValid {

        String message() default "Validation error";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

}

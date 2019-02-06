package dmit2015.hr.service;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MinMaxSalaryValidator.class})
public @interface ValidMinMaxSalary {

	String message() default "{dmit2015.hr.entity.Job.minMaxSalary}";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}

package dmit2015.hr.service;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dmit2015.hr.entity.Job;

public class MinMaxSalaryValidator implements ConstraintValidator<ValidMinMaxSalary, Job> {

	@Override
	public void initialize(ValidMinMaxSalary constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Job job, ConstraintValidatorContext context) {
		boolean validSalary = false;
		
		if (job.getMinSalary() == null && job.getMaxSalary() == null) {
			validSalary = true;
		} else if (job.getMinSalary() != null ^ job.getMaxSalary() == null) {
			validSalary = job.getMaxSalary().compareTo(job.getMinSalary().multiply(BigDecimal.valueOf(1.25))) >= 0;			
		}
		
		return validSalary;
	}

}

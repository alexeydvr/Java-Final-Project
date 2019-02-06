package dmit2015.hr.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import dmit2015.hr.entity.Job;

@Stateless
public class HumanResourceService {

	@Inject
	private EntityManager entityManager;
	
	private void validateMinMaxSalary(Job currentJob) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Job>> constraintViolations = validator.validate(currentJob);
		if (constraintViolations.size() > 0) {
			StringBuilder builder = new StringBuilder("Please correct the following error(s): ");
			Iterator<ConstraintViolation<Job>> iter =  constraintViolations.iterator();
			while (iter.hasNext()) {
				String errorMessage = iter.next().getMessage();
				builder.append(errorMessage);
			}
			throw new RuntimeException(builder.toString());
		}		
	}
	
	public void addJob(Job newJob) {
		validateMinMaxSalary(newJob);

		entityManager.persist(newJob);					
	}
	
	public void updateJob(Job existingJob) {
		validateMinMaxSalary(existingJob);

		entityManager.merge( existingJob );
	}
	
	public void deleteJob(Job existingJob) throws Exception { 
		existingJob = entityManager.merge(existingJob);
		if (existingJob.getEmployees().size() > 0 || existingJob.getJobHistories().size() > 0) {
			throw new Exception("A job with employees or job histories cannot be deleted.");
		}
		entityManager.remove( existingJob );
	}
	public void deleteJob(String jobId) throws Exception { 
		Job existingJob = findOneJob(jobId);
		deleteJob(existingJob);
	}
	
	public Job findOneJob(String jobId) {
		return entityManager.find(Job.class, jobId);
	}
	
	public List<Job> findAllJobs() {
		return entityManager.createQuery(
			"SELECT j FROM Job j ORDER BY j.jobTitle", Job.class
			).getResultList();
	}
	
}

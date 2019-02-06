package dmit2015.hr.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;

@Path("hr-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON})
public class HumanREsourceRESTService {

	@Inject
	private HumanResourceService hrService;
	
	@Path("jobs")
	@GET
	public List<Job> findAllJobs(){
		return hrService.findAllJobs();
	}
	
	
	@Path("jobs/{id}")
	@GET
	public Job findOneJob(@PathParam("id") String jobID) {
		return hrService.findOneJob(jobID);
	}
	
	
	@Path("jobs")
	@POST
	public void createJob(Job newJob) {
		hrService.addJob(newJob);
		
	}
	
	@Path("jobs")
	@PUT
	public void updateJob(Job existingJob) {
		hrService.updateJob(existingJob);
		
	}
	
	@Path("jobs/{id}")
	@DELETE
	public void deleteJob(@PathParam("id") String jobId) throws Exception {
		Job existingJob = hrService.findOneJob(jobId);
		hrService.deleteJob(existingJob);
	}
	
	/*
	curl GET command (all jobs)
	curl -k -XGET http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs
	
	curl get one Job (IT-PROG)
	curl -k -XGET http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs/IT_PROG
	
	curl Create one Job (Test_Job, TestTitle)
	curl -k -XPOST -HContent-type:application/json --data '{"jobId":"Test_Job","jobTitle":"TestTitle"}' http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs
	
	curl Update one job (Test_Job, UpdatedTitle)
	curl -k -XPUT -HContent-type:application/json --data '{"jobId":"Test_Job","jobTitle":"UpdatedTitle"}' http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs 
	
	curl Delete one job (Test_Job)
	curl -k -XDELETE http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs/Test_Job
 */
	
}

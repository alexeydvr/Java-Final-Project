package dmit2015.hr.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import dmit2015.hr.entity.Job;
import dmit2015.hr.service.HumanResourceService;

@Named
@ViewScoped
public class JobCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private HumanResourceService hrService;
	
	@Inject
	private Logger logger;
	
	private List<Job> jobs;		// +getter	

	@PostConstruct
	public void init() {
		jobs = hrService.findAllJobs();
		newJob = new Job();
	}
	
	@Produces
	@Named
	public List<Job> getJobs() {
		return jobs;
	}
	
	@Produces
	@Named
	private Job newJob;
	
	public String createNewJob() {
		String nextUrl = null;
		try {
			hrService.addJob(newJob);
			init();
			Messages.addFlashGlobalInfo("Add successful");
			nextUrl = "viewJobs.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			Messages.addGlobalError("Add unsuccessful");
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return nextUrl;
	}
	
	
	@Produces
	@Named
	private Job existingJob;
	
	@NotBlank(message="Search value is required.")
	private String idQueryValue;		// +getter +setter
	
	public String getIdQueryValue() {
		return idQueryValue;
	}

	public void setIdQueryValue(String idQueryValue) {
		this.idQueryValue = idQueryValue;
	}

	public void findJob() {
		try {
			existingJob = hrService.findOneJob(idQueryValue);
			if (existingJob != null) {
				Messages.addGlobalInfo("Query successful");
				
			} else {
				Messages.addGlobalError("Query unsuccessful");
				
			}
		} catch (Exception ex) {
			Messages.addGlobalError("Query unsucessful");
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	public String updateJob() {
		String nextUrl = null;
		try {
			hrService.updateJob(existingJob);
			Messages.addFlashGlobalInfo("Update successful");
			nextUrl = "viewJobs.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			Messages.addGlobalError("Update unsuccessful");	
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return nextUrl;
	}

	public void deleteJob() {
		try {
			hrService.deleteJob(existingJob);
			existingJob = null;
			idQueryValue = null;
			Messages.addGlobalInfo("Delete successful");
		} catch (Exception ex) {
			Messages.addGlobalError("Delete unsuccessful");			
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	public void cancel() {
		existingJob = null;
		idQueryValue = null;
	}

	public void findByQueryParameterId() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (idQueryValue != null && existingJob == null) {
				findJob();		
			}
		}
	}
}

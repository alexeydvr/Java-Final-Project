package dmit2015.hr.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import dmit2015.hr.service.NewJobGroup;
import dmit2015.hr.service.ValidMinMaxSalary;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the JOBS database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ValidMinMaxSalary(message="Maximum salary must be 25% greater or equal to the minimum salary")
@Entity
@Table(name="JOBS")
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_ID")
	@NotBlank(message="JobId value is required.", groups= {NewJobGroup.class})
	@Size(max=10, message="JobId must be {max} or less characters.")
	private String jobId;

	@Column(name="JOB_TITLE")
	@NotBlank(message="Job Title value is required.")
	@Size(max=35, message="Job Title must be {max} or less characters.")
	private String jobTitle;

	@Column(name="MAX_SALARY")
	@DecimalMax(value="1000000",message="Maximum salary must be less or equal to ${value}")
	private BigDecimal maxSalary;

	@Column(name="MIN_SALARY")
	@DecimalMin(value="1000",message="The minimum salary ${formatter.format('$%,.2f', validatedValue)} is less than the minimum value ${value}")
	private BigDecimal minSalary;

	//bi-directional many-to-one association to Employee
	@XmlTransient
	@OneToMany(mappedBy="job")
	private List<Employee> employees;

	//bi-directional many-to-one association to JobHistory
	@XmlTransient
	@OneToMany(mappedBy="job")
	private List<JobHistory> jobHistories;

	public Job() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(BigDecimal maxSalary) {
		this.maxSalary = maxSalary;
	}

	public BigDecimal getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setJob(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setJob(null);

		return employee;
	}

	public List<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(List<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistory addJobHistory(JobHistory jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setJob(this);

		return jobHistory;
	}

	public JobHistory removeJobHistory(JobHistory jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setJob(null);

		return jobHistory;
	}

}
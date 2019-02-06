package dmit2015.rest.client;

import java.util.List;

import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

//import org.primefaces.component.keyboard.Keyboard;

import java.io.BufferedReader;
//import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import dmit2015.entity.Job;

public class JobClientApp {

	// create the client, get the response, push the response to the list.
	final static String BASE_URI = "http://localhost:8080/dmit2015-fall2018term-project-server-start/rest/hr-api/jobs";

	public static void FetchOneJob() throws IOException {

		Response response;
		Client restClient = ClientBuilder.newClient();
		System.out.println("Enter Job ID: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);

		// cin.read();

		String jobId = keyboard.readLine();

		response = restClient.target(BASE_URI).path(jobId).request().get();

		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			Job job = response.readEntity(Job.class);
			System.out.println(
					job.getJobId() + ", " + job.getJobTitle() + ", " + job.getMinSalary() + ", " + job.getMaxSalary());
		} else {
			JOptionPane.showMessageDialog(null, "Server error status " + response.getStatus(), "Server response",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static void DeleteOneJob() throws IOException {

		Response response;
		Client restClient = ClientBuilder.newClient();
		System.out.println("Enter Job to Delete ID: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);

		// cin.read();

		String jobId = keyboard.readLine();

		response = restClient.target(BASE_URI).path(jobId).request().delete();

		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			//Job job = response.readEntity(Job.class);
			System.out.println("job delete succesfully");
		} else {
			JOptionPane.showMessageDialog(null, "Server error status " + response.getStatus(), "Server response",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void FetchAllJobs() {
		Response response;

		// public List<Job> getAllJobs(){
		Client restClient = ClientBuilder.newClient();
		response = restClient.target(BASE_URI).request().get();
		// response = restClient.target(BASE_URI).request().post(Entity.json(newJob));

		// WebTarget jobResource = restClient.target(BASE_URI).path("jobs");
		// GenericType<List<Job>> responseType = new GenericType<List<Job>>() {};
		// List<Job> jobs = response.

		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			List<Job> jobs = response.readEntity(new GenericType<List<Job>>() {
			});
			jobs.forEach(oneJob -> System.out.println(oneJob.getJobId() + ", " + oneJob.getJobTitle() + ", "
					+ oneJob.getMinSalary() + ", " + oneJob.getMaxSalary()));
		} else {
			JOptionPane.showMessageDialog(null, "Server error status " + response.getStatus(), "Server response",
					JOptionPane.ERROR_MESSAGE);
		}
		// return jobs;
	}

	public static void CreateOneJob() throws IOException {
		Response response;

		// System.out.println("Enter Job ID: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);

		// cin.read();

		System.out.println("Enter New Job ID: ");
		String jobId = keyboard.readLine();

		System.out.println("Enter New Job Title: ");
		String jobTitle = keyboard.readLine();

		System.out.println("Enter Max Salary: ");
		String jobMaxSalary = keyboard.readLine();

		System.out.println("Enter Min Salary: ");
		String jobMinSalary = keyboard.readLine();

		Job newJob = new Job();
		newJob.setJobId(jobId);
		newJob.setJobTitle(jobTitle);
		newJob.setMinSalary(Integer.parseInt(jobMinSalary));
		newJob.setMaxSalary(Integer.parseInt(jobMaxSalary));

		// public List<Job> getAllJobs(){
		Client restClient = ClientBuilder.newClient();
		// response = restClient.target(BASE_URI).request().get();
		response = restClient.target(BASE_URI).request().post(Entity.json(newJob));

		// WebTarget jobResource = restClient.target(BASE_URI).path("jobs");
		// GenericType<List<Job>> responseType = new GenericType<List<Job>>() {};
		// List<Job> jobs = response.

		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			System.out.println("Job Created Successfully");
		} else {
			JOptionPane.showMessageDialog(null, "Server error status " + response.getStatus(), "Server response",
					JOptionPane.ERROR_MESSAGE);
		}
		/*
		 * if (response.getStatusInfo().getFamily() ==
		 * Response.Status.Family.SUCCESSFUL) { List<Job> jobs = response.readEntity(new
		 * GenericType<List<Job>>() { }); jobs.forEach(oneJob -> System.out.println(
		 * oneJob.getJobId() + ", " + oneJob.getJobTitle() + ", " +
		 * oneJob.getMinSalary() + ", " + oneJob.getMaxSalary())); } else {
		 * JOptionPane.showMessageDialog(null, "Server error status " +
		 * response.getStatus(), "Server response", JOptionPane.ERROR_MESSAGE); }
		 */
		// return jobs;
	}
	
	public static void UpdateOneJob() throws IOException {
		Response response;

		// System.out.println("Enter Job ID: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);

		// cin.read();

		System.out.println("Enter Job ID to Update: ");
		String jobId = keyboard.readLine();

		System.out.println("Enter Updated Job Title: ");
		String jobTitle = keyboard.readLine();

		System.out.println("Enter  Max Salary: ");
		String jobMaxSalary = keyboard.readLine();

		System.out.println("Enter  Min Salary: ");
		String jobMinSalary = keyboard.readLine();

		Job newJob = new Job();
		newJob.setJobId(jobId);
		newJob.setJobTitle(jobTitle);
		newJob.setMinSalary(Integer.parseInt(jobMinSalary));
		newJob.setMaxSalary(Integer.parseInt(jobMaxSalary));

		// public List<Job> getAllJobs(){
		Client restClient = ClientBuilder.newClient();
		// response = restClient.target(BASE_URI).request().get();
		response = restClient.target(BASE_URI).request().put(Entity.json(newJob));

		// WebTarget jobResource = restClient.target(BASE_URI).path("jobs");
		// GenericType<List<Job>> responseType = new GenericType<List<Job>>() {};
		// List<Job> jobs = response.

		if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			System.out.println("Job Updated Successfully");
		} else {
			JOptionPane.showMessageDialog(null, "Server error status " + response.getStatus(), "Server response",
					JOptionPane.ERROR_MESSAGE);
		}
		/*
		 * if (response.getStatusInfo().getFamily() ==
		 * Response.Status.Family.SUCCESSFUL) { List<Job> jobs = response.readEntity(new
		 * GenericType<List<Job>>() { }); jobs.forEach(oneJob -> System.out.println(
		 * oneJob.getJobId() + ", " + oneJob.getJobTitle() + ", " +
		 * oneJob.getMinSalary() + ", " + oneJob.getMaxSalary())); } else {
		 * JOptionPane.showMessageDialog(null, "Server error status " +
		 * response.getStatus(), "Server response", JOptionPane.ERROR_MESSAGE); }
		 */
		// return jobs;
	}

	public static void main(String[] args) throws IOException {
		//CreateOneJob();
		//FetchAllJobs();
		//UpdateOneJob();
		//FetchOneJob();
		DeleteOneJob();

	}
	// }

}

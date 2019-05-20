package sg.edu.nus.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annual_leave")
public class AnnualLeave {

	//DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
	
	@Id
	private int aLeaveId;
	private String employeeId;
	private int daysApplied;
	private LocalDateTime dateOfApplication;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String status;
	private String reason;
	private String workDissemination;
	private int contactNo;
	private String managerComments;
	
	@ManyToOne
	//@JoinColumn(name="employeeid") //make sure this ties with the table
	@JoinColumn(referencedColumnName="employeeid")
	private Employee employee;
	
	public AnnualLeave() {
		super();
	}
	
	//Comment from LeeSiong: I need an employee object (from employee login) to be passed to my ProductController before I can pass it on as an argument in the annual leave constructor. 
	public AnnualLeave(String employeeId, int daysApplied, LocalDateTime dateOfApplication,
			LocalDateTime startDate, LocalDateTime endDate, String status, String reason, String workDissemination,
			int contactNo, String managerComments/* , Employee employee */) {
		super();
		this.employeeId = employeeId;
		this.daysApplied = daysApplied;
		this.dateOfApplication = dateOfApplication;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.reason = reason;
		this.workDissemination = workDissemination;
		this.contactNo = contactNo;
		this.managerComments = managerComments;
		/* this.employee = employee; */
	}

	
	public int getaLeaveId() {
		return aLeaveId;
	}


	public void setaLeaveId(int aLeaveId) {
		this.aLeaveId = aLeaveId;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public int getDaysApplied() {
		return daysApplied;
	}


	public void setDaysApplied(int daysApplied) {
		this.daysApplied = daysApplied;
	}


	public LocalDateTime getDateOfApplication() {
		return dateOfApplication;
	}


	public void setDateOfApplication(LocalDateTime dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}


	public LocalDateTime getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public LocalDateTime getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getWorkDissemination() {
		return workDissemination;
	}


	public void setWorkDissemination(String workDissemination) {
		this.workDissemination = workDissemination;
	}


	public int getContactNo() {
		return contactNo;
	}


	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}


	public String getManagerComments() {
		return managerComments;
	}


	public void setManagerComments(String managerComments) {
		this.managerComments = managerComments;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	@Override
	public String toString() {
		return "AnnualLeave [aLeaveId=" + aLeaveId + ", employeeId=" + employeeId + ", daysApplied=" + daysApplied
				+ ", dateOfApplication=" + dateOfApplication + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", reason=" + reason + ", workDissemination=" + workDissemination
				+ ", contactNo=" + contactNo + ", managerComments=" + managerComments + ", employee=" + employee + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	//assume view personal leave history by start_date
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnualLeave other = (AnnualLeave) obj;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
		
}

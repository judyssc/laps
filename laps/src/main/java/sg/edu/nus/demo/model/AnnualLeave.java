package sg.edu.nus.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
	private String workDessemination;
	private String managerComments;
	
	@ManyToOne
	@JoinColumn(name="EmployeeId") //make sure this ties with the table
	private Employee employee;
	
	public AnnualLeave() {}

	public AnnualLeave(int aLeaveId, String employeeId, int daysApplied, LocalDateTime dateOfApplication, LocalDateTime startDate, LocalDateTime endDate,
			String status, String managerComments) {
		super();
		this.aLeaveId = aLeaveId;
		this.employeeId = employeeId;
		this.daysApplied = daysApplied;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.managerComments = managerComments;
	}

	public LocalDateTime getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(LocalDateTime dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
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

	public String getManagerComments() {
		return managerComments;
	}

	public void setManagerComments(String managerComments) {
		this.managerComments = managerComments;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getWorkDessemination() {
		return workDessemination;
	}

	public void setWorkDessemination(String workDessemination) {
		this.workDessemination = workDessemination;
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
				+ ", status=" + status + ", managerComments=" + managerComments + "]";
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

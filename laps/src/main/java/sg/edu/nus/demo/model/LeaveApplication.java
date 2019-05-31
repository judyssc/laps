package sg.edu.nus.demo.model;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "leave_application")
public class LeaveApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int LeaveId;
	@Column(name = "employeeId")
	private int employeeId;
	@Column(name = "daysapplied")
	private int daysApplied;
	@Column(name = "dateofapplication")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfApplication;
	@Column(name = "startdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@Column(name = "enddate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	@Column(name = "status")
	private String status;
	@Column(name = "reason")
	@Size(min=5,max=25, message="Please key in a valid reason with 5-20 letters")
	private String reason;
	@Column(name = "workdissemination")
	@Size(min=1,max=25, message="Please arrow someone to do your work!")
	private String workDissemination;
	@Column(name = "contactno")
	private int contactNo;
	@Column(name = "managercomments")
	private String managerComments;
	@Column(name = "type")
	private String type;
	
	
	//@JoinColumn(name="employeeid") //make sure this ties with the table	
//	@ManyToOne
//	@Column(insertable = false, updatable = false)
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name="employeeId",insertable = false, updatable = false)
	private Employee employee;
	
	public LeaveApplication() {
		super();
	}
	
	public LeaveApplication(int daysApplied, LocalDate dateOfApplication, LocalDate startDate,
			LocalDate endDate, String status, String reason, String workDissemination, int contactNo,
			String managerComments, String type, Employee employee) {
		super();
		
		this.daysApplied = daysApplied;
		this.dateOfApplication = dateOfApplication;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.reason = reason;
		this.workDissemination = workDissemination;
		this.contactNo = contactNo;
		this.managerComments = managerComments;
		this.type = type;
//		this.employee = employee;
	}

	public int getLeaveId() {
		return LeaveId;
	}


	public void setLeaveId(int LeaveId) {
		this.LeaveId = LeaveId;
	}

	
	 public int getEmployeeId() { return employeeId; }
	 
	  public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
	

	public int getDaysApplied() {
		return daysApplied;
	}


	public void setDaysApplied(int daysApplied) {
		this.daysApplied = daysApplied;
	}


	public LocalDate getDateOfApplication() {
		return dateOfApplication;
	}


	public void setDateOfApplication(LocalDate dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LeaveApplication [LeaveId=" + LeaveId + ", daysApplied=" + daysApplied + ", dateOfApplication="
				+ dateOfApplication + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
				+ ", reason=" + reason + ", workDissemination=" + workDissemination + ", contactNo=" + contactNo
				+ ", managerComments=" + managerComments + ", type=" + type + ", employee="+ employee + " ]";
	}
			
}


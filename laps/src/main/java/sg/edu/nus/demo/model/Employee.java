package sg.edu.nus.demo.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO/* , generator = "system-uuid" */)
	/* @GenericGenerator(name = "system-uuid", strategy = "uuid2") */
	private int employeeId; 
	private String employeeName;
	private String userId;
	private String password;
	private String designation;
	private String employeeType; //admin or pro(employee,manager)	
	private int annualLeaveBalance;
	private int medicalLeaveBalance;
	private int compLeaveBalance;
	
	@OneToMany(targetEntity=LeaveApplication.class, mappedBy="employee")
	public Collection<LeaveApplication> LeaveApplicationList;
		
	@ManyToOne 
	//@JoinColumn(name="managerid")	 
	@JoinColumn(name="mgr_id")	
	private Manager manager; //assume each employee only one manager
		
	public Employee() {}
	
	
	public Employee(String employeeName, String userId, String password, String designation, String employeeType,
			int annualLeaveBalance, int medicalLeaveBalance, int compLeaveBalance,
			Collection<LeaveApplication> leaveApplicationList, Manager manager) {
		super();
		this.employeeName = employeeName;
		this.userId = userId;
		this.password = password;
		this.designation = designation;
		this.employeeType = employeeType;
		this.annualLeaveBalance = annualLeaveBalance;
		this.medicalLeaveBalance = medicalLeaveBalance;
		this.compLeaveBalance = compLeaveBalance;
		LeaveApplicationList = leaveApplicationList;
		this.manager = manager;
		
		if(designation=="developer")
		{
			this.annualLeaveBalance = 14;
		}
		else if (designation=="teamlead")
		{
			this.annualLeaveBalance = 18;
		}
		this.medicalLeaveBalance = 60;
		this.compLeaveBalance = 0;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public int getAnnualLeaveBalance() {
		return annualLeaveBalance;
	}

	public void setAnnualLeaveBalance(int annualLeaveBalance) {
		this.annualLeaveBalance = annualLeaveBalance;
	}

	public int getMedicalLeaveBalance() {
		return medicalLeaveBalance;
	}

	public void setMedicalLeaveBalance(int medicalLeaveBalance) {
		this.medicalLeaveBalance = medicalLeaveBalance;
	}

	public int getCompLeaveBalance() {
		return compLeaveBalance;
	}

	public void setCompLeaveBalance(int compLeaveBalance) {
		this.compLeaveBalance = compLeaveBalance;
	}

	
	public Collection<LeaveApplication> getLeaveApplicationList() {
		return LeaveApplicationList;
	}


	public void setLeaveApplicationList(Collection<LeaveApplication> leaveApplicationList) {
		LeaveApplicationList = leaveApplicationList;
	}


	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", userId=" + userId
				+ ", password=" + password + ", designation=" + designation + ", employeeType=" + employeeType
				+ ", annualLeaveBalance=" + annualLeaveBalance + ", medicalLeaveBalance=" + medicalLeaveBalance
				+ ", compLeaveBalance=" + compLeaveBalance + ", LeaveApplicationList=" + LeaveApplicationList
				+ ", manager=" + manager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}

	//Assuming manager view subordinate leave balance will be sorted by employee name
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		return true;
	}

}

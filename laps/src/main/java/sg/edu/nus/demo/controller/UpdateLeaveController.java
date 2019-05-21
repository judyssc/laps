package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sg.edu.nus.demo.repo.*;

@Controller
public class UpdateLeaveController {	
	private EmployeeRepository employeeRepository;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
       
	private LeaveRepository LeaveRepository;
    @Autowired
    public void setAnnualLeaveRepository(LeaveRepository LeaveRepository) {
        this.LeaveRepository = LeaveRepository;
    }   
}

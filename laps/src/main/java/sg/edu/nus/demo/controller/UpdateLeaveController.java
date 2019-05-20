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
       
	private AnnualLeaveRepository annualLeaveRepository;
    @Autowired
    public void setAnnualLeaveRepository(AnnualLeaveRepository annualLeaveRepository) {
        this.annualLeaveRepository = annualLeaveRepository;
    }   
    
    private MedicalLeaveRepository medicalLeaveRepository;
    @Autowired
    public void setMedicalLeaveRepository(MedicalLeaveRepository medicalLeaveRepository) {
        this.medicalLeaveRepository = medicalLeaveRepository;
    }
    
    private CompLeaveRepository compLeaveRepository;
    @Autowired
    public void setCompLeaveRepository(CompLeaveRepository compLeaveRepository) {
        this.compLeaveRepository = compLeaveRepository;
    }

}

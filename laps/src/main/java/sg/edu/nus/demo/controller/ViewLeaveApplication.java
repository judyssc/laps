package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.ViewLeaveRepository;


@Controller
public class ViewLeaveApplication {
	@Autowired
	private ViewLeaveRepository rep;
	
	public void setRep(ViewLeaveRepository rep) {
		this.rep = rep;
	}
	private EmployeeRepository empRepo;
	@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	private String viewLeaveDetails() {					
		return "redirect:/login/employee";
	}
	@RequestMapping(path="/viewleave/{employeeId}", method = RequestMethod.GET)
	public String viewLeaveApplication (@PathVariable("employeeId") int employeeId ,Model model) {
		Employee emp = empRepo.findById(employeeId).orElse(null);
		if(emp==null) {
			return "redirect:/login/employee";}
		ArrayList<LeaveApplication> listnew =new ArrayList<LeaveApplication> ();
		
		ArrayList<LeaveApplication> lista =(ArrayList<LeaveApplication>)rep.findLeaveApplicationByEID(employeeId);		
		 for(LeaveApplication leave:lista) {
		    	LocalDate local = leave.getStartDate(); 
		    	int year = local.getYear(); 
		    	LocalDate ltnow = LocalDate.now(); 
		        int currentYear=ltnow.getYear();
		    	if(year==currentYear) {
		    		listnew.add(leave);
		    	}
		    }	
			model.addAttribute("pesonalleave",listnew);
			model.addAttribute("employee",emp);		
		return "employeeLeaveAll";
	}
	
	@RequestMapping(path="/viewdetails/{LeaveId}", method = RequestMethod.GET)
	private String viewLeaveDetails(Model model,@PathVariable("LeaveId") int id ) {	
		
		LeaveApplication leaveObject = rep.findById(id).orElse(null);
		if(leaveObject==null) {
			return "redirect:/login/employee";}
		Employee employee = empRepo.findById(leaveObject.getEmployeeId()).orElse(null);
		model.addAttribute("leave", leaveObject);
		model.addAttribute("employee", employee); 			
		return "employeeLeaveDetails";
	}
}

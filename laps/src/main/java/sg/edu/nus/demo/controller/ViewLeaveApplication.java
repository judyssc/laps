package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.ViewLeaveRepository;


@Controller
public class ViewLeaveApplication {
	@Autowired
	private ViewLeaveRepository rep;
	
	public void setRep(ViewLeaveRepository rep) {
		this.rep = rep;
	}

	@RequestMapping(path="/viewleave/{employeeId}", method = RequestMethod.GET)
	public String viewLeaveApplication (@PathVariable("employeeId") int employeeId ,Model model) {
		
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
		
		return "employeeLeaveAll";
	}
	
	@RequestMapping(path="/viewdetails/{LeaveId}", method = RequestMethod.GET)
	private String viewLeaveDetails(Model model,@PathVariable("LeaveId") int id ) {	
		
	    model.addAttribute("leave",rep.findById(id).orElse(null));
		return "employeeLeaveDetails";
	}
	

}

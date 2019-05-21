package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.demo.model.LeaveApplication;
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
    @RequestMapping(path = "/leave/edit/{idd}", method = RequestMethod.GET)
	public String editLeave(Model model, @PathVariable(value = "idd") String id) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	p.setStatus("Updated");
		model.addAttribute("leave", p);
		return "leaveform";
	}
    
    @RequestMapping(path = "/leave/delete/{id}", method = RequestMethod.GET)
	public String deleteLeave(@PathVariable(name = "id") String id) {
    	int ida = Integer.parseInt(id);
    	LeaveRepository.delete(LeaveRepository.findById(ida).orElse(null));
		return "redirect:/leaveform";
	}
    
    @RequestMapping(path = "/leave/cancel/{id}", method = RequestMethod.GET)
	public String cancelLeave(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	p.setStatus("Cancelled");
    	LeaveRepository.save(p);
		model.addAttribute("leave", p);
		return "leaveconfirmation";
	}
    
    //for manager
    @RequestMapping(path = "/leave/approve/{id}", method = RequestMethod.GET)
	public String approvallLeave(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	p.setStatus("Approved");
    	LeaveRepository.save(p);
		model.addAttribute("leave", p);
		return "one page";
	}
    
    //for manager
    @RequestMapping(path = "/leave/reject/{id}", method = RequestMethod.GET)
	public String rejectLeave(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	p.setStatus("Rejected");
    	LeaveRepository.save(p);
		model.addAttribute("leave", p);
		return "one page";
	}
	
}

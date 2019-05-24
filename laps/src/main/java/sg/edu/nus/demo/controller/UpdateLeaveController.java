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
    
    private LeaveTypesRepository Leavtypes;
    
    @Autowired
    public void setLeavtypes(LeaveTypesRepository leavtypes) {
		Leavtypes = leavtypes;
	}
	@RequestMapping(path = "/index/{id}", method = RequestMethod.GET)
	public String index(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	System.out.println("here1");
    	Employee employee = employeeRepository.findById(ida).orElse(null);
		model.addAttribute("employee", employee);
    	System.out.println("here2");
		return "index";
	}
    @RequestMapping(path = "/leave/edit/{id}", method = RequestMethod.GET)
	public String editLeave(Model model, @PathVariable(value = "id") int id) {
    	LeaveApplication leaveObject = LeaveRepository.findById(id).orElse(null);
		Employee employee = employeeRepository.findById(leaveObject.getEmployeeId()).orElse(null);
		ArrayList<String> listTypes = (ArrayList<String>)Leavtypes.listAllLeaveTypes();
		leaveObject.setDateOfApplication(LocalDate.now());	
		leaveObject.setStatus("Updated");
		LeaveRepository.save(leaveObject);	
		model.addAttribute("listofLeaveTypes", listTypes);
		model.addAttribute("leave", leaveObject);
		model.addAttribute("employee", employee); 
		return "leaveform";
	}
    
    @RequestMapping(path = "/leave/delete/{id}", method = RequestMethod.GET)
	public String deleteLeave(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	Employee employee = employeeRepository.findById(p.getEmployeeId()).orElse(null);
    	p.setStatus("Deleted");
    	LeaveRepository.save(p);
		model.addAttribute("employee", employee);
    	model.addAttribute("leave", p);
		return "employeeLeaveDetails";
	}
    
    
    @RequestMapping(path = "/leave/cancel/{id}", method = RequestMethod.GET)
	public String cancelLeave(@PathVariable(name = "id") String id, Model model) {
    	int ida = Integer.parseInt(id);
    	LeaveApplication p = LeaveRepository.findById(ida).orElse(null);
    	Employee employee = employeeRepository.findById(p.getEmployeeId()).orElse(null);
    	p.setStatus("Cancelled");
    	LeaveRepository.save(p);
		model.addAttribute("leave", p);
		model.addAttribute("employee", employee);
		return "employeeLeaveDetails";
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

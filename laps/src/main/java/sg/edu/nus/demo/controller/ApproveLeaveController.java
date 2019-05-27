package sg.edu.nus.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.LeaveRepository;
import sg.edu.nus.demo.repo.ManagerRepository;
import sg.edu.nus.demo.service.Mail_utility;

@Controller
public class ApproveLeaveController {
	
	@Autowired
	private LeaveRepository leaveRepo;

	public void setLeaveRepo(LeaveRepository leaveRepo) {
		this.leaveRepo = leaveRepo;
	}

	@Autowired
	private ManagerRepository mgrRepo;
	
	public void setMgrRepo(ManagerRepository mgrRepo) {
		this.mgrRepo = mgrRepo;
	}
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@RequestMapping(path = "/index_mgr/{mgrId}", method = RequestMethod.GET)
	public String index(@PathVariable(name = "mgrId") String mgrId, Model model) {
    	int intMgrId = Integer.parseInt(mgrId);
    	Manager mgr = mgrRepo.findById(intMgrId).orElse(null);
		model.addAttribute("manager", mgr);
		return "index_mgr";
	}
	
	@GetMapping("/leaveapplications/{mgrId}")
	public String showleaveapp(Model model, @PathVariable(name = "mgrId") int mgrId) {
		
		model.addAttribute("leaves",leaveRepo.findSubordinatesLeave(mgrId));
		
		HashMap<Integer,Employee> empName = new HashMap<Integer,Employee>();
		for(LeaveApplication i :leaveRepo.findSubordinatesLeave(mgrId)) {
			empName.put(i.getEmployeeId(),leaveRepo.findEmployeeById(i.getEmployeeId()));
		}
		model.addAttribute("empdict",empName);
		
		Manager mgr = mgrRepo.findById(mgrId).orElse(null);
		model.addAttribute("manager", mgr);
		
		return "leaveappsMgr";
	}
	
	@PostMapping("/leaveapplications/{mgrId}") 
	public String updateleave(Model model, LeaveApplication leaveapp, @PathVariable(name = "mgrId") int mgrId) {
		LeaveApplication la = leaveRepo.findById(leaveapp.getLeaveId()).orElse(null);
		la.setStatus(leaveapp.getStatus());
		la.setManagerComments(leaveapp.getManagerComments());
		leaveRepo.save(la);
		
		Employee employee = empRepo.findById(la.getEmployeeId()).orElse(null);		
		model.addAttribute("employee", employee);
		String message = "Your leave status has been updated. Please login from http://localhost:8080/login/employee to see your leave status.";
		String mailId = employee.getEmail();
		String status = Mail_utility.sendemail(message, mailId);
		
		return "redirect:/leaveapplications/"+mgrId;
	}

	@RequestMapping(path = "/updateleave/{mgrId}/{leaveid}", method = RequestMethod.GET)
	public String editleave(Model model,@PathVariable(value = "leaveid") String leaveid,@PathVariable(name = "mgrId") int mgrId) {
		int ida = Integer.parseInt(leaveid);
		LeaveApplication la = leaveRepo.findById(ida).orElse(null);
		model.addAttribute("updateleave", la);
		
		Manager mgr = mgrRepo.findById(mgrId).orElse(null);
		model.addAttribute("manager", mgr);
		return "approverejectleave";
	}

}

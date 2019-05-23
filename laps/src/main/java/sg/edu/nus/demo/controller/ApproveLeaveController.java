package sg.edu.nus.demo.controller;

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

@Controller
public class ApproveLeaveController {
	
	
	private LeaveRepository leaveRepository;
	
	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}

	@GetMapping("/leaveapplications")
	public String showleaveapp(Model model, Employee emp) {
		model.addAttribute("leaves",leaveRepository.findAll());
		return "leaveappsMgr";
	}
	
	@PostMapping("/leaveapplications") 
	public String updateleave(Model model, LeaveApplication leaveapp) {
		LeaveApplication la = leaveRepository.findById(leaveapp.getLeaveId()).orElse(null);
		la.setStatus(leaveapp.getStatus());
		la.setManagerComments(leaveapp.getManagerComments());
		leaveRepository.save(la);
		return "redirect:/leaveapplications";
	}
	
	@RequestMapping(path = "/updateleave/{leaveid}", method = RequestMethod.GET)
	public String editleave(Model model,@PathVariable(value = "leaveid") String leaveid) {
		int ida = Integer.parseInt(leaveid);
		LeaveApplication la = leaveRepository.findById(ida).orElse(null);
		model.addAttribute("updateleave", la);
		return "approverejectleave";
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public String cancelupdate() {
		return "redirect:/leaveapplications";
	}
}

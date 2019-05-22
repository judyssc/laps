package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.LeaveRepository;

@Controller
public class ApproveLeaveController {
	
	
	private LeaveRepository leaveRepository;
	
	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}
	
	@GetMapping("/leaveapplications")
	public String showleaveapp(Model model) {
		model.addAttribute("leaves",leaveRepository.findAll());
		return "leaveappsMgr";
	}
	
	@PostMapping("/saveleave") 
	public String saveemployee(LeaveApplication leaveapp) {
		
		leaveRepository.save(leaveapp);
		return "redirect:/leaveapplications";
	}
	
	
	@GetMapping("/updateleave/{leaveid}")
	public String updateleave(Model model,@PathVariable("leaveid") int leave)
	{
		model.addAttribute("leaveupdate",leaveRepository.findById(leave).get());
		return "approverejectleave";
		
	}
}

package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.LeaveRepository;


@Controller
public class SubmitLeaveController {

	@Autowired
	private LeaveRepository leaveRepository;
	
	@GetMapping("/leaveform")
	public String createNewLeave(Model model) {
		
		model.addAttribute("leave", new LeaveApplication());
		return "leaveform";
	}
	
	@PostMapping("/leaveconfirmation")
	public ModelAndView saveLeave(LeaveApplication leave) {
		leaveRepository.save(leave);
		return new ModelAndView("leaveconfirmation","leave", leave);		
	}
	
	@GetMapping("/leaveconfirmation")
	public String confirmLeave(Model model, LeaveApplication leave) {		
		model.addAttribute("leave", leave);
		return "leaveconfirmation";
	}
	
	
}

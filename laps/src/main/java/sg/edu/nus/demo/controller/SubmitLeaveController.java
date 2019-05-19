package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.AnnualLeave;
import sg.edu.nus.demo.repo.AnnualLeaveRepository;

@Controller
public class SubmitLeaveController {

	@Autowired
	private AnnualLeaveRepository annualleaveRepository;
	
	@GetMapping("/annualLeaveForm")
	public String createNewAnnualLeave(Model model) {
		
		model.addAttribute("annualLeave", new AnnualLeave());
		return "annualLeaveForm";
	}
	
	@PostMapping("/all")
	public String saveAnnualLeave(AnnualLeave annualLeave) {
		annualleaveRepository.save(annualLeave);
		return "redirect:/all";
	}
	
	@GetMapping(path="/all")
	public String getAllAnnualLeave(Model model) {
		
		model.addAttribute("listAnnualLeave", annualleaveRepository.findAll());
		return "all";
	}
	
	
}

package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.demo.model.AnnualLeave;
import sg.edu.nus.demo.repo.AnnualLeaveRepository;
import sg.edu.nus.demo.repo.CompLeaveRepository;
import sg.edu.nus.demo.repo.MedicalLeaveRepository;

@Controller
public class SubmitLeaveController {

	@Autowired
	private AnnualLeaveRepository annualleaveRepository;
	
	public void setAnnualleaveRepository(AnnualLeaveRepository annualleaveRepository) {
		this.annualleaveRepository = annualleaveRepository;
	}
	
	@Autowired
    private CompLeaveRepository comleaveRepository;

	public void setComleaveRepository(CompLeaveRepository comleaveRepository) {
		this.comleaveRepository = comleaveRepository;
	}

	@Autowired
	private MedicalLeaveRepository medicalleaveRepository;
	
	public void setMedicalleaveRepository(MedicalLeaveRepository medicalleaveRepository) {
		this.medicalleaveRepository = medicalleaveRepository;
	}
	
	
	@GetMapping("/annualLeaveForm")
	public String createNewAnnualLeave(Model model) {
		
		model.addAttribute("annualLeave", new AnnualLeave());
		return "annualLeaveForm";
	}
	
	@PostMapping("/annualleaveconfirmation")
	public ModelAndView saveAnnualLeave(AnnualLeave annualLeave) {
		annualleaveRepository.save(annualLeave);
		return new ModelAndView("annualleaveconfirmation","annualLeave", annualLeave);		
	}
	
	@GetMapping(path="/annualleaveconfirmation")
	public String confirmAnnualLeave(Model model, AnnualLeave annualLeave) {		
		model.addAttribute("annualLeave", annualLeave);
		return "annualleaveconfirmation";
	}
	
	//xiaolin update 61-86
	@RequestMapping(path="/")
	public String viewPersonalLeave () {		
		return "index";
	}

	@RequestMapping(path="/viewleave", method = RequestMethod.GET)
	public String viewLeave () {		
		return "ViewLeave";
	}
	
	@RequestMapping(path="/viewAnnualleave", method = RequestMethod.GET)
	public String viewAnnuallLeave (Model model) {
		model.addAttribute("annualleave",annualleaveRepository.findAll());
		return "ViewAnnualLeave";
	}
	@RequestMapping(path="/viewComplLeave", method = RequestMethod.GET)
	public String viewComplLeave (Model model) {
		model.addAttribute("compleave",comleaveRepository.findAll());
		return "ViewCompLeave";
	}
	@RequestMapping(path="/viewMedicalLeave", method = RequestMethod.GET)
	public String viewMedicalLeave (Model model) {
		model.addAttribute("medicalleave",medicalleaveRepository.findAll());
		return "ViewMedicalLeave";
	}
	
	
	
}

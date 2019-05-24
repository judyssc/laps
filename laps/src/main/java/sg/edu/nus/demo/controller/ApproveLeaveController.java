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
import sg.edu.nus.demo.repo.LeaveRepository;
import sg.edu.nus.demo.repo.ManagerRepository;

@Controller
public class ApproveLeaveController {
	
	@Autowired
	private LeaveRepository leaveRepo;
	
	@Autowired
	private ManagerRepository mgrRepo;
	
	public void setMgrRepo(ManagerRepository mgrRepo) {
		this.mgrRepo = mgrRepo;
	}

	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.leaveRepo = leaveRepository;
	}

	@GetMapping("/leaveapplications/{name}")
	public String showleaveapp(Model model,@PathVariable(value = "name") String name) {
		model.addAttribute("leaves",leaveRepo.findAll());
		
		HashMap<Integer,Employee> empName = new  HashMap<Integer,Employee>();
		
		for(LeaveApplication i :leaveRepo.findAll() ) {
			empName.put(i.getEmployeeId(),leaveRepo.findEmployeeById(i.getEmployeeId()));
		}
		
		model.addAttribute("empdict",empName);
		model.addAttribute("manager", mgrRepo.findManagerByUserId(name));
		return "leaveappsMgr";
	}
	
	@PostMapping("/leaveapplications/{name}") 
	public String updateleave(Model model, LeaveApplication leaveapp,@PathVariable(value = "name") String name) {
		LeaveApplication la = leaveRepo.findById(leaveapp.getLeaveId()).orElse(null);
		la.setStatus(leaveapp.getStatus());
		la.setManagerComments(leaveapp.getManagerComments());
		leaveRepo.save(la);
		
		return "redirect:/leaveapplications/"+name;
	}
	
	@RequestMapping(path = "/updateleave/{name}/{leaveid}", method = RequestMethod.GET)
	public String editleave(Model model,@PathVariable(value = "leaveid") String leaveid,@PathVariable(value = "name") String name) {
		int ida = Integer.parseInt(leaveid);
		LeaveApplication la = leaveRepo.findById(ida).orElse(null);
		model.addAttribute("updateleave", la);
		model.addAttribute("manager", mgrRepo.findManagerByUserId(name));
		return "approverejectleave";
	}
	
	@RequestMapping(path = "/cancel/{name}", method = RequestMethod.GET)
	public String cancelupdate(@PathVariable(value = "name") String name) {
		return "redirect:/leaveapplications/"+name;
	}
}

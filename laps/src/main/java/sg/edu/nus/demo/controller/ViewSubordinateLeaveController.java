package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.ManagerRepository;
import sg.edu.nus.demo.repo.ViewLeaveRepository;

@Controller
public class ViewSubordinateLeaveController {
	
	@Autowired
	private ViewLeaveRepository rep;
	
	public void setRep(ViewLeaveRepository rep) {
		this.rep = rep;
	}
	
	@Autowired
	private ManagerRepository mgrRepo;
	
	public void setMgrRepo(ManagerRepository mgrRepo) {
		this.mgrRepo = mgrRepo;
	}

	@RequestMapping(path="/viewsubordinateleave/{mgrId}", method = RequestMethod.GET)
	public String viewSubordinateLeave(@PathVariable("mgrId") int mgrId, Model model) {
		model.addAttribute("leavelist",rep.findSubordinatesLeave(mgrId));
		
		Manager mgr = mgrRepo.findById(mgrId).orElse(null);
		model.addAttribute("manager", mgr);
		
		return "viewsubordinateleave";
   }

}
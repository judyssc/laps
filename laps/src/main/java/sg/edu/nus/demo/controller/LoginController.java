package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/login/employee")
	public String login(Model model) {
		
		model.addAttribute("employee", new Employee());
	        
		//Then show login page
	    return "loginemployee";
	}
	
	@PostMapping("/authenticateEmployee")
	public String authenticateEmployee(@ModelAttribute Employee employee) {
		// After keying in the username and password
		boolean loginTrueFalse = loginService.authenticateEmployee(employee.getUserId(), employee.getPassword());

		if(loginTrueFalse) {
			return "loginsuccessfulemployee";
		}
		else {
			return "loginemployee";
		}
	}
	
//	@GetMapping("/login")
//	public String login(Model model) {
//		//Prepare a User model called "user" in the form 
//		model.addAttribute("user", new User());
//	        
//		//Then show login page
//	    return "login";
//	}
//	
//	@PostMapping("/authenticateEmployee")
//	public String authenticateEmployee(@ModelAttribute User user) {
//		//@PathVariable
//		// After keying in the username and password
//		boolean loginTrueFalse = loginService.authenticateEmployee(user.getUserId(), user.getPassword());
//	        //model.addAttribute("error", "Invalid username and/or password");
//
//		if(loginTrueFalse) {
//			return "loginsuccessful";
//		}
//		else {
//			return "login";
//		}
//	}
}

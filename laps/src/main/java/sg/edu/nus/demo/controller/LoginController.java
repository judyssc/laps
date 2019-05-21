package sg.edu.nus.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.User;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.service.LoginEmployeeService;

@Controller
public class LoginController {
	
	private EmployeeRepository employeeRepository;

	@GetMapping("/login")
	public String login(Model model) {
		//Prepare a User model called "user" in the form 
		model.addAttribute("user", new User());
	        
		//Then show login page
	    return "login";
	}
	
	@PostMapping("/authenticateEmployee")
	public String authenticateEmployee(@ModelAttribute User user) {
		
		// After keying in the username and password
		LoginEmployeeService ls = new LoginEmployeeService();
		boolean loginTrueFalse = ls.authenticateEmployee(user.getUserId(), user.getPassword());
	        //model.addAttribute("error", "Invalid username and/or password");

		if(loginTrueFalse) {
			return "loginsuccessful";
		}
		else {
			return "login";
		}
	}
}

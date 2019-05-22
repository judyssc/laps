package sg.edu.nus.demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.Administrator;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.service.LoginService;

@Controller
public class LoginController {
	
	
	@Autowired
	LoginService loginService;
	
	@Resource
	private EmployeeRepository employeeRepository;
	
	//Employee login
	@GetMapping("/login/employee")
	public String loginEmployee(Model model) {
		
		model.addAttribute("employee", new Employee());
	    return "loginemployee"; //to see login page
	}
	
	@PostMapping("/authenticateEmployee")
	public String authenticateEmployee(Model model, @ModelAttribute Employee employee) {
		// After keying in the username and password
		boolean loginTrueFalse = loginService.authenticateEmployee(employee.getUserId(), employee.getPassword());

		if(loginTrueFalse) {
			Employee emp = employeeRepository.findEmployeeByUserId(employee.getUserId());
			model.addAttribute("employee", employee);
			return "index";
		}
		else {
			return "loginemployee";
		}
	}
	
	//Manager login
	@GetMapping("/login/manager")
	public String loginManager(Model model) {
		
		model.addAttribute("manager", new Manager());
	    return "loginmanager";
	}
	
	@PostMapping("/authenticateManager")
	public String authenticateManager(Model model, @ModelAttribute Manager manager) {
		// After keying in the username and password
		boolean loginTrueFalse = loginService.authenticateManager(manager.getUserid(), manager.getPassword());

		if(loginTrueFalse) {
			model.addAttribute("manager", manager);
			return "loginsuccessfulmanager";
		}
		else {
			return "loginmanager";
		}
	}
	
	//Administrator login
	@GetMapping("/login/administrator")
	public String loginAdministrator(Model model) {
		
		model.addAttribute("administrator", new Administrator());
	    return "loginadministrator";
	}
	
	@PostMapping("/authenticateAdministrator")
	public String authenticateAdministator(Model model, @ModelAttribute Administrator administrator) {
		// After keying in the username and password
		boolean loginTrueFalse = loginService.authenticateAdministrator(administrator.getUserId(), administrator.getPassword());

		if(loginTrueFalse) {
			model.addAttribute("administrator", administrator);
			return "loginsuccessfuladministrator";
		}
		else {
			return "loginadministrator";
			}
		}
}

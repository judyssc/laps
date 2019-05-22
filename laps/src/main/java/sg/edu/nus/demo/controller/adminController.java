package sg.edu.nus.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.repo.EmployeeRepository;



@Controller
public class adminController {

	private EmployeeRepository empRepo;
	@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}


	@GetMapping("/showemp")
	public String showemp(Model model) {
	
		model.addAttribute("employee",empRepo.findAll());
		System.out.println(empRepo.findAll());
		return "showemp";
	}
	
	@GetMapping("/createemp")
	public String createmp(Model model)
	{
		model.addAttribute("employe1",new Employee());
		return "createEmp";
	}
	
	@PostMapping("/createemp")
	public String createemp(Model model)
	{
		model.addAttribute("employe1",new Employee());
		return "createEmp";
	}
	@PostMapping("/saveemp") 
	public String saveemployee(Employee emp) {
		
		empRepo.save(emp);
		return "redirect:/showemp";
	}
	@GetMapping("/delup/{empid}")
	public String delup(Model model,@PathVariable("empid") int emp1)
	{
		model.addAttribute("employedelup",empRepo.findById(emp1).get() );
		return "delupdate";
	}
	@GetMapping("/deleteempid/{empid}")
	public String deleteemp(Model model,@PathVariable("empid") int emp) 
	{
		empRepo.deleteById(emp);
		return "redirect:/showemp";
	}
	@GetMapping("/updateempid/{empid}")
	public String updateemployee(Model model,@PathVariable("empid") int emp)
	{
		model.addAttribute("empupdate",empRepo.findById(emp).get() );
		System.out.println(empRepo.findById(emp).get());
		return "updateemp";
		
	}
	
	

}

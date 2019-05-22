package sg.edu.nus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveType;
import sg.edu.nus.demo.repo.CalenderRepository;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.LeaveTypesRepository;



@Controller
public class adminController {

	private EmployeeRepository empRepo;
	@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
	private CalenderRepository calRepo;
	@Autowired
	public void setCalRepo(CalenderRepository calenderRepo) {
		this.calRepo = calenderRepo;
	}
	
	private LeaveTypesRepository leaveRepo;
	@Autowired
	public void setLeaveRepo(LeaveTypesRepository lRepo) {
		this.leaveRepo = lRepo;
	}
	
	
	@GetMapping("/admin")
	public String adminhome() {
		return "admin_homepage";
	}
	
	
	@GetMapping("/list_holidays")
	public String listholiday(Model model) {
		model.addAttribute("holidays",calRepo.findAll());
		System.out.println(calRepo.findAll());
		
		return "listholiday";
	}
	
	@GetMapping("/add_holidays")
	public String formeditgetholiday(Model model) {
		model.addAttribute("calender",new Calender());
		return "editholiday";
	}
	
	@PostMapping("/add_holidays")
	public String formeditpostholiday(Calender cal,Model model) {
		calRepo.save(cal);
		model.addAttribute("holidays",calRepo.findAll());
		return "redirect:/list_holidays";
	}
	@GetMapping("/delete_holidays/{date}")
	public String delete_holidays(@PathVariable String date ,Model model) {
		calRepo.deleteById(date);
		return "redirect:/list_holidays";
	}
	
	
	
	@GetMapping("/list_leavetypes")
	public String list_leavetypes(Model model) {
		model.addAttribute("leave_types",leaveRepo.findAll());
		System.out.println(leaveRepo.findAll());
		return "listleavetypes";
	}
	
	@GetMapping("/add_leavetypes")
	public String add_leavetypes(Model model) {
		model.addAttribute("leave_types",new LeaveType());
		return "addleavetypes";
	}
	
	@PostMapping("/add_leavetypes")
	public String add_leavetypes_(LeaveType lt,Model model) {
		leaveRepo.save(lt);
		model.addAttribute("leave_types",leaveRepo.findAll());
		return "redirect:/list_leavetypes";
	}
	@GetMapping("/delete_leavetypes/{id}")
	public String delete_leavetypes(@PathVariable int id ,Model model) {
		
		leaveRepo.deleteById(id);
		return "redirect:/list_leavetypes";
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

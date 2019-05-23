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
	
	
	@GetMapping("/admin/list_holidays")
	public String listholiday(Model model) {
		model.addAttribute("holidays",calRepo.findAll());
		System.out.println(calRepo.findAll());
		
		return "listholiday";
	}
	
	@GetMapping("/admin/add_holidays")
	public String formeditgetholiday(Model model) {
		model.addAttribute("calender",new Calender());
		return "editholiday";
	}
	
	@PostMapping("/admin/add_holidays")
	public String formeditpostholiday(Calender cal,Model model) {
		calRepo.save(cal);
		model.addAttribute("holidays",calRepo.findAll());
		return "redirect:/admin/list_holidays";
	}
	@GetMapping("/admin/delete_holidays/{date}")
	public String delete_holidays(@PathVariable String date ,Model model) {
		calRepo.deleteById(date);
		return "redirect:/admin/list_holidays";
	}
	
	
	
	@GetMapping("/admin/list_leavetypes")
	public String list_leavetypes(Model model) {
		model.addAttribute("leave_types",leaveRepo.findAll());
		System.out.println(leaveRepo.findAll());
		return "listleavetypes";
	}
	
	@GetMapping("/admin/add_leavetypes")
	public String add_leavetypes(Model model) {
		model.addAttribute("leave_types",new LeaveType());
		return "addleavetypes";
	}
	
	@PostMapping("/admin/add_leavetypes")
	public String add_leavetypes_(LeaveType lt,Model model) {
		leaveRepo.save(lt);
		model.addAttribute("leave_types",leaveRepo.findAll());
		return "redirect:/admin/list_leavetypes";
	}
	@GetMapping("/admin/delete_leavetypes/{id}")
	public String delete_leavetypes(@PathVariable int id ,Model model) {
		
		leaveRepo.deleteById(id);
		return "redirect:/admin/list_leavetypes";
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
	@PostMapping("/saveemp1")
	public String saveemp1(Employee emp)
	{
		System.out.println(emp.getDesignation());
		if(emp.getDesignation().equals("teamlead"))
		{
			emp.setAnnualLeaveBalance(18);
			empRepo.save(emp);
			
		}else if(emp.getDesignation().equals("developer")) {
			emp.setAnnualLeaveBalance(14);
			empRepo.save(emp);
		}
		return "redirect:/showemp";
		
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

package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveType;
import sg.edu.nus.demo.model.PagerModel;
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
	public ModelAndView listholiday(@RequestParam("pageSize") Optional<Integer> pageSize,@RequestParam("page") Optional<Integer> page) {
		
		ModelAndView modelAndView = new ModelAndView("listholiday");
		int BUTTONS_TO_SHOW = 3;
		int INITIAL_PAGE = 0;
		int INITIAL_PAGE_SIZE = 10;
		int[] PAGE_SIZES = { 5, 10};
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		
		Page<Calender> clientlist = calRepo.findAll(new PageRequest(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(clientlist.getTotalPages(), clientlist.getNumber(), BUTTONS_TO_SHOW);
	
		modelAndView.addObject("clientlist", clientlist);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);

	    return modelAndView;

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
		LocalDate da= LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		calRepo.deleteById(da);
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
	public String saveemployee(@Valid Employee emp,BindingResult bindingresult) {
		if(bindingresult.hasErrors()) {
			return "createEmp";
		}
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

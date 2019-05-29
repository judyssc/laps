package sg.edu.nus.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.CalenderRepository;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.LeaveRepository;
import sg.edu.nus.demo.repo.LeaveTypesRepository;
import sg.edu.nus.demo.repo.ManagerRepository;
import sg.edu.nus.demo.service.Mail_utility;
import sg.edu.nus.demo.validator.DateValidator;


@Controller
public class SubmitLeaveController {
	
	@Autowired
	private DateValidator dateValidator;
	
	private LeaveRepository leaveRepository;
	
	private EmployeeRepository empRepo;
	private ManagerRepository mgrRepo; 
	
	private CalenderRepository calRepo;
	private LeaveTypesRepository leaveTypeRepo;
	
	@Autowired
	public void setLeaveTypeRepo(LeaveTypesRepository leaveTypeRepo) {
		this.leaveTypeRepo = leaveTypeRepo;
	}
	
	@Autowired
	public void setCalRepo(CalenderRepository calRepo) {
		this.calRepo = calRepo;
	}

	@Autowired
	public void setLeaveRepository(LeaveRepository leaveRepository) {
		this.leaveRepository = leaveRepository;
	}

	@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@Autowired
	public void setMgrRepo(ManagerRepository mgrRepo) {
		this.mgrRepo = mgrRepo;
	}

	@GetMapping("/leaveform/{employeeId}")
	public String createNewLeave(Model model, @PathVariable int employeeId) {
		
		LeaveApplication leave = new LeaveApplication();
		leave.setDateOfApplication(LocalDate.now());
		
		Employee employee = empRepo.findById(employeeId).orElse(null);
		
		ArrayList<String> listTypes = (ArrayList<String>)leaveTypeRepo.listAllLeaveTypes();
		
		model.addAttribute("listofLeaveTypes", listTypes);
		
		model.addAttribute("leave", leave);
		model.addAttribute("employee", employee); 
		
		return "leaveform";
	}
	
	@PostMapping("/leaveconfirmation/{employeeId}")
	public String saveLeave(@Valid @ModelAttribute("leave") LeaveApplication leave, BindingResult result, Model model, @PathVariable int employeeId ) {
		
		dateValidator.validate(leave, result); 
		
		if(result.hasErrors()) {
			Employee employee = empRepo.findById(employeeId).orElse(null);
			ArrayList<String> listTypes = (ArrayList<String>)leaveTypeRepo.listAllLeaveTypes();
			model.addAttribute("employee", employee);
			model.addAttribute("listofLeaveTypes", listTypes);
			return "leaveform";
		}
		
		leave.setDaysApplied(getWorkingDays(leave.getStartDate(), leave.getEndDate()));
		if(leave.getLeaveId()==0) {
		leave.setStatus("Applied");
		}
		leave.setManagerComments("Awaiting manager's comments.");
		
		Employee employee = empRepo.findById(employeeId).orElse(null);		
		model.addAttribute("employee", employee);
		
		if(leave.getType().equals("Annual")) {
			if(employee.getAnnualLeaveBalance()-leave.getDaysApplied()<0) {
				leave.setStatus("Rejected");
				leave.setManagerComments("Not enough leave lah!");
			}
			
			else if(!isWorkingDay(leave.getStartDate(), leave.getEndDate())) {
				leave.setStatus("Rejected");
				leave.setManagerComments("Leave start/end date cannot be a non-working day. Please check your date selection.");
			}
			
			else {
			employee.setAnnualLeaveBalance(employee.getAnnualLeaveBalance()-leave.getDaysApplied());
			}
		}
		
		if(leave.getType().equals("Medical")) {
			if(employee.getMedicalLeaveBalance()-leave.getDaysApplied()<0) {
				leave.setStatus("Rejected");
				leave.setManagerComments("MC King ah you?! *#*@!*#(@");
			}
			
			else if(!isWorkingDay(leave.getStartDate(), leave.getEndDate())) {
				leave.setStatus("Rejected");
				leave.setManagerComments("Leave start/end date cannot be a non-working day. Please check your date selection.");
			}
			
			else {
			employee.setMedicalLeaveBalance(employee.getMedicalLeaveBalance()-leave.getDaysApplied());
			}
		}
		
		if(leave.getType().equals("Compensation")) {
			if(employee.getCompLeaveBalance()-leave.getDaysApplied()<0) {
				leave.setStatus("Rejected");
				leave.setManagerComments("You where got OT so much!? Check leh!");
			}
			
			else if(!isWorkingDay(leave.getStartDate(), leave.getEndDate())) {
				leave.setStatus("Rejected");
				leave.setManagerComments("Leave start/end date cannot be a non-working day. Please check your date selection.");
			}
			
			else {
			employee.setCompLeaveBalance(employee.getCompLeaveBalance()-leave.getDaysApplied());
			}
		}				
		
		
		leave.setEmployee(employee);		
		leaveRepository.save(leave);		

		model.addAttribute("leave", leave);
		
		String message = "You have submitted leave.";
		String mailId = employee.getEmail();
		String status =Mail_utility.sendemail(message, mailId);
		System.out.println(status);
		
		Manager mgr = employee.getManager();
		int mgrid = mgr.getMgrId();
		mgr = mgrRepo.findById(mgrid).orElse(null);
		String mgrmessage = employee.getEmployeeName() + " has submitted leave. Click http://localhost:8080/login/manager to update leave.";
		String mgremail = mgr.getMgremail();
		status =Mail_utility.sendemail(mgrmessage, mgremail);
		
		return "leaveconfirmation";		
	}
	
	public boolean isWorkingDay(LocalDate startdate, LocalDate enddate) {
		
		if(startdate.getDayOfWeek().toString().equals("SATURDAY") || startdate.getDayOfWeek().toString().equals("SUNDAY") || enddate.getDayOfWeek().toString().equals("SUNDAY")||enddate.getDayOfWeek().toString().equals("SATURDAY")||isPublicHoliday(startdate)||isPublicHoliday(enddate)) {
			return false;
		}
		
		return true;
	}
	
	@GetMapping("/leaveconfirmation/{employeeId}")
	public String confirmLeave(Model model, LeaveApplication leave, @PathVariable int employeeId) {		

		model.addAttribute("leave", leave);
		
		Employee employee = empRepo.findById(employeeId).orElse(null);
		
		model.addAttribute("employee", employee); 

		return "leaveconfirmation";
	}
	
	public int getWorkingDays(LocalDate startDate, LocalDate endDate ) {
		
		int countWorkDays = 0;
		
		if (startDate.getDayOfWeek() != DayOfWeek.SATURDAY && startDate.getDayOfWeek() != DayOfWeek.SUNDAY
				&& !isPublicHoliday(startDate)) {
			countWorkDays = 1;
		}

		while (startDate.isBefore(endDate)) {
			startDate = startDate.plusDays(1);
			if (startDate.getDayOfWeek() != DayOfWeek.SATURDAY && startDate.getDayOfWeek() != DayOfWeek.SUNDAY
					&& !isPublicHoliday(startDate)) {
				++countWorkDays;
			}
		}
		
		Period period = Period.between(startDate, endDate);
		if(period.getDays() <= 14) {
			return countWorkDays;
		}
		else {
			return (period.getDays()+1);
		}
		
	}
	
	
	public boolean isPublicHoliday(LocalDate dateToCheck) {
		
		ArrayList<LocalDate> listPB = getLocalDate ( calRepo);

		return listPB.contains(dateToCheck);
	}
	
	public static ArrayList<LocalDate> getLocalDate (CalenderRepository calRepo){
		
		ArrayList<LocalDate> array = new ArrayList<LocalDate>();
		for(Calender i : calRepo.findAll()) {
			
			String date = i.getDate();
			LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			array.add(localDate);
		}
		return array;
				
	}
	
	
}
package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


@Controller
public class SubmitLeaveController {

	
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
		
		LeaveApplication leaveObject = new LeaveApplication();
		leaveObject.setDateOfApplication(LocalDate.now());
		
		Employee employee = empRepo.findById(employeeId).orElse(null);
		
		ArrayList<String> listTypes = (ArrayList<String>)leaveTypeRepo.listAllLeaveTypes();
		
		model.addAttribute("listofLeaveTypes", listTypes);
		
		model.addAttribute("leave", leaveObject);
		model.addAttribute("employee", employee); 
		return "leaveform";
	}
	
	@PostMapping("/leaveconfirmation/{employeeId}")
	public String saveLeave(Model model, LeaveApplication leave, @PathVariable int employeeId) {
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
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(java.sql.Date.valueOf(startDate));
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(java.sql.Date.valueOf(endDate));
		
		if(startCal.getTime() == endCal.getTime()) {
			return 1;
		}
		
		if(startCal.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY && !isPublicHoliday(startDate)) {
			countWorkDays = 1;
		}
		
		while(startCal.getTime().before(endCal.getTime())) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if(startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && !isPublicHoliday(startCal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
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
		
//		ArrayList<LocalDate> listPB = calRepo.listofPublicHolidays();
		ArrayList<LocalDate> listPB = getLocalDate ( calRepo);
		
		/*
		 * HashMap<String, LocalDate> allPublicHolidays = new HashMap<String,
		 * LocalDate>(); listOfPublicHolidays.put("New Year's Day", LocalDate.of(2019,
		 * 1, 1)); listOfPublicHolidays.put("Chinese New Year", LocalDate.of(2019, 2,
		 * 5)); listOfPublicHolidays.put("Chinese New Year", LocalDate.of(2019, 2, 6));
		 * listOfPublicHolidays.put("Good Friday", LocalDate.of(2019, 4, 19));
		 * listOfPublicHolidays.put("Labour Day", LocalDate.of(2019, 5, 1));
		 * listOfPublicHolidays.put("Vesak Day", LocalDate.of(2019, 5, 20));
		 * listOfPublicHolidays.put("Hari Raya Puasa", LocalDate.of(2019, 6, 5));
		 * listOfPublicHolidays.put("National Day", LocalDate.of(2019, 8, 9));
		 * listOfPublicHolidays.put("Hari Raya Haji", LocalDate.of(2019, 8, 12));
		 * listOfPublicHolidays.put("Deepavali", LocalDate.of(2019, 10, 28));
		 * listOfPublicHolidays.put("Christmas", LocalDate.of(2019, 12, 25));
		 */
		
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
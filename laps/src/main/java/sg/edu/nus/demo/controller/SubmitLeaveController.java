package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.LeaveRepository;


@Controller
public class SubmitLeaveController {

	@Autowired
	private LeaveRepository leaveRepository;
	
	@GetMapping("/leaveform")
	public String createNewLeave(Model model) {
		
		LeaveApplication leaveObject = new LeaveApplication();
		leaveObject.setDateOfApplication(LocalDate.now());
		
		model.addAttribute("leave", leaveObject);
		return "leaveform";
	}
	
	@PostMapping("/leaveconfirmation")
	public String saveLeave(Model model, LeaveApplication leave) {
		
		leave.setDaysApplied(getWorkingDays(leave.getStartDate(), leave.getEndDate()));
		leave.setStatus("Applied");
		leave.setManagerComments("Awaiting manager's comments.");
		
		model.addAttribute("leave", leave);
		
		leaveRepository.save(leave);
		return "leaveconfirmation";		
	}
	
	@GetMapping("/leaveconfirmation")
	public String confirmLeave(Model model, LeaveApplication leave) {		
		model.addAttribute("leave", leave);
		return "leaveconfirmation";
	}
	
	public static int getWorkingDays(LocalDate startDate, LocalDate endDate ) {
		
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
	
	public static boolean isPublicHoliday(LocalDate dateToCheck) {
		
		HashMap<String, LocalDate> listOfPublicHolidays = new HashMap<String, LocalDate>();
		listOfPublicHolidays.put("New Year's Day", LocalDate.of(2019, 1, 1));
		listOfPublicHolidays.put("Chinese New Year", LocalDate.of(2019, 2, 5));
		listOfPublicHolidays.put("Chinese New Year", LocalDate.of(2019, 2, 6));
		listOfPublicHolidays.put("Good Friday", LocalDate.of(2019, 4, 19));
		listOfPublicHolidays.put("Labour Day", LocalDate.of(2019, 5, 1));
		listOfPublicHolidays.put("Vesak Day", LocalDate.of(2019, 5, 20));
		listOfPublicHolidays.put("Hari Raya Puasa", LocalDate.of(2019, 6, 5));
		listOfPublicHolidays.put("National Day", LocalDate.of(2019, 8, 9));
		listOfPublicHolidays.put("Hari Raya Haji", LocalDate.of(2019, 8, 12));
		listOfPublicHolidays.put("Deepavali", LocalDate.of(2019, 10, 28));
		listOfPublicHolidays.put("Christmas", LocalDate.of(2019, 12, 25));
		
		return listOfPublicHolidays.containsValue(dateToCheck);
	}
	
	
}

package sg.edu.nus.demo.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView saveLeave(LeaveApplication leave) {
		/*
		 * Period period = Period.between(leave.getStartDate(), leave.getEndDate());
		 * leave.setDaysApplied(period.getDays());
		 */
		leave.setDaysApplied(getWorkingDays(leave.getStartDate(), leave.getEndDate()));
		leave.setStatus("Applied");
		leave.setManagerComments("Awaiting manager's comments.");
		
		leaveRepository.save(leave);
		return new ModelAndView("leaveconfirmation","leave", leave);		
	}
	
	@GetMapping("/leaveconfirmation")
	public String confirmLeave(Model model, LeaveApplication leave) {		
		model.addAttribute("leave", leave);
		return "leaveconfirmation";
	}
	
	public static int getWorkingDays(LocalDate startDate, LocalDate endDate ) {
		
		int countWorkDays = 0;
		int countWeekends = 0;
		
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(java.sql.Date.valueOf(startDate));
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(java.sql.Date.valueOf(endDate));
		
		if(startCal.getTime() == endCal.getTime()) {
			return 1;
		}
		
		if(startCal.get(Calendar.DAY_OF_WEEK)!= Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY ) {
			countWorkDays = 1;
		}
		
		while(startCal.getTime().before(endCal.getTime())) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if(startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY ) {
				++countWorkDays;
			}
			else {
				++countWeekends;
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
	
	
}

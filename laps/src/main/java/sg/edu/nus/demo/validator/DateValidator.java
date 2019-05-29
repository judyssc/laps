package sg.edu.nus.demo.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.model.LeaveApplication;
import sg.edu.nus.demo.repo.CalenderRepository;

@Component("datevalidator")
public class DateValidator implements Validator {

	private static final String REQUIRED = "required";
	private static final String startCheck = "startNotWorkingDay";
	private static final String endCheck = "endNotWorkingDay";

	private CalenderRepository calRepo;
	
	@Autowired
	public void setCalRepo(CalenderRepository calRepo) {
		this.calRepo = calRepo;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		LeaveApplication leave = (LeaveApplication) target;
		
		if (leave.getStartDate() == null) {
			errors.rejectValue("startDate", REQUIRED, REQUIRED);
		}
		
		else if (!isWorkingDay(leave.getStartDate())) {
			errors.rejectValue("startDate", startCheck, startCheck);
		}

		if (leave.getEndDate() == null) {
			errors.rejectValue("endDate", REQUIRED, REQUIRED);
		}	
		
		else if (!isWorkingDay(leave.getEndDate())) {
			errors.rejectValue("endDate", endCheck, endCheck);
		}
		
	}

	public boolean isWorkingDay(LocalDate startdate) {
		
		if(startdate.getDayOfWeek().toString().equals("SATURDAY") || startdate.getDayOfWeek().toString().equals("SUNDAY")||isPublicHoliday(startdate)) {
			return false;
		}
		
		return true;
	}
	
	public boolean isPublicHoliday(LocalDate dateToCheck) {
		
		ArrayList<LocalDate> listPB = getLocalDate (calRepo);

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
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.equals(clazz);
	}
}

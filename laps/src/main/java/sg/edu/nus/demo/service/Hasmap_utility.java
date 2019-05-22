package sg.edu.nus.demo.service;

import java.util.HashMap;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.repo.CalenderRepository;

public class Hasmap_utility {
	
	public static HashMap<String, String> getDates(CalenderRepository calRepo){
		
		
		HashMap<String, String> hm = new HashMap<String, String>();
		for(Calender i : calRepo.findAll()) {
			hm.put(i.getDate(), i.getHoliday_flag());
		}
		return hm;
	}
	

}

package sg.edu.nus.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "calender")
public class Calender {
	
	@Id
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(length=100)
	private String date;
	private String holiday_flag;
	
	public Calender() {
		
	}
	public Calender(String d, String hf) {
		super();
		this.date=d;
		this.holiday_flag=hf;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHoliday_flag() {
		return holiday_flag;
	}
	public void setHoliday_flag(String holiday_flag) {
		this.holiday_flag = holiday_flag;
	}

}

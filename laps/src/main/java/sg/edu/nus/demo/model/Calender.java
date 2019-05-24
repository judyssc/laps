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
	@Column(length=10)
	private String date;
	private String holiday_flag;
	public Calender() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Calender(String date, String holiday_flag) {
		super();
		this.date = date;
		this.holiday_flag = holiday_flag;
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
	
	@Override
	public String toString() {
		return "Calender [date=" + date + ", holiday_flag=" + holiday_flag + ", dateString=" + date.toString() + "]";
	}
	
	
}

package sg.edu.nus.demo.model;

import java.time.LocalDate;

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
	private LocalDate date;
	private String holiday_flag;
	public Calender() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Calender(LocalDate date, String holiday_flag) {
		super();
		this.date = date;
		this.holiday_flag = holiday_flag;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getHoliday_flag() {
		return holiday_flag;
	}
	public void setHoliday_flag(String holiday_flag) {
		this.holiday_flag = holiday_flag;
	}
	
	
}

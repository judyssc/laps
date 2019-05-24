package sg.edu.nus.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leave_types")
public class LeaveType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int id;
	private String type;
	
	public LeaveType() {
		
	}

	public LeaveType(/* int id_, */String type_) {
		/* this.id=id_; */
		this.type=type_;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

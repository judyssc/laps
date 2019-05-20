package sg.edu.nus.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "administrator")
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO/* , generator = "system-uuid" */)
	/* @GenericGenerator(name = "system-uuid", strategy = "uuid2") */
	private Integer adminId;
	private String name;
	private String userId;
	private String password;
	
	public Administrator() {}

	public Administrator(String name, String userId, String password) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", name=" + name + "]";
	}
	
	
}

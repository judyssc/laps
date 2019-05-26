package sg.edu.nus.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "administrator")
public class Administrator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO/* , generator = "system-uuid" */)
	/* @GenericGenerator(name = "system-uuid", strategy = "uuid2") */
	@Column(name = "id")
	private int adminId;
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Size(min=2, message="Username is required")
	@Column(name = "userid")
	private String userId;
	@NotNull
	@Size(min=6, message="Password is required")
	@Column(name = "password")
	private String password;
	
	public Administrator() {}

	public Administrator(String name, String userId, String password) {
		super();
		this.name = name;
		this.userId = userId;
		this.password = password;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
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

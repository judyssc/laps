package sg.edu.nus.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "manager")
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO/* , generator = "system-uuid" */)
	/* @GenericGenerator(name = "system-uuid", strategy = "uuid2") */
	@Column(name = "id")
	private int mgrId;
	@Column(name = "name")
	private String name;
	@Column(name = "userid")
	private String userid;
	@Column(name = "password")
	private String password;
	
	public Manager() {}

	public Manager(String name, String userid, String password) {
		super();
		this.name = name;
		this.userid = userid;
		this.password = password;
	}

	@OneToMany(targetEntity=Employee.class,mappedBy="manager")
	public Collection<Employee> employees;
	
	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Manager [mgrId=" + mgrId + ", name=" + name + "]";
	}
	
	
	
}

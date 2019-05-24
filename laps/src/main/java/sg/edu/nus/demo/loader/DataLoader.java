package sg.edu.nus.demo.loader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.edu.nus.demo.model.Calender;
import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveType;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.CalenderRepository;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.LeaveTypesRepository;
import sg.edu.nus.demo.repo.ManagerRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private EmployeeRepository empRepo;
	private ManagerRepository mgrRepo;
	private CalenderRepository calRepo;
	private LeaveTypesRepository leaveTypeRepo;
	
	@Autowired
	public void setCalRepo(CalenderRepository calRepo) {
		this.calRepo = calRepo;
	}

	@Autowired
	public void setLeaveTypeRepo(LeaveTypesRepository leaveTypeRepo) {
		this.leaveTypeRepo = leaveTypeRepo;
	}

	@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@Autowired
	public void setMgrRepo(ManagerRepository mgrRepo) {
		this.mgrRepo = mgrRepo;
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		  DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		  Manager managerTest = new Manager("Ah hua", "hua", "h123"); Employee
		  employeeTest = new Employee("Bran the Broken", "abc",
		  "5f4dcc3b5aa765d61d8327deb882cf99","Slave","employee",18,60,0, managerTest);
		  
		  employeeTest.setEmail("chua113@gmail.com");
		  
		  Calender vesak = new Calender(LocalDate.parse("2019-05-20", df),"Vesak Day");
		  Calender sa48break = new Calender(LocalDate.parse("2019-05-30", df),"SA48 Break!");
		  Calender sa48break2 = new Calender(LocalDate.parse("2019-05-31", df),"SA48 Break!!");
		  
		  LeaveType annual = new LeaveType("Annual");
		  LeaveType medical = new LeaveType("Medical");
		  LeaveType compensation = new LeaveType("Compensation");
		  LeaveType hospital = new LeaveType("Hospitalization");
		  
		  mgrRepo.save(managerTest); empRepo.save(employeeTest);
		  calRepo.save(vesak);calRepo.save(sa48break); calRepo.save(sa48break2); 
		  
		  leaveTypeRepo.save(annual); leaveTypeRepo.save(medical);leaveTypeRepo.save(compensation);leaveTypeRepo.save(hospital);
		 
	}
}

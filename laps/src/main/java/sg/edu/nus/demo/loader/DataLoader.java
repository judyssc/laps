package sg.edu.nus.demo.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.Manager;
import sg.edu.nus.demo.repo.EmployeeRepository;
import sg.edu.nus.demo.repo.ManagerRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private EmployeeRepository empRepo;
	private ManagerRepository mgrRepo;

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
		
		
		  Manager managerTest = new Manager("Ah hua", "hua", "h123"); Employee
		  employeeTest = new Employee("Lee Siong", "abc",
		  "abc123","Slave","employee",18,60,0, managerTest);
		  
		  mgrRepo.save(managerTest); empRepo.save(employeeTest);
		 
	}
}

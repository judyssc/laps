package sg.edu.nus.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.repo.EmployeeRepository;

@Service
public class LoginService {
	
	@Resource
	private EmployeeRepository employeeRepository;
	
	public boolean authenticateEmployee(String username, String password) {
		
		Employee employee = employeeRepository.findEmployeeByUserId(username);
		System.out.println(username);
		System.out.println(password);
		if(password.compareTo(employee.getPassword()) == 0) {
			
			System.out.println(employee.getPassword());
			return true;
		}

    	//if (employee == null) throw new UsernameNotFoundException(username);
        //compare password from form with employee password in database, return true if same, false if different
        return false;
	}
}

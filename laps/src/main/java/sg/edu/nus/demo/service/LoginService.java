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
		System.out.println(username);
    	try {
    		if(employeeRepository != null) {
	    		Employee employee = employeeRepository.findEmployeeByName(username);
	          System.out.println(employee.getPassword());
    		}
    		else {
    			System.out.println("Null repository");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    	//if (employee == null) throw new UsernameNotFoundException(username);
        //compare password from form with employee password in database, return true if same, false if different
        return false;
	}
}

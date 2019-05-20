package sg.edu.nus.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.repo.EmployeeRepository;

@Service
public class LoginEmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public boolean authenticateEmployee(String userid, String password) {
    	System.out.println(userid);
    	try {
    		List<Employee> employee = employeeRepository.findByUserId(userid);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
//        System.out.println(employee.getPassword());
//        if (employee == null) throw new UsernameNotFoundException(username);
        
        //compare password from form with employee password in database, return true if same, false if different

        return false;
    }
}

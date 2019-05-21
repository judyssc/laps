package sg.edu.nus.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	
	List<Employee> findByUserId(String userid);
	
}


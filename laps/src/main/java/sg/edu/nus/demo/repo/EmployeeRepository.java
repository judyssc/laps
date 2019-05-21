package sg.edu.nus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e where e.userId = :name")
	Employee findEmployeeByUserId(@Param("name") String name);
	
}


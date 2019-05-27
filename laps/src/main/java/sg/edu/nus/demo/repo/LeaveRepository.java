package sg.edu.nus.demo.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.Employee;
import sg.edu.nus.demo.model.LeaveApplication;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveApplication, Integer> {
	@Query("SELECT e FROM Employee e where e.id = :id")
	Employee findEmployeeById(@Param("id") int id);
	
	@Query("SELECT l from LeaveApplication l  inner join l.employee le inner join le.manager m  WHERE m.mgrId= :mgrId And l.status= 'Applied' ")
	ArrayList<LeaveApplication> findSubordinatesLeave(@Param("mgrId") int mgrId);
}

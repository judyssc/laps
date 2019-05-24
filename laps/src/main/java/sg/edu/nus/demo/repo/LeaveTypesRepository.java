package sg.edu.nus.demo.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.demo.model.LeaveType;

public interface LeaveTypesRepository  extends JpaRepository<LeaveType, Integer>{
	
	@Query("SELECT t.type from LeaveType t")
	ArrayList<String> listAllLeaveTypes(); 
	
}

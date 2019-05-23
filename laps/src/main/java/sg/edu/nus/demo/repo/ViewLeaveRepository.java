package sg.edu.nus.demo.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.LeaveApplication;

@Repository
public interface ViewLeaveRepository extends JpaRepository<LeaveApplication, Integer>{

	
	@Query("SELECT l from LeaveApplication l WHERE l.employeeId = :eid")
	ArrayList<LeaveApplication> findLeaveApplicationByEID(@Param("eid") int eid);
	
	@Query("SELECT l from LeaveApplication l  inner join l.employee le inner join le.manager m  WHERE m.mgrId= :mgrId")
	ArrayList<LeaveApplication> findSubordinatesLeave(@Param("mgrId") int mgrId);
	  
}
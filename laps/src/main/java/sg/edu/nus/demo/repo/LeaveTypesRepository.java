package sg.edu.nus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.demo.model.LeaveType;

public interface LeaveTypesRepository  extends JpaRepository<LeaveType, Integer>{

}

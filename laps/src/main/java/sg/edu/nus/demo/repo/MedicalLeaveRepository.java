package sg.edu.nus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.MedicalLeave;

@Repository
public interface MedicalLeaveRepository extends JpaRepository<MedicalLeave, String> {

}

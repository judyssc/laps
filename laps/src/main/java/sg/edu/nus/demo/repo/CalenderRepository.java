package sg.edu.nus.demo.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.demo.model.Calender;

@Repository
public interface CalenderRepository extends	PagingAndSortingRepository<Calender,String>{

	@Query("SELECT c.date from Calender c")
	ArrayList<LocalDate> listofPublicHolidays();
}

package com.innominds.SpringbootProject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innominds.SpringbootProject.entity.TimesheetDetails;

@Repository
public interface TimesheetRepository extends JpaRepository<TimesheetDetails, Long> {

	@Query(value = "select * from timesheets where date like ?1 and emp_id=?2",nativeQuery = true)
	List<TimesheetDetails> findBy(String input,int empId);

	@Query(value = "select * from timesheets where emp_id=?1",nativeQuery = true)
	List<TimesheetDetails> findBy(int empId);

	//Optional<Timesheets> findTimesheet(int empId);
	@Query(value = "select * from timesheets where emp_id=?1",nativeQuery = true)
	TimesheetDetails findTimesheet(int empId);

	//Optional<Timesheets> findTimesheet(int empId);
		@Query(value = "select * from timesheets where emp_id=?1 and id=?2",nativeQuery = true)
		TimesheetDetails findTimesheetByid(int empId , int id);
		
		@Transactional
		@Modifying
	    @Query(value = "delete from timesheets where id=?1",nativeQuery = true)
	    void deleteBy(int id);
}

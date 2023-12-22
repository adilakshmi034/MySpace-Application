package com.innominds.SpringbootProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innominds.SpringbootProject.entity.EmployeeDetails;

@Repository
public interface Employeerepository extends JpaRepository<EmployeeDetails, Long> {

	@Query(value = "select * from employee where emp_id=?1",nativeQuery = true)
	EmployeeDetails findBy(long empId);
	
	@Modifying
    @Query(value = "delete from employee where emp_id=?1",nativeQuery = true)
    void deleteBy(long empId);
	
	

}

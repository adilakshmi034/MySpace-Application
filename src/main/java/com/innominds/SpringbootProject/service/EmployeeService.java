package com.innominds.SpringbootProject.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.innominds.SpringbootProject.entity.EmployeeDetails;
import com.innominds.SpringbootProject.repository.Employeerepository;

@Service
@Transactional
public class EmployeeService {
	

	
	@Autowired
	private Employeerepository employeeRepo;
	
	
	
	List<EmployeeDetails> getAllEmployees() {
		return null;
	}
	
	public String saveEmployee(@RequestBody EmployeeDetails employee) {
		if(employee==null) {
			return "data is empty --- failed!!!";
		}
		employeeRepo.save(employee);
		return "success";
	}
	
	
	 public EmployeeDetails updateEmployee(EmployeeDetails employee, int empId) {
	        Optional<EmployeeDetails> emp = Optional.ofNullable(employeeRepo.findBy(empId));
	        EmployeeDetails employee1 = new EmployeeDetails();
	        if(emp.isPresent()) {
	            employee1=emp.get();
	        }
	        employee1.setEmail(employee.getEmail());
	        employee1.setEmpId(employee.getEmpId());
	        employee.setName(employee.getName());
	        employeeRepo.save(employee);
	        return employee;
	    }

	public String deleteEmployeeById(long empId) {
        employeeRepo.deleteBy(empId);
        return "Employee was deleted with id - "+empId;
    }


}

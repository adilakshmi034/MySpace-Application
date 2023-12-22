package com.innominds.SpringbootProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innominds.SpringbootProject.entity.EmployeeDetails;
import com.innominds.SpringbootProject.repository.Employeerepository;
import com.innominds.SpringbootProject.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private Employeerepository emp;
	
	private static Map<Integer,EmployeeDetails> employeeCache = new HashMap<>();
	
	@PostMapping("/employee")
    public String saveEmployee(@Valid @RequestBody EmployeeDetails employee) {
            return employeeService.saveEmployee(employee);
    }
	
	@GetMapping("/employee")
	public List<EmployeeDetails>getAllEmployee()
	{
		
		return emp.findAll();
	}
	
	@GetMapping(value = { "/employee/{empId}"})
	public EmployeeDetails getAllEmployee(@PathVariable("empId") int empId)
	{
		if(employeeCache.containsKey(empId)) {
			System.out.println("loading empdata from cache for empId:"+empId);
			return employeeCache.get(empId);
		}
		EmployeeDetails empData = emp.findBy(empId);
		if(empData != null) {
			employeeCache.put(empId, empData);
			System.out.println("updated cache from db for emp:"+empId);
			
		}
		return empData;
	}
	
	@PutMapping("/employee/{empId}")
    public EmployeeDetails updateEmployee(@Valid @RequestBody EmployeeDetails emp , @PathVariable("empId") int empId) {
        
        return employeeService.updateEmployee(emp , empId);   
    }
	@DeleteMapping("/employee/{empId}")
    public String deleteEmployee(@PathVariable("empId") long empId) {
        return employeeService.deleteEmployeeById(empId);
    }

}

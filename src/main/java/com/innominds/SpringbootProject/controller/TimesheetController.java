package com.innominds.SpringbootProject.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.innominds.SpringbootProject.entity.EmployeeDetails;
import com.innominds.SpringbootProject.entity.TimesheetDetails;
import com.innominds.SpringbootProject.repository.TimesheetRepository;
import com.innominds.SpringbootProject.service.TimeSheetService;
import com.innominds.SpringbootProject.utilities.EmployeeAllData;

@RestController
@RequestMapping("/api")
public class TimesheetController {
	
	@Autowired
//	@Qualifier("clientService")
	
	private TimeSheetService sheetService;
	@Autowired
	private TimesheetRepository timesheet;
	
	@PostMapping("/timesheet")
	public String saveTimeSheets(@Valid @RequestBody TimesheetDetails timesheet) {
		return sheetService.saveTimeSheets(timesheet);
	}
	
	@GetMapping(value = { "/timesheet/{empId}"})
	public List<TimesheetDetails>getAllTimesheets(@PathVariable("empId") int empId)
	{
		return timesheet.findBy(empId);
	}
	
	@GetMapping(value = { "/timesheet"})
	public List<TimesheetDetails>getAllTimesheets1()
	{
		return timesheet.findAll();
	}
	
	@GetMapping(value = {
			"/summery/{empId}/{date}","/summery/{empId}"
	})
	public EmployeeAllData getSummery(@PathVariable int empId,@PathVariable(required = false) String date) {
		
		if(date==null) {
			return  sheetService.getSummeryById(empId);
		}
		   
		LocalDate demoDate=LocalDate.parse(date);
		return sheetService.getSummery(empId,demoDate);
		
	}
	
	@PutMapping("/timesheet/{empId}/{id}")
    public TimesheetDetails updateTimesheet(@Valid @RequestBody TimesheetDetails tmp , @PathVariable("empId") int empId , @PathVariable("id") int id) {
        
        return sheetService.updateTimesheet(tmp,empId, id);
        
    }
	@DeleteMapping("/timesheet/{id}")
    public String deleteTimesheet(@PathVariable("id") int id) {
        return sheetService.deleteTimesheetById(id);
    }
}

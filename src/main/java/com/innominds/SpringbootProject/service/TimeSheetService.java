package com.innominds.SpringbootProject.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.innominds.SpringbootProject.entity.EmployeeDetails;
import com.innominds.SpringbootProject.entity.TimesheetDetails;
import com.innominds.SpringbootProject.repository.Employeerepository;
import com.innominds.SpringbootProject.repository.TimesheetRepository;
import com.innominds.SpringbootProject.utilities.EmployeeAllData;

//@Service
@Service("clientService")
public class TimeSheetService {
	@Autowired
	//@Qualifier("clientRepository")
	private TimesheetRepository timesheetsRepo;
	
	@Autowired
	private Employeerepository employeeRepo;  
	
	
	public String saveTimeSheets(@RequestBody TimesheetDetails timesheet) {
		if(timesheet==null || employeeRepo.findBy(timesheet.getEmp_id())==null) {
			return "data is empty --- failed!!!";
		}
		
		LocalDate date=timesheet.getDate();
		LocalDate current=LocalDate.now();
		String monthdate=getMonthDate(current);
		System.out.println(current.getMonth()+""+current.getMonthValue());
		LocalDate monthStartDate=LocalDate.parse(monthdate);
		if((date.isEqual(monthStartDate)| date.isAfter(monthStartDate)) && (date.isEqual(current)| date.isBefore(current))){
			timesheetsRepo.save(timesheet);
			return "success";
		}
	
		return "date is invalid";
	}

	private String getMonthDate(LocalDate current) {
		if(current.getMonthValue()<10) {
			return current.getYear()+"-0"+current.getMonthValue()+"-"+"01";
		}
		
		return current.getYear()+"-"+current.getMonthValue()+"-"+"01";
	}

	public EmployeeAllData getSummery(int empId, LocalDate date) {
		String input=date.getYear()+"-"+date.getMonth().getValue()+"-"+"%";
		List<TimesheetDetails> timeSheet=timesheetsRepo.findBy( input,empId);
		EmployeeDetails employee=employeeRepo.findBy(empId);
		EmployeeAllData monthSummery=new EmployeeAllData();
		monthSummery.setEmployee(employee);
		monthSummery.setTimesheets(timeSheet);
		return monthSummery;
	}

	public EmployeeAllData getSummeryById(int empId) {
		List<TimesheetDetails> timeSheet=timesheetsRepo.findBy(empId);
		EmployeeDetails employee=employeeRepo.findBy(empId);
		EmployeeAllData monthSummery=new EmployeeAllData();
		monthSummery.setEmployee(employee);
		monthSummery.setTimesheets(timeSheet);
		return monthSummery;
	}

	public  TimesheetDetails updateTimesheet(TimesheetDetails timesheet, int empId, int id) {
		Optional<TimesheetDetails> sheet = Optional.ofNullable(timesheetsRepo.findTimesheetByid(empId,id));
		TimesheetDetails timesheet1 = new TimesheetDetails();
		if(sheet.isPresent()) {
			timesheet1=sheet.get();
		}
		
		timesheet1.setNo_of_hours(timesheet.getNo_of_hours());
//		if(timesheet1.getNo_of_hours() >12) {
//			return timesheet1;
//		}
//		System.out.println("Invalid Working Hours");
		
		timesheet1.setEmp_id(timesheet.getEmp_id());
		timesheet1.setDescription(timesheet.getDescription());
		timesheet1.setId(timesheet.getId());
		timesheet1.setDate(timesheet.getDate());
		
		timesheetsRepo.save(timesheet1);
		return timesheet1;
	}	
	public String deleteTimesheetById(int id) {
		timesheetsRepo.deleteBy(id);
        return "Timesheet was deleted with id - "+id;
    }
}

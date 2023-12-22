package com.innominds.SpringbootProject.utilities;

import java.util.List;

import com.innominds.SpringbootProject.entity.EmployeeDetails;
import com.innominds.SpringbootProject.entity.TimesheetDetails;

public class EmployeeAllData {

	private EmployeeDetails employee;
	
	private List<TimesheetDetails> timesheets;

	public EmployeeDetails getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDetails employee) {
		this.employee = employee;
	}

	public List<TimesheetDetails> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<TimesheetDetails> timesheets) {
		this.timesheets = timesheets;
	}
	
	
}

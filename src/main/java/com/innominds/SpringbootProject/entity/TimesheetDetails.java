package com.innominds.SpringbootProject.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="Timesheets")
public class TimesheetDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Min(0)
	@Max(24)
	private int no_of_hours;
	
	private String Description;
	
	/*
	 * @JsonFormat(pattern="yyyy-MM-dd")
	 * 
	 * @NotNull(message="Date field is mandatory")
	 * 
	 * @Past(message="Date must contain only current date or past date")
	 */
	private LocalDate date;
	
	private int emp_id;

	

	public TimesheetDetails(int id, int no_of_hours, String description, LocalDate date, int emp_id) {
		super();
		this.id = id;
		this.no_of_hours = no_of_hours;
		this.Description = description;
		this.date = date;
		this.emp_id = emp_id;
	}

	public TimesheetDetails() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getNo_of_hours() {
		return no_of_hours;
	}

	public void setNo_of_hours(int no_of_hours) {
		this.no_of_hours = no_of_hours;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}


}

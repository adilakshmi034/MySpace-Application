package com.innominds.SpringbootProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="Employee",
uniqueConstraints={@UniqueConstraint(columnNames= {"empId"})})
public class EmployeeDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int empId;
	
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String name;
	
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@NotEmpty
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;

	public EmployeeDetails(long id, int empId, String name, String email) {
		super();
		this.id = id;
		this.empId = empId;
		this.name = name;
		this.email = email;
	}
	
	public EmployeeDetails() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

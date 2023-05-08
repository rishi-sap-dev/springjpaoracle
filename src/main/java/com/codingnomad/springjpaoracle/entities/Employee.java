package com.codingnomad.springjpaoracle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private int id;
	
	@Column(name="EMP_FIRST_NAME")
	private String firstName;
	
	@Column(name="EMP_LAST_NAME")
	private String lastName;
	
	@Column(name="EMP_CITY")
	private String empCity;// for custom derived method 
	//https://stackoverflow.com/questions/23456197/spring-data-jpa-repository-underscore-on-entity-column-name

	public Employee(String firstName, String lastName, String eMP_CITY) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empCity = eMP_CITY;
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEMP_CITY() {
		return empCity;
	}

	public void setEMP_CITY(String eMP_CITY) {
		empCity = eMP_CITY;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", EMP_CITY=" + empCity
				+ "]";
	}
	
	
}

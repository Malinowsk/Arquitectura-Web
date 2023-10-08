package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

@Data
public class DTOStudent {

	private int universityNotebookNumber;
	private int documentNumber;
	private String fullName;
	private String gender;
	private String city;
	private Timestamp birthdate;

	public DTOStudent(int universityNotebookNumber, int documentNumber,
					  String name, String surname, String gender, String city, Timestamp birthdate)
	{
		this.universityNotebookNumber = universityNotebookNumber;
		this.documentNumber = documentNumber;
		this.fullName = surname + ", " + name;
		this.gender = gender;
		this.city = city;
		this.birthdate = birthdate;
	}
	
}

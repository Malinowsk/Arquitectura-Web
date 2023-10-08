package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DTOResponseStudent {

	private int universityNotebookNumber;
	private int documentNumber;
	private String fullName;
	private String gender;
	private String city;
	private Timestamp birthdate;

	public DTOResponseStudent(int universityNotebookNumber, int documentNumber,
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

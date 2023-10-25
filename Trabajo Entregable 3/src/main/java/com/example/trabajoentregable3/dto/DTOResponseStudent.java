package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

@Data
public class DTOResponseStudent {

	private int universityNotebookNumber;
	private int documentNumber;
	private String fullName;
	private String gender;
	private String city;
	private int age;

	public DTOResponseStudent(int universityNotebookNumber, int documentNumber,
							  String name, String surname, String gender, String city, Timestamp birthdate)
	{
		this.universityNotebookNumber = universityNotebookNumber;
		this.documentNumber = documentNumber;
		this.fullName = surname + ", " + name;
		this.gender = gender;
		this.city = city;
		this.age = getAge(birthdate);
	}

	private int getAge(Timestamp birthdate) {
		LocalDate birthdateLocalDate = birthdate.toLocalDateTime().toLocalDate();
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthdateLocalDate, currentDate);
		return period.getYears();
	}

}

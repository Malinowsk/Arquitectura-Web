package com.example.trabajoentregable3.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DTOStudent {

	private int universityNotebookNumber;
	private int documentNumber;
	private String fullName;
	private String gender;
	private String city;
	private Timestamp birthdate;
	
}

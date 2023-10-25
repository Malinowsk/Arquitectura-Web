package main.resources.tp2.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public class DTOStudent {
	private int universityNotebookNumber;
	private int documentNumber;
	private String fullName;
	private String gender;
	private String city;
	private Timestamp birthdate;
	
	public DTOStudent(Integer universityNotebookNumber, Integer documentNumber, String fullName, String gender,
			String city, Timestamp birthdate) {
		super();
		this.universityNotebookNumber = universityNotebookNumber;
		this.documentNumber = documentNumber;
		this.fullName = fullName;
		this.gender = gender;
		this.city = city;
		this.birthdate = birthdate;
	}

	public Integer getUniversityNotebookNumber() {
		return universityNotebookNumber;
	}

	public void setUniversityNotebookNumber(Integer universityNotebookNumber) {
		this.universityNotebookNumber = universityNotebookNumber;
	}

	public void setDocumentNumber(Integer documentNumber) {
		this.documentNumber = documentNumber;
	}

	public int getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(int documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}
	
	public int getAge() {
        LocalDate birthdateLocalDate = birthdate.toLocalDateTime().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdateLocalDate, currentDate);
        return period.getYears();
    }

	@Override
	public String toString() {
		return "\n Nro LU: " + universityNotebookNumber +
                ", DNI: " + documentNumber +
                ", Nombre y Apellido: '" + fullName + '\'' +
                ", Edad: " + getAge() +
                ", Sexo: '" + gender + '\'' +
                ", Ciudad: '" + city+"'";
	}
	
	
	
	
}

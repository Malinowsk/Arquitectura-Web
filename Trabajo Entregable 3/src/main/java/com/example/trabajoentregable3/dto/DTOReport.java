package com.example.trabajoentregable3.dto;

public class DTOReport {
	private String career_name;
	private Integer year;
	private Double enrolled_qty;
	private Double graduated_qty;
	
	public DTOReport(String career_name, Integer year, Double cant_inscriptos, Double cant_egresos) {
		super();
		this.career_name = career_name;
		this.year = year;
		this.enrolled_qty = cant_inscriptos;	
		this.graduated_qty = cant_egresos;
	}	

	public String getCareer_name() {
		return career_name;
	}

	public void setCareer_name(String career_name) {
		this.career_name = career_name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getEnrolled_qty() {
		return enrolled_qty;
	}

	public void setEnrolled_qty(Double enrolled_qty) {
		this.enrolled_qty = enrolled_qty;
	}

	public Double getGraduated_qty() {
		return graduated_qty;
	}

	public void setGraduated_qty(Double graduated_qty) {
		this.graduated_qty = graduated_qty;
	}

	@Override
	public String toString() {
		return "Carrera: " + career_name + ", Año: " + year + ", Cantidad Inscriptos: " + enrolled_qty.intValue()
				+ ", Cantidad Egresos: " + graduated_qty.intValue() + " \n";
	}
	
}

package com.example.trabajoentregable3.dto;

public class DTOReport {
	private String career_name;
	private Integer year;
	private Integer enrolled_qty;
	private Integer graduated_qty;
	
	public DTOReport(String career_name, Integer year, Double cant_inscriptos, Double cant_egresos) {
		super();
		this.career_name = career_name;
		this.year = year;
		this.enrolled_qty = cant_inscriptos.intValue();
		this.graduated_qty = cant_egresos.intValue();
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

	public Integer getEnrolled_qty() {
		return enrolled_qty;
	}

	public void setEnrolled_qty(Integer enrolled_qty) {
		this.enrolled_qty = enrolled_qty;
	}

	public Integer getGraduated_qty() {
		return graduated_qty;
	}

	public void setGraduated_qty(Integer graduated_qty) {
		this.graduated_qty = graduated_qty;
	}

	@Override
	public String toString() {
		return "Carrera: " + career_name + ", Año: " + year + ", Cantidad Inscriptos: " + enrolled_qty
				+ ", Cantidad Egresos: " + graduated_qty + " \n";
	}
	
}

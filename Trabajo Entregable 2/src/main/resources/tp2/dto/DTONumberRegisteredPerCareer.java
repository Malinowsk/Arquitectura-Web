package main.resources.tp2.dto;

public class DTONumberRegisteredPerCareer {
	
	private String career_name;
	private long enrolled_qty;
	
	public DTONumberRegisteredPerCareer(String career_name, long enrolled_qty) {
		this.career_name = career_name;
		this.enrolled_qty = enrolled_qty;
	}
	
	public String getCareer_name() {
		return career_name;
	}
	
	public long getEnrolled_qty() {
		return enrolled_qty;
	}

	@Override
	public String toString() {
		return "\n Carrera: " + career_name
				+ ", Cantidad Inscriptos: " + enrolled_qty;
	}
	
	





}

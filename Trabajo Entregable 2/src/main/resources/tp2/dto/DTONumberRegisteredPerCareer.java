package main.resources.tp2.dto;

public class DTONumberRegisteredPerCareer {
	
	private int career_id;
	private String career_name;
	private int enrolled_qty;
	
	public DTONumberRegisteredPerCareer(int career_id, String career_name, int enrolled_qty) {
		super();
		this.career_id = career_id;
		this.career_name = career_name;
		this.enrolled_qty = enrolled_qty;
	}
	
	public int getCareer_id() {
		return career_id;
	}
	
	public String getCareer_name() {
		return career_name;
	}
	
	public int getEnrolled_qty() {
		return enrolled_qty;
	}

	@Override
	public String toString() {
		return "DTONumberRegisteredPerCareer ID Carrera: " + career_id + ", Nombre Carrera: " + career_name
				+ ", Cantidad Inscriptos: " + enrolled_qty;
	}
	
	





}

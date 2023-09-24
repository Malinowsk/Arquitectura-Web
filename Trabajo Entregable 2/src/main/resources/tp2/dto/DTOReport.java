package main.resources.tp2.dto;

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

	@Override
	public String toString() {
		return "Carrera: " + career_name + ", Anioz: " + year + ", Cantidad Inscriptos: " + enrolled_qty.intValue()
				+ ", Cantidad Egresos: " + graduated_qty.intValue() + " \n";
	}
	
}

package main.resources.tp2.dto;

public class DTOReport {
	private String career_name;
	private Integer año;
	private Double cant_inscriptos;
	private Double cant_egresos;
	
	public DTOReport(String career_name, Integer año, Double cant_inscriptos, Double cant_egresos) {
		super();
		this.career_name = career_name;
		this.año = año;
		this.cant_inscriptos = cant_inscriptos;
		this.cant_egresos = cant_egresos;
	}

	@Override
	public String toString() {
		return "Carrera: " + career_name + " | Año: " + año + " | Cantidad Inscriptos: " + cant_inscriptos
				+ " | Cantidad Egresos: " + cant_egresos + " \n";
	}
	
	
	
}

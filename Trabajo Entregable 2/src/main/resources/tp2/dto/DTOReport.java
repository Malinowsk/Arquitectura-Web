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
<<<<<<< HEAD
		return "Carrera: " + career_name + ", Anio: " + year + ", Cantidad Inscriptos: " + Math.round(enrolled_qty)
				+ ", Cantidad Egresos: " + Math.round(graduated_qty) + " \n";
=======
		return "Carrera: " + career_name + " , A�o: " + year + " , Cantidad Inscriptos: " + cant_inscriptos
				+ " , Cantidad Egresos: " + cant_egresos + " \n";
>>>>>>> d07f4b74bbabef75dc0e6fd38c3df893d73c3268
	}
	
}

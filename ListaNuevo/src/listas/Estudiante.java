package listas;

public class Estudiante implements Comparable {

	String cedula, barrio, nombre;
	Double notaFinal;
	
	public Estudiante(String cedula, String barrio, String nombre, Double notaFinal) {
		
		this.cedula = cedula;
		this.barrio = barrio;
		this.nombre = nombre;
		this.notaFinal = notaFinal;
		
	}

	String getCedula() {
		return cedula;
	}

	String getBarrio() {
		return barrio;
	}

	String getNombre() {
		return nombre;
	}

	Double getNotaFinal() {
		return notaFinal;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
	
}

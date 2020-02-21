package listas;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cursos {

	public static void main(String[] args) {
		
		/*
		 * Clase que ejecuta todo lo de los estudiantes
		 */
		
		DoublyLinkedList<Estudiante> ed = new DoublyLinkedList<Estudiante>();
		
		ed.addStudent(new Estudiante("101", "belén", "albertico", 4.0));
		ed.addStudent(new Estudiante("102", "a", "juan", 1.0));
		ed.addStudent(new Estudiante("103", "b", "luis", 2.0));
		ed.addStudent(new Estudiante("104", "b", "pedro", 5.0));
		
		System.out.println("Estudiantes: ");
		System.out.println(ed.ShowStudent());
		
		System.out.println("Buscar estudiante: ");
		try {
			System.out.println(ed.searchStudent("101"));
		} catch (Exception e) {
			Logger.getLogger(Listas.class.getName()).log(Level.SEVERE, null, e);
		}
		System.out.println();
		System.out.println("Todos los estudintes de un barrio: ");
		try {
			System.out.println(ed.searchBarrio("b"));
		} catch (Exception e) {
			Logger.getLogger(Listas.class.getName()).log(Level.SEVERE, null, e);
		}
		
		DoublyLinkedList<Estudiante> menor = new DoublyLinkedList<Estudiante>();
		menor = ed.listaMenor();
		
		System.out.println("Lista de los estudiantes que perdieron: ");
		System.out.println(menor.ShowStudent());
		
		DoublyLinkedList<Estudiante> mayor = new DoublyLinkedList<Estudiante>();
		mayor = ed.listaMayor();
		
		System.out.println("Lista de los estudiantes que ganaron: ");
		System.out.println(mayor.ShowStudent());
		
	}

}

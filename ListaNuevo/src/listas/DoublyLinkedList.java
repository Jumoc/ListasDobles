/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas;

import org.omg.CORBA.Current;

/**
 *
 * @author s103e28
 */
public class DoublyLinkedList<T extends Comparable> implements Ilist<T> {

	private DoubleNode<T> head;
	private DoubleNode<T> tail;

	public DoublyLinkedList() {
		head = tail = null;
	}

	// agregar datos al inicio
	@Override
	public void add(T d) {

		if (isEmpty()) {
			head = tail = new DoubleNode<>(d);
		} else {
			head = new DoubleNode<>(d, null, head);
			head.getNextNode().setPreviousNode(head);
		}

	}

	@Override
	public void addLast(T d) {
		if (isEmpty()) {
			head = tail = new DoubleNode<>(d);
		} else {
			tail = new DoubleNode<>(d, tail, null);
			tail.getPreviousNode().setNextNode(tail);
		}
	}

	@Override
	public void addOrdered(T d) {

		if (isEmpty() || d.compareTo(head.getData()) == -1) {
			add(d);
			return;
		}
		if (d.compareTo(tail.getData()) == 1) {
			addLast(d);
			return;
		}

		DoubleNode<T> current = head.getNextNode();
		DoubleNode<T> newNode;

		while (current.getData().compareTo(d) == -1) {
			current = current.getNextNode();
		}

		newNode = new DoubleNode<>(d, current.getPreviousNode(), current);
		current.getPreviousNode().setNextNode(newNode);
		current.setPreviousNode(newNode);
	}

	@Override
	/**
	 * Borrar el primer nodo
	 */
	public void delete() throws Exception {
		if (isEmpty()) {
			throw new Exception("Lista ya esta vacÃ­a");
		} else if (head == tail) {
			head = tail = null;
		} else {
			head.getNextNode().setPreviousNode(null);
			head = head.getNextNode();
		}
	}

	@Override
	public void deleteLast() throws Exception {
		if (isEmpty()) {
			throw new Exception("Lista ya esta vacÃ­a");
		} else if (head == tail) {
			head = tail = null;
		} else {
			tail.getPreviousNode().setNextNode(null);
			tail = tail.getPreviousNode();
		}
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public String showData() {
		String data = "";
		DoubleNode<T> current = this.head;
		while (current != null) {
			data = data + current.getData() + " ";
			current = current.getNextNode();
		}
		return data;
	}

	public String showDataReverse() {
		String data = "";
		DoubleNode<T> current = this.tail;
		while (current != null) {
			data += current.getData() + " ";
			current = current.getPreviousNode();
		}
		return data;
	}

	// Determinar si un dato ingresado por el usuario existe en la lista.
	public boolean searchData(T d) {

		DoubleNode<T> current = this.head;

		while (current != null) {
			// if (current.getData()==d){}
			if (current.getData() == d) {
				// if (current.getData().compareTo(d) == 0) {
				return true;
			}
			current = current.getNextNode();
		}

		return false;

	}

	// Insertar nuevos nodos ordenados de mayor a menor e impedir datos duplicados
	// (si el dato ya se encuentra en la lista, no ingresarlo y lanzar excepción).
	public void addOrderedMayor(T d) throws Exception {

		if (isEmpty() || d.compareTo(head.getData()) == 1) {
			add(d);
			return;
		}

		if (d.compareTo(tail.getData()) == -1) {
			addLast(d);
			return;
		}

		DoubleNode<T> aux = this.head;

		while (aux != null) {

			if (aux.getData() == d) {
				throw new Exception("El dato ya existe en la lista");
			}
			aux = aux.getNextNode();
		}

		DoubleNode<T> current = head.getNextNode();
		DoubleNode<T> newNode;

		while (current.getData().compareTo(d) == 1) {
			current = current.getNextNode();
		}
		newNode = new DoubleNode<>(d, current.getPreviousNode(), current);
		current.getPreviousNode().setNextNode(newNode);
		current.setPreviousNode(newNode);
	}

	// Eliminar un nodo con un dato específico ingresado por el usuario.
	public void deleteAt(T d) {

		DoubleNode<T> current = this.head;

		if (current.getData() == d) {
			current.getNextNode().setPreviousNode(null);
			this.head = current.getNextNode();
		} else if (this.tail.getData() == d) {
			this.tail.getPreviousNode().setNextNode(null);
		} else {

			while (current.getNextNode().getNextNode() != null) {

				if (current.getNextNode().getData() == d) {
					current.getNextNode().getNextNode().setPreviousNode(current);
					current.setNextNode(current.getNextNode().getNextNode());
				}

				current = current.getNextNode();

			}

		}
	}

	// Insertar nuevos nodos después de un dato especificado por el usuario (si no
	// se encuentra el dato, lanzar excepción).
	public void addAfter(T d, T b) throws Exception {

		DoubleNode<T> newNode = new DoubleNode<>(b);
		DoubleNode<T> current = this.head;

		while (current != null) {

			if (current.getData() == d) {

				newNode.setNextNode(current.getNextNode());
				newNode.setPreviousNode(current);
				current.setNextNode(newNode);
				return;
			}
			current = current.getNextNode();
		}
		throw new Exception("El dato no existe en la lista");
	}

	// Los estudiantes que aprueban la materia, se agregan a la cabeza de la lista,
	// desplazando los anteriores estudiantes a la derecha.
	// Los estudiantes que reprueban la materia, se agregan al final de la lista.
	public void addStudent(Estudiante e) {

		if (e.getNotaFinal() >= 3) {
			add((T) e);
		} else {
			addLast((T) e);
		}
	}

	// Permitir mostrar la lista de estudiantes con todos sus datos desde la cabeza
	// a la cola.
	public String ShowStudent() {

		String data = "";
		DoubleNode<Estudiante> current = (DoubleNode<Estudiante>) this.head;
		while (current != null) {
			data += current.getData().getCedula() + " " + current.getData().getBarrio() + " "
					+ current.getData().getNombre() + " " + current.getData().getNotaFinal() + '\n';

			current = current.getNextNode();
		}
		return data;
	}

	//Permitir buscar un estudiante por su código y mostrar todos sus datos, si no se encuentra el estudiante, lanzar excepción.
	public String searchStudent(String d) throws Exception {
		String data = "";

		DoubleNode<Estudiante> current = (DoubleNode<Estudiante>) this.head;

		while (current != null) {
			if (current.getData().getCedula() == d) {
				data = current.getData().getCedula() + " " + current.getData().getBarrio() + " "
						+ current.getData().getNombre() + " " + current.getData().getNotaFinal();
				return data;
			}

			current = current.getNextNode();
		}

		throw new Exception("El estudiante no existe");

	}

	//Mostrar el nombre de todos los estudiantes que pertenecen a un mismo barrio ingresado por el usuario, sino existe el barrio, lanzar excepción.
	public String searchBarrio(String d) throws Exception {
		String data = "";
		DoubleNode<Estudiante> current = (DoubleNode<Estudiante>) this.head;

		while (current != null) {
			if (current.getData().getBarrio() == d) {
				data += current.getData().getCedula() + " " + current.getData().getBarrio() + " "
						+ current.getData().getNombre() + " " + current.getData().getNotaFinal() + '\n';
			}
			current = current.getNextNode();
		}
		if (data.equals("")) {
			throw new Exception("El estudiante no existe");
		} else {
			return data;
		}
	}

	//Crear una nueva lista doble con los estudiantes que han aprobado la materia.
	public DoublyLinkedList<Estudiante> listaMayor() {

		DoubleNode<Estudiante> current = (DoubleNode<Estudiante>) this.head;
		DoublyLinkedList<Estudiante> n = new DoublyLinkedList<>();

		while (current != null) {

			if (current.getData().getNotaFinal() >= 3) {
				n.addStudent(current.getData());
			}
			current = current.getNextNode();
		}
		return n;
	}

	//Crear una nueva lista doble con los estudiantes que han reprobado la materia.
	public DoublyLinkedList<Estudiante> listaMenor() {

		DoubleNode<Estudiante> current = (DoubleNode<Estudiante>) this.head;
		DoublyLinkedList<Estudiante> n = new DoublyLinkedList<>();

		while (current != null) {

			if (current.getData().getNotaFinal() <= 3) {
				n.addStudent(current.getData());
			}
			current = current.getNextNode();
		}
		return n;
	}
}

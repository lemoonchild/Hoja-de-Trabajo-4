package Model.Stack;

import Model.DoubleLinkedList;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Stack con implementación DoubleLinkedList  
 */

public class StackDoubleLinkedList<T> implements IStack<T> {

	private DoubleLinkedList<T> listadouble;
	
	public StackDoubleLinkedList() {

		listadouble = new DoubleLinkedList<T>();

	}
	
	@Override
	public int count() {
		return listadouble.Count();
	}

	@Override
	public boolean isEmpty() {
		return listadouble.IsEmpty();
	}

	@Override
	public void push(T value) {
		listadouble.InsertAtStart(value);
	}

	@Override
	public T pull() {
		return listadouble.DeleteAtStart();
	}

	@Override
	public T peek() {
		return listadouble.Get(0);
	}

	
}
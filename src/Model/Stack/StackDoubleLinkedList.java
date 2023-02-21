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

	private DoubleLinkedList<T> listaInterna;
	
	public StackDoubleLinkedList() {

		listaInterna = new DoubleLinkedList<T>();

	}
	
	@Override
	public int count() {
		return listaInterna.Count();
	}

	@Override
	public boolean isEmpty() {
		return listaInterna.IsEmpty();
	}

	@Override
	public void push(T value) {
		listaInterna.InsertAtStart(value);
	}

	@Override
	public T pull() {
		return listaInterna.DeleteAtStart();
	}

	@Override
	public T peek() {
		return listaInterna.Get(0);
	}

	
}
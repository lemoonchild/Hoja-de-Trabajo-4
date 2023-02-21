package Model.Stack;

import Model.SingleLinkedList;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Stack con implementación SingleLinkedList  
 */

public class StackSingleLinkedList<T> implements IStack<T> {

	private SingleLinkedList<T> listaInterna;
	
	public StackSingleLinkedList(){

		listaInterna = new SingleLinkedList<T>();

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
		return listaInterna.Delete(0);
	}

	@Override
	public T peek() {
		return listaInterna.Get(0);
	}

}
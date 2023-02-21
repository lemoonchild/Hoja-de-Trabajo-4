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

	private SingleLinkedList<T> listasingle;
	
	public StackSingleLinkedList(){

		listasingle = new SingleLinkedList<T>();

	}
	
	@Override
	public int count() {
		return listasingle.Count();
	}

	@Override
	public boolean isEmpty() {
		return listasingle.IsEmpty();
	}

	@Override
	public void push(T value) {
		listasingle.InsertAtStart(value);
	}

	@Override
	public T pull() {
		return listasingle.Delete(0);
	}

	@Override
	public T peek() {
		return listasingle.Get(0);
	}

}
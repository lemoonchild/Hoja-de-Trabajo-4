package Model.Stack;


import Model.AbstractList;
import Model.AbstractStack;
import Model.ListFactory;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Stack con implementación DoubleLinkedList  
 */

public class StackList<T> extends AbstractStack<T> {

	private AbstractList<T> ListaAbstracta;
	
	public StackList(String typeList) {

		ListFactory<T> listfactory = new ListFactory<>();
		ListaAbstracta = listfactory.getInstance(typeList); 

	}
	
	@Override
	public int count() {
		return ListaAbstracta.Count();
	}

	@Override
	public boolean isEmpty() {
		return ListaAbstracta.IsEmpty();
	}

	@Override
	public void push(T value) {
		ListaAbstracta.InsertAtStart(value);
	}

	@Override
	public T pull() {
		return ListaAbstracta.DeleteAtStart();
	}

	@Override
	public T peek() {
		return ListaAbstracta.Get(0);
	}

	
}
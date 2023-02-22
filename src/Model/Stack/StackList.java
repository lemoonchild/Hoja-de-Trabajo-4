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
	/**
	 * Metodo que cuenta la cantidad de elementos que hay dentro de la pila
	 * @return Cantidad de elementos dentro de la pila
	 */
	@Override
	public int count() {
		return ListaAbstracta.Count();
	}
	/**
	 * Metodo que verifica si la pila se encuentra vacia
	 * @return true si la pila esta vacia
	 * @return false si la pila no esta vacia
	 */
	@Override
	public boolean isEmpty() {
		return ListaAbstracta.IsEmpty();
	}
	/**
	 * Metodo que agrega a la pila un valor del indice 0
	 * @param value valor para agregar dentro de la pila
	 */
	@Override
	public void push(T value) {
		ListaAbstracta.InsertAtStart(value);
	}
	/**
	 * Metodo que remueve de la pila un valor del indice 0
	 * @return valor eliminado de la pila
	 */
	@Override
	public T pull() {
		return ListaAbstracta.DeleteAtStart();
	}

	/**
	 * Metodo que obtiene del Stack el valor del indice 0
	 * @return
	 */
	@Override
	public T peek() {
		return ListaAbstracta.Get(0);
	}

	
}
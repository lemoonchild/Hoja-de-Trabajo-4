package Model;

/**
 * @author MAAG
 * Tomado del reposotorio de la sección 10 creado por Moises Alonso 
 * Hoja de Trabajo #4
 */

public class Node<T> {
	private T value;
	private Node<T> next;
	
	public Node(T value) {
		this.setValue(value);
	}
	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	/**
	 * @return the next
	 */
	public Node<T> getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	
}

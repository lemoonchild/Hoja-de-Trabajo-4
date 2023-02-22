package Model.Stack;


/**
 * @author MAAG
 * Tomado del reposotorio de la sección 10 creado por Moises Alonso 
 * Hoja de Trabajo #4
 */

public interface IStack<T> {

	int count();
	
	boolean isEmpty();
	
	void push(T value);
	
	T pull();
	
	T peek();

}

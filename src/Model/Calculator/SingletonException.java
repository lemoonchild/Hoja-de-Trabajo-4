package Model.Calculator;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Singleton Exception 
 */

public class SingletonException extends RuntimeException {
	
	
	/**
	 * Clase Exception para singleton
	 */
	public SingletonException(){
		super();
	}
	/**
	 * Clase Exception para singleton
	 */
	public SingletonException(String s){
		super(s);
	}
}
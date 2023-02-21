package Model.Calculator;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Singleton Exception 
 */

public class SingletonException extends RuntimeException {
	
	//new exception type for singleton classes
	public SingletonException(){
		super();
	}
	
	public SingletonException(String s){
		super(s);
	}
}
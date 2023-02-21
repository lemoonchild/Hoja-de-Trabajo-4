package Model.Calculator;

import Model.Stack.IStack;

/**
 * @author Seccion 10
 * Tomado del reposotorio de la sección 10 creado por Moises Alonso 
 * Hoja de Trabajo #4
 */

 public interface IPostfixCalculator {
	
	boolean isOneItem(IStack operandos);
	
	int suma(int a, int b);
	
	int resta(int a, int b);
	
	int multiplicacion(int a, int b);
	
	int division(int a, int b);
	
	boolean isOperator(String item);
	
	java.util.ArrayList<String> getItems(String _expresion);
}




package Model.Calculator;
import java.util.ArrayList;
import java.util.Arrays;

import Model.Stack.IStack;

/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4 
 * Clase tomada de la Hoja de Trabajo #2 - Grupo 7
 * Propósito: Operaciones que realiza la calculadora postfix 
 */


public class PostfixCalculator<T> implements IPostfixCalculator{

    private static PostfixCalculator<Integer> _theOnlyCalculator;
    private static boolean oneCalculator = false; 
    
    private PostfixCalculator() throws SingletonException{
        oneCalculator = true; 
    }
    
    public static PostfixCalculator<Integer> getInstance() {
		if (oneCalculator) {
			return _theOnlyCalculator;
		} else {
			_theOnlyCalculator = new PostfixCalculator<Integer>();
			return _theOnlyCalculator;
		}
	}

    /**
     * Metodo que verifica si queda un dato en el Stack 
     * @param operandos elementos dentro de la pila
     * @return true si solo se encuentra un elemento
     * @return false no se encuentra solo un elemento 
     */
    @Override
    public boolean isOneItem(IStack operandos) {

        if (operandos.count() == 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que realiza la suma de las operaciones Postfix
     * @return Suma de dos numeros 
     */
    @Override
    public int suma(int a, int b) {
        return a + b;
    }

    /**
     * Metodo que realiza la resta de las operaciones Postfix
     * @return Resta de dos numeros 
     */
    @Override
    public int resta(int a, int b) {
        return a - b;
    }

    /**
     * Metodo que realiza la multiplicacion de las operaciones Postfix
     * @return Multiplicacion de dos numeros 
     */
    @Override
    public int multiplicacion(int a, int b) {
        return a * b;
    }

    /**
     * Metodo que realiza la division de las operaciones Postfix
     * @return Division de dos numeros 
     */
    @Override
    public int division(int a, int b) {
        return a/b;
    }

    /**
     * Metodo que verifica si un elemento del Stack es operador o no 
     * @return true si es operador 
     * @return false si no es operador 
     */
    @Override
    public boolean isOperator(String item) {
        
        if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/")){
            return true;
        } else {
            return false; 
        }
    }

    /**
     * Metodo que guarda en una lista cada elemento de la expresión omitiendo espacios. 
     * @param _expresion Elementos de tipo String de la expresion 
     * @return Lista convertida en un ArrayList 
     */
    @Override
    public ArrayList<String> getItems(String _expresion) {

        String[] elements = _expresion.split(""); 
        
        return new ArrayList<String>(Arrays.asList(elements)); 
    }

}
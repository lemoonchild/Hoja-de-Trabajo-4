package Controller;
import java.util.ArrayList;
import java.util.Stack;

import Model.Calculator.PostfixCalculator;
import Model.Calculator.SingletonException;
import Model.Stack.IStack;

/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Clase tomada de la Hoja de Trabajo #2 - Grupo 7
 * Propósito: Realizar los cálculos correspondientes de la calculadora según las operaciones presentadas 
 */

public class Calculadora<T> {

    private static Stack<Integer> stack;

    static PostfixCalculator calculadora; 

    static ArrayList<String> operaciones;

    Stack pila = new Stack<T>(); 

    /**
     * Constructor de la calculadora 
     */
    public Calculadora() {

        stack = new Stack<Integer>();

    }

    /**
     * Metodo que guarda en la pila los elememtos del ArrayList y realiza las operaciones cuando encuentra un operando (solo con los primeros dos elementos)
     * @param Firstpostfix Primera linea del documento txt 
     * @return resultado de la pila 
     */
    public static int calculate(String Firstpostfix, IStack<Integer> UserStack){

        try {
            
            calculadora = PostfixCalculator.getInstance(); 
        
            operaciones = calculadora.getItems(Firstpostfix); 
    
            for (int i = 0; i < operaciones.size(); i++) {
    
                String num_op = operaciones.get(i); 
                
                if (calculadora.isOperator(num_op)){
                    
                    int a = UserStack.pull(); 
                    int b = UserStack.pull(); 
    
                    switch(num_op){
    
                        case "+":
                            UserStack.push(calculadora.suma(a, b));
                            break; 
                        case "-" :
                            UserStack.push(calculadora.resta(a, b));
                            break; 
                        case "*": 
                            UserStack.push(calculadora.multiplicacion(a, b));
                            break; 
                        case "/": 
                            UserStack.push(calculadora.division(a, b));
                            break; 
                    }
    
                } else {
                    UserStack.push(Integer.valueOf(num_op));
                }
                
            }
            
        
        } catch (SingletonException e) {
            System.out.println(e.getMessage());
        }
        return UserStack.pull(); 
        

    }
}
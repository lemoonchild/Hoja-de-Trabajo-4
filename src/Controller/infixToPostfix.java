package Controller;
import java.util.ArrayList;

import Model.Stack.StackArrayList;


/**
* @author Harnoor Singh
 * Date: Oct 31, 2017
 * URL: https://github.com/iharnoor/InfixToPostFix-Calculator/blob/master/calc.java 
 */

public class infixToPostfix{
    
    /**
     * Convertidor de Infix a Postfix 
     * @param infix expresion infix leida del txt
     * @return expresion postfix
     */
    public static String converterPostfix(String infix) {

        StackArrayList<String> operator = new StackArrayList<>();

        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < infix.length(); i++) {

            String elementoActual = infix.charAt(i) + "";

            if (esOperando(elementoActual)) {
                
                numberLoop:

                while (i + 1 < infix.length()) {

                    String siguienteElemento = infix.substring(i + 1, i + 2);

                    if (esOperando(siguienteElemento)) {
                        
                        elementoActual += siguienteElemento;
                        i++;

                    } else break numberLoop;
                }

                output.add(elementoActual);

            } else if (esOperador(elementoActual)) {

                if (operator.isEmpty())
                    operator.push(elementoActual);

                else if (jerarquiaOperciones(elementoActual) > jerarquiaOperciones(operator.peek())) {
                    operator.push(elementoActual);

                } else if (jerarquiaOperciones(elementoActual) <= jerarquiaOperciones(operator.peek())) {

                    while (!operator.isEmpty() && jerarquiaOperciones(elementoActual) <= jerarquiaOperciones(operator.peek())) {
                        output.add(operator.pull());
                    }

                    operator.push(elementoActual);

                }
            } else if (Lparentesis(elementoActual)) {

                operator.push(elementoActual);
                continue;

            } else if (Rparentesis(elementoActual)) {

                while (!operator.isEmpty() && !Lparentesis(operator.peek())) {
                    output.add(operator.pull());

                }
                operator.pull();
            }
            if (i == infix.length() - 1) {
                
                while (!operator.isEmpty()) {
                    output.add(operator.pull());
                }
            }
        }

        String cleanInfix = ""; 

        for (String clean : output) {

            cleanInfix += clean; 
            
        }

        return cleanInfix; 

    }

    /**
     * Identifica el parentesis abierto dentro del stack 
     * @param left operadores recibidos del stack 
     * @return verdadero si encuentra un unico elemento "(" dentro del stack 
     */
    private static boolean Lparentesis(String left) {
        return left.length() == 1 && (left.charAt(0) == '(');
    }
    /**
     * Identifica el parentesis cerrado dentro del stack 
     * @param right operadores recibidos del stack 
     * @return verdadero si encuentra un unico elemento ")" dentro del stack 
     */
    private static boolean Rparentesis(String right) {
        return right.length() == 1 && (right.charAt(0) == ')');
    }
    /**
     * Identifica si es operando el elemento del stack ingresado 
     * @param num operadores recibidos del stack 
     * @return verdadero si este es un operando
     */
    public static boolean esOperando(String num) {
        char operand = num.charAt(0);
        return operand >= '0' && operand <= '9' || operand == 'x' && num.length() == 1;
        
    }
    /**
     * Identifica si es operador el elemento del stack ingresado
     * @param operator operadores recibidos del Stack 
     * @return verdadero si este es operador
     */
    public static boolean esOperador(String operator) {
        char sign = operator.charAt(0);
        return operator.length() == 1 && sign == '*' || sign == '-' || sign == '/' || sign == '+' || sign == '%';
    }
    /**
     * Verificará la jerarquía de los operadores 
     * @param string operador de infix 
     * @return numero en orden jerarquico 
     */
    private static int jerarquiaOperciones(String operator) {
        char op = operator.charAt(0);
        return (op == '*' || op == '%' || op == '/') ? 2 : (op == '+' || op == '-') ? 1 : -1;
    }

}



    

  

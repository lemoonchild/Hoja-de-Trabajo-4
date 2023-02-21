package Controller;
import java.util.ArrayList;
import java.util.Stack;


/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Propósito: Convertir las operaciones de infix a postfix según el txt 
 * 
 */

public class infixToPostix{
    
    public static ArrayList<String> converterPostfix(String infix) {

        Stack<String> operator = new Stack<>();
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < infix.length(); i++) {

            String currentChar = infix.charAt(i) + "";

            if (isOperand(currentChar)) {
                
                numberLoop:

                while (i + 1 < infix.length()) {

                    String nextChar = infix.substring(i + 1, i + 2);

                    if (isOperand(nextChar)) {
                        
                        currentChar += nextChar;
                        i++;

                    } else break numberLoop;
                }

                output.add(currentChar);

            } else if (isOperator(currentChar)) {

                if (operator.isEmpty())
                    operator.push(currentChar);

                else if (precedenceLevel(currentChar) > precedenceLevel(operator.peek())) {
                    operator.push(currentChar);

                } else if (precedenceLevel(currentChar) <= precedenceLevel(operator.peek())) {

                    while (!operator.isEmpty() && precedenceLevel(currentChar) <= precedenceLevel(operator.peek())) {
                        output.add(operator.pop());
                    }

                    operator.push(currentChar);

                }
            } else if (isLeftParentheses(currentChar)) {

                operator.push(currentChar);
                continue;

            } else if (isRightParentheses(currentChar)) {

                while (!operator.isEmpty() && !isLeftParentheses(operator.peek())) {
                    output.add(operator.pop());

                }
                operator.pop();
            }
            if (i == infix.length() - 1) {
                
                while (!operator.isEmpty()) {
                    output.add(operator.pop());
                }
            }
        }
        return output;
    }

    /**
     * @param s
     * @return
     */
    private static boolean isLeftParentheses(String left) {
        return left.length() == 1 && (left.charAt(0) == '(');
    }
    /**
     * @param s
     * @return
     */
    private static boolean isRightParentheses(String right) {
        return right.length() == 1 && (right.charAt(0) == ')');
    }
    /**
     * @param op
     * @return
     */
    public static boolean isOperand(String num) {
        char operand = num.charAt(0);
        return operand >= '0' && operand <= '9' || operand == 'x' && num.length() == 1;
        
    }
    /**
     * @param operator
     * @return
     */
    public static boolean isOperator(String operator) {
        char sign = operator.charAt(0);
        return operator.length() == 1 && sign == '*' || sign == '-' || sign == '/' || sign == '+' || sign == '%';
    }
    /**
     * Verificará la jerarquía de los operadores 
     * @param string operador de infix 
     * @return numero en orden jerarquico 
     */
    private static int precedenceLevel(String operator) {
        char op = operator.charAt(0);
        return (op == '*' || op == '%' || op == '/') ? 2 : (op == '+' || op == '-') ? 1 : -1;
    }

}



    

  

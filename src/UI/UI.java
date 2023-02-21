package UI;

import java.util.Scanner;

import Controller.Calculadora;
import Controller.infixToPostfix;
import Model.Stack.StackFactory;

/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Propósito: Interfaz de usuario 
 */


public class UI {
    
    static Scanner sc = new Scanner(System.in); 
    static Controller.readFile readFile = new Controller.readFile();
    static StackFactory factory = new StackFactory(); 
    static Calculadora cal = new Calculadora(); 

    infixToPostfix infix = new infixToPostfix();

    public static void main(String[] args) {
        
        menuPrincipal(); 
        
    }

    public static void menuPrincipal(){
        

String operationsInfix = readFile._readfile("C:\\Users\\ncast\\OneDrive\\Documentos\\Universidad\\Semestres\\Tercer Semestre\\Algoritmos y Estructura de Datos\\Hojas de Trabajo\\Hoja-de-Trabajo-4\\src\\datos.txt");  

        System.out.println("\nExpresion Infija: " + operationsInfix);
        
        String raw_input = infixToPostfix.converterPostfix(operationsInfix).toString();
        
        raw_input=raw_input.replace("[", " ");
        raw_input=raw_input.replace("]", " ");
        raw_input=raw_input.replace(",", " ");
        raw_input=raw_input.replace(" ", " ");

        System.out.println("Expresion Postfija: " + raw_input);

        System.out.println("\nBienvenido a la Calculadora Postfix");
        System.out.println("¿Qué tipo de Stack le gustaría utilizar? Escriba su opcion por favor.");
        System.out.println("1. ArrayList");
        System.out.println("2. Vector");
        System.out.println("3. Single Linked List (solo escribir la palabra single)");
        System.out.println("4. Double Linked List (solo escribir la palabra double)");

        System.out.println();
        String op = sc.next(); 

        
        System.out.println("\nResultado de la operacion: " + Calculadora.calculate(raw_input, factory.getTypeStack(op)));
        

        
    }
}

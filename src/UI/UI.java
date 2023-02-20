import java.util.Scanner;

/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Propósito: Interfaz de usuario 
 */


public class UI {
    
    static Scanner sc = new Scanner(System.in); 
    static readFile readFile = new readFile();

    Calculadora calculadora = new Calculadora(); 

    public static void main(String[] args) {
        
    }

    public void menuPrincipal(){
        
        String postfix = readFile._readfile("datos.txt");

        System.out.println("\nBienvenido a la Calculadora Postfix");
        System.out.println("¿Qué tipo de Stack le gustaría utilizar?");
        System.out.println("1. ArrayList");
        System.out.println("2. List");
        System.out.println("3. Vector");

        System.out.println("\n\tOperación a realizar: " + postfix);

        System.out.println("\tResultado obtenido: " + Calculadora.calculate(postfix) + "\n");

    }
}

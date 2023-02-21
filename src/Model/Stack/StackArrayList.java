package Model.Stack;
import java.util.ArrayList;

/**
 * @author MAAG
 * Stack utilizado del repositorio de la seccion 10 creado por el catedratico Moises Alonso
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Proposito: Stack con implementación ArrayList  
 */

public class StackArrayList<T> implements IStack<T>{

    private ArrayList<T> ourStack;

    public StackArrayList() {
        ourStack = new ArrayList<T>();
    }
    
    /**
     * Metodo que cuenta la cantidad de elementos que hay dentro de la pila 
     * @return Cantidad de elementos dentro de la pila 
     */
    @Override
    public int count() {
        return ourStack.size();
    }

    /**
     * Metodo que verifica si la pila se encuentra vacia 
     * @return true si la pila esta vacia 
     * @return false si la pila no esta vacia 
     */
    @Override
    public boolean isEmpty() {
        return ourStack.isEmpty();
    }

    /**
     * Metodo que agrega a la pila un valor del indice 0
     * @param value valor para agregar dentro de la pila
     */
    @Override
    public void push(T value) {
        ourStack.add(0, value); 
    }

    /**
     * Metodo que remueve de la pila un valor del indice 0 
     * @return valor eliminado de la pila 
     */
    @Override
    public T pull() {
        return ourStack.remove(0);
    }

    /**
     * Metodo que obtiene del Stack el valor del indice 0
     * @return 
     */
    @Override
    public T peek() {
        return ourStack.get(0);
    }
    
}
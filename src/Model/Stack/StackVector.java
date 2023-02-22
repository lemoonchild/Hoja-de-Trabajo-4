package Model.Stack;

import java.util.Vector;

import Model.AbstractStack;
/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Propósito: Stack con implementación Vector 
 */

 public class StackVector<T> extends AbstractStack<T>{

        private Vector<T> myVector;

        public StackVector() {
            myVector = new Vector<T>();
        }
        /**
         * Metodo que cuenta la cantidad de elementos que hay dentro de la pila
         * @return Cantidad de elementos dentro de la pila
         */
        @Override
        public int count() {
            return myVector.size();
        }
        /**
         * Metodo que verifica si la pila se encuentra vacia
         * @return true si la pila esta vacia
         * @return false si la pila no esta vacia
         */
        @Override
        public boolean isEmpty() {
            return myVector.isEmpty();
        }
        /**
         * Metodo que agrega a la pila un valor del indice 0
         * @param value valor para agregar dentro de la pila
         */
        @Override
        public void push(T value) {
            myVector.add(value);
        }
        /**
         * Metodo que remueve de la pila un valor del indice 0
         * @return valor eliminado de la pila
         */
        @Override
        public T pull() {
            return myVector.remove(myVector.size()-1);
        }
        /**
         * Metodo que obtiene del Stack el valor del indice 0
         * @return
         */
        @Override
        public T peek() {
            return myVector.get(myVector.size()-1);
        }
}


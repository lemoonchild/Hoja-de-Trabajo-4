package Model.Stack;

import java.util.Vector;
/**
 * @author 
 * Madeline Castro 22473
 * Hoja de Trabajo #4
 * Propósito: Stack con implementación Vector 
 */

 public class StackVector<T> implements IStack<T>{

        private Vector<T> myVector;
    
        public StackVector() {
            myVector = new Vector<T>();
        }
    
        @Override
        public int count() {
            return myVector.size();
        }
    
        @Override
        public boolean isEmpty() {
            return myVector.isEmpty();
        }
    
        @Override
        public void push(T value) {
            myVector.add(value);
        }
    
        @Override
        public T pull() {
            return myVector.remove(myVector.size()-1);
        }
    
        @Override
        public T peek() {
            return myVector.get(myVector.size()-1);
        }
}


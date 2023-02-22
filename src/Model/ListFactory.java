package Model;

import Model.List.DoubleLinkedList;
import Model.List.SingleLinkedList;



public class ListFactory<T> {
    /**
     * Realizacion del factory para el Stack de Lista
     * @return tipo de Lista a usar dentro del Stack
     */
    public AbstractList<T> getInstance(String userStack){

        AbstractList<T> typeList = null; 
        
        switch(userStack.toLowerCase()){

            case "single": 
                typeList = new SingleLinkedList<>(); 
                break; 

            case "double": 
                typeList = new DoubleLinkedList<>(); 
                break; 
        
            
        }
        return typeList;
    }
}

package Model;

import Model.Stack.IStack;
import Model.Stack.StackArrayList;
import Model.Stack.StackList;
import Model.Stack.StackVector;

public class StackFactory {
    
    /**
     * Realiza la implementacion de Factory para los tipos de Stack
     * @param userStack seleccion de usuario para stack que desea utilizar 
     * @return tipo stack seleccionado por usuario 
     */
    public IStack<Integer> getTypeStack(String userStack){

        IStack<Integer> typeStack; 

        switch(userStack.toLowerCase()){

            case "arraylist": 
                typeStack = new StackArrayList<>();
                break; 
            case "vector": 
                typeStack = new StackVector<>();
                break; 
            case "double": 
                typeStack = new StackList<>(userStack);
                break;
            case "single": 
                typeStack = new StackList<>(userStack);
                break;
            default: 
                typeStack = null; 
                System.out.println("¡Ha ocurrido un error!");
                break; 
        }
        return typeStack;
    }
}


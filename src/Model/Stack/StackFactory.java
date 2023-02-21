package Model.Stack;

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
                typeStack = new StackDoubleLinkedList<>();
                break;
            case "single": 
                typeStack = new StackSingleLinkedList<>();
                break;
            default: 
                typeStack = null; 
                System.out.println("¡Ha ocurrido un error!");
        }
        return typeStack;
    }
}


package Model.Stack;

public class StackFactory {
    
    public IStack<Integer> getTypeStack(String userStack){

        IStack<Integer> typeStack; 

        switch(userStack.toLowerCase()){

            case "arraylist": 
                typeStack = new StackArrayList<>();
                break; 
            
            case "vector": 
                typeStack = new StackVector<>();
                break; 
            case "double linked list": 
                typeStack = new StackDoubleLinkedList<>();
                break;
            case "single linked list": 
                typeStack = new StackSingleLinkedList<>();
                break;
            default: 
                typeStack = null; 
                System.out.println("¡Ha ocurrido un error!");
        }
        return typeStack;
    }
}


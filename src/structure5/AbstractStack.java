package structure5;

import structure5.AbstractLinear;
import structure5.Stack;

public abstract class AbstractStack extends AbstractLinear implements Stack {

   public void push(Object item) {
      this.add(item);
   }

   public Object pop() {
      return this.remove();
   }

   @Deprecated
   public Object getFirst() {
      return this.get();
   }

   public Object peek() {
      return this.get();
   }
}

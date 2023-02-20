package structure5;

import structure5.AbstractLinear;
import structure5.Queue;

public abstract class AbstractQueue extends AbstractLinear implements Queue {

   public void enqueue(Object item) {
      this.add(item);
   }

   public Object dequeue() {
      return this.remove();
   }

   public Object getFirst() {
      return this.get();
   }

   public Object peek() {
      return this.get();
   }
}

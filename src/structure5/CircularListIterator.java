package structure5;

import structure5.AbstractIterator;
import structure5.Node;

class CircularListIterator extends AbstractIterator {

   protected Node tail;
   protected Node current;


   public CircularListIterator(Node t) {
      this.tail = t;
      this.reset();
   }

   public void reset() {
      if(this.tail == null) {
         this.current = null;
      } else {
         this.current = this.tail.next();
      }

   }

   public boolean hasNext() {
      return this.current != null;
   }

   public Object next() {
      Object result = this.current.value();
      if(this.current == this.tail) {
         this.current = null;
      } else {
         this.current = this.current.next();
      }

      return result;
   }

   public Object get() {
      return this.current.value();
   }
}

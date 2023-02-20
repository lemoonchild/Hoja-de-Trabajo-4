package structure5;

import structure5.AbstractIterator;
import structure5.Node;

class SinglyLinkedListIterator extends AbstractIterator {

   protected Node current;
   protected Node head;


   public SinglyLinkedListIterator(Node t) {
      this.head = t;
      this.reset();
   }

   public void reset() {
      this.current = this.head;
   }

   public boolean hasNext() {
      return this.current != null;
   }

   public Object next() {
      Object temp = this.current.value();
      this.current = this.current.next();
      return temp;
   }

   public Object get() {
      return this.current.value();
   }
}

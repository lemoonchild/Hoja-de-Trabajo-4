package structure5;

import structure5.AbstractIterator;
import structure5.DoublyLinkedNode;

public class DoublyLinkedListIterator extends AbstractIterator {

   protected DoublyLinkedNode head;
   protected DoublyLinkedNode tail;
   protected DoublyLinkedNode current;


   public DoublyLinkedListIterator(DoublyLinkedNode h) {
      this.head = h;
      this.tail = null;
      this.reset();
   }

   public DoublyLinkedListIterator(DoublyLinkedNode headDummy, DoublyLinkedNode tailDummy) {
      this.head = headDummy.next();
      this.tail = tailDummy;
      this.reset();
   }

   public void reset() {
      this.current = this.head;
   }

   public boolean hasNext() {
      return this.current != this.tail;
   }

   public Object next() {
      Object result = this.current.value();
      this.current = this.current.next();
      return result;
   }

   public Object get() {
      return this.current.value();
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractStack;
import structure5.List;
import structure5.SinglyLinkedList;
import structure5.Stack;

public class StackList extends AbstractStack implements Stack {

   protected List data = new SinglyLinkedList();


   public void clear() {
      this.data.clear();
   }

   public boolean empty() {
      return this.data.isEmpty();
   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public Object get() {
      return this.data.getFirst();
   }

   public void add(Object value) {
      this.data.addFirst(value);
   }

   public Object remove() {
      return this.data.removeFirst();
   }

   public int size() {
      return this.data.size();
   }

   public String toString() {
      return "<StackList: " + this.data + ">";
   }
}

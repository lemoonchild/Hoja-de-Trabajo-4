package structure5;

import java.util.Iterator;
import structure5.AbstractQueue;
import structure5.CircularList;
import structure5.List;
import structure5.Queue;

public class QueueList extends AbstractQueue implements Queue {

   protected List data = new CircularList();


   public void add(Object value) {
      this.data.addLast(value);
   }

   public Object remove() {
      return this.data.removeFirst();
   }

   public Object get() {
      return this.data.getFirst();
   }

   public int size() {
      return this.data.size();
   }

   public void clear() {
      this.data.clear();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<QueueList:");
      Iterator li = this.data.iterator();

      while(li.hasNext()) {
         s.append(" " + li.next());
      }

      s.append(">");
      return s.toString();
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractQueue;
import structure5.Queue;
import structure5.Vector;

public class QueueVector extends AbstractQueue implements Queue {

   protected Vector data;


   public QueueVector() {
      this.data = new Vector();
   }

   public QueueVector(int size) {
      this.data = new Vector(size);
   }

   public void add(Object value) {
      this.data.add(value);
   }

   public Object remove() {
      return this.data.remove(0);
   }

   public Object get() {
      return this.data.get(0);
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
      s.append("<QueueArray:");

      for(int i = 0; i < this.data.size(); ++i) {
         s.append(" " + this.data.get(i));
      }

      s.append(">");
      return s.toString();
   }
}

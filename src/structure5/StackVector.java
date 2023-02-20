package structure5;

import java.util.Iterator;
import structure5.AbstractStack;
import structure5.Stack;
import structure5.Vector;

public class StackVector extends AbstractStack implements Stack {

   protected Vector data;


   public StackVector() {
      this.data = new Vector();
   }

   public StackVector(int size) {
      this.data = new Vector(size);
   }

   public void add(Object item) {
      this.data.add(item);
   }

   public Object remove() {
      return this.data.remove(this.size() - 1);
   }

   public Object get() {
      return this.data.get(this.size() - 1);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public int size() {
      return this.data.size();
   }

   public void clear() {
      this.data.clear();
   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("<StackVector:");

      for(int i = this.data.size() - 1; i >= 0; --i) {
         sb.append(" " + i);
      }

      return sb.toString() + ">";
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractStack;
import structure5.ArrayIterator;
import structure5.Assert;
import structure5.Stack;

public class StackArray extends AbstractStack implements Stack {

   protected int top;
   protected Object[] data;


   public StackArray(int size) {
      this.data = new Object[size];
      this.clear();
   }

   public void clear() {
      this.top = -1;
   }

   public void add(Object item) {
      Assert.pre(!this.isFull(), "Stack is not full.");
      ++this.top;
      this.data[this.top] = item;
   }

   public Object remove() {
      Assert.pre(!this.isEmpty(), "Stack is not empty.");
      Object result = this.data[this.top];
      this.data[this.top] = null;
      --this.top;
      return result;
   }

   public Object get() {
      Assert.pre(!this.isEmpty(), "Stack is not empty.");
      return this.data[this.top];
   }

   public Iterator iterator() {
      return new ArrayIterator(this.data, 0, this.size());
   }

   public int size() {
      return this.top + 1;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean isFull() {
      return this.top == this.data.length - 1;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("<StackArray: ");

      for(int i = this.top; i >= 0; --i) {
         sb.append(" " + this.data[i]);
      }

      sb.append(">");
      return sb.toString();
   }
}

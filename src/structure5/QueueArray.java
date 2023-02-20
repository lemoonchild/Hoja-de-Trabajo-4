package structure5;

import java.util.Iterator;
import structure5.AbstractQueue;
import structure5.ArrayIterator;
import structure5.Assert;
import structure5.Queue;

public class QueueArray extends AbstractQueue implements Queue {

   protected Object[] data;
   protected int head;
   protected int count;


   public QueueArray(int size) {
      this.data = new Object[size];
      this.head = 0;
      this.count = 0;
   }

   public void add(Object value) {
      Assert.pre(!this.isFull(), "Queue is not full.");
      int tail = (this.head + this.count) % this.data.length;
      this.data[tail] = value;
      ++this.count;
   }

   public Object remove() {
      Assert.pre(!this.isEmpty(), "The queue is not empty.");
      Object value = this.data[this.head];
      this.head = (this.head + 1) % this.data.length;
      --this.count;
      return value;
   }

   public Object get() {
      Assert.pre(!this.isEmpty(), "The queue is not empty.");
      return this.data[this.head];
   }

   public int size() {
      return this.count;
   }

   public void clear() {
      this.count = 0;
      this.head = 0;
   }

   public boolean isFull() {
      return this.count == this.data.length;
   }

   public boolean isEmpty() {
      return this.count == 0;
   }

   public Iterator iterator() {
      return new ArrayIterator(this.data, this.head, this.count);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<QueueArray:");
      int i = 0;

      for(int l = this.head; i < this.count; l = (l + 1) % this.data.length) {
         s.append(" " + this.data[l]);
         ++i;
      }

      s.append(">");
      return s.toString();
   }
}

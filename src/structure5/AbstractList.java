package structure5;

import structure5.AbstractStructure;
import structure5.List;

public abstract class AbstractList extends AbstractStructure implements List {

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void addFirst(Object value) {
      this.add(0, value);
   }

   public void addLast(Object value) {
      this.add(this.size(), value);
   }

   public Object getFirst() {
      return this.get(0);
   }

   public Object getLast() {
      return this.get(this.size() - 1);
   }

   public Object removeFirst() {
      return this.remove(0);
   }

   public Object removeLast() {
      return this.remove(this.size() - 1);
   }

   public void add(Object value) {
      this.addLast(value);
   }

   public Object remove() {
      return this.removeLast();
   }

   public Object get() {
      return this.getLast();
   }

   public boolean contains(Object value) {
      return -1 != this.indexOf(value);
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractList;
import structure5.Assert;
import structure5.CircularListIterator;
import structure5.Node;

public class CircularList extends AbstractList {

   protected Node tail = null;
   protected int count = 0;


   public void add(Object value) {
      this.addFirst(value);
   }

   public void addFirst(Object value) {
      Node temp = new Node(value);
      if(this.tail == null) {
         this.tail = temp;
         this.tail.setNext(this.tail);
      } else {
         temp.setNext(this.tail.next());
         this.tail.setNext(temp);
      }

      ++this.count;
   }

   public void addLast(Object value) {
      this.addFirst(value);
      this.tail = this.tail.next();
   }

   public Object getFirst() {
      return this.tail.next().value();
   }

   public Object getLast() {
      return this.tail.value();
   }

   public Object removeFirst() {
      Node temp = this.tail.next();
      if(this.tail == this.tail.next()) {
         this.tail = null;
      } else {
         this.tail.setNext(temp.next());
         temp.setNext((Node)null);
      }

      --this.count;
      return temp.value();
   }

   public Object removeLast() {
      Assert.pre(!this.isEmpty(), "list is not empty.");

      Node finger;
      for(finger = this.tail; finger.next() != this.tail; finger = finger.next()) {
         ;
      }

      Node temp = this.tail;
      if(finger == this.tail) {
         this.tail = null;
      } else {
         finger.setNext(this.tail.next());
         this.tail = finger;
      }

      --this.count;
      return temp.value();
   }

   public boolean contains(Object value) {
      if(this.tail == null) {
         return false;
      } else {
         Node finger;
         for(finger = this.tail.next(); finger != this.tail && !finger.value().equals(value); finger = finger.next()) {
            ;
         }

         return finger.value().equals(value);
      }
   }

   public Object remove(Object value) {
      if(this.tail == null) {
         return null;
      } else {
         Node finger = this.tail.next();
         Node previous = this.tail;

         for(int compares = 0; compares < this.count && !finger.value().equals(value); ++compares) {
            previous = finger;
            finger = finger.next();
         }

         if(finger.value().equals(value)) {
            if(this.tail == this.tail.next()) {
               this.tail = null;
            } else {
               if(finger == this.tail) {
                  this.tail = previous;
               }

               previous.setNext(previous.next().next());
            }

            finger.setNext((Node)null);
            --this.count;
            return finger.value();
         } else {
            return null;
         }
      }
   }

   public int size() {
      return this.count;
   }

   public Object get(int i) {
      if(i >= this.size()) {
         return null;
      } else {
         Node finger;
         for(finger = this.tail.next(); i > 0; --i) {
            finger = finger.next();
         }

         return finger.value();
      }
   }

   protected Node getTail() {
      return this.tail;
   }

   public Object set(int i, Object o) {
      if(i >= this.size()) {
         return null;
      } else {
         Node finger;
         for(finger = this.tail.next(); i > 0; --i) {
            finger = finger.next();
         }

         Object result = finger.value();
         finger.setValue(o);
         return result;
      }
   }

   public void add(int i, Object o) {
      Assert.pre(0 <= i && i <= this.size(), "Index in range.");
      if(i == 0) {
         this.addFirst(o);
      } else if(i == this.size()) {
         this.addLast(o);
      } else {
         Node previous = this.tail;

         Node next;
         for(next = this.tail.next(); i > 0; --i) {
            previous = next;
            next = next.next();
         }

         Node current = new Node(o, next);
         ++this.count;
         previous.setNext(current);
      }

   }

   public Object remove(int i) {
      Assert.pre(0 <= i && i < this.size(), "Index in range.");
      if(i == 0) {
         return this.removeFirst();
      } else if(i == this.size() - 1) {
         return this.removeLast();
      } else {
         Node previous = this.tail;

         Node finger;
         for(finger = this.tail.next(); i > 0; finger = finger.next()) {
            --i;
            previous = finger;
         }

         previous.setNext(finger.next());
         --this.count;
         return finger.value();
      }
   }

   public int indexOf(Object value) {
      int i = 0;

      Node finger;
      for(finger = this.tail.next(); finger != null && !finger.value().equals(value); ++i) {
         if(finger == this.tail) {
            finger = null;
         } else {
            finger = finger.next();
         }
      }

      return finger == null?-1:i;
   }

   public int lastIndexOf(Object value) {
      int result = -1;
      int i = 0;

      for(Node finger = this.tail.next(); finger != null; ++i) {
         if(finger.value().equals(value)) {
            result = i;
         }

         if(finger == this.tail) {
            finger = null;
         } else {
            finger = finger.next();
         }
      }

      return result;
   }

   public Iterator iterator() {
      return new CircularListIterator(this.tail);
   }

   public boolean isEmpty() {
      return this.tail == null;
   }

   public void clear() {
      this.count = 0;
      this.tail = null;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<CircularList:");
      Iterator li = this.iterator();

      while(li.hasNext()) {
         s.append(" " + li.next());
      }

      s.append(">");
      return s.toString();
   }
}

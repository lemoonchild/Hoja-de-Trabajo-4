package structure5;

import java.util.Enumeration;
import java.util.Iterator;
import structure5.AbstractList;
import structure5.Assert;
import structure5.Node;
import structure5.SinglyLinkedListIterator;

public class SinglyLinkedList extends AbstractList {

   protected int count = 0;
   protected Node head = null;


   public void add(Object value) {
      this.addLast(value);
   }

   public void addFirst(Object value) {
      this.head = new Node(value, this.head);
      ++this.count;
   }

   public Object removeFirst() {
      Node temp = this.head;
      this.head = this.head.next();
      --this.count;
      return temp.value();
   }

   public void addLast(Object value) {
      Node temp = new Node(value, (Node)null);
      if(this.head != null) {
         Node finger;
         for(finger = this.head; finger.next() != null; finger = finger.next()) {
            ;
         }

         finger.setNext(temp);
      } else {
         this.head = temp;
      }

      ++this.count;
   }

   public Object removeLast() {
      Node finger = this.head;
      Node previous = null;
      Assert.pre(this.head != null, "List is not empty.");

      while(finger.next() != null) {
         previous = finger;
         finger = finger.next();
      }

      if(previous == null) {
         this.head = null;
      } else {
         previous.setNext((Node)null);
      }

      --this.count;
      return finger.value();
   }

   public Object getFirst() {
      return this.head.value();
   }

   public Object getLast() {
      Node finger = this.head;
      Assert.condition(finger != null, "List is not empty.");

      while(finger != null && finger.next() != null) {
         finger = finger.next();
      }

      return finger.value();
   }

   public boolean contains(Object value) {
      Node finger;
      for(finger = this.head; finger != null && !finger.value().equals(value); finger = finger.next()) {
         ;
      }

      return finger != null;
   }

   public Object remove(Object value) {
      Node finger = this.head;

      Node previous;
      for(previous = null; finger != null && !finger.value().equals(value); finger = finger.next()) {
         previous = finger;
      }

      if(finger != null) {
         if(previous == null) {
            this.head = finger.next();
         } else {
            previous.setNext(finger.next());
         }

         --this.count;
         return finger.value();
      } else {
         return null;
      }
   }

   public int size() {
      return this.count;
   }

   public void clear() {
      this.head = null;
      this.count = 0;
   }

   public Object get(int i) {
      if(i >= this.size()) {
         return null;
      } else {
         Node finger;
         for(finger = this.head; i > 0; --i) {
            finger = finger.next();
         }

         return finger.value();
      }
   }

   public Object set(int i, Object o) {
      if(i >= this.size()) {
         return null;
      } else {
         Node finger;
         for(finger = this.head; i > 0; --i) {
            finger = finger.next();
         }

         Object result = finger.value();
         finger.setValue(o);
         return result;
      }
   }

   public void add(int i, Object o) {
      Assert.pre(0 <= i && i <= this.size(), "Index in range.");
      if(i == this.size()) {
         this.addLast(o);
      } else if(i == 0) {
         this.addFirst(o);
      } else {
         Node previous = null;

         Node finger;
         for(finger = this.head; i > 0; --i) {
            previous = finger;
            finger = finger.next();
         }

         Node current = new Node(o, finger);
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
         Node previous = null;

         Node finger;
         for(finger = this.head; i > 0; --i) {
            previous = finger;
            finger = finger.next();
         }

         previous.setNext(finger.next());
         --this.count;
         return finger.value();
      }
   }

   public int indexOf(Object value) {
      int i = 0;

      Node finger;
      for(finger = this.head; finger != null && !finger.value().equals(value); ++i) {
         finger = finger.next();
      }

      return finger == null?-1:i;
   }

   public int lastIndexOf(Object value) {
      int result = -1;
      int i = 0;

      for(Node finger = this.head; finger != null; ++i) {
         if(finger.value().equals(value)) {
            result = i;
         }

         finger = finger.next();
      }

      return result;
   }

   public Iterator iterator() {
      return new SinglyLinkedListIterator(this.head);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<SinglyLinkedList:");
      Enumeration li = (Enumeration)this.iterator();

      while(li.hasMoreElements()) {
         s.append(" " + li.nextElement());
      }

      s.append(">");
      return s.toString();
   }
}

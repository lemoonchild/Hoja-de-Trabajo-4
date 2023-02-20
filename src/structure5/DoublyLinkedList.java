package structure5;

import java.util.Iterator;
import structure5.AbstractList;
import structure5.Assert;
import structure5.DoublyLinkedListIterator;
import structure5.DoublyLinkedNode;

public class DoublyLinkedList extends AbstractList {

   protected int count = 0;
   protected DoublyLinkedNode head = null;
   protected DoublyLinkedNode tail = null;


   public void add(Object value) {
      this.addFirst(value);
   }

   public void addFirst(Object value) {
      this.head = new DoublyLinkedNode(value, this.head, (DoublyLinkedNode)null);
      if(this.tail == null) {
         this.tail = this.head;
      }

      ++this.count;
   }

   public Object removeFirst() {
      Assert.pre(!this.isEmpty(), "List is not empty.");
      DoublyLinkedNode temp = this.head;
      this.head = this.head.next();
      if(this.head != null) {
         this.head.setPrevious((DoublyLinkedNode)null);
      } else {
         this.tail = null;
      }

      temp.setNext((DoublyLinkedNode)null);
      --this.count;
      return temp.value();
   }

   public void addLast(Object value) {
      this.tail = new DoublyLinkedNode(value, (DoublyLinkedNode)null, this.tail);
      if(this.head == null) {
         this.head = this.tail;
      }

      ++this.count;
   }

   public Object removeLast() {
      Assert.pre(!this.isEmpty(), "List is not empty.");
      DoublyLinkedNode temp = this.tail;
      this.tail = this.tail.previous();
      if(this.tail == null) {
         this.head = null;
      } else {
         this.tail.setNext((DoublyLinkedNode)null);
      }

      --this.count;
      return temp.value();
   }

   public Object getFirst() {
      return this.head.value();
   }

   public Object getLast() {
      return this.tail.value();
   }

   public boolean contains(Object value) {
      DoublyLinkedNode finger;
      for(finger = this.head; finger != null && !finger.value().equals(value); finger = finger.next()) {
         ;
      }

      return finger != null;
   }

   public Object remove(Object value) {
      DoublyLinkedNode finger;
      for(finger = this.head; finger != null && !finger.value().equals(value); finger = finger.next()) {
         ;
      }

      if(finger != null) {
         if(finger.previous() != null) {
            finger.previous().setNext(finger.next());
         } else {
            this.head = finger.next();
         }

         if(finger.next() != null) {
            finger.next().setPrevious(finger.previous());
         } else {
            this.tail = finger.previous();
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

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void clear() {
      this.head = this.tail = null;
      this.count = 0;
   }

   public Object get(int i) {
      if(i >= this.size()) {
         return null;
      } else {
         DoublyLinkedNode finger;
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
         DoublyLinkedNode finger;
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
      if(i == 0) {
         this.addFirst(o);
      } else if(i == this.size()) {
         this.addLast(o);
      } else {
         DoublyLinkedNode before = null;

         DoublyLinkedNode after;
         for(after = this.head; i > 0; --i) {
            before = after;
            after = after.next();
         }

         DoublyLinkedNode current = new DoublyLinkedNode(o, after, before);
         ++this.count;
         before.setNext(current);
         after.setPrevious(current);
      }

   }

   public Object remove(int i) {
      Assert.pre(0 <= i && i < this.size(), "Index in range.");
      if(i == 0) {
         return this.removeFirst();
      } else if(i == this.size() - 1) {
         return this.removeLast();
      } else {
         DoublyLinkedNode previous = null;

         DoublyLinkedNode finger;
         for(finger = this.head; i > 0; --i) {
            previous = finger;
            finger = finger.next();
         }

         previous.setNext(finger.next());
         finger.next().setPrevious(previous);
         --this.count;
         return finger.value();
      }
   }

   public int indexOf(Object value) {
      int i = 0;

      DoublyLinkedNode finger;
      for(finger = this.head; finger != null && !finger.value().equals(value); ++i) {
         finger = finger.next();
      }

      return finger == null?-1:i;
   }

   public int lastIndexOf(Object value) {
      int i = this.size() - 1;

      DoublyLinkedNode finger;
      for(finger = this.tail; finger != null && !finger.value().equals(value); --i) {
         finger = finger.previous();
      }

      return finger == null?-1:i;
   }

   public Iterator iterator() {
      return new DoublyLinkedListIterator(this.head);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<DoublyLinkedList:");
      Iterator li = this.iterator();

      while(li.hasNext()) {
         s.append(" " + li.next());
      }

      s.append(">");
      return s.toString();
   }
}

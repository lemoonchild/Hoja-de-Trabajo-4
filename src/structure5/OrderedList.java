package structure5;

import java.util.Comparator;
import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.NaturalComparator;
import structure5.Node;
import structure5.OrderedStructure;
import structure5.SinglyLinkedListIterator;

public class OrderedList extends AbstractStructure implements OrderedStructure {

   protected Node data;
   protected int count;
   protected Comparator ordering;


   public OrderedList() {
      this(new NaturalComparator());
   }

   public OrderedList(Comparator ordering) {
      this.ordering = ordering;
      this.clear();
   }

   public void clear() {
      this.data = null;
      this.count = 0;
   }

   public void add(Comparable value) {
      Node previous = null;

      for(Node finger = this.data; finger != null && this.ordering.compare(finger.value(), value) < 0; finger = finger.next()) {
         previous = finger;
      }

      if(previous == null) {
         this.data = new Node(value, this.data);
      } else {
         previous.setNext(new Node(value, previous.next()));
      }

      ++this.count;
   }

   public boolean contains(Comparable value) {
      Node finger;
      for(finger = this.data; finger != null && this.ordering.compare(finger.value(), value) < 0; finger = finger.next()) {
         ;
      }

      return finger != null && value.equals(finger.value());
   }

   public Comparable remove(Comparable value) {
      Node previous = null;

      Node finger;
      for(finger = this.data; finger != null && this.ordering.compare(finger.value(), value) < 0; finger = finger.next()) {
         previous = finger;
      }

      if(finger != null && value.equals(finger.value())) {
         if(previous == null) {
            this.data = finger.next();
         } else {
            previous.setNext(finger.next());
         }

         --this.count;
         return (Comparable)finger.value();
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

   public Iterator iterator() {
      return new SinglyLinkedListIterator(this.data);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<OrderedList:");
      Iterator si = this.iterator();

      while(si.hasNext()) {
         s.append(" " + si.next());
      }

      s.append(">");
      return s.toString();
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractSet;
import structure5.List;
import structure5.SinglyLinkedList;
import structure5.Structure;

public class SetList extends AbstractSet {

   protected List data = new SinglyLinkedList();


   public void clear() {
      this.data = new SinglyLinkedList();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public void add(Object e) {
      if(!this.data.contains(e)) {
         this.data.add(e);
      }

   }

   public Object remove(Object e) {
      return this.data.remove(e);
   }

   public boolean contains(Object e) {
      return this.data.contains(e);
   }

   public boolean containsAll(Structure other) {
      Iterator myElements = this.data.iterator();

      do {
         if(!myElements.hasNext()) {
            return true;
         }
      } while(other.contains(myElements.next()));

      return false;
   }

   public Object clone() {
      SetList result = new SetList();
      Iterator myElements = this.iterator();

      while(myElements.hasNext()) {
         result.add(myElements.next());
      }

      return result;
   }

   public void addAll(Structure other) {
      Iterator yourElements = other.iterator();

      while(yourElements.hasNext()) {
         this.add(yourElements.next());
      }

   }

   public void retainAll(Structure other) {
      SetList temp = new SetList();
      Iterator myElements = this.data.iterator();

      while(myElements.hasNext()) {
         Object v = myElements.next();
         if(other.contains(v)) {
            temp.add(v);
         }
      }

      this.clear();
      this.addAll(temp);
   }

   public void removeAll(Structure other) {
      Iterator yourElements = other.iterator();

      while(yourElements.hasNext()) {
         this.remove(yourElements.next());
      }

   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public int size() {
      return this.data.size();
   }

   public String toString() {
      return "<SetList: " + this.data + ">";
   }
}

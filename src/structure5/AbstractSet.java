package structure5;

import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.Set;
import structure5.SinglyLinkedList;
import structure5.Structure;

public abstract class AbstractSet extends AbstractStructure implements Set {

   public void addAll(Structure other) {
      Iterator i = other.iterator();

      while(i.hasNext()) {
         this.add(i.next());
      }

   }

   public boolean containsAll(Structure other) {
      Iterator i = other.iterator();

      do {
         if(!i.hasNext()) {
            return true;
         }
      } while(this.contains(i.next()));

      return false;
   }

   public void removeAll(Structure other) {
      Iterator i = other.iterator();

      while(i.hasNext()) {
         this.remove(i.next());
      }

   }

   public void retainAll(Structure other) {
      SinglyLinkedList drop = new SinglyLinkedList();
      Iterator i = other.iterator();

      while(i.hasNext()) {
         Object o = i.next();
         if(!other.contains(o)) {
            drop.add(o);
         }
      }

      while(!drop.isEmpty()) {
         this.remove(drop.removeFirst());
      }

   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.Association;
import structure5.List;
import structure5.SinglyLinkedList;
import structure5.Vector;

class ChainedHashtableIterator extends AbstractIterator {

   protected List data;
   protected AbstractIterator elements;


   public ChainedHashtableIterator(Vector table) {
      int capacity = table.size();
      this.data = new SinglyLinkedList();

      for(int i = 0; i < capacity; ++i) {
         if(table.get(i) != null) {
            List entry = (List)table.get(i);
            Iterator i$ = entry.iterator();

            while(i$.hasNext()) {
               Association a = (Association)i$.next();
               this.data.addFirst(a);
            }
         }
      }

      this.elements = (AbstractIterator)this.data.iterator();
   }

   public void reset() {
      this.elements.reset();
   }

   public boolean hasNext() {
      return this.elements.hasNext();
   }

   public Association next() {
      return (Association)this.elements.next();
   }

   public Association get() {
      return (Association)this.elements.get();
   }
}

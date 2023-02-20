package structure5;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.StructCollection;
import structure5.Structure;

public abstract class AbstractStructure implements Structure {

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Enumeration elements() {
      return (AbstractIterator)this.iterator();
   }

   public boolean contains(Object value) {
      Iterator i = this.iterator();

      do {
         if(!i.hasNext()) {
            return false;
         }
      } while(!i.next().equals(value));

      return true;
   }

   public int hashCode() {
      Iterator i = this.iterator();
      int result = 0;

      while(i.hasNext()) {
         Object o = i.next();
         result *= 31;
         if(o != null) {
            result += o.hashCode();
         }
      }

      return result;
   }

   public Collection values() {
      return new StructCollection(this);
   }
}

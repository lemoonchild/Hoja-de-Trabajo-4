package structure5;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import structure5.Assert;
import structure5.Structure;

public class StructCollection implements Collection {

   protected Structure base;


   public StructCollection(Structure s) {
      this.base = s;
   }

   public boolean add(Object o) {
      this.base.add(o);
      return true;
   }

   public boolean addAll(Collection c) {
      Iterator i = c.iterator();

      boolean result;
      for(result = false; i.hasNext(); result |= this.add(i.next())) {
         ;
      }

      return result;
   }

   public void clear() {
      this.base.clear();
   }

   public boolean contains(Object o) {
      return this.base.contains(o);
   }

   public boolean containsAll(Collection c) {
      boolean contained = true;

      for(Iterator i = c.iterator(); contained && i.hasNext(); contained &= this.contains(i.next())) {
         ;
      }

      return contained;
   }

   public boolean equals(Object o) {
      return this.base.equals(o);
   }

   public int hashCode() {
      return this.base.hashCode();
   }

   public boolean isEmpty() {
      return this.base.isEmpty();
   }

   public Iterator iterator() {
      return this.base.iterator();
   }

   public boolean remove(Object o) {
      Object removed = this.base.remove(o);
      return removed != null;
   }

   public boolean removeAll(Collection c) {
      boolean result = true;

      for(Iterator i = c.iterator(); i.hasNext(); result &= this.remove(i.next())) {
         ;
      }

      return result;
   }

   public boolean retainAll(Collection c) {
      java.util.Vector v = new java.util.Vector();
      Iterator i = this.iterator();
      boolean mustChange = false;

      while(i.hasNext()) {
         Object e = i.next();
         if(c.contains(e)) {
            v.addElement(e);
         } else {
            mustChange = true;
         }
      }

      if(mustChange) {
         this.clear();
         this.addAll(v);
      }

      return mustChange;
   }

   public int size() {
      return this.base.size();
   }

   public Object[] toArray() {
      int size = this.size();
      Object[] result = new Object[size];
      return this.toArray(result);
   }

   public Object[] toArray(Object[] target) {
      int size = this.size();
      if(size > target.length) {
         target = (Object[])((Object[])Array.newInstance(target.getClass().getComponentType(), size));
      }

      Iterator i = this.iterator();

      int pos;
      for(pos = 0; i.hasNext() && pos < size; target[pos++] = i.next()) {
         ;
      }

      Assert.pre(!i.hasNext(), "Collection size() consistent with iterator.");
      if(pos < size) {
         target[pos] = null;
      }

      return target;
   }

   public String toString() {
      return "<StructCollection wrapping " + this.base + ">";
   }
}

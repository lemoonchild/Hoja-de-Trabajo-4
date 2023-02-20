package structure5;

import java.util.Collection;
import java.util.Iterator;
import structure5.AbstractList;
import structure5.Assert;
import structure5.VectorIterator;

public class Vector extends AbstractList implements Cloneable {

   private Object[] elementData;
   protected int elementCount;
   protected int capacityIncrement;
   protected Object initialValue;
   protected static final int defaultCapacity = 10;


   public Vector() {
      this(10);
   }

   public Vector(int initialCapacity) {
      Assert.pre(initialCapacity >= 0, "Capacity must not be negative");
      this.elementData = new Object[initialCapacity];
      this.elementCount = 0;
      this.capacityIncrement = 0;
      this.initialValue = null;
   }

   public Vector(int initialCapacity, int capacityIncr) {
      Assert.pre(initialCapacity >= 0, "Capacity must not be negative.");
      Assert.pre(capacityIncr >= 0, "The capacity increment must be 0, for doubling, or positive for incremental growth.");
      this.elementData = new Object[initialCapacity];
      this.elementCount = 0;
      this.capacityIncrement = capacityIncr;
      this.initialValue = null;
   }

   public Vector(int initialCapacity, int capacityIncr, Object initValue) {
      Assert.pre(initialCapacity >= 0, "Nonnegative capacity.");
      this.capacityIncrement = capacityIncr;
      this.elementData = new Object[initialCapacity];
      this.elementCount = 0;
      this.initialValue = initValue;
   }

   public Vector(Vector that) {
      this(that.values());
   }

   public Vector(Collection c) {
      this(c.size());
      Iterator i = c.iterator();

      while(i.hasNext()) {
         this.add(i.next());
      }

   }

   public void ensureCapacity(int minCapacity) {
      if(this.elementData.length < minCapacity) {
         int newLength = this.elementData.length;
         if(this.capacityIncrement == 0) {
            if(newLength == 0) {
               newLength = 1;
            }

            while(newLength < minCapacity) {
               newLength *= 2;
            }
         } else {
            while(newLength < minCapacity) {
               newLength += this.capacityIncrement;
            }
         }

         Object[] newElementData = new Object[newLength];

         for(int i = 0; i < this.elementCount; ++i) {
            newElementData[i] = this.elementData[i];
         }

         this.elementData = newElementData;
      }

   }

   public void add(Object obj) {
      this.ensureCapacity(this.elementCount + 1);
      this.elementData[this.elementCount] = obj;
      ++this.elementCount;
   }

   public void addElement(Object o) {
      this.add(o);
   }

   public Object remove(Object element) {
      Object result = null;
      int i = this.indexOf(element);
      if(i >= 0) {
         result = this.get(i);
         this.remove(i);
      }

      return result;
   }

   public int capacity() {
      return this.elementData.length;
   }

   public Object clone() {
      Vector copy = null;

      try {
         copy = (Vector)super.clone();
         copy.elementData = (Object[])this.elementData.clone();
      } catch (CloneNotSupportedException var3) {
         Assert.fail("Vector cannot be cloned.");
      }

      return copy;
   }

   public boolean contains(Object elem) {
      for(int i = 0; i < this.elementCount; ++i) {
         if(elem.equals(this.elementData[i])) {
            return true;
         }
      }

      return false;
   }

   public void copyInto(Object[] dest) {
      for(int i = 0; i < this.elementCount; ++i) {
         dest[i] = this.elementData[i];
      }

   }

   public Object elementAt(int index) {
      Assert.pre(0 <= index && index < this.size(), "index is within bounds");
      return this.get(index);
   }

   public Object get(int index) {
      return this.elementData[index];
   }

   public Iterator iterator() {
      return new VectorIterator(this);
   }

   public Object firstElement() {
      return this.get(0);
   }

   public int indexOf(Object elem) {
      return this.indexOf(elem, 0);
   }

   public int indexOf(Object elem, int index) {
      for(int i = index; i < this.elementCount; ++i) {
         if(elem.equals(this.elementData[i])) {
            return i;
         }
      }

      return -1;
   }

   public void insertElementAt(Object obj, int index) {
      this.add(index, obj);
   }

   public void add(int index, Object obj) {
      this.ensureCapacity(this.elementCount + 1);

      for(int i = this.elementCount; i > index; --i) {
         this.elementData[i] = this.elementData[i - 1];
      }

      this.elementData[index] = obj;
      ++this.elementCount;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Object lastElement() {
      return this.get(this.elementCount - 1);
   }

   public int lastIndexOf(Object obj) {
      return this.lastIndexOf(obj, this.elementCount - 1);
   }

   public int lastIndexOf(Object obj, int index) {
      for(int i = index; i >= 0; --i) {
         if(obj.equals(this.elementData[i])) {
            return i;
         }
      }

      return -1;
   }

   public void clear() {
      this.setSize(0);
   }

   public void removeAllElements() {
      this.setSize(0);
   }

   public void removeElementAt(int where) {
      this.remove(where);
   }

   public Object remove(int where) {
      Object result = this.get(where);
      --this.elementCount;

      while(where < this.elementCount) {
         this.elementData[where] = this.elementData[where + 1];
         ++where;
      }

      this.elementData[this.elementCount] = null;
      return result;
   }

   public void setElementAt(Object obj, int index) {
      this.set(index, obj);
   }

   public Object set(int index, Object obj) {
      Assert.pre(0 <= index && index < this.elementCount, "index is within bounds");
      Object previous = this.elementData[index];
      this.elementData[index] = obj;
      return previous;
   }

   public void setSize(int newSize) {
      int i;
      if(newSize < this.elementCount) {
         for(i = newSize; i < this.elementCount; ++i) {
            this.elementData[i] = null;
         }
      } else {
         this.ensureCapacity(newSize);

         for(i = this.elementCount; i < newSize; ++i) {
            this.elementData[i] = this.initialValue;
         }
      }

      this.elementCount = newSize;
   }

   public int size() {
      return this.elementCount;
   }

   public void trimToSize() {
      Object[] newElementData = new Object[this.elementCount];
      this.copyInto(newElementData);
      this.elementData = newElementData;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("<Vector:");

      for(int i = 0; i < this.size(); ++i) {
         sb.append(" " + this.get(i));
      }

      sb.append(">");
      return sb.toString();
   }
}

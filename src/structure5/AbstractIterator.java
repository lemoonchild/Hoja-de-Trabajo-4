package structure5;

import java.util.Enumeration;
import java.util.Iterator;
import structure5.Assert;

public abstract class AbstractIterator implements Enumeration, Iterator, Iterable {

   public abstract void reset();

   public abstract boolean hasNext();

   public abstract Object get();

   @Deprecated
   public final Object value() {
      return this.get();
   }

   public abstract Object next();

   public void remove() {
      Assert.fail("Remove not implemented.");
   }

   public final boolean hasMoreElements() {
      return this.hasNext();
   }

   public final Object nextElement() {
      return this.next();
   }

   public final Iterator iterator() {
      return this;
   }
}

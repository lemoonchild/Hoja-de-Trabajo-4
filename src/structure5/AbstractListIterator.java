package structure5;

import java.util.ListIterator;
import structure5.AbstractIterator;
import structure5.Assert;

public abstract class AbstractListIterator extends AbstractIterator implements ListIterator {

   public abstract Object get();

   public void remove() {
      Assert.fail("remove not implemented.");
   }

   public void set(Object o) {
      Assert.fail("set not implemented.");
   }

   public void add(Object o) {
      Assert.fail("set not implemented.");
   }
}

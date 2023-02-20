package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.Association;

class KeyIterator extends AbstractIterator {

   protected Iterator slave;


   public KeyIterator(Iterator slave) {
      this.slave = (AbstractIterator)slave;
   }

   public void reset() {
      ((AbstractIterator)this.slave).reset();
   }

   public boolean hasNext() {
      return this.slave.hasNext();
   }

   public Object next() {
      Association pair = (Association)this.slave.next();
      return pair.getKey();
   }

   public Object get() {
      Association pair = (Association)((AbstractIterator)this.slave).get();
      return pair.getKey();
   }
}

package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.Association;

class ValueIterator extends AbstractIterator {

   protected AbstractIterator slave;


   public ValueIterator(Iterator slave) {
      this.slave = (AbstractIterator)slave;
   }

   public void reset() {
      this.slave.reset();
   }

   public boolean hasNext() {
      return this.slave.hasNext();
   }

   public Object next() {
      Association pair = (Association)this.slave.next();
      return pair.getValue();
   }

   public Object get() {
      Association pair = (Association)this.slave.get();
      return pair.getValue();
   }
}

package structure5;

import structure5.AbstractIterator;
import structure5.Vector;

class VectorIterator extends AbstractIterator {

   protected Vector theVector;
   protected int current;


   public VectorIterator(Vector v) {
      this.theVector = v;
      this.reset();
   }

   public void reset() {
      this.current = 0;
   }

   public boolean hasNext() {
      return this.current < this.theVector.size();
   }

   public Object get() {
      return this.theVector.get(this.current);
   }

   public Object next() {
      return this.theVector.get(this.current++);
   }
}

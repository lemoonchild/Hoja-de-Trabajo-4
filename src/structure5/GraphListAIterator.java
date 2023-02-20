package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.Edge;

class GraphListAIterator extends AbstractIterator {

   protected AbstractIterator edges;
   protected Object vertex;


   public GraphListAIterator(Iterator i, Object v) {
      this.edges = (AbstractIterator)i;
      this.vertex = v;
   }

   public void reset() {
      this.edges.reset();
   }

   public boolean hasNext() {
      return this.edges.hasNext();
   }

   public Object next() {
      Edge e = (Edge)this.edges.next();
      return this.vertex.equals(e.here())?e.there():e.here();
   }

   public Object get() {
      Edge e = (Edge)this.edges.get();
      return this.vertex.equals(e.here())?e.there():e.here();
   }
}

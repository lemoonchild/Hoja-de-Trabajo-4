package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.DoublyLinkedList;
import structure5.Edge;
import structure5.GraphListVertex;
import structure5.Map;

class GraphListEIterator extends AbstractIterator {

   protected AbstractIterator edges;


   public GraphListEIterator(Map dict) {
      DoublyLinkedList l = new DoublyLinkedList();
      Iterator dictIterator = dict.values().iterator();

      while(dictIterator.hasNext()) {
         GraphListVertex vtx = (GraphListVertex)dictIterator.next();
         Iterator vtxIterator = vtx.adjacentEdges();

         while(vtxIterator.hasNext()) {
            Edge e = (Edge)vtxIterator.next();
            if(vtx.label().equals(e.here())) {
               l.addLast(e);
            }
         }
      }

      this.edges = (AbstractIterator)l.iterator();
   }

   public void reset() {
      this.edges.reset();
   }

   public boolean hasNext() {
      return this.edges.hasNext();
   }

   public Edge get() {
      return (Edge)this.edges.get();
   }

   public Edge next() {
      return (Edge)this.edges.next();
   }
}

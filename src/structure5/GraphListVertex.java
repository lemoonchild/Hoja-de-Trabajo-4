package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.GraphListAIterator;
import structure5.SinglyLinkedList;
import structure5.Structure;
import structure5.Vertex;

class GraphListVertex extends Vertex {

   protected Structure adjacencies = new SinglyLinkedList();


   public GraphListVertex(Object key) {
      super(key);
   }

   public void addEdge(Edge e) {
      if(!this.containsEdge(e)) {
         this.adjacencies.add(e);
      }

   }

   public boolean containsEdge(Edge e) {
      return this.adjacencies.contains(e);
   }

   public Edge removeEdge(Edge e) {
      return (Edge)this.adjacencies.remove(e);
   }

   public Edge getEdge(Edge e) {
      Iterator edges = this.adjacencies.iterator();

      Edge adjE;
      do {
         if(!edges.hasNext()) {
            return null;
         }

         adjE = (Edge)edges.next();
      } while(!e.equals(adjE));

      return adjE;
   }

   public int degree() {
      return this.adjacencies.size();
   }

   public Iterator adjacentVertices() {
      return new GraphListAIterator(this.adjacentEdges(), this.label());
   }

   public Iterator adjacentEdges() {
      return this.adjacencies.iterator();
   }

   public String toString() {
      return "<GraphListVertex: " + this.label() + ">";
   }
}

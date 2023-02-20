package structure5;

import structure5.Edge;

public class ComparableEdge extends Edge implements Comparable {

   public ComparableEdge(Object vtx1, Object vtx2, Comparable label, boolean directed) {
      super(vtx1, vtx2, label, directed);
   }

   public ComparableEdge(Edge e) {
      this(e.here(), e.there(), (Comparable)e.label(), e.isDirected());
   }

   public int compareTo(ComparableEdge other) {
      Comparable thisLabel = (Comparable)this.label();
      Comparable thatLabel = (Comparable)other.label();
      return thisLabel.compareTo(thatLabel);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Edge:");
      if(this.visited) {
         s.append(" visited");
      }

      s.append(" " + this.here());
      if(this.directed) {
         s.append(" <->");
      } else {
         s.append("->");
      }

      s.append(" " + this.there() + ">");
      return s.toString();
   }
}

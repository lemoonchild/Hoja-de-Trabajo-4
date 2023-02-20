package structure5;


public class Edge {

   protected Object here;
   protected Object there;
   protected Object label;
   protected boolean visited;
   protected boolean directed;


   public Edge(Object vtx1, Object vtx2, Object label, boolean directed) {
      this.here = vtx1;
      this.there = vtx2;
      this.label = label;
      this.visited = false;
      this.directed = directed;
   }

   public Object here() {
      return this.here;
   }

   public Object there() {
      return this.there;
   }

   public void setLabel(Object label) {
      this.label = label;
   }

   public Object label() {
      return this.label;
   }

   public boolean visit() {
      boolean was = this.visited;
      this.visited = true;
      return was;
   }

   public boolean isVisited() {
      return this.visited;
   }

   public boolean isDirected() {
      return this.directed;
   }

   public void reset() {
      this.visited = false;
   }

   public int hashCode() {
      return this.directed?this.here().hashCode() - this.there().hashCode():this.here().hashCode() ^ this.there().hashCode();
   }

   public boolean equals(Object o) {
      Edge e = (Edge)o;
      return this.here().equals(e.here()) && this.there().equals(e.there()) || !this.directed && this.here().equals(e.there()) && this.there().equals(e.here());
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Edge:");
      if(this.visited) {
         s.append(" visited");
      }

      s.append(" " + this.here());
      if(this.directed) {
         s.append(" ->");
      } else {
         s.append(" <->");
      }

      s.append(" " + this.there() + ">");
      return s.toString();
   }
}

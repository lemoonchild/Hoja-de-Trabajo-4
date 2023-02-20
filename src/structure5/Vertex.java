package structure5;

import structure5.Assert;

class Vertex {

   protected Object label;
   protected boolean visited;


   public Vertex(Object label) {
      Assert.pre(label != null, "Vertex key is non-null");
      this.label = label;
      this.visited = false;
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

   public void reset() {
      this.visited = false;
   }

   public boolean equals(Object o) {
      Vertex v = (Vertex)o;
      return this.label.equals(v.label());
   }

   public int hashCode() {
      return this.label.hashCode();
   }

   public String toString() {
      return "<Vertex: " + this.label + ">";
   }
}

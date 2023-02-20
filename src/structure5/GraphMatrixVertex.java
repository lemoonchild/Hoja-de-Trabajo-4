package structure5;

import structure5.Vertex;

class GraphMatrixVertex extends Vertex {

   protected int index;


   public GraphMatrixVertex(Object label, int idx) {
      super(label);
      this.index = idx;
   }

   public int index() {
      return this.index;
   }

   public String toString() {
      return "<GraphMatrixVertex: " + this.label() + ">";
   }
}

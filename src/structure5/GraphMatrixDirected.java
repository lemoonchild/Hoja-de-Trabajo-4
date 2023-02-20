package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.GraphMatrix;
import structure5.GraphMatrixVertex;
import structure5.SinglyLinkedList;

public class GraphMatrixDirected extends GraphMatrix {

   public GraphMatrixDirected(int size) {
      super(size, true);
   }

   public void addEdge(Object vLabel1, Object vLabel2, Object label) {
      GraphMatrixVertex vtx1 = (GraphMatrixVertex)this.dict.get(vLabel1);
      GraphMatrixVertex vtx2 = (GraphMatrixVertex)this.dict.get(vLabel2);
      Edge e = new Edge(vtx1.label(), vtx2.label(), label, true);
      this.data[vtx1.index()][vtx2.index()] = e;
   }

   public Object removeEdge(Object vLabel1, Object vLabel2) {
      int row = ((GraphMatrixVertex)this.dict.get(vLabel1)).index();
      int col = ((GraphMatrixVertex)this.dict.get(vLabel2)).index();
      Edge e = (Edge)this.data[row][col];
      this.data[row][col] = null;
      return e == null?null:e.label();
   }

   public int edgeCount() {
      int sum = 0;

      for(int row = 0; row < this.size; ++row) {
         for(int col = 0; col < this.size; ++col) {
            if(this.data[row][col] != null) {
               ++sum;
            }
         }
      }

      return sum;
   }

   public Iterator edges() {
      SinglyLinkedList list = new SinglyLinkedList();

      for(int row = this.size - 1; row >= 0; --row) {
         for(int col = this.size - 1; col >= 0; --col) {
            Edge e = (Edge)this.data[row][col];
            if(e != null) {
               list.add(e);
            }
         }
      }

      return list.iterator();
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      Iterator source = this.iterator();
      s.append("<GraphMatrixDirected:");

      while(source.hasNext()) {
         Object srcVal = source.next();
         s.append(" (" + srcVal + "->");
         Iterator dest = this.neighbors(srcVal);

         while(dest.hasNext()) {
            s.append(srcVal + "->" + dest.next());
         }

         s.append(")");
      }

      s.append(">");
      return s.toString();
   }
}

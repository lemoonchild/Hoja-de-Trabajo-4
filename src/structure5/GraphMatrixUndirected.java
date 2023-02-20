package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.GraphMatrix;
import structure5.GraphMatrixVertex;
import structure5.SinglyLinkedList;

public class GraphMatrixUndirected extends GraphMatrix {

   public GraphMatrixUndirected(int size) {
      super(size, false);
   }

   public void addEdge(Object vLabel1, Object vLabel2, Object label) {
      GraphMatrixVertex vtx1 = (GraphMatrixVertex)this.dict.get(vLabel1);
      GraphMatrixVertex vtx2 = (GraphMatrixVertex)this.dict.get(vLabel2);
      Edge e = new Edge(vtx1.label(), vtx2.label(), label, false);
      this.data[vtx1.index()][vtx2.index()] = e;
      this.data[vtx2.index()][vtx1.index()] = e;
   }

   public Object removeEdge(Object vLabel1, Object vLabel2) {
      int row = ((GraphMatrixVertex)this.dict.get(vLabel1)).index();
      int col = ((GraphMatrixVertex)this.dict.get(vLabel2)).index();
      Edge e = (Edge)this.data[row][col];
      this.data[row][col] = null;
      this.data[col][row] = null;
      return e == null?null:e.label();
   }

   public int edgeCount() {
      int sum = 0;

      for(int row = 0; row < this.size; ++row) {
         for(int col = row; col < this.size; ++col) {
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
         for(int col = this.size - 1; col >= row; --col) {
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
      s.append("<GraphMatrixUndirected:");

      while(source.hasNext()) {
         Object srcValue = source.next();
         s.append(" (" + srcValue + "->");
         Iterator dest = this.neighbors(srcValue);

         while(dest.hasNext()) {
            s.append(srcValue + "->" + dest.next());
         }

         s.append(")");
      }

      s.append(">");
      return s.toString();
   }
}

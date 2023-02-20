package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.GraphList;
import structure5.GraphListVertex;

public class GraphListUndirected extends GraphList {

   public GraphListUndirected() {
      super(false);
   }

   public void addEdge(Object vLabel1, Object vLabel2, Object label) {
      GraphListVertex v1 = (GraphListVertex)this.dict.get(vLabel1);
      GraphListVertex v2 = (GraphListVertex)this.dict.get(vLabel2);
      Edge e = new Edge(v1.label(), v2.label(), label, false);
      v1.addEdge(e);
      v2.addEdge(e);
   }

   public Object remove(Object label) {
      GraphListVertex v = (GraphListVertex)this.dict.get(label);
      Iterator vi = this.neighbors(label);

      while(vi.hasNext()) {
         Object v2 = vi.next();
         this.removeEdge(label, v2);
      }

      this.dict.remove(label);
      return v.label();
   }

   public Object removeEdge(Object vLabel1, Object vLabel2) {
      GraphListVertex v1 = (GraphListVertex)this.dict.get(vLabel1);
      GraphListVertex v2 = (GraphListVertex)this.dict.get(vLabel2);
      Edge e = new Edge(v1.label(), v2.label(), (Object)null, false);
      v2.removeEdge(e);
      e = v1.removeEdge(e);
      return e == null?null:e.label();
   }

   public int edgeCount() {
      int count = 0;

      for(Iterator i = this.dict.values().iterator(); i.hasNext(); count += ((GraphListVertex)i.next()).degree()) {
         ;
      }

      return count / 2;
   }

   public String toString() {
      return "<GraphListUndirected: " + this.dict.toString() + ">";
   }
}

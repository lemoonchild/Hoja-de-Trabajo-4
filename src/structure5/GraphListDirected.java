package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.GraphList;
import structure5.GraphListVertex;

public class GraphListDirected extends GraphList {

   public GraphListDirected() {
      super(true);
   }

   public void addEdge(Object vLabel1, Object vLabel2, Object label) {
      GraphListVertex v1 = (GraphListVertex)this.dict.get(vLabel1);
      GraphListVertex v2 = (GraphListVertex)this.dict.get(vLabel2);
      Edge e = new Edge(v1.label(), v2.label(), label, true);
      v1.addEdge(e);
   }

   public Object remove(Object label) {
      GraphListVertex v = (GraphListVertex)this.dict.get(label);
      Iterator vi = this.iterator();

      while(vi.hasNext()) {
         Object v2 = vi.next();
         if(!label.equals(v2)) {
            this.removeEdge(v2, label);
         }
      }

      this.dict.remove(label);
      return v.label();
   }

   public Object removeEdge(Object vLabel1, Object vLabel2) {
      GraphListVertex v1 = (GraphListVertex)this.dict.get(vLabel1);
      GraphListVertex v2 = (GraphListVertex)this.dict.get(vLabel2);
      Edge e = new Edge(v1.label(), v2.label(), (Object)null, true);
      e = v1.removeEdge(e);
      return e == null?null:e.label();
   }

   public int edgeCount() {
      int count = 0;

      for(Iterator i = this.dict.values().iterator(); i.hasNext(); count += ((GraphListVertex)i.next()).degree()) {
         ;
      }

      return count;
   }

   public String toString() {
      return "<GraphListDirected: " + this.dict.toString() + ">";
   }
}

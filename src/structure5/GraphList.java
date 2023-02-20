package structure5;

import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.Assert;
import structure5.ComparableAssociation;
import structure5.Edge;
import structure5.Graph;
import structure5.GraphListDirected;
import structure5.GraphListEIterator;
import structure5.GraphListVertex;
import structure5.Hashtable;
import structure5.Map;
import structure5.SkewHeap;

public abstract class GraphList extends AbstractStructure implements Graph {

   protected Map dict = new Hashtable();
   protected boolean directed;


   protected GraphList(boolean dir) {
      this.directed = dir;
   }

   public void add(Object label) {
      if(!this.dict.containsKey(label)) {
         GraphListVertex v = new GraphListVertex(label);
         this.dict.put(label, v);
      }
   }

   public abstract void addEdge(Object var1, Object var2, Object var3);

   public abstract Object remove(Object var1);

   public abstract Object removeEdge(Object var1, Object var2);

   public Object get(Object label) {
      Assert.condition(this.dict.containsKey(label), "Vertex exists");
      return ((GraphListVertex)this.dict.get(label)).label();
   }

   public Edge getEdge(Object label1, Object label2) {
      Assert.condition(this.dict.containsKey(label1), "Vertex exists");
      Edge e = new Edge(this.get(label1), this.get(label2), (Object)null, this.directed);
      return ((GraphListVertex)this.dict.get(label1)).getEdge(e);
   }

   public boolean contains(Object label) {
      return this.dict.containsKey(label);
   }

   public boolean containsEdge(Object vLabel1, Object vLabel2) {
      Assert.condition(this.dict.containsKey(vLabel1), "Vertex exists");
      Edge e = new Edge(vLabel1, vLabel2, (Object)null, this.directed);
      return ((GraphListVertex)this.dict.get(vLabel1)).containsEdge(e);
   }

   public boolean visit(Object label) {
      return ((GraphListVertex)this.dict.get(label)).visit();
   }

   public boolean visitEdge(Edge e) {
      return e.visit();
   }

   public boolean isVisited(Object label) {
      return ((GraphListVertex)this.dict.get(label)).isVisited();
   }

   public boolean isVisitedEdge(Edge e) {
      return e.isVisited();
   }

   public void reset() {
      Iterator vi = this.dict.values().iterator();

      while(vi.hasNext()) {
         GraphListVertex ei = (GraphListVertex)vi.next();
         ei.reset();
      }

      Iterator ei1 = this.edges();

      while(ei1.hasNext()) {
         Edge e = (Edge)ei1.next();
         e.reset();
      }

   }

   public int size() {
      return this.dict.size();
   }

   public int degree(Object label) {
      Assert.condition(this.dict.containsKey(label), "Vertex exists.");
      return ((GraphListVertex)this.dict.get(label)).degree();
   }

   public abstract int edgeCount();

   public Iterator iterator() {
      return this.dict.keySet().iterator();
   }

   public Iterator neighbors(Object label) {
      Assert.condition(this.dict.containsKey(label), "Vertex exists");
      return ((GraphListVertex)this.dict.get(label)).adjacentVertices();
   }

   public Iterator edges() {
      return new GraphListEIterator(this.dict);
   }

   public void clear() {
      this.dict.clear();
   }

   public boolean isEmpty() {
      return this.dict.isEmpty();
   }

   public boolean isDirected() {
      return this.directed;
   }

   public static void main(String[] argv) {
      GraphListDirected theaters = new GraphListDirected();
      SkewHeap heap = new SkewHeap();
      String[] locations = new String[]{"TCL 312", "Images Cinema", "Movie Plex 3", "Cinema 1,2,&3", "Cinema 7", "Berkshire Mall Cinemas", "Hathaway\'s Drive Inn Theatre", "Hollywood Drive-In Theatre"};
      double[] distances = new double[]{-1.0D, 0.0D, 12.6D, 12.9D, 12.9D, 14.7D, 16.5D, 18.0D};

      int show;
      for(show = 0; show < locations.length; ++show) {
         theaters.add(locations[show]);
      }

      for(show = 1; show < distances.length; ++show) {
         theaters.addEdge(locations[0], locations[show], new Double(distances[show]));
      }

      Iterator var8 = theaters.neighbors(locations[0]);

      while(var8.hasNext()) {
         String theater = (String)var8.next();
         Double distance = (Double)theaters.getEdge(locations[0], theater).label();
         heap.add(new ComparableAssociation(distance, theater));
      }

      while(!heap.isEmpty()) {
         ComparableAssociation var9 = (ComparableAssociation)heap.remove();
         System.out.println((String)var9.getValue() + " is " + var9.getKey() + " miles away.");
      }

   }
}

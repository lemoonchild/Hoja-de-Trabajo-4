package structure5;

import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.Assert;
import structure5.Edge;
import structure5.Graph;
import structure5.GraphMatrixVertex;
import structure5.Hashtable;
import structure5.List;
import structure5.Map;
import structure5.SinglyLinkedList;
import structure5.Vertex;

public abstract class GraphMatrix extends AbstractStructure implements Graph {

   protected int size;
   protected Object[][] data;
   protected Map dict;
   protected List freeList;
   protected boolean directed;


   protected GraphMatrix(int size, boolean dir) {
      this.size = size;
      this.directed = dir;
      this.data = new Object[size][size];
      this.dict = new Hashtable(size);
      this.freeList = new SinglyLinkedList();

      for(int row = size - 1; row >= 0; --row) {
         this.freeList.add(new Integer(row));
      }

   }

   public void add(Object label) {
      if(!this.dict.containsKey(label)) {
         Assert.pre(!this.freeList.isEmpty(), "Matrix not full");
         int row = ((Integer)this.freeList.removeFirst()).intValue();
         this.dict.put(label, new GraphMatrixVertex(label, row));
      }
   }

   public abstract void addEdge(Object var1, Object var2, Object var3);

   public Object remove(Object label) {
      GraphMatrixVertex vert = (GraphMatrixVertex)this.dict.remove(label);
      if(vert == null) {
         return null;
      } else {
         int index = vert.index();

         for(int row = 0; row < this.size; ++row) {
            this.data[row][index] = null;
            this.data[index][row] = null;
         }

         this.freeList.add(new Integer(index));
         return vert.label();
      }
   }

   public abstract Object removeEdge(Object var1, Object var2);

   public Object get(Object label) {
      GraphMatrixVertex vert = (GraphMatrixVertex)this.dict.get(label);
      return vert.label();
   }

   public Edge getEdge(Object label1, Object label2) {
      int row = ((GraphMatrixVertex)this.dict.get(label1)).index();
      int col = ((GraphMatrixVertex)this.dict.get(label2)).index();
      return (Edge)this.data[row][col];
   }

   public boolean contains(Object label) {
      return this.dict.containsKey(label);
   }

   public boolean containsEdge(Object vLabel1, Object vLabel2) {
      GraphMatrixVertex vtx1 = (GraphMatrixVertex)this.dict.get(vLabel1);
      GraphMatrixVertex vtx2 = (GraphMatrixVertex)this.dict.get(vLabel2);
      Assert.condition(vtx1 != null, "Vertex exists");
      Assert.condition(vtx2 != null, "Vertex exists");
      return this.data[vtx1.index()][vtx2.index()] != null;
   }

   public boolean visit(Object label) {
      Vertex vert = (Vertex)this.dict.get(label);
      return vert.visit();
   }

   public boolean visitEdge(Edge e) {
      return e.visit();
   }

   public boolean isVisited(Object label) {
      GraphMatrixVertex vert = (GraphMatrixVertex)this.dict.get(label);
      return vert.isVisited();
   }

   public boolean isVisitedEdge(Edge e) {
      return e.isVisited();
   }

   public void reset() {
      Iterator it = this.dict.values().iterator();

      while(it.hasNext()) {
         ((GraphMatrixVertex)it.next()).reset();
      }

      for(int row = 0; row < this.size; ++row) {
         for(int col = 0; col < this.size; ++col) {
            Edge e = (Edge)this.data[row][col];
            if(e != null) {
               e.reset();
            }
         }
      }

   }

   public int size() {
      return this.dict.size();
   }

   public int degree(Object label) {
      int row = ((GraphMatrixVertex)this.dict.get(label)).index();
      int result = 0;

      for(int col = 0; col < this.size; ++col) {
         if(this.data[row][col] != null) {
            ++result;
         }
      }

      return result;
   }

   public abstract int edgeCount();

   public Iterator iterator() {
      return this.dict.keySet().iterator();
   }

   public Iterator neighbors(Object label) {
      GraphMatrixVertex vert = (GraphMatrixVertex)this.dict.get(label);
      SinglyLinkedList list = new SinglyLinkedList();

      for(int row = this.size - 1; row >= 0; --row) {
         Edge e = (Edge)this.data[vert.index()][row];
         if(e != null) {
            if(e.here().equals(vert.label())) {
               list.add(e.there());
            } else {
               list.add(e.here());
            }
         }
      }

      return list.iterator();
   }

   public abstract Iterator edges();

   public void clear() {
      this.dict.clear();

      int row;
      for(row = 0; row < this.size; ++row) {
         for(int col = 0; col < this.size; ++col) {
            this.data[row][col] = null;
         }
      }

      this.freeList = new SinglyLinkedList();

      for(row = this.size - 1; row >= 0; --row) {
         this.freeList.add(new Integer(row));
      }

   }

   public boolean isEmpty() {
      return this.dict.isEmpty();
   }

   public boolean isDirected() {
      return this.directed;
   }
}

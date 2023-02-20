package structure5;

import java.util.Iterator;
import structure5.Edge;
import structure5.Structure;

public interface Graph extends Structure {

   void add(Object var1);

   void addEdge(Object var1, Object var2, Object var3);

   Object remove(Object var1);

   Object removeEdge(Object var1, Object var2);

   Object get(Object var1);

   Edge getEdge(Object var1, Object var2);

   boolean contains(Object var1);

   boolean containsEdge(Object var1, Object var2);

   boolean visit(Object var1);

   boolean visitEdge(Edge var1);

   boolean isVisited(Object var1);

   boolean isVisitedEdge(Edge var1);

   void reset();

   int size();

   int degree(Object var1);

   int edgeCount();

   Iterator iterator();

   Iterator neighbors(Object var1);

   Iterator edges();

   void clear();

   boolean isEmpty();

   boolean isDirected();
}

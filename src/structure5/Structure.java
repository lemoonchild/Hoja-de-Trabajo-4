package structure5;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public interface Structure extends Iterable {

   int size();

   boolean isEmpty();

   void clear();

   boolean contains(Object var1);

   void add(Object var1);

   Object remove(Object var1);

   Enumeration elements();

   Iterator iterator();

   Collection values();
}

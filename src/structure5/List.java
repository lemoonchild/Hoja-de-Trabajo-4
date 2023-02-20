package structure5;

import java.util.Iterator;
import structure5.Structure;

public interface List extends Structure {

   int size();

   boolean isEmpty();

   void clear();

   void addFirst(Object var1);

   void addLast(Object var1);

   Object getFirst();

   Object getLast();

   Object removeFirst();

   Object removeLast();

   Object remove(Object var1);

   void add(Object var1);

   Object remove();

   Object get();

   boolean contains(Object var1);

   int indexOf(Object var1);

   int lastIndexOf(Object var1);

   Object get(int var1);

   Object set(int var1, Object var2);

   void add(int var1, Object var2);

   Object remove(int var1);

   Iterator iterator();
}

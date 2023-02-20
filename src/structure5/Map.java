package structure5;

import structure5.Set;
import structure5.Structure;

public interface Map {

   int size();

   boolean isEmpty();

   boolean containsKey(Object var1);

   boolean containsValue(Object var1);

   Object get(Object var1);

   Object put(Object var1, Object var2);

   Object remove(Object var1);

   void putAll(Map var1);

   void clear();

   Set keySet();

   Structure values();

   Set entrySet();

   boolean equals(Object var1);

   int hashCode();
}

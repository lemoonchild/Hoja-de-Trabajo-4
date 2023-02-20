package structure5;

import java.util.Iterator;
import structure5.Map;

public abstract class AbstractMap implements Map {

   public void putAll(Map other) {
      Iterator i = other.keySet().iterator();

      while(i.hasNext()) {
         Object k = i.next();
         this.put(k, other.get(k));
      }

   }

   public int hashCode() {
      return this.values().hashCode();
   }
}

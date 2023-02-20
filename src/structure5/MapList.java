package structure5;

import java.util.Iterator;
import structure5.Association;
import structure5.List;
import structure5.Map;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.Structure;
import structure5.ValueIterator;

public class MapList implements Map {

   protected List data;


   public MapList() {
      this.data = new SinglyLinkedList();
   }

   public MapList(Map source) {
      this();
      this.putAll(source);
   }

   public int size() {
      return this.data.size();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public boolean containsKey(Object k) {
      return this.data.contains(new Association(k, (Object)null));
   }

   public boolean containsValue(Object v) {
      ValueIterator i = new ValueIterator(this.data.iterator());

      Object value;
      do {
         if(!i.hasNext()) {
            return false;
         }

         value = i.next();
      } while(value == null || !v.equals(value));

      return true;
   }

   public Object get(Object k) {
      int i = this.data.indexOf(new Association(k, (Object)null));
      return i >= 0?((Association)this.data.get(i)).getValue():null;
   }

   public Object put(Object k, Object v) {
      Association temp = new Association(k, v);
      Association result = (Association)this.data.remove(temp);
      this.data.add(temp);
      return result == null?null:result.getValue();
   }

   public Object remove(Object k) {
      Association v = (Association)this.data.remove(new Association(k, (Object)null));
      return v == null?null:v.getValue();
   }

   public void putAll(Map other) {
      Iterator i = other.entrySet().iterator();

      while(i.hasNext()) {
         Association e = (Association)i.next();
         this.put(e.getKey(), e.getValue());
      }

   }

   public void clear() {
      this.data.clear();
   }

   public Set keySet() {
      SetList result = new SetList();
      Iterator i = this.data.iterator();

      while(i.hasNext()) {
         Association a = (Association)i.next();
         result.add(a.getKey());
      }

      return result;
   }

   public Structure values() {
      SinglyLinkedList result = new SinglyLinkedList();
      ValueIterator i = new ValueIterator(this.data.iterator());

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Set entrySet() {
      SetList result = new SetList();
      Iterator i = this.data.iterator();

      while(i.hasNext()) {
         Association a = (Association)i.next();
         result.add(a);
      }

      return result;
   }

   public boolean equals(Object other) {
      MapList that = (MapList)other;
      return this.data.equals(that.data);
   }

   public int hashCode() {
      return this.data.hashCode();
   }
}

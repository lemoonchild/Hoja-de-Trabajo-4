package structure5;

import java.util.Iterator;
import structure5.AbstractMap;
import structure5.Association;
import structure5.ChainedHashtableIterator;
import structure5.KeyIterator;
import structure5.List;
import structure5.Map;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.Structure;
import structure5.ValueIterator;
import structure5.Vector;

public class ChainedHashtable extends AbstractMap implements Map, Iterable {

   protected Vector data;
   protected int count;


   public ChainedHashtable(int size) {
      this.data = new Vector();
      this.data.setSize(size);
      this.count = 0;
   }

   public ChainedHashtable() {
      this(997);
   }

   public void clear() {
      Iterator i$ = this.data.iterator();

      while(i$.hasNext()) {
         List l = (List)i$.next();
         if(l != null) {
            l.clear();
         }
      }

      this.count = 0;
   }

   public int size() {
      return this.count;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   protected List locate(Object key) {
      int hash = Math.abs(key.hashCode() % this.data.size());
      if(this.data.get(hash) == null) {
         this.data.set(hash, new SinglyLinkedList());
      }

      return (List)this.data.get(hash);
   }

   public boolean containsValue(Object value) {
      Iterator i$ = this.iterator();

      Object v;
      do {
         if(!i$.hasNext()) {
            return false;
         }

         v = i$.next();
      } while(!value.equals(v));

      return true;
   }

   public boolean containsKey(Object key) {
      List l = this.locate(key);
      return l.contains(new Association(key, (Object)null));
   }

   public Iterator iterator() {
      return new ValueIterator(new ChainedHashtableIterator(this.data));
   }

   public Set keySet() {
      SetList result = new SetList();
      KeyIterator i = new KeyIterator(new ChainedHashtableIterator(this.data));

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Set entrySet() {
      SetList result = new SetList();
      ChainedHashtableIterator i = new ChainedHashtableIterator(this.data);

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Structure values() {
      SinglyLinkedList result = new SinglyLinkedList();
      ValueIterator i = new ValueIterator(new ChainedHashtableIterator(this.data));

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Object get(Object key) {
      List l = this.locate(key);
      Association a = (Association)l.remove(new Association(key, (Object)null));
      if(a == null) {
         return null;
      } else {
         l.addFirst(a);
         return a.getValue();
      }
   }

   public Iterator keys() {
      return new KeyIterator(new ChainedHashtableIterator(this.data));
   }

   public Object put(Object key, Object value) {
      List l = this.locate(key);
      Association newa = new Association(key, value);
      Association olda = (Association)l.remove(newa);
      l.addFirst(newa);
      if(olda != null) {
         return olda.getValue();
      } else {
         ++this.count;
         return null;
      }
   }

   public Object remove(Object key) {
      List l = this.locate(key);
      Association pair = (Association)l.remove(new Association(key, (Object)null));
      if(pair == null) {
         return null;
      } else {
         --this.count;
         return pair.getValue();
      }
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<ChainedHashtable:");
      ChainedHashtableIterator hi = new ChainedHashtableIterator(this.data);

      while(hi.hasNext()) {
         Association a = (Association)hi.next();
         s.append(" " + a.getKey() + "=" + a.getValue());
      }

      s.append(">");
      return s.toString();
   }
}

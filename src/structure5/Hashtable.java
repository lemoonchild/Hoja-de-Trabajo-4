package structure5;

import java.util.Iterator;
import structure5.Assert;
import structure5.Association;
import structure5.HashAssociation;
import structure5.HashtableIterator;
import structure5.KeyIterator;
import structure5.Map;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.Structure;
import structure5.ValueIterator;
import structure5.Vector;

public class Hashtable implements Map, Iterable {

   protected static final String RESERVED = "RESERVED";
   protected Vector data;
   protected int count;
   protected final double maximumLoadFactor;


   public Hashtable(int initialCapacity) {
      this.maximumLoadFactor = 0.6D;
      Assert.pre(initialCapacity > 0, "Hashtable capacity must be positive.");
      this.data = new Vector();
      this.data.setSize(initialCapacity);
      this.count = 0;
   }

   public Hashtable() {
      this(997);
   }

   public void clear() {
      for(int i = 0; i < this.data.size(); ++i) {
         this.data.set(i, (Object)null);
      }

      this.count = 0;
   }

   public int size() {
      return this.count;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsValue(Object value) {
      Iterator i$ = this.iterator();

      Object tableValue;
      do {
         if(!i$.hasNext()) {
            return false;
         }

         tableValue = i$.next();
      } while(!tableValue.equals(value));

      return true;
   }

   public boolean containsKey(Object key) {
      int hash = this.locate(key);
      return this.data.get(hash) != null && !((HashAssociation)this.data.get(hash)).reserved();
   }

   public Iterator iterator() {
      return new ValueIterator(new HashtableIterator(this.data));
   }

   public Object get(Object key) {
      int hash = this.locate(key);
      return this.data.get(hash) != null && !((HashAssociation)this.data.get(hash)).reserved()?((HashAssociation)this.data.get(hash)).getValue():null;
   }

   public Iterator keys() {
      return new KeyIterator(new HashtableIterator(this.data));
   }

   protected int locate(Object key) {
      int hash = Math.abs(key.hashCode() % this.data.size());
      int reservedSlot = -1;

      boolean foundReserved;
      for(foundReserved = false; this.data.get(hash) != null; hash = (1 + hash) % this.data.size()) {
         if(((HashAssociation)this.data.get(hash)).reserved()) {
            if(!foundReserved) {
               reservedSlot = hash;
               foundReserved = true;
            }
         } else if(key.equals(((HashAssociation)this.data.get(hash)).getKey())) {
            return hash;
         }
      }

      if(!foundReserved) {
         return hash;
      } else {
         return reservedSlot;
      }
   }

   public Object put(Object key, Object value) {
      if(0.6D * (double)this.data.size() <= (double)(1 + this.count)) {
         this.extend();
      }

      int hash = this.locate(key);
      if(this.data.get(hash) != null && !((HashAssociation)this.data.get(hash)).reserved()) {
         HashAssociation a = (HashAssociation)this.data.get(hash);
         Object oldValue = a.getValue();
         a.setValue(value);
         return oldValue;
      } else {
         this.data.set(hash, new HashAssociation(key, value));
         ++this.count;
         return null;
      }
   }

   public void putAll(Map other) {
      Iterator i$ = other.entrySet().iterator();

      while(i$.hasNext()) {
         Association e = (Association)i$.next();
         this.put(e.getKey(), e.getValue());
      }

   }

   public Object remove(Object key) {
      int hash = this.locate(key);
      if(this.data.get(hash) != null && !((HashAssociation)this.data.get(hash)).reserved()) {
         --this.count;
         Object oldValue = ((HashAssociation)this.data.get(hash)).getValue();
         ((HashAssociation)this.data.get(hash)).reserve();
         return oldValue;
      } else {
         return null;
      }
   }

   protected void extend() {
      HashtableIterator it = new HashtableIterator(this.data);
      int newSize = 2 * this.data.size();
      Assert.condition(newSize > 0, "Hashtable vector size must be greater than 0.");
      this.data = new Vector();
      this.data.setSize(newSize);
      this.count = 0;

      while(it.hasNext()) {
         Association a = (Association)it.next();
         this.put(a.getKey(), a.getValue());
      }

   }

   public Set entrySet() {
      SetList result = new SetList();
      HashtableIterator i = new HashtableIterator(this.data);

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Set keySet() {
      SetList result = new SetList();
      KeyIterator i = new KeyIterator(new HashtableIterator(this.data));

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public Structure values() {
      SinglyLinkedList result = new SinglyLinkedList();
      ValueIterator i = new ValueIterator(new HashtableIterator(this.data));

      while(i.hasNext()) {
         result.add(i.next());
      }

      return result;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Hashtable: size=" + this.size() + " capacity=" + this.data.size());
      HashtableIterator hi = new HashtableIterator(this.data);

      while(hi.hasNext()) {
         Association a = (Association)hi.next();
         s.append(" key=" + a.getKey() + ", value=" + a.getValue());
      }

      s.append(">");
      return s.toString();
   }
}

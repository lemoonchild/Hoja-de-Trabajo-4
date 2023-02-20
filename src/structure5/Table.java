package structure5;

import java.util.Iterator;
import structure5.AbstractMap;
import structure5.Association;
import structure5.ComparableAssociation;
import structure5.KeyIterator;
import structure5.OrderedMap;
import structure5.OrderedStructure;
import structure5.ReadStream;
import structure5.Set;
import structure5.SetList;
import structure5.SinglyLinkedList;
import structure5.SplayTree;
import structure5.Structure;
import structure5.ValueIterator;

public class Table extends AbstractMap implements OrderedMap {

   protected OrderedStructure data = new SplayTree();


   public Table() {}

   public Table(Table other) {
      Iterator i = other.entrySet().iterator();

      while(i.hasNext()) {
         Association o = (Association)i.next();
         this.put((Comparable)o.getKey(), o.getValue());
      }

   }

   public Object get(Comparable key) {
      ComparableAssociation ca = new ComparableAssociation(key, (Object)null);
      ComparableAssociation result = (ComparableAssociation)this.data.remove(ca);
      if(result == null) {
         return null;
      } else {
         this.data.add(result);
         return result.getValue();
      }
   }

   public Object put(Comparable key, Object value) {
      ComparableAssociation ca = new ComparableAssociation(key, value);
      ComparableAssociation old = (ComparableAssociation)this.data.remove(ca);
      this.data.add(ca);
      return old == null?null:old.getValue();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public void clear() {
      this.data.clear();
   }

   public Iterator keys() {
      return new KeyIterator(this.data.iterator());
   }

   public Iterator iterator() {
      return new ValueIterator(this.data.iterator());
   }

   public boolean containsKey(Comparable key) {
      ComparableAssociation a = new ComparableAssociation(key, (Object)null);
      return this.data.contains(a);
   }

   public boolean containsValue(Object value) {
      Iterator i = this.iterator();

      Object nextValue;
      do {
         if(!i.hasNext()) {
            return false;
         }

         nextValue = i.next();
      } while(nextValue == null || !nextValue.equals(value));

      return true;
   }

   public Object remove(Comparable key) {
      ComparableAssociation target = new ComparableAssociation(key, (Object)null);
      target = (ComparableAssociation)this.data.remove(target);
      return target == null?null:target.getValue();
   }

   public int size() {
      return this.data.size();
   }

   public Set keySet() {
      SetList result = new SetList();
      KeyIterator i = new KeyIterator(this.data.iterator());

      while(i.hasNext()) {
         result.add(i.next());
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
         result.add(i.next());
      }

      return result;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<Table: size=" + this.size());
      Iterator ti = this.data.iterator();

      while(ti.hasNext()) {
         ComparableAssociation ca = (ComparableAssociation)ti.next();
         s.append(" key=" + ca.getKey() + ", value=" + ca.getValue());
      }

      s.append(">");
      return s.toString();
   }

   public static void main(String[] argv) {
      Table dict = new Table();
      ReadStream r = new ReadStream();
      System.out.println("Enter a word: ");

      while(!r.eof()) {
         String word = r.readLine();
         System.out.println("Enter a definition: ");
         String def = r.readLine();
         dict.put(word, def);
         System.out.println("Enter a word: ");
      }

      System.out.println(dict);
   }
}

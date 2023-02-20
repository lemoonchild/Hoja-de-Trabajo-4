package structure5;

import java.util.Iterator;
import structure5.AbstractSet;
import structure5.Structure;
import structure5.Vector;

public class SetVector extends AbstractSet {

   protected Vector data;


   public SetVector() {
      this.data = new Vector();
   }

   public SetVector(Structure other) {
      this();
      this.addAll(other);
   }

   public void clear() {
      this.data.clear();
   }

   public boolean isEmpty() {
      return this.data.isEmpty();
   }

   public void add(Object e) {
      if(!this.data.contains(e)) {
         this.data.add(e);
      }

   }

   public Object remove(Object e) {
      return this.data.remove(e);
   }

   public boolean contains(Object e) {
      return this.data.contains(e);
   }

   public boolean containsAll(Structure other) {
      Iterator myElements = this.data.iterator();

      do {
         if(!myElements.hasNext()) {
            return true;
         }
      } while(other.contains(myElements.next()));

      return false;
   }

   public Object clone() {
      SetVector result = new SetVector();
      Iterator myElements = this.iterator();

      while(myElements.hasNext()) {
         result.add(myElements.next());
      }

      return result;
   }

   public void addAll(Structure other) {
      Iterator yourElements = other.iterator();

      while(yourElements.hasNext()) {
         this.add(yourElements.next());
      }

   }

   public void retainAll(Structure other) {
      SetVector temp = new SetVector();
      Iterator myElements = this.data.iterator();

      while(myElements.hasNext()) {
         Object v = myElements.next();
         if(other.contains(v)) {
            temp.add(v);
         }
      }

      this.clear();
      this.addAll(temp);
   }

   public void removeAll(Structure other) {
      Iterator yourElements = other.iterator();

      while(yourElements.hasNext()) {
         this.remove(yourElements.next());
      }

   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   public int size() {
      return this.data.size();
   }

   public String toString() {
      return "<SetVector: " + this.data + ">";
   }

   public static void main(String[] argv) {
      String[] thesis = new String[]{"Doug", "Evan", "Feng"};
      String[] grad = new String[]{"Doug", "Feng", "Lida"};
      SetVector thesisSet = new SetVector();
      SetVector gradSet = new SetVector();

      int i;
      for(i = 0; i < thesis.length; ++i) {
         thesisSet.add(thesis[i]);
      }

      for(i = 0; i < grad.length; ++i) {
         gradSet.add(grad[i]);
      }

      thesisSet.retainAll(gradSet);
      System.out.println(thesisSet);
   }
}

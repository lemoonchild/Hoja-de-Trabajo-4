package structure5;

import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.OrderedStructure;
import structure5.Vector;

public class OrderedVector extends AbstractStructure implements OrderedStructure {

   protected Vector data = new Vector();


   public void add(Comparable value) {
      int position = this.locate(value);
      this.data.add(position, value);
   }

   public boolean contains(Comparable value) {
      int position = this.locate(value);
      return position < this.size() && ((Comparable)this.data.get(position)).equals(value);
   }

   public Comparable remove(Comparable value) {
      if(this.contains(value)) {
         int position = this.locate(value);
         Comparable target = (Comparable)this.data.get(position);
         this.data.remove(position);
         return target;
      } else {
         return null;
      }
   }

   public boolean isEmpty() {
      return this.data.size() == 0;
   }

   public void clear() {
      this.data.setSize(0);
   }

   public int size() {
      return this.data.size();
   }

   public Iterator iterator() {
      return this.data.iterator();
   }

   protected int locate(Comparable target) {
      int low = 0;
      int high = this.data.size();

      for(int mid = (low + high) / 2; low < high; mid = (low + high) / 2) {
         Comparable midValue = (Comparable)this.data.get(mid);
         if(midValue.compareTo(target) < 0) {
            low = mid + 1;
         } else {
            high = mid;
         }
      }

      return low;
   }

   public String toString() {
      return "<OrderedVector: " + this.data + ">";
   }
}

package structure5;

import structure5.Assert;
import structure5.PriorityQueue;
import structure5.Vector;

public class PriorityVector implements PriorityQueue {

   protected Vector data = new Vector();


   public Comparable getFirst() {
      return (Comparable)this.data.get(0);
   }

   public Comparable remove() {
      return (Comparable)this.data.remove(0);
   }

   public void add(Comparable value) {
      int position = this.indexOf(value);
      this.data.add(position, value);
   }

   protected int indexOf(Comparable target) {
      int low = 0;
      int high = this.data.size();

      for(int mid = (low + high) / 2; low < high; mid = (low + high) / 2) {
         Assert.condition(mid < high, "Middle element exists.");
         Comparable midValue = (Comparable)this.data.get(mid);
         if(midValue.compareTo(target) < 0) {
            low = mid + 1;
         } else {
            high = mid;
         }
      }

      return low;
   }

   public boolean isEmpty() {
      return this.data.size() == 0;
   }

   public int size() {
      return this.data.size();
   }

   public void clear() {
      this.data.clear();
   }

   public String toString() {
      return "<PriorityVector: " + this.data + ">";
   }
}

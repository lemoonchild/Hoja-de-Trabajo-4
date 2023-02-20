package structure5;

import java.util.Comparator;
import structure5.NaturalComparator;

public class ReverseComparator implements Comparator {

   protected Comparator base;


   public ReverseComparator() {
      this.base = new NaturalComparator();
   }

   public ReverseComparator(Comparator base) {
      this.base = base;
   }

   public int compare(Comparable a, Comparable b) {
      return -this.base.compare(a, b);
   }

   public boolean equals(Object b) {
      if(b == null) {
         return false;
      } else if(!(b instanceof ReverseComparator)) {
         return false;
      } else {
         ReverseComparator that = (ReverseComparator)b;
         return this.base.equals(that.base);
      }
   }
}

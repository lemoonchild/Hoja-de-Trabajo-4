package structure5;

import java.util.Comparator;

public class NaturalComparator implements Comparator {

   public int compare(Comparable a, Comparable b) {
      return a.compareTo(b);
   }

   public boolean equals(Object b) {
      return b != null && b instanceof NaturalComparator;
   }
}

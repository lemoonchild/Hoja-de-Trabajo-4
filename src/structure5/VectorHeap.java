package structure5;

import structure5.PriorityQueue;
import structure5.Vector;

public class VectorHeap implements PriorityQueue {

   protected Vector data;


   public VectorHeap() {
      this.data = new Vector();
   }

   public VectorHeap(Vector v) {
      this.data = new Vector(v.size());

      for(int i = 0; i < v.size(); ++i) {
         this.add((Comparable)v.get(i));
      }

   }

   protected static int parent(int i) {
      return (i - 1) / 2;
   }

   protected static int left(int i) {
      return 2 * i + 1;
   }

   protected static int right(int i) {
      return 2 * (i + 1);
   }

   public Comparable getFirst() {
      return (Comparable)this.data.get(0);
   }

   public Comparable remove() {
      Comparable minVal = this.getFirst();
      this.data.set(0, this.data.get(this.data.size() - 1));
      this.data.setSize(this.data.size() - 1);
      if(this.data.size() > 1) {
         this.pushDownRoot(0);
      }

      return minVal;
   }

   public void add(Comparable value) {
      this.data.add(value);
      this.percolateUp(this.data.size() - 1);
   }

   public boolean isEmpty() {
      return this.data.size() == 0;
   }

   protected void percolateUp(int leaf) {
      int parent = parent(leaf);

      Comparable value;
      for(value = (Comparable)this.data.get(leaf); leaf > 0 && value.compareTo(this.data.get(parent)) < 0; parent = parent(parent)) {
         this.data.set(leaf, this.data.get(parent));
         leaf = parent;
      }

      this.data.set(leaf, value);
   }

   protected void pushDownRoot(int root) {
      int heapSize = this.data.size();

      int childpos;
      for(Comparable value = (Comparable)this.data.get(root); root < heapSize; root = childpos) {
         childpos = left(root);
         if(childpos >= heapSize) {
            this.data.set(root, value);
            return;
         }

         if(right(root) < heapSize && ((Comparable)this.data.get(childpos + 1)).compareTo(this.data.get(childpos)) < 0) {
            ++childpos;
         }

         if(((Comparable)this.data.get(childpos)).compareTo(value) >= 0) {
            this.data.set(root, value);
            return;
         }

         this.data.set(root, this.data.get(childpos));
      }

   }

   public int size() {
      return this.data.size();
   }

   public void clear() {
      this.data.clear();
   }

   public String toString() {
      return "<VectorHeap: " + this.data + ">";
   }
}

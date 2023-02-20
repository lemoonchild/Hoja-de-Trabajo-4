package structure5;

import structure5.AbstractIterator;

public class ArrayIterator extends AbstractIterator {

   protected Object[] data;
   protected int head;
   protected int count;
   protected int current;
   protected int remaining;


   public ArrayIterator(Object[] source) {
      this(source, 0, source.length);
   }

   public ArrayIterator(Object[] source, int first, int size) {
      this.data = (Object[])source;
      this.head = first;
      this.count = size;
      this.reset();
   }

   public void reset() {
      this.current = this.head;
      this.remaining = this.count;
   }

   public boolean hasNext() {
      return this.remaining > 0;
   }

   public Object next() {
      Object temp = this.data[this.current];
      this.current = (this.current + 1) % this.data.length;
      --this.remaining;
      return temp;
   }

   public Object get() {
      return this.data[this.current];
   }

   public static void main(String[] argv) {
      String testString = "For loops are much faster than iterators";
      String[] test = new String[10000000];

      int i;
      for(i = 0; i < test.length; ++i) {
         test[i] = testString;
      }

      long start = System.nanoTime();
      ArrayIterator var12 = new ArrayIterator(test);

      while(var12.hasNext()) {
         var12.next();
      }

      long finish = System.nanoTime();
      long iteration = finish - start;
      System.out.println("Iteration over array took " + iteration + " nanoseconds to perform.");
      start = System.nanoTime();

      for(i = 0; i < test.length; ++i) {
         ;
      }

      finish = System.nanoTime();
      long looping = finish - start;
      System.out.println("Looping over array took " + (finish - start) + " nanoseconds to perform.");
      System.out.println("Iterators are " + (double)iteration / (double)looping + " times " + "slower than loops.");
   }
}

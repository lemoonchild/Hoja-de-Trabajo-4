package structure5;


public class BitSet {

   protected final int bitsPerInt = 32;
   protected final int initialCapacity = 256;
   protected int[] data;
   protected int allocated;


   public BitSet() {
      this.clear(256);
   }

   public BitSet(int count) {
      this.clear(count);
   }

   public void add(int i) {
      this.extend(i);
      int index = this.indexOf(i);
      int offset = this.offsetOf(i);
      this.data[index] |= 1 << offset;
   }

   public void remove(int i) {
      if(this.probe(i)) {
         int index = this.indexOf(i);
         int offset = this.offsetOf(i);
         this.data[index] &= ~(1 << offset);
      }

   }

   public boolean contains(int i) {
      return this.probe(i) && 0 != (this.data[this.indexOf(i)] & 1 << this.offsetOf(i));
   }

   public void clear() {
      this.clear(256);
   }

   public void clear(int count) {
      this.allocated = (count + 32 - 1) / 32;
      this.data = new int[this.allocated];

      for(int i = 0; i < this.allocated; ++i) {
         this.data[i] = 0;
      }

   }

   public Object clone() {
      BitSet duplicate = new BitSet(this.allocated * 32);

      for(int i = 0; i < this.allocated; ++i) {
         duplicate.data[i] = this.data[i];
      }

      return duplicate;
   }

   public Object union(BitSet other) {
      int leftSize = this.allocated;
      int rightSize = other.allocated;
      if(leftSize < rightSize) {
         return other.union(this);
      } else {
         BitSet result = new BitSet(leftSize * 32);

         int i;
         for(i = 0; i < rightSize; ++i) {
            result.data[i] = this.data[i] | other.data[i];
         }

         while(i < leftSize) {
            result.data[i] = this.data[i];
            ++i;
         }

         return result;
      }
   }

   public Object intersection(BitSet other) {
      int leftSize = this.allocated;
      int rightSize = other.allocated;
      if(leftSize < rightSize) {
         return other.intersection(this);
      } else {
         BitSet result = new BitSet(rightSize * 32);

         for(int i = 0; i < rightSize; ++i) {
            result.data[i] = this.data[i] & other.data[i];
         }

         return result;
      }
   }

   public Object difference(BitSet other) {
      int leftSize = this.allocated;
      int rightSize = other.allocated;
      BitSet result = new BitSet(leftSize * 32);
      int i;
      if(leftSize <= rightSize) {
         for(i = 0; i < leftSize; ++i) {
            result.data[i] = this.data[i] & ~other.data[i];
         }
      } else {
         for(i = 0; i < rightSize; ++i) {
            result.data[i] = this.data[i] & ~other.data[i];
         }

         for(i = rightSize; i < leftSize; ++i) {
            result.data[i] = this.data[i];
         }
      }

      return result;
   }

   public boolean subset(BitSet other) {
      int leftSize = this.allocated;
      int rightSize = other.allocated;
      int i;
      if(leftSize <= rightSize) {
         for(i = 0; i < leftSize; ++i) {
            if(0 != (this.data[i] & ~other.data[i])) {
               return false;
            }
         }
      } else {
         for(i = 0; i < rightSize; ++i) {
            if(0 != (this.data[i] & ~other.data[i])) {
               return false;
            }
         }

         for(i = rightSize; i < leftSize; ++i) {
            if(0 != this.data[i]) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean isEmpty() {
      for(int i = 0; i < this.allocated; ++i) {
         if(this.data[i] != 0) {
            return false;
         }
      }

      return true;
   }

   public boolean equals(Object o) {
      BitSet other = (BitSet)o;
      int leftSize = this.allocated;
      int rightSize = other.allocated;
      if(leftSize < rightSize) {
         return other.equals(this);
      } else {
         int i;
         for(i = 0; i < rightSize; ++i) {
            if(this.data[i] != other.data[i]) {
               return false;
            }
         }

         for(i = rightSize; i < leftSize; ++i) {
            if(this.data[i] != 0) {
               return false;
            }
         }

         return true;
      }
   }

   protected int indexOf(int b) {
      return b / 32;
   }

   protected int offsetOf(int bit) {
      return bit % 32;
   }

   protected void extend(int bit) {
      if(!this.probe(bit)) {
         int index = this.indexOf(bit);

         int newAllocated;
         for(newAllocated = this.allocated; newAllocated <= index; newAllocated *= 2) {
            ;
         }

         int[] newData = new int[newAllocated];

         int i;
         for(i = 0; i < this.allocated; ++i) {
            newData[i] = this.data[i];
         }

         for(i = this.allocated; i < newAllocated; ++i) {
            newData[i] = 0;
         }

         this.data = newData;
         this.allocated = newAllocated;
      }

   }

   protected boolean probe(int bit) {
      int index = this.indexOf(bit);
      return this.data.length > index;
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<BitSet:");

      for(int i = 0; this.probe(i); ++i) {
         if(this.contains(i)) {
            s.append(" " + Integer.toString(i));
         }
      }

      s.append(">");
      return s.toString();
   }
}

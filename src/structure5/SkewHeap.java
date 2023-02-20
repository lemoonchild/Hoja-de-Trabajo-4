package structure5;

import java.util.Iterator;
import java.util.Random;
import structure5.Assert;
import structure5.BinaryTree;
import structure5.MergeableHeap;

public class SkewHeap implements MergeableHeap {

   protected BinaryTree root;
   protected final BinaryTree EMPTY = new BinaryTree();
   protected int count;


   public SkewHeap() {
      this.root = this.EMPTY;
      this.count = 0;
   }

   public Comparable getFirst() {
      return (Comparable)this.root.value();
   }

   public Comparable remove() {
      Comparable result = (Comparable)this.root.value();
      this.root = merge(this.root.left(), this.root.right());
      --this.count;
      return result;
   }

   public void add(Comparable value) {
      BinaryTree smallTree = new BinaryTree(value, this.EMPTY, this.EMPTY);
      this.root = merge(smallTree, this.root);
      ++this.count;
   }

   public int size() {
      return this.count;
   }

   public void clear() {
      this.root = this.EMPTY;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void merge(MergeableHeap otherHeap) {
      Assert.pre(otherHeap instanceof SkewHeap, "otherHeap must be instance of SkewHeap");
      SkewHeap that = (SkewHeap)otherHeap;
      this.root = merge(this.root, that.root);
      that.root = null;
      this.count += that.count;
   }

   protected static BinaryTree merge(BinaryTree left, BinaryTree right) {
      if(left.isEmpty()) {
         return right;
      } else if(right.isEmpty()) {
         return left;
      } else {
         Comparable leftVal = (Comparable)left.value();
         Comparable rightVal = (Comparable)right.value();
         BinaryTree result;
         if(rightVal.compareTo(leftVal) < 0) {
            result = merge(right, left);
         } else {
            result = left;
            if(left.left().isEmpty()) {
               left.setLeft(right);
            } else {
               BinaryTree temp = left.right();
               left.setRight(left.left());
               left.setLeft(merge(temp, right));
            }
         }

         return result;
      }
   }

   public String toString() {
      if(this.root.isEmpty()) {
         return "<SkewHeap: >";
      } else {
         StringBuffer sb = new StringBuffer();
         sb.append("<SkewHeap:");
         if(!this.root.isEmpty()) {
            Iterator i = this.root.iterator();

            while(i.hasNext()) {
               sb.append(" " + i.next());
            }
         }

         return sb + ">";
      }
   }

   public static void main(String[] argv) {
      byte s1 = 0;
      byte s2 = 0;
      int s3 = 1;
      boolean max = false;

      try {
         while(s3 != 198) {
            Random e = new Random();
            max = false;
            boolean var10 = false;
            boolean var12 = false;
            boolean var14 = false;
            SkewHeap t1 = new SkewHeap();
            SkewHeap t2 = new SkewHeap();
            int var15 = e.nextInt(100);

            int i;
            for(i = 0; i < var15; ++i) {
               t1.add(new Integer(e.nextInt(1000)));
            }

            var15 = e.nextInt(100);

            for(i = 0; i < var15; ++i) {
               t2.add(new Integer(e.nextInt(1000)));
            }

            int var11 = t1.size();
            int var13 = t2.size();
            t1.merge(t2);
            s3 = t1.size();
            Assert.condition(var11 + var13 == s3, "Trees merge with right sizes.");
            System.out.println(var11 + " : " + var13 + " : " + s3);
         }
      } catch (NullPointerException var9) {
         System.out.println(s1 + " : " + s2 + " : " + s3);
      }

   }
}

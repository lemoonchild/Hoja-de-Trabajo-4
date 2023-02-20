package structure5;

import structure5.AbstractIterator;
import structure5.BinaryTree;

class SplayTreeIterator extends AbstractIterator {

   protected BinaryTree tree;
   protected final BinaryTree LEAF;
   protected BinaryTree current;


   public SplayTreeIterator(BinaryTree root, BinaryTree leaf) {
      this.tree = root;
      this.LEAF = leaf;
      this.reset();
   }

   public void reset() {
      this.current = this.tree;
      if(!this.current.isEmpty()) {
         for(this.current = this.current.root(); !this.current.left().isEmpty(); this.current = this.current.left()) {
            ;
         }
      }

   }

   public boolean hasNext() {
      return !this.current.isEmpty();
   }

   public Comparable next() {
      Comparable result = (Comparable)this.current.value();
      if(!this.current.right().isEmpty()) {
         for(this.current = this.current.right(); !this.current.left().isEmpty(); this.current = this.current.left()) {
            ;
         }
      } else {
         boolean lefty;
         do {
            lefty = this.current.isLeftChild();
            this.current = this.current.parent();
         } while(this.current != null && !lefty);

         if(this.current == null) {
            this.current = new BinaryTree();
         }
      }

      return result;
   }

   public Comparable get() {
      return (Comparable)this.current.value();
   }
}

package structure5;

import structure5.AbstractIterator;
import structure5.RedBlackTree;
import structure5.Stack;
import structure5.StackList;

class RedBlackIterator extends AbstractIterator {

   protected RedBlackTree root;
   protected Stack todo = new StackList();


   public RedBlackIterator(RedBlackTree root) {
      this.root = root;
      this.reset();
   }

   public void reset() {
      this.todo.clear();

      for(RedBlackTree current = this.root; current != RedBlackTree.EMPTY; current = current.left()) {
         this.todo.push(current);
      }

   }

   public boolean hasNext() {
      return !this.todo.isEmpty();
   }

   public Comparable get() {
      return ((RedBlackTree)this.todo.get()).value();
   }

   public Comparable next() {
      RedBlackTree old = (RedBlackTree)this.todo.pop();
      Comparable result = old.value();
      if(!old.right().isEmpty()) {
         RedBlackTree current = old.right();

         do {
            this.todo.push(current);
            current = current.left();
         } while(!current.isEmpty());
      }

      return result;
   }
}

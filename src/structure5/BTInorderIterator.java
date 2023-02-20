package structure5;

import structure5.AbstractIterator;
import structure5.BinaryTree;
import structure5.Stack;
import structure5.StackList;

class BTInorderIterator extends AbstractIterator {

   protected BinaryTree root;
   protected Stack todo = new StackList();


   public BTInorderIterator(BinaryTree root) {
      this.root = root;
      this.reset();
   }

   public void reset() {
      this.todo.clear();

      for(BinaryTree current = this.root; !current.isEmpty(); current = current.left()) {
         this.todo.push(current);
      }

   }

   public boolean hasNext() {
      return !this.todo.isEmpty();
   }

   public Object get() {
      return ((BinaryTree)this.todo.get()).value();
   }

   public Object next() {
      BinaryTree old = (BinaryTree)this.todo.pop();
      Object result = old.value();
      if(!old.right().isEmpty()) {
         BinaryTree current = old.right();

         do {
            this.todo.push(current);
            current = current.left();
         } while(!current.isEmpty());
      }

      return result;
   }
}

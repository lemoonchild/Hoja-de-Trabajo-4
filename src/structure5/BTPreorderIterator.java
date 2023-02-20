package structure5;

import structure5.AbstractIterator;
import structure5.BinaryTree;
import structure5.Stack;
import structure5.StackList;

class BTPreorderIterator extends AbstractIterator {

   protected BinaryTree root;
   protected Stack todo = new StackList();


   public BTPreorderIterator(BinaryTree root) {
      this.root = root;
      this.reset();
   }

   public void reset() {
      this.todo.clear();
      if(this.root != null) {
         this.todo.push(this.root);
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
         this.todo.push(old.right());
      }

      if(!old.left().isEmpty()) {
         this.todo.push(old.left());
      }

      return result;
   }
}

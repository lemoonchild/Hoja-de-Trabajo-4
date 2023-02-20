package structure5;

import structure5.AbstractIterator;
import structure5.BinaryTree;
import structure5.Stack;
import structure5.StackList;

class BTPostorderIterator extends AbstractIterator {

   protected BinaryTree root;
   protected Stack todo = new StackList();


   public BTPostorderIterator(BinaryTree root) {
      this.root = root;
      this.reset();
   }

   public void reset() {
      this.todo.clear();
      BinaryTree current = this.root;

      while(!current.isEmpty()) {
         this.todo.push(current);
         if(!current.left().isEmpty()) {
            current = current.left();
         } else {
            current = current.right();
         }
      }

   }

   public boolean hasNext() {
      return !this.todo.isEmpty();
   }

   public Object get() {
      return ((BinaryTree)this.todo.get()).value();
   }

   public Object next() {
      BinaryTree current = (BinaryTree)this.todo.pop();
      Object result = current.value();
      if(!this.todo.isEmpty()) {
         BinaryTree parent = (BinaryTree)this.todo.get();
         if(current == parent.left()) {
            current = parent.right();

            while(!current.isEmpty()) {
               this.todo.push(current);
               if(!current.left().isEmpty()) {
                  current = current.left();
               } else {
                  current = current.right();
               }
            }
         }
      }

      return result;
   }
}

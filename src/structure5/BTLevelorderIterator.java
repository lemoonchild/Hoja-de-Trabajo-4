package structure5;

import structure5.AbstractIterator;
import structure5.BinaryTree;
import structure5.Queue;
import structure5.QueueList;

class BTLevelorderIterator extends AbstractIterator {

   protected BinaryTree root;
   protected Queue todo = new QueueList();


   public BTLevelorderIterator(BinaryTree root) {
      this.root = root;
      this.reset();
   }

   public void reset() {
      this.todo.clear();
      if(!this.root.isEmpty()) {
         this.todo.enqueue(this.root);
      }

   }

   public boolean hasNext() {
      return !this.todo.isEmpty();
   }

   public Object get() {
      return ((BinaryTree)this.todo.get()).value();
   }

   public Object next() {
      BinaryTree current = (BinaryTree)this.todo.dequeue();
      Object result = current.value();
      if(!current.left().isEmpty()) {
         this.todo.enqueue(current.left());
      }

      if(!current.right().isEmpty()) {
         this.todo.enqueue(current.right());
      }

      return result;
   }
}

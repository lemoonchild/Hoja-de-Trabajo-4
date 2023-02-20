package structure5;

import java.util.Iterator;
import structure5.AbstractIterator;
import structure5.Assert;
import structure5.BTInorderIterator;
import structure5.BTLevelorderIterator;
import structure5.BTPostorderIterator;
import structure5.BTPreorderIterator;

public class BinaryTree {

   protected Object val;
   protected BinaryTree parent;
   protected BinaryTree left;
   protected BinaryTree right;


   public BinaryTree() {
      this.val = null;
      this.parent = null;
      this.left = this.right = this;
   }

   public BinaryTree(Object value) {
      Assert.pre(value != null, "Tree values must be non-null.");
      this.val = value;
      this.right = this.left = new BinaryTree();
      this.setLeft(this.left);
      this.setRight(this.right);
   }

   public BinaryTree(Object value, BinaryTree left, BinaryTree right) {
      Assert.pre(value != null, "Tree values must be non-null.");
      this.val = value;
      if(left == null) {
         left = new BinaryTree();
      }

      this.setLeft(left);
      if(right == null) {
         right = new BinaryTree();
      }

      this.setRight(right);
   }

   public BinaryTree left() {
      return this.left;
   }

   public BinaryTree right() {
      return this.right;
   }

   public BinaryTree parent() {
      return this.parent;
   }

   public void setLeft(BinaryTree newLeft) {
      if(!this.isEmpty()) {
         if(this.left != null && this.left.parent() == this) {
            this.left.setParent((BinaryTree)null);
         }

         this.left = newLeft;
         this.left.setParent(this);
      }
   }

   public void setRight(BinaryTree newRight) {
      if(!this.isEmpty()) {
         if(this.right != null && this.right.parent() == this) {
            this.right.setParent((BinaryTree)null);
         }

         this.right = newRight;
         this.right.setParent(this);
      }
   }

   protected void setParent(BinaryTree newParent) {
      if(!this.isEmpty()) {
         this.parent = newParent;
      }

   }

   public int size() {
      return this.isEmpty()?0:this.left().size() + this.right().size() + 1;
   }

   public BinaryTree root() {
      return this.parent() == null?this:this.parent().root();
   }

   public int height() {
      return this.isEmpty()?-1:1 + Math.max(this.left.height(), this.right.height());
   }

   public int depth() {
      return this.parent() == null?0:1 + this.parent.depth();
   }

   public boolean isFull() {
      return this.isEmpty()?true:(this.left().height() != this.right().height()?false:this.left().isFull() && this.right().isFull());
   }

   public boolean isEmpty() {
      return this.val == null;
   }

   public boolean isComplete() {
      if(this.isEmpty()) {
         return true;
      } else {
         int leftHeight = this.left().height();
         int rightHeight = this.right().height();
         boolean leftIsFull = this.left().isFull();
         boolean rightIsFull = this.right().isFull();
         boolean leftIsComplete = this.left().isComplete();
         boolean rightIsComplete = this.right().isComplete();
         return leftIsFull && rightIsComplete && leftHeight == rightHeight?true:leftIsComplete && rightIsFull && leftHeight == rightHeight + 1;
      }
   }

   public boolean isBalanced() {
      return this.isEmpty()?true:Math.abs(this.left().height() - this.right().height()) <= 1 && this.left().isBalanced() && this.right().isBalanced();
   }

   public Iterator iterator() {
      return this.inorderIterator();
   }

   public AbstractIterator preorderIterator() {
      return new BTPreorderIterator(this);
   }

   public AbstractIterator inorderIterator() {
      return new BTInorderIterator(this);
   }

   public AbstractIterator postorderIterator() {
      return new BTPostorderIterator(this);
   }

   public AbstractIterator levelorderIterator() {
      return new BTLevelorderIterator(this);
   }

   protected void rotateRight() {
      BinaryTree parent = this.parent();
      BinaryTree newRoot = this.left();
      boolean wasChild = parent != null;
      boolean wasLeftChild = this.isLeftChild();
      this.setLeft(newRoot.right());
      newRoot.setRight(this);
      if(wasChild) {
         if(wasLeftChild) {
            parent.setLeft(newRoot);
         } else {
            parent.setRight(newRoot);
         }
      }

   }

   protected void rotateLeft() {
      BinaryTree parent = this.parent();
      BinaryTree newRoot = this.right();
      boolean wasChild = parent != null;
      boolean wasRightChild = this.isRightChild();
      this.setRight(newRoot.left());
      newRoot.setLeft(this);
      if(wasChild) {
         if(wasRightChild) {
            parent.setRight(newRoot);
         } else {
            parent.setLeft(newRoot);
         }
      }

   }

   public boolean isLeftChild() {
      return this.parent() == null?false:this == this.parent().left();
   }

   public boolean isRightChild() {
      return this.parent() == null?false:this == this.parent().right();
   }

   public Object value() {
      return this.val;
   }

   public void setValue(Object value) {
      this.val = value;
   }

   public int hashCode() {
      if(this.isEmpty()) {
         return 0;
      } else {
         int result = this.left().hashCode() + this.right().hashCode();
         if(this.value() != null) {
            result += this.value().hashCode();
         }

         return result;
      }
   }

   public String treeString() {
      String s = "";

      for(int i = 0; i < this.depth(); ++i) {
         s = s + "\t|";
      }

      s = s + "<" + this.val + " : " + this.getHand() + ">\n";
      if(!this.left.isEmpty()) {
         s = s + this.left.treeString();
      }

      if(!this.right.isEmpty()) {
         s = s + this.right.treeString();
      }

      return s;
   }

   private String getHand() {
      return this.isRightChild()?"R":(this.isLeftChild()?"L":"Root");
   }

   public String toString() {
      if(this.isEmpty()) {
         return "<BinaryTree: empty>";
      } else {
         StringBuffer s = new StringBuffer();
         s.append("<BinaryTree " + this.value());
         if(!this.left().isEmpty()) {
            s.append(" " + this.left());
         } else {
            s.append(" -");
         }

         if(!this.right().isEmpty()) {
            s.append(" " + this.right());
         } else {
            s.append(" -");
         }

         s.append('>');
         return s.toString();
      }
   }
}

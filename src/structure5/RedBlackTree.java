package structure5;

import java.util.Iterator;
import structure5.Assert;
import structure5.RedBlackIterator;

public class RedBlackTree {

   protected RedBlackTree left;
   protected RedBlackTree right;
   protected RedBlackTree parent;
   protected Comparable value;
   protected boolean isRed;
   public static final RedBlackTree EMPTY = new RedBlackTree();


   public RedBlackTree() {
      this.value = null;
      this.parent = null;
      this.left = this.right = this;
      this.isRed = false;
   }

   public RedBlackTree(Comparable v) {
      Assert.pre(v != null, "Red-black tree values must be non-null.");
      this.value = v;
      this.parent = null;
      this.left = this.right = new RedBlackTree();
      this.isRed = false;
   }

   protected boolean isRed() {
      return this.isRed;
   }

   protected boolean isBlack() {
      return !this.isRed;
   }

   protected void setRed() {
      this.isRed = true;
   }

   protected void setRed(boolean isRed) {
      this.isRed = isRed;
   }

   protected void setBlack() {
      this.isRed = false;
   }

   protected Comparable value() {
      return this.value;
   }

   protected RedBlackTree left() {
      return this.left;
   }

   protected RedBlackTree right() {
      return this.right;
   }

   protected RedBlackTree parent() {
      return this.parent;
   }

   protected void setParent(RedBlackTree newParent) {
      this.parent = newParent;
   }

   protected void setLeft(RedBlackTree newLeft) {
      if(!this.isEmpty()) {
         if(this.left.parent() == this) {
            this.left.setParent((RedBlackTree)null);
         }

         this.left = newLeft;
         this.left.setParent(this);
      }
   }

   protected void setRight(RedBlackTree newRight) {
      if(!this.isEmpty()) {
         if(this.right.parent() == this) {
            this.right.setParent((RedBlackTree)null);
         }

         this.right = newRight;
         this.right.setParent(this);
      }
   }

   public boolean isLeftChild() {
      return this.parent() == null?false:this == this.parent().left();
   }

   public boolean isRightChild() {
      return this.parent() == null?false:this == this.parent().right();
   }

   public boolean isEmpty() {
      return this.value == null;
   }

   protected boolean isRoot() {
      return this.parent == null;
   }

   protected RedBlackTree root() {
      RedBlackTree result;
      for(result = this; !result.isRoot(); result = result.parent()) {
         ;
      }

      return result;
   }

   public int depth() {
      return this.parent() == null?0:1 + this.parent.depth();
   }

   protected void rotateRight() {
      RedBlackTree parent = this.parent();
      RedBlackTree newRoot = this.left();
      boolean wasChild = !this.isRoot();
      boolean wasLeftChild = this.isLeftChild();
      this.setLeft(newRoot.right());
      newRoot.setRight(this);
      if(wasChild) {
         if(wasLeftChild) {
            parent.setLeft(newRoot);
         } else {
            parent.setRight(newRoot);
         }
      } else {
         Assert.post(newRoot.isRoot(), "Rotate at root preserves root.");
      }

   }

   protected void rotateLeft() {
      RedBlackTree parent = this.parent();
      RedBlackTree newRoot = this.right();
      boolean wasChild = !this.isRoot();
      boolean wasRightChild = this.isRightChild();
      this.setRight(newRoot.left());
      newRoot.setLeft(this);
      if(wasChild) {
         if(wasRightChild) {
            parent.setRight(newRoot);
         } else {
            parent.setLeft(newRoot);
         }
      } else {
         Assert.post(newRoot.isRoot(), "Left rotate at root preserves root.");
      }

   }

   public RedBlackTree add(Comparable c) {
      RedBlackTree tree = this.insert(c);
      tree.setRed();
      tree.redFixup();
      return tree.root();
   }

   protected RedBlackTree insert(Comparable c) {
      if(this.isEmpty()) {
         return new RedBlackTree(c);
      } else {
         RedBlackTree result;
         if(c.compareTo(this.value()) < 0) {
            if(this.left().isEmpty()) {
               result = new RedBlackTree(c);
               this.setLeft(result);
               return result;
            } else {
               return this.left().insert(c);
            }
         } else if(this.right().isEmpty()) {
            result = new RedBlackTree(c);
            this.setRight(result);
            return result;
         } else {
            return this.right().insert(c);
         }
      }
   }

   public void redFixup() {
      if(!this.isRoot() && this.parent().isRed()) {
         RedBlackTree parent = this.parent();
         RedBlackTree grandParent = parent.parent();
         RedBlackTree aunt;
         if(parent.isLeftChild()) {
            aunt = grandParent.right();
            if(aunt.isRed()) {
               grandParent.setRed();
               aunt.setBlack();
               parent.setBlack();
               grandParent.redFixup();
            } else if(this.isRightChild()) {
               parent.rotateLeft();
               parent.redFixup();
            } else {
               grandParent.rotateRight();
               grandParent.setRed();
               parent.setBlack();
            }
         } else {
            aunt = grandParent.left();
            if(aunt.isRed()) {
               grandParent.setRed();
               aunt.setBlack();
               parent.setBlack();
               grandParent.redFixup();
            } else if(this.isLeftChild()) {
               parent.rotateRight();
               parent.redFixup();
            } else {
               grandParent.rotateLeft();
               grandParent.setRed();
               parent.setBlack();
            }
         }
      } else {
         this.root().setBlack();
      }

   }

   public RedBlackTree remove(Comparable c) {
      RedBlackTree target = this.locate(c);
      if(target.isEmpty()) {
         return this.root();
      } else {
         RedBlackTree freeNode;
         if(!target.left().isEmpty() && !target.right().isEmpty()) {
            for(freeNode = target.left(); !freeNode.right().isEmpty(); freeNode = freeNode.right()) {
               ;
            }
         } else {
            freeNode = target;
         }

         target.value = freeNode.value;
         RedBlackTree child;
         if(freeNode.left().isEmpty()) {
            child = freeNode.right();
         } else {
            child = freeNode.left();
         }

         child.setParent(freeNode.parent());
         if(!freeNode.isRoot()) {
            if(freeNode.isLeftChild()) {
               freeNode.parent().setLeft(child);
            } else {
               freeNode.parent().setRight(child);
            }
         }

         RedBlackTree result = child.root();
         if(freeNode.isBlack()) {
            child.blackFixup();
         }

         return result.root();
      }
   }

   protected void blackFixup() {
      if(!this.isRoot() && !this.isRed()) {
         RedBlackTree parent = this.parent();
         RedBlackTree sibling;
         if(this.isLeftChild()) {
            sibling = parent.right();
            if(sibling.isRed()) {
               sibling.setBlack();
               parent.setRed();
               parent.rotateLeft();
               this.blackFixup();
            } else if(sibling.left().isBlack() && sibling.right().isBlack()) {
               sibling.setRed();
               parent.blackFixup();
            } else {
               if(sibling.right().isBlack()) {
                  sibling.left().setBlack();
                  sibling.setRed();
                  sibling.rotateRight();
                  sibling = parent.right();
               }

               sibling.setRed(parent.isRed());
               parent.setBlack();
               sibling.right().setBlack();
               parent.rotateLeft();
               this.root().blackFixup();
            }
         } else {
            sibling = parent.left();
            if(sibling.isRed()) {
               sibling.setBlack();
               parent.setRed();
               parent.rotateRight();
               this.blackFixup();
            } else if(sibling.left().isBlack() && sibling.right().isBlack()) {
               sibling.setRed();
               parent.blackFixup();
            } else {
               if(sibling.left().isBlack()) {
                  sibling.right().setBlack();
                  sibling.setRed();
                  sibling.rotateLeft();
                  sibling = parent.left();
               }

               sibling.setRed(parent.isRed());
               parent.setBlack();
               sibling.left().setBlack();
               parent.rotateRight();
               this.root().blackFixup();
            }
         }
      } else {
         this.setBlack();
      }

   }

   public boolean contains(Comparable c) {
      return this.locate(c) != null;
   }

   protected RedBlackTree locate(Comparable c) {
      if(this.isEmpty()) {
         return null;
      } else {
         int relation = c.compareTo(this.value());
         return relation == 0?this:(relation < 0?this.left().locate(c):this.right().locate(c));
      }
   }

   public Comparable get(Comparable c) {
      RedBlackTree n = this.locate(c);
      return n == null?null:n.value();
   }

   public boolean consistency() {
      return this.redConsistency() && this.blackConsistency();
   }

   protected int blackHeight() {
      return this.isEmpty()?0:(this.isBlack()?1 + this.left().blackHeight():this.left().blackHeight());
   }

   protected boolean redConsistency() {
      return this.isEmpty()?true:(this.isRed() && (this.left().isRed() || this.right().isRed())?false:this.left().redConsistency() && this.right().redConsistency());
   }

   protected boolean blackConsistency() {
      if(!this.isRoot()) {
         Assert.debug("Tree consistency not tested at root.");
         return false;
      } else if(!this.isBlack()) {
         Assert.debug("Root is not black.");
         return false;
      } else if(!this.consistentlyBlackHeight(this.blackHeight())) {
         Assert.debug("Black height inconsistent.");
         return false;
      } else {
         return true;
      }
   }

   protected boolean consistentlyBlackHeight(int height) {
      if(this.isEmpty()) {
         return height == 0;
      } else {
         if(this.isBlack()) {
            --height;
         }

         return this.left().consistentlyBlackHeight(height) && this.right().consistentlyBlackHeight(height);
      }
   }

   public void print() {
      if(!this.isEmpty()) {
         this.left().print();
         System.out.println(this.value());
         this.right().print();
      }
   }

   public Iterator iterator() {
      return new RedBlackIterator(this);
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

      s = s + "<" + this.value() + " : " + this.getHand() + " : " + this.getColor() + ">\n";
      if(this.left != EMPTY) {
         s = s + this.left.treeString();
      }

      if(this.right != EMPTY) {
         s = s + this.right.treeString();
      }

      return s;
   }

   private String getHand() {
      return this.isRightChild()?"R":(this.isLeftChild()?"L":"Root");
   }

   private String getColor() {
      return this.isRed?"Red":"Black";
   }

   public String toString() {
      return this.isEmpty()?"":(this.isRed()?"(" + this.left() + this.value() + this.right() + ")":"[" + this.left() + this.value() + this.right() + "]");
   }

}

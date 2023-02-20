package structure5;

import java.util.Comparator;
import java.util.Iterator;
import structure5.BinarySearchTree;
import structure5.BinaryTree;
import structure5.NaturalComparator;
import structure5.OrderedStructure;
import structure5.SplayTreeIterator;

public class SplayTree extends BinarySearchTree implements OrderedStructure {

   public SplayTree() {
      this(new NaturalComparator());
   }

   public SplayTree(Comparator alternateOrder) {
      super(alternateOrder);
   }

   public void add(Comparable val) {
      BinaryTree newNode = new BinaryTree(val, this.EMPTY, this.EMPTY);
      if(this.root.isEmpty()) {
         this.root = newNode;
      } else {
         BinaryTree insertLocation = this.locate(this.root, val);
         Comparable nodeValue = (Comparable)insertLocation.value();
         if(this.ordering.compare(nodeValue, val) < 0) {
            insertLocation.setRight(newNode);
         } else if(!insertLocation.left().isEmpty()) {
            this.predecessor(insertLocation).setRight(newNode);
         } else {
            insertLocation.setLeft(newNode);
         }

         this.splay(this.root = newNode);
      }

      ++this.count;
   }

   public boolean contains(Comparable val) {
      if(this.root.isEmpty()) {
         return false;
      } else {
         BinaryTree possibleLocation = this.locate(this.root, val);
         if(val.equals(possibleLocation.value())) {
            this.splay(this.root = possibleLocation);
            return true;
         } else {
            return false;
         }
      }
   }

   public Comparable get(Comparable val) {
      if(this.root.isEmpty()) {
         return null;
      } else {
         BinaryTree possibleLocation = this.locate(this.root, val);
         this.splay(this.root = possibleLocation);
         return val.equals(possibleLocation.value())?(Comparable)possibleLocation.value():null;
      }
   }

   public Comparable remove(Comparable val) {
      if(this.isEmpty()) {
         return null;
      } else {
         BinaryTree location;
         if(val.equals(this.root.value())) {
            location = this.removeTop(this.root);
            --this.count;
            Comparable parent1 = (Comparable)this.root.value();
            this.root = location;
            return parent1;
         } else {
            location = this.locate(this.root, val);
            if(val.equals(location.value())) {
               --this.count;
               BinaryTree parent = location.parent();
               if(parent.right() == location) {
                  parent.setRight(this.removeTop(location));
               } else {
                  parent.setLeft(this.removeTop(location));
               }

               this.splay(this.root = parent);
               return (Comparable)location.value();
            } else {
               return null;
            }
         }
      }
   }

   protected void splay(BinaryTree splayedNode) {
      BinaryTree parent;
      while((parent = splayedNode.parent()) != null) {
         BinaryTree grandParent;
         if((grandParent = parent.parent()) == null) {
            if(splayedNode.isLeftChild()) {
               parent.rotateRight();
            } else {
               parent.rotateLeft();
            }
         } else if(parent.isLeftChild()) {
            if(splayedNode.isLeftChild()) {
               grandParent.rotateRight();
               parent.rotateRight();
            } else {
               parent.rotateLeft();
               grandParent.rotateRight();
            }
         } else if(splayedNode.isRightChild()) {
            grandParent.rotateLeft();
            parent.rotateLeft();
         } else {
            parent.rotateRight();
            grandParent.rotateLeft();
         }
      }

   }

   public Iterator iterator() {
      return new SplayTreeIterator(this.root, this.EMPTY);
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<SplayTree: size=" + this.count + " root=" + this.root + ">");
      return s.toString();
   }
}

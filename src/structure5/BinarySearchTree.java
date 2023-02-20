package structure5;

import java.util.Comparator;
import java.util.Iterator;
import structure5.AbstractStructure;
import structure5.Assert;
import structure5.BinaryTree;
import structure5.NaturalComparator;
import structure5.OrderedStructure;

public class BinarySearchTree extends AbstractStructure implements OrderedStructure {

   protected BinaryTree root;
   protected final BinaryTree EMPTY;
   protected int count;
   protected Comparator ordering;


   public BinarySearchTree() {
      this(new NaturalComparator());
   }

   public BinarySearchTree(Comparator alternateOrder) {
      this.EMPTY = new BinaryTree();
      this.root = this.EMPTY;
      this.count = 0;
      this.ordering = alternateOrder;
   }

   public boolean isEmpty() {
      return this.root == this.EMPTY;
   }

   public void clear() {
      this.root = new BinaryTree();
      this.count = 0;
   }

   public int size() {
      return this.count;
   }

   protected BinaryTree locate(BinaryTree root, Comparable value) {
      Comparable rootValue = (Comparable)root.value();
      if(rootValue.equals(value)) {
         return root;
      } else {
         BinaryTree child;
         if(this.ordering.compare(rootValue, value) < 0) {
            child = root.right();
         } else {
            child = root.left();
         }

         return child.isEmpty()?root:this.locate(child, value);
      }
   }

   protected BinaryTree predecessor(BinaryTree root) {
      Assert.pre(!root.isEmpty(), "No predecessor to middle value.");
      Assert.pre(!root.left().isEmpty(), "Root has left child.");

      BinaryTree result;
      for(result = root.left(); !result.right().isEmpty(); result = result.right()) {
         ;
      }

      return result;
   }

   protected BinaryTree successor(BinaryTree root) {
      Assert.pre(!root.isEmpty(), "Tree is non-null.");
      Assert.pre(!root.right().isEmpty(), "Root has right child.");

      BinaryTree result;
      for(result = root.right(); !result.left().isEmpty(); result = result.left()) {
         ;
      }

      return result;
   }

   public void add(Comparable value) {
      BinaryTree newNode = new BinaryTree(value, this.EMPTY, this.EMPTY);
      if(this.root.isEmpty()) {
         this.root = newNode;
      } else {
         BinaryTree insertLocation = this.locate(this.root, value);
         Comparable nodeValue = (Comparable)insertLocation.value();
         if(this.ordering.compare(nodeValue, value) < 0) {
            insertLocation.setRight(newNode);
         } else if(!insertLocation.left().isEmpty()) {
            this.predecessor(insertLocation).setRight(newNode);
         } else {
            insertLocation.setLeft(newNode);
         }
      }

      ++this.count;
   }

   public boolean contains(Comparable value) {
      if(this.root.isEmpty()) {
         return false;
      } else {
         BinaryTree possibleLocation = this.locate(this.root, value);
         return value.equals(possibleLocation.value());
      }
   }

   public Comparable get(Comparable value) {
      if(this.root.isEmpty()) {
         return null;
      } else {
         BinaryTree possibleLocation = this.locate(this.root, value);
         return value.equals(possibleLocation.value())?(Comparable)possibleLocation.value():null;
      }
   }

   public Comparable remove(Comparable value) {
      if(this.isEmpty()) {
         return null;
      } else {
         BinaryTree location;
         if(value.equals(this.root.value())) {
            location = this.removeTop(this.root);
            --this.count;
            Comparable parent1 = (Comparable)this.root.value();
            this.root = location;
            return parent1;
         } else {
            location = this.locate(this.root, value);
            if(value.equals(location.value())) {
               --this.count;
               BinaryTree parent = location.parent();
               if(parent.right() == location) {
                  parent.setRight(this.removeTop(location));
               } else {
                  parent.setLeft(this.removeTop(location));
               }

               return (Comparable)location.value();
            } else {
               return null;
            }
         }
      }
   }

   protected BinaryTree removeTop(BinaryTree topNode) {
      BinaryTree left = topNode.left();
      BinaryTree right = topNode.right();
      topNode.setLeft(this.EMPTY);
      topNode.setRight(this.EMPTY);
      if(left.isEmpty()) {
         return right;
      } else if(right.isEmpty()) {
         return left;
      } else {
         BinaryTree predecessor = left.right();
         if(predecessor.isEmpty()) {
            left.setRight(right);
            return left;
         } else {
            BinaryTree parent;
            for(parent = left; !predecessor.right().isEmpty(); predecessor = predecessor.right()) {
               parent = predecessor;
            }

            parent.setRight(predecessor.left());
            predecessor.setLeft(left);
            predecessor.setRight(right);
            return predecessor;
         }
      }
   }

   public Iterator iterator() {
      return this.root.inorderIterator();
   }

   public int hashCode() {
      return this.root.hashCode();
   }

   public String treeString() {
      return this.root.treeString();
   }

   public String toString() {
      StringBuffer s = new StringBuffer();
      s.append("<BinarySearchTree:");
      if(!this.root.isEmpty()) {
         s.append(this.root);
      }

      s.append(">");
      return s.toString();
   }
}

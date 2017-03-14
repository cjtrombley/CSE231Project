/**
 * Created by cjt on 3/10/2017.
 */

package Project;

public class BST {

    private BSTNode root;

    public void add(PersonNode node){
        if (root == null) {
            root = new BSTNode(node, null);
        }

        else {

            BSTNode nptr = root;

            while (nptr!= null) {
                if (node.getKey() < nptr.getKey()) {
                    if(nptr.getLeftChild() == null) {
                        nptr.setLeftChild(new BSTNode(node, nptr));
                        return;
                    }
                    else {
                        nptr = nptr.getLeftChild();
                    }


                    }
                 else if (node.getKey() > nptr.getKey()) {
                    if(nptr.rightChild == null) {
                        nptr.rightChild = new BSTNode(node, nptr);
                        return;
                    }
                    else {
                        nptr = nptr.rightChild;
                    }
                }
            }

        }
    }

    public void display(BSTNode root) {
        if(root != null) {
            display(root.leftChild);
            System.out.println("-> " + root.getName());
            display(root.rightChild);
        }
    }


    public BSTNode getRoot() { return this.root; }


    private class BSTNode extends PersonNode {

        private BSTNode leftChild;
        private BSTNode rightChild;
        private BSTNode parent;

        public BSTNode() {
        }

        public BSTNode(PersonNode node, BSTNode nodeParent) {
            this.leftChild = null;
            this.rightChild = null;

            this.parent = nodeParent;
        }

        public BSTNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(BSTNode node) {
            this.leftChild = node;

        }
        public BSTNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(BSTNode node) {
            this.rightChild = node;
        }

        public BSTNode getParent() {
            return parent;
        }

        public void setParent(BSTNode node) {
            this.parent = node;
        }
    }
}

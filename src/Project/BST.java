/**
 * Created by cjt on 3/10/2017.
 */

package Project;

import java.io.BufferedWriter;
import java.io.IOException;

class BST {

    BSTNode root;

    BST() {
        root = null;
    }




    /**
     * Adds node to BinarySearchTree.
     *
     * @param node Node to be added to BST.
     */
    void add(PersonNode node) {
         if (root == null) {
             root = new BSTNode(node);
         } else {

             BSTNode nptr = root;

             while (nptr != null) {
                 if (node.key < nptr.key) {
                     if (nptr.leftChild == null) {
                         nptr.leftChild = new BSTNode(node);
                         return;
                     } else {
                         nptr = nptr.leftChild;
                     }


                 } else if (node.key > nptr.key) {
                     if (nptr.rightChild == null) {
                         nptr.rightChild = new BSTNode(node);
                         return;
                     } else {
                         nptr = nptr.rightChild;
                     }
                 }
             }
         }
     }




    /**
     * Print In-order traversal of BST rooted at node root
     * to the terminal window.
     *
     * @param root Root node of BST
     */
    void display(BSTNode root) {
        if (root != null) {
            display(root.leftChild);
            System.out.println("-> " + root.name);
            display(root.rightChild);
        }
    }



    /**
     * Search through BST for node with name matching
     * the search parameter.
     *
     *
     * @param searchName Name to be searched
     * @return Node with name matching search param
     */
    BSTNode search(String searchName) {

        int searchKey = Math.abs(searchName.hashCode());
        BSTNode nptr = root;

        while (nptr != null) {
            if (nptr.key == searchKey) {
                return nptr;
            } else {
                if (searchKey < nptr.key) {
                    nptr = nptr.leftChild;
                } else if (searchKey > nptr.key) {
                    nptr = nptr.rightChild;
                }
            }
        }
        //key not found
        return null;

    }



    /**
     * Remove node with name matching search
     * parameter from the BST.
     *
     * @param delString Name to be deleted.
     */
    void remove(String delString) {
        int delKey = Math.abs(delString.hashCode());
        root = delete(delKey, root);
    }



    /**
     * Remove node with name matching key from the
     * Binary Search Tree.
     *
     * @param delKey name to be searched for
     * @param node root of binary tree to be searched
     * @return Root node with edited binary tree
     */
    private BSTNode delete(int delKey, BSTNode node) {
        if ( node == null) { //key does not exist in BST
            System.out.println("Name not found");
            return null;
        } else if (delKey < node.key) { //search left subtree
            node.leftChild = delete(delKey, node.leftChild);
        } else if (delKey > node.key) { //search right subtree
            node.rightChild = delete(delKey, node.rightChild);
        }
        //if node has 2 children, find minimum, copy it to deleted node,
        //delete the minimum
        else if (node.leftChild != null && node.rightChild != null) {
            BSTNode min = minValue(node.rightChild);

            node.name = min.name;
            node.address = min.address;
            node.phoneNumber = min.phoneNumber;
            node.key = min.key;

            node.rightChild = delete(min.key, node.rightChild);
        } else {
            //if node only has one child, set the node to the appropriate child
            node = node.leftChild!= null ? node.leftChild: node.rightChild;
        }
        return node;
    }




    /**
     * Returns the node with the smallest value rooted
     * at node.
     *
     * @param node root node of BST
     * @return Leftmost node of tree rooted at node
     */
    BSTNode minValue(BSTNode node) {
        if (node == null) {
            return null;
        }
        else if (node.leftChild == null) {
            return node;
        }
        else {
            return minValue(node.leftChild);
        }
    }



    /**
     * Prints the BST rooted at node root to the file
     * buffered by BufferedWriter.
     *
     * @param root Root node of BST to be printed
     * @param bw BufferedWriter of outputFile.
     */
    void printToFile(BSTNode root, BufferedWriter bw) {
        try {
            if (root!= null) {
                printToFile(root.leftChild, bw);
                bw.write(((PersonNode)root).toString());
                bw.newLine();
                bw.flush();
                printToFile(root.rightChild, bw);
            }
        } catch (IOException e) {
            System.err.println("Error printing to file.");
        }
    }



    private class BSTNode extends PersonNode {
        /**
         * Private internal class representing
         * a single BinarySearchTree node of
         * data.
         */


        BSTNode leftChild;
        BSTNode rightChild;

        BSTNode(PersonNode node) {
            this.name = node.name;
            this.address = node.address;
            this.phoneNumber = node.phoneNumber;
            this.key = node.key;

            this.leftChild = null;
            this.rightChild = null;
        }
    }
}






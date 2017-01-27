/** @author Robert Keith Campbell
 *  @version 2017/01/27
 *
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 implements Worksheet2Interface {
    // Exercise 1

    /** negateAll is a method which when given a tree of integers a,
     * returns a new tree containing all the elements of a with their
     * sign negated.
     *
     * @param t a tree of integers
     * @return a tree with all elements of a with their signs negated
     */
    public static Tree negateAll(Tree t) {
        if (t.isEmpty()) {
            return new Tree();
        }

        else {
            return new Tree(-t.getValue(), negateAll(t.getLeft()), negateAll(t.getRight()));
        }
    }

    // Exercise 2

    /** mirror is a method which when given a tree of a,
     * returns a tree that is the mirror image of a along the
     * left-right axis
     *
     * @param t a tree of integers
     * @return a tree which is a mirror of the given tree along the left-right axis
     */
    public static Tree mirror(Tree t) {
        if (t.isEmpty()) {
            return new Tree();
        }

        else {
            return new Tree(t.getValue(), mirror(t.getRight()), mirror(t.getLeft()));

        }
    }

    // Exercise 3

    /** postorder is a method which when given a tree a, returns a List containing
     * all the values in a by traversing the nodes in postorder, i.e. for every node,
     * all the values in the left subtree should be listed first, then all the values
     * in the right subtree and then finally the value in the node itself
     *
     * @param t a tree
     * @return a List containing all the values in the tree traversed in postorder
     */
    public static List postorder(Tree t) {
        if (t.isEmpty()) {
            return List.empty();
        }
        else {
            return ListOps.addToEnd(ListOps.append(postorder(t.getLeft()), postorder(t.getRight())), t.getValue());

        }
    }


    // Exercise 4

    /** allPositive is a method which when given a tree of integers a, returns a boolean
     * value indicating whether all the values in its nodes are positive i.e. >= 0
     * Note: if the tree is empty then the function will return "true"
     *
     * @param a a tree of integers
     * @return a boolean indicating whether all values in the tree are >= 0
     */
    public static boolean allPositive(Tree a) {
        if (a.isEmpty()) {
            return true;
        }

        else if (a.getValue() < 0) {
            return false;
        }

        else {
//			return (allPositive(a.getLeft()) == false || allPositive(a.getRight()) == false) ? false : true;
            return (allPositive(a.getLeft()) && allPositive(a.getRight()));
        }
    }

    // Exercise 5 BINARY SEARCH TREES!!
    // Use a helper for this one

    /** isSearchTree is a method which when given a tree of integers a, returns a boolean value
     * indicating whether a is a binary search tree.
     * i.e:
     *      All values stored in the left subtree of the node are less than the value at the node
     *      All values stored in the right subtree of the node are greater than the value at the node
     *
     * Note: if the tree is empty then the boolean "true" will be returned
     *
     * @param a a tree of integers
     * @return a boolean result indicating if the tree a, is a binary search tree
     */
    public static boolean isSearchTree(Tree a) {
        if (a.isEmpty()) {
            return true;
        }

        else {
            return helpIsSearchTree(a, Integer.MIN_VALUE, Integer.MAX_VALUE);

        }
    }

    /** helpIsSearchTree is a method which acts as a helper function for isSearchTree(Tree a)
     * It works by comparing the value at each node with a min and max value which are updated
     * recursively as the program works through the each subsequent subtree.
     *
     * @param a a tree of integers
     * @param min the minimum value that the node of a tree/subtree must be greater than
     * @param max the maximum value that the node of a tree/subtree must be less than
     * @return a boolean indicating whether the tree is a binary search tree
     */
    public static boolean helpIsSearchTree(Tree a, int min, int max) {
        if (a.isEmpty()) {
            return true;
        }

        if (a.getValue() <= min || a.getValue() >= max) {
            return false;
        }

        else {
            return (helpIsSearchTree(a.getLeft(), min, a.getValue()) && helpIsSearchTree(a.getRight(), a.getValue(), max));

        }
    }

    // Exercise 6

    /** printDescending is a method which when given a binary search tree
     * of integers a, prints the values stored in it in descending order.
     * It does this without building a separate list of the values.
     * Note: each value is separated by a single character of whitespace.
     *
     * @param a a Tree of integers
     */
    public static void printDescending(Tree a) {
        if (a.isEmpty()) {
            System.out.print("");
        }
        else {
            printDescending(a.getRight());
            System.out.print(a.getValue() + " ");
            printDescending(a.getLeft());
        }
    }

    // Exercise 7

    /** max is a method which, assuming the argument tree is a binary search tree,
     * finds the maximum value stored in the tree. The method recursively calls itself
     * on the right subtree until the final value, which must be the max in a BST, is found.
     *
     * @param a a Binary Search Tree of integers
     * @return the maximum value stored in the tree
     */
    public static int max(Tree a) {
        if (a.isEmpty()) {
            throw new IllegalStateException("The tree is empty!");
        }
        else if (a.getRight().isEmpty()) {
            return a.getValue();
        }
        else {
            return max(a.getRight());
        }
    }
    /** min is a method which, assuming the argument tree is a binary search tree,
     * finds the minimum value stored in the tree. The method recursively calls itself
     * on the left subtree until the final value, which must be the min in a BST, is found.
     *
     * @param a a Binary Search Tree of integers
     * @return the minimum value stored in the tree
     */
    public static int min(Tree a) {
        if (a.isEmpty()) {
            throw new IllegalStateException("The tree is empty!");
        }
        else if (a.getLeft().isEmpty()) {
            return a.getValue();
        }
        else {
            return min(a.getLeft());
        }
    }

    // Exercise 8
    // You did all this without bloody planning it again. DO IT ON PAPER!!

    /** delete is a method which, assuming the argument tree a is a Binary Search Tree,
     * deletes the value x from a and returns the resulting tree. THe original tree is not
     * altered instead a new tree is built which is contains all the values of a except for one
     * copy of x.
     *
     * @param x the value you wish to remove from the binary search tree
     * @param a a binary search tree of integers
     * @return a BST with all values of a except for one copy of x
     */
    public static Tree delete(int x, Tree a) {
        if (a.isEmpty()) {
            return new Tree();
        }
        else if (a.getValue() == x && a.getLeft().isEmpty() && a.getRight().isEmpty()) {
            return new Tree();
        }
        else if (a.getValue() == x && a.getLeft().isEmpty()) {
            int max = max(a.getRight());
            return new Tree(max, new Tree(), delete(max, a.getRight()));
        }
        else if (a.getValue() == x) {
            int max = max(a.getLeft());
            return new Tree(max, delete(max, a.getLeft()), a.getRight());
        }
        else {
            return (x < a.getValue()) ?
                    new Tree(a.getValue(), delete(x, a.getLeft()), a.getRight())
                    : new Tree(a.getValue(), a.getLeft(), delete(x, a.getRight()));
        }
    }

    // Exercise 9

    /** isHeightBalanced is a method which when given a tree of integers a, checks
     * to see if it is height-balanced. It accomplishes this by calculating the
     * different in heights of the left and right subtrees.
     *
     * @param a a Tree of integers
     * @return a boolean indicating if the tree is balanced or not
     */
    public static boolean isHeightBalanced(Tree a) {
        if (a.isEmpty()) {
            return true;
        }
        else if (a.getLeft().isEmpty()) {
            return a.getRight().getHeight() <= 1;
        }
        else if (a.getRight().isEmpty()) {
            return a.getLeft().getHeight() <= 1;
        }
        else if (-1 > (a.getLeft().getHeight()-a.getRight().getHeight()) || (a.getLeft().getHeight()-a.getRight().getHeight()) > 1) {
            return false;
        }
        else {
            return (isHeightBalanced(a.getLeft()) && isHeightBalanced(a.getRight()));
        }
    }

    // Exercise 10
    // Gonna need helper methods for balance(Tree a), rotateLeft(Tree a) and rotateRight(Tree a)

    /** rotateLeft is a method which takes a tree and returns a new tree rotated once to the left.
     *  If the tree or its right subtree are empty then the method simply returns the tree unaltered.
     *
     * @param a a Tree
     * @return a new Tree which contains all the same values as a after one single left rotation
     */
    public static Tree rotateLeft(Tree a) {
        if (a.isEmpty() || a.getHeight() == 1) {
            return a;
        }
        else if (a.getRight().getHeight() == 0) {
            return a;
        }
        else {
            Tree x = new Tree(a.getValue(), a.getLeft(), a.getRight().getLeft());
            return new Tree(a.getRight().getValue(), x, a.getRight().getRight());
        }
    }

    /** rotateRight is a method which takes a tree and returns a new tree rotated once to the right.
     *  If the tree or its left subtree are empty then the method simply returns the tree unaltered.
     *
     * @param a a Tree
     * @return a new Tree which contains all the same values as a after one single right rotation
     */
    public static Tree rotateRight(Tree a) {
        if (a.isEmpty() || a.getHeight() == 1) {
            return a;
        } else if (a.getLeft().getHeight() == 0) {
            return a;
        }
        else {
            Tree x = new Tree(a.getValue(), a.getLeft().getRight(), a.getRight());
            return new Tree(a.getLeft().getValue(), a.getLeft().getLeft(), x);
        }
    }

    /** balance is a function which takes a previously A.V.L (Height-balanced tree)
     * which has had a new element added to it thus resulting in an imbalance.
     * It then performs left and/or right rotations on the tree and/or its subtrees
     * to re-balance the tree.
     * It does not guarantee balancing for trees which have subtree height differences of
     * less than -2 or greater than 2.
     * Where height difference is calculated for the Tree a using:
     *      a.getLeft().getHeight() - a.getRight().getHeight() = height difference
     *
     *
     * @param a an height-IMBALANCED AVL binary search tree
     * @return a height-balanced AVL binary search tree
     */
    public static Tree balance(Tree a) {
//        int heightDiff = a.getLeft().getHeight() - a.getRight().getHeight();
        if (a.isEmpty()) {
            return a;
        }
        // Tree is right-heavy
        else if (a.getLeft().getHeight() - a.getRight().getHeight() < -1) {
            // Subtree is right-heavy
            if (a.getRight().getLeft().getHeight() < a.getRight().getRight().getHeight()) {
                return rotateLeft(a);
            }
            // Subtree is left-heavy
            else return rotateLeft(new Tree(a.getValue(), a.getLeft(), rotateRight(a.getRight())));
        }
        // Tree is left-heavy
        else if (a.getLeft().getHeight() - a.getRight().getHeight() > 1) {
            // Subtree is left-heavy
            if (a.getLeft().getLeft().getHeight() > a.getLeft().getRight().getHeight()) {
                return rotateRight(a);
            }
            // Subtree must be right-heavy
            else return rotateRight(new Tree(a.getValue(), rotateLeft(a.getLeft()), a.getRight()));
        }
        // If already balanced just return the tree
        else {
            return a;
        }
    }

    /** insertHB is a method which takes a height-balanced integer BST a, and returns a new
     * height-balanced integer BST which contains all of the values of a along with the newly
     * inserted value x.
     * If the HB-BST a already contains the value x, then the tree a is returned unaltered as
     * we do not allow duplicate entries in the tree.
     *
     * @param x the value you wish to insert into the tree
     * @param a a height-balanced binary search tree of integers
     * @return a new height-balanced binary search tree with all values of a and the new value x
     */
    public static Tree insertHB(int x, Tree a) {
        if (a.isEmpty()) {
            return new Tree(x);
        }
        else if (x == a.getValue()) {
            return a;
        }
        else {
            if (x < a.getValue()) {
                Tree b = new Tree(a.getValue(), insertHB(x, a.getLeft()), a.getRight());
                return balance(b);
//                return b;
            }
            else {
                Tree b = new Tree(a.getValue(), a.getLeft(), insertHB(x, a.getRight()));
                return balance(b);
//                return b;
            }
        }
    }

    /** insertHB is a method which takes a height-balanced integer BST a, and returns a new
     * height-balanced integer BST which contains all of the values of a except for the value x.
     * If the HB-BST a doesn't contain the value x, then the tree a is returned unaltered.
     *
     * @param x the value you wish to remove from the tree
     * @param a a height-balanced binary search tree of integers
     * @return a new height-balanced binary search tree with all values of a except for x
     */
    public static Tree deleteHB(int x, Tree a) {
        if (a.isEmpty()) {
            return new Tree();
        }
        else if (a.getValue() == x && a.getLeft().isEmpty() && a.getRight().isEmpty()) {
            return new Tree();
        }
        else if (a.getValue() == x && a.getLeft().isEmpty()) {
            int max = max(a.getRight());
            return balance(new Tree(max, new Tree(), deleteHB(max, a.getRight())));
        }
        else if (a.getValue() == x) {
            int max = max(a.getLeft());
            return balance(new Tree(max, deleteHB(max, a.getLeft()), a.getRight()));
        }
        else {
            return (x < a.getValue()) ?
                    balance(new Tree(a.getValue(), deleteHB(x, a.getLeft()), a.getRight()))
                    : balance(new Tree(a.getValue(), a.getLeft(), deleteHB(x, a.getRight())));
        }
    }

    public static void main(String[] args) {
//        System.out.println("************* LEFT ROTATE *************");
//
//        Tree t2 = new Tree(20, new Tree(), new Tree(30, new Tree(), new Tree(40)));
//
//        Tree.print(t2);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t2) + "\n");
//
//        Tree t2result = rotateLeft(t2);
//        Tree.print(t2result);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t2result) + "\n");
//
//        Tree t2balance = balance(t2);
//        Tree.print(t2balance);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t2balance) + "\n");

//        System.out.println("************* RIGHT ROTATE *************");
//
//        Tree t1 = new Tree(20, new Tree(10, new Tree(5), new Tree()), new Tree());
//
//        Tree.print(t1);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t1) + "\n");
//
//        Tree t1result = rotateRight(t1);
//        Tree.print(t1result);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t1result) + "\n");
//
//        Tree t1balance = balance(t1);
//        Tree.print(t1balance);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t1balance) + "\n");
//
//        System.out.println("************* LEFT RIGHT ROTATE *************");
//
//        Tree t3 = new Tree(40, new Tree(20, new Tree(), new Tree(30)), new Tree());
//
//        Tree.print(t3);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t3) + "\n");
//
//        Tree t3result = rotateRight(new Tree(t3.getValue(), rotateLeft(t3.getLeft()), t3.getRight()));
//        Tree.print(t3result);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t3result) + "\n");
//
//        Tree t3balance = balance(t3);
//        Tree.print(t3balance);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t3balance) + "\n");
//
//        System.out.println("************* RIGHT LEFT ROTATE *************");
//
//        Tree t4 = new Tree(40, new Tree(), new Tree(50, new Tree(45), new Tree()));
//
//        Tree.print(t4);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t4) + "\n");
//
//        Tree t4result = rotateLeft(new Tree(t4.getValue(), t4.getLeft(), rotateRight(t4.getRight())));
//        Tree.print(t4result);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t4result) + "\n");
//
//        Tree t4balance = balance(t4);
//        Tree.print(t4balance);
//        System.out.println("\nIs it balanced? " + isHeightBalanced(t4balance) + "\n");

//        System.out.println("************** INSERT + BALANCE *****************");
//
//        Tree t5 = new Tree(10, new Tree(5), new Tree());
//        Tree.print(t5);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t5) + "\n");
//
//        Tree t5result = insertHB(1, t5);
//        Tree.print(t5result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t5result) + "\n****************\n");
//
//        Tree t6 = new Tree(10, new Tree(), new Tree(15));
//        Tree.print(t6);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t6) + "\n");
//
//        Tree t6result = insertHB(19, t6);
//        Tree.print(t6result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t6result) + "\n****************\n");
//
//        Tree t7 = new Tree(10, new Tree(), new Tree(15));
//        Tree.print(t7);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t7) + "\n");
//
//        Tree t7result = insertHB(19, t7);
//        Tree.print(t7result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t7result) + "\n****************\n");

//        System.out.println("************** DELETE + BALANCE *****************");
//
//        Tree t8 = new Tree(10, new Tree(5), new Tree());
//        Tree.print(t8);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t8) + "\n");
//
//        Tree t8result = deleteHB(10, t8);
//        Tree.print(t8result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t8result) + "\n****************\n");
//
//        Tree t9 = new Tree(10, new Tree(5, new Tree(1), new Tree(6)), new Tree(15, new Tree(14), new Tree(20, new Tree(19), new Tree())));
//        Tree.print(t9);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t9) + "\n");
//
//        Tree t9result = deleteHB(14, t9);
//        Tree.print(t9result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t9result) + "\n****************\n");

//
//        System.out.println("************** DELETE + BALANCE *****************");
//
//        Tree t10 = new Tree(20, new Tree(10, new Tree(5, new Tree(3, new Tree(2, new Tree(1), new Tree()), new Tree()), new Tree()), new Tree(6)), new Tree(25));
//        Tree.print(t10);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t10) + "\n");
//
//        Tree t10result = deleteHB(1, t10);
//        Tree.print(t10result);
//        System.out.println("\n\nIs it balanced? " + isHeightBalanced(t10result) + "\n");

        Tree t11 = new Tree(5, new Tree(1, new Tree(0), new Tree(4)), new Tree(6));
        System.out.println(t11);

        System.out.println(deleteHB(5, t11));
    }
}

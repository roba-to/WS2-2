/** @author Robert Keith Campbell
 *  @version 2017/01/25
 *
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 implements Worksheet2Interface {
    // Exercise 1

    public static Tree negateAll(Tree t) {
        if (t.isEmpty()) {
            return new Tree();
        }

        else {
            return new Tree(-t.getValue(), negateAll(t.getLeft()), negateAll(t.getRight()));
        }
    }

    // Exercise 2

    public static Tree mirror(Tree t) {
        if (t.isEmpty()) {
            return new Tree();
        }

        else {
            return new Tree(t.getValue(), mirror(t.getRight()), mirror(t.getLeft()));

        }
    }

    // Exercise 3

    public static List postorder(Tree t) {
        if (t.isEmpty()) {
            return List.empty();
        }
        else {
            return ListOps.addToEnd(ListOps.append(postorder(t.getLeft()), postorder(t.getRight())), t.getValue());

        }
    }


    // Exercise 4

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

    public static boolean isSearchTree(Tree a) {
        if (a.isEmpty()) {
            return true;
        }

        else {
            return helpIsSearchTree(a, Integer.MIN_VALUE, Integer.MAX_VALUE);

        }
    }

    /** helpIsSearchTree is a function which acts as a helper function for isSearchTree(Tree a)
     *
     * @param a
     * @param min
     * @param max
     * @return
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
//		Redundant code!!! It doesn't need to check this!
//		else if (a.getValue() == x && a.getRight().isEmpty()) {
//			int min = min(a.getLeft());
//			return new Tree(min, delete(min, a.getLeft()), new Tree());
//		}
        else if (a.getValue() == x) {
            int newHead = a.getLeft().getValue();
            return new Tree(newHead, delete(newHead, a.getLeft()), a.getRight());
        }
        else {
            return (x < a.getValue()) ?
                    new Tree(a.getValue(), delete(x, a.getLeft()), a.getRight())
                    : new Tree(a.getValue(), a.getLeft(), delete(x, a.getRight()));
        }
    }

    // Exercise 9

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

    public static Tree rotateLeft(Tree a) {
        if (a.isEmpty()) {
            return a;
        }
        else {
            Tree x = new Tree(a.getValue(), a.getLeft(), a.getRight().getLeft());
            return new Tree(a.getRight().getValue(), x, a.getRight().getRight());
        }
    }

    public static Tree rotateRight(Tree a) {
        if (a.isEmpty()) {
            return a;
        }
        else {
            Tree x = new Tree(a.getValue(), a.getLeft().getRight(), a.getRight());
            return new Tree(a.getLeft().getValue(), a.getLeft().getLeft(), x);
        }
    }

    public static Tree balance(Tree a) {
        int heightDiff = a.getLeft().getHeight() - a.getRight().getHeight();
        if (heightDiff < -1) {
            if (a.getLeft().getHeight() > a.getRight().getHeight()) {
                // do a double rotation
                return a;
            }
            else {
                // do a single rotation
                return a;
            }
        }
        else if (heightDiff > 1) {
            if (a.getRight().getHeight() <  a.getLeft().getHeight()) {
                // do a double rotation
                return a;
            }
            else {
                // do a single rotation
                return a;
            }
        }
        else {
            return a;
        }
    }

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

    public static Tree deleteHB(int x, Tree a) {
        return new Tree();
    }

    public static void main(String[] args) {

        Tree t7 = new Tree(40, new Tree(), new Tree(50));
        Tree.print(t7);
        System.out.println("\nIs it balanced?!  " + isHeightBalanced(t7) + "\n");
        Tree t8 = insertHB(45, t7);
        Tree.print(t8);
        System.out.println("\nIs it balanced?!  " + isHeightBalanced(t8) + "\n");
//        Tree t9 = balance(t8);
//        Tree.print(t9);
//        System.out.println("\nIs it balanced?!  " + isHeightBalanced(t9) + "\n");
    }
}

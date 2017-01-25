import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Robert Keith Campbell
 * @created 2017/01/21
 * @version 2017/01/25
 *
 */

public class Worksheet2Test {

    @Test
    public void negateAllTest() {
        Tree t1 = new Tree();
        Tree expected = new Tree();
        assertEquals(expected, Worksheet2.negateAll(t1));

        t1 = new Tree(2);
        expected = new Tree(-2);
        assertEquals(expected, Worksheet2.negateAll(t1));

        t1 = new Tree(3, new Tree(2, new Tree(1), new Tree()), new Tree(4));
        expected = new Tree(-3, new Tree(-2, new Tree(-1), new Tree()), new Tree(-4));
        assertEquals(expected, Worksheet2.negateAll(t1));

    }

    @Test
    public void mirrorTest() {
        Tree t1 = new Tree();
        Tree expected = new Tree();
        assertEquals(expected, Worksheet2.mirror(t1));

        t1 = new Tree(2);
        expected = new Tree(2);
        assertEquals(expected, Worksheet2.mirror(t1));

        t1 = new Tree(2, new Tree(1), new Tree());
        expected = new Tree(2, new Tree(), new Tree(1));
        assertEquals(expected, Worksheet2.mirror(t1));

        t1 = new Tree(2, new Tree(1), new Tree(3));
        expected = new Tree(2, new Tree(3), new Tree(1));
        assertEquals(expected, Worksheet2.mirror(t1));

    }

    @Test
    public void allPositiveTest() {
        Tree t1 = new Tree();
        assertTrue(Worksheet2.allPositive(t1));

        t1 = new Tree(2);
        assertTrue(Worksheet2.allPositive(t1));

        t1 = new Tree(-9);
        assertFalse(Worksheet2.allPositive(t1));

        t1 = new Tree(20,
                //left subtree
                new Tree(10, new Tree(5, new Tree(3), new Tree()), new Tree(15, new Tree(13), new Tree(17, new Tree(16), new Tree(19)))),
                //right subtree
                new Tree(30, new Tree(25, new Tree(23), new Tree(27)), new Tree(35, new Tree(33, new Tree(31), new Tree()), new Tree(37, new Tree(36), new Tree()))));
        assertTrue(Worksheet2.allPositive(t1));

        t1 = Worksheet2.negateAll(t1);
        assertFalse(Worksheet2.allPositive(t1));

        t1 = new Tree(20,
                //left subtree
                new Tree(10, new Tree(5, new Tree(3), new Tree()), new Tree(15, new Tree(13), new Tree(17, new Tree(16), new Tree(19)))),
                //right subtree (note -31 here)
                new Tree(30, new Tree(25, new Tree(23), new Tree(27)), new Tree(35, new Tree(33, new Tree(-31), new Tree()), new Tree(37, new Tree(36), new Tree()))));
        assertFalse(Worksheet2.allPositive(t1));

        t1 = new Tree(10, new Tree(9, new Tree(6), new Tree(8)), new Tree(11, new Tree(15), new Tree(12, new Tree(10), new Tree(-9))));
        assertFalse(Worksheet2.allPositive(t1));

    }

    @Test
    public void isSearchTreeTest() {
        Tree t1 = new Tree(); //valid
        Tree t2 = new Tree(20); //valid
        Tree t3 = new Tree(20, new Tree(19), new Tree(21)); //valid
        Tree t4 = new Tree(1, new Tree(2), new Tree()); //INVALID

        assertTrue(Worksheet2.isSearchTree(t1));
        assertTrue(Worksheet2.isSearchTree(t2));
        assertTrue(Worksheet2.isSearchTree(t3));
        assertFalse(Worksheet2.isSearchTree(t4));

        Tree t5 = new Tree(20,
                //left subtree
                new Tree(10, new Tree(5, new Tree(3), new Tree()), new Tree(15, new Tree(13), new Tree(17, new Tree(16), new Tree(19)))),
                //right subtree
                new Tree(30, new Tree(25, new Tree(23), new Tree(27)), new Tree(35, new Tree(33, new Tree(31), new Tree()), new Tree(37, new Tree(36), new Tree()))));

        assertTrue(Worksheet2.isSearchTree(t5));

        Tree t6 = new Tree(20,
                //left subtree
                new Tree(10, new Tree(5, new Tree(3), new Tree()), new Tree(15, new Tree(13), new Tree(17, new Tree(16), new Tree(19)))),
                //right subtree (Final 38 is an INVALID input)
                new Tree(30, new Tree(25, new Tree(23), new Tree(27)), new Tree(35, new Tree(33, new Tree(31), new Tree()), new Tree(37, new Tree(38), new Tree()))));
        assertFalse(Worksheet2.isSearchTree(t6));

        Tree t7 = new Tree(3, new Tree(2, new Tree(1), new Tree(4)), new Tree(5));
        assertFalse(Worksheet2.isSearchTree(t7));

        Tree t8 = new Tree(10, new Tree(8, new Tree(), new Tree(11)), new Tree(20, new Tree(), new Tree(30)));
        assertFalse(Worksheet2.isSearchTree(t8));

        Tree t9 = new Tree(10, new Tree(9), new Tree(11));
        assertTrue(Worksheet2.isSearchTree(t9));

        Tree t10 = new Tree(10, new Tree(9, new Tree(8, new Tree(7, new Tree(6, new Tree(5, new Tree(4), new Tree()), new Tree()), new Tree()), new Tree()), new Tree()), new Tree(11));
        assertTrue(Worksheet2.isSearchTree(t10));
    }

    @Test
    public void testHelpSearchTree() {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        Tree t1 = new Tree();
        assertTrue(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20);
        assertTrue(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(), new Tree(30));
        assertTrue(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(10), new Tree());
        assertTrue(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(30), new Tree(10));
        assertFalse(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(30), new Tree(30));
        assertFalse(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(10), new Tree(30));
        assertTrue(Worksheet2.helpIsSearchTree(t1, min, max));

        t1 = new Tree(20, new Tree(20), new Tree(20));
        assertFalse(Worksheet2.helpIsSearchTree(t1, min, max));

    }

    @Test(expected = IllegalStateException.class)
    public void testMaxException() {
        Tree t1 = new Tree();

        Worksheet2.max(t1);
    }

    @Test
    public void testMax() {
        Tree t1 = new Tree(20);
        int expected = 20;

        assertEquals(Worksheet2.max(t1), expected);

        expected = 30;

        t1 = new Tree(20, new Tree(10), new Tree(30));
        assertEquals(Worksheet2.max(t1), expected);

        t1 = new Tree(20, new Tree(10, new Tree(5), new Tree(15, new Tree(), new Tree(19))), new Tree(30));
        assertEquals(Worksheet2.max(t1), expected);

        t1 = new Tree(20, new Tree(10, new Tree(5), new Tree(15, new Tree(), new Tree(19))), new Tree(30, new Tree(25, new Tree(), new Tree(29)), new Tree()));
        assertEquals(Worksheet2.max(t1), expected);
    }

    @Test
    public void minTest() {
        Tree t1 = new Tree(20);
        int expected = 20;

        assertEquals(Worksheet2.min(t1), expected);

        expected = 10;
        t1 = new Tree(20, new Tree(10), new Tree(30));
        assertEquals(Worksheet2.min(t1), expected);

        expected = 5;
        t1 = new Tree(20, new Tree(10, new Tree(5), new Tree(15, new Tree(), new Tree(19))), new Tree(30));
        assertEquals(Worksheet2.min(t1), expected);

        t1 = new Tree(20, new Tree(10, new Tree(5), new Tree(15, new Tree(), new Tree(19))), new Tree(30, new Tree(25, new Tree(), new Tree(29)), new Tree()));
        assertEquals(Worksheet2.min(t1), expected);
    }

    @Test
    public void deleteTest() {
        Tree t1 = new Tree();
        Tree expected = new Tree();
        int x = 10;

        assertTrue(expected.equals(Worksheet2.delete(x, t1)));

        t1 = new Tree(10);
        assertTrue(expected.equals(Worksheet2.delete(x, t1)));

//    	10
//    	   |
//    	   |- 20
//    	   |
//    	   |- 5
        t1 = new Tree(10, new Tree(5), new Tree(20));
        expected = new Tree(5, new Tree(), new Tree(20));
        assertTrue(expected.equals(Worksheet2.delete(x, t1)));

        x = 20;
        expected = new Tree(10, new Tree(5), new Tree());
        assertTrue(expected.equals(Worksheet2.delete(x, t1)));

        x = 5;
        expected = new Tree(10, new Tree(), new Tree(20));
        assertTrue(expected.equals(Worksheet2.delete(x, t1)));

//    	5
//    	   |
//    	   |- 8
//    	   |   |
//    	   |   |- 9
//    	   |   |   |
//    	   |   |   |- 11
//    	   |   |   |   |
//    	   |   |   |   |- [nil]
//    	   |   |   |   |
//    	   |   |   |   |- 10
//    	   |   |   |
//    	   |   |   |- [nil]
//    	   |   |
//    	   |   |- 7
//    	   |       |
//    	   |       |- [nil]
//    	   |       |
//    	   |       |- 6
//    	   |
//    	   |- 3
//    	       |
//    	       |- 4
//    	       |
//    	       |- 2
//    	           |
//    	           |- [nil]
//    	           |
//    	           |- 1
        Tree t2 = new Tree(5, new Tree(3, new Tree(2, new Tree(1), new Tree()), new Tree(4)), new Tree(8, new Tree(7, new Tree(6), new Tree()), new Tree(9, new Tree(), new Tree(11, new Tree(10), new Tree()))));
//    	Remove the number 8
        x = 8;
        expected = new Tree(5, new Tree(3, new Tree(2, new Tree(1), new Tree()), new Tree(4)), new Tree(7, new Tree(6), new Tree(9, new Tree(), new Tree(11, new Tree(10), new Tree()))));
        assertTrue(expected.equals(Worksheet2.delete(x, t2)));

//    	Remove the number 3
        x = 3;
        expected = new Tree(5, new Tree(2, new Tree(1), new Tree(4)), new Tree(8, new Tree(7, new Tree(6), new Tree()), new Tree(9, new Tree(), new Tree(11, new Tree(10), new Tree()))));
        assertTrue(expected.equals(Worksheet2.delete(x, t2)));

    }

    @Test
    public void isHeightBalancedTest() {
        Tree t1 = new Tree();
//    	Empty tree therefore balanced
        assertTrue(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20);
//    	Balanced tree with single node
        assertTrue(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(), new Tree(25));
//    	Balanced Tree with right node of height 1
        assertTrue(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(10), new Tree(25));
//    	Balanced Tree with left and right nodes of height 1
        assertTrue(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(10), new Tree());
//    	Balanced Tree with left node of height 1
        assertTrue(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(10, new Tree(5, new Tree(1), new Tree()), new Tree(6)), new Tree(25));
//    	Balanced Tree with left and right nodes of height 1
        assertFalse(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(10, new Tree(5, new Tree(3, new Tree(1), new Tree()), new Tree()), new Tree()), new Tree());
//    	Tree with a left subtree of height 4 and a right subtree of height 0
        assertFalse(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(20, new Tree(), new Tree(25, new Tree(), new Tree(30, new Tree(), new Tree(35, new Tree(), new Tree(40)))));
//    	Tree with a right subtree of height 4 and a left subtree of height 0
        assertFalse(Worksheet2.isHeightBalanced(t1));

        t1 = new Tree(7, new Tree(1), new Tree(15, new Tree(8), new Tree(20)));
        assertTrue(Worksheet2.isHeightBalanced(t1));
    }
}

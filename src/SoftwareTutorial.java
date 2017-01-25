/**
 * @Author Robert Keith Campbell
 * @Created 24/01/2017
 * @Version 24/01/2017
 */
public class SoftwareTutorial implements Worksheet2Interface {



    public static void main(String[] args) {
        Tree t1 = new Tree(10, new Tree(6), new Tree(14, new Tree(13, new Tree(12), new Tree()), new Tree(17)));

        System.out.println(t1);
    }
}

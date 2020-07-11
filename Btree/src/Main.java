import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BinaryTree<Integer> bt=new BinaryTree<>();
        bt.insert(8);
        bt.insert(11);
        bt.insert(10);
        bt.insert(9);
        bt.insert(1);
        bt.insert(7);
        bt.traversePreOrder();
        bt.f(8);
        bt.traversePreOrder();
       




    }
}

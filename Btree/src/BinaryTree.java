import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree <T extends Comparable<T>> implements Comparator<BinaryNode<T>> {
    BinaryNode <T> head,root;
    BinaryNode <T> node;
    void insert(T el){
        node=new BinaryNode  <T>(el);
        head=add(head,node);
    }
    BinaryNode <T> add(BinaryNode<T> head, BinaryNode<T> node){
        //System.out.println(head!=null?head.value:"null");
        if (head==null) {
            return node;
        }
        if (compare(head,node)<0){
            head.right=add(head.right,node);
        }
        else if (compare(head,node)>0){
            head.left=add(head.left,node);
        }
        return head;
    }
    public void traversePreOrder( ) {
        Queue<BinaryNode<T>> queue=new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            BinaryNode<T> node=queue.poll();
            System.out.print(" " + node.value);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right!= null) {
                queue.add(node.right);
            }
        }
    }
    public void f(T target){
//       BinaryNode<T> res=find(head,target);
        delete(head, target);
    }
    public BinaryNode<T> find(BinaryNode<T> head,T target) {
        if (compare(head, target) == 0) {

            return head;
        } else if (compare(head, target) < 0) {
            System.out.println(head.value+" -"+ head.right.value);
            return find(head.right, target);
        } else if (compare(head, target) > 0) {
            System.out.println(head.value+" -"+ head.left.value);
            return find(head.left, target);
        }
        return head;
    }
    public BinaryNode<T> delete(BinaryNode<T> head,T target){
        if (compare(head, target) == 0){
            if (head.left==null&&head.right==null) {

                return null;
            }
            else if(head.left!=null&&head.right==null){
                return head.left;
            }
            else if(head.left == null) {
                return head.right;
            }
            else {
                head.value=min(head.right).value;

                head.right=delete(head.right,head.value);
            }

        }
        else if (compare(head, target) < 0){
            head.right=delete(head.right,target);
        }
        else if(compare(head, target) > 0){
            head.left=delete(head.left,target);
        }
        return head;
    }
    BinaryNode <T> min(BinaryNode<T> head){
        return head.left==null ? head:min(head.left);
    }


    @Override
    public int compare(
            BinaryNode<T>  o1, BinaryNode<T> o2) {
        return o1.value.compareTo(o2.value);
    }

    public int compare(BinaryNode<T> o1, T o2) {
        return o1.value.compareTo(o2);
    }



}

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tester {
    private BinarySearchTree createBinaryTree() {
        BinarySearchTree bt = new BinarySearchTree();

        bt.add(new Item(6));
        bt.add(new Item(4));
        bt.add(new Item(8));
        bt.add(new Item(3));
        bt.add(new Item(5));
        bt.add(new Item(7));
        bt.add(new Item(9));

        return bt;
    }
    @Test
    public void testAdd(){
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(new Item(6));
        bt.add(new Item(4));
        assertTrue(bt.contains(6));
        assertTrue( bt.contains(4));
    }
    @Test
    public void testDelete(){
        BinarySearchTree bt = createBinaryTree();
        bt.delete(new Item(6));
        assertFalse(bt.contains(6));

    }
    @org.junit.Test
    public void testContains() {
        BinarySearchTree bt = createBinaryTree();

        assertTrue(bt.contains(6));
        assertTrue(bt.contains(4));
        assertFalse(bt.contains(2));
        assertTrue(bt.contains(9));
        bt.delete(new Item(9));
        assertFalse(bt.contains(9));
    }
    @Test
    public void testInOrder(){
        System.out.println("Testing In order");
        BinarySearchTree bt = createBinaryTree();
        bt.printInOrder();
    }
    @Test
    public void testPreOrder(){
        System.out.println("Testing pre order");
        BinarySearchTree bt = createBinaryTree();
        bt.printPreOrder();
    }
    @Test
    public void testPostOrder(){
        System.out.println("Testing post order");
        BinarySearchTree bt =createBinaryTree();
        bt.printPostOrder();
    }
    @Test
    public void testBalanceTree(){
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(new Item(10));
        bt.add(new Item(8));
        bt.add(new Item(7));
        bt.add(new Item(6));
        bt.add(new Item(5));

        bt.root = bt.buildBalancedTree(bt.root);
        bt.printPreOrder();
    }
    @Test
    public void testBalanceTreeCase2(){
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(new Item(4));
        bt.add(new Item(3));
        bt.add(new Item(2));
        bt.add(new Item(1));
        bt.add(new Item(5));
        bt.add(new Item(6));
        bt.add(new Item(7));

        bt.root = bt.buildBalancedTree(bt.root);
        bt.printPreOrder();
    }
    @Test
    public void testBalanceTreeCase3(){
        BinarySearchTree bt = new BinarySearchTree();
        bt.add(new Item(4));
        bt.add(new Item(3));
        bt.add(new Item(2));
        bt.add(new Item(1));

        bt.root = bt.buildBalancedTree(bt.root);
        bt.printPreOrder();
    }

}

import java.io.Serializable;
import java.util.ArrayList;

public class BinarySearchTree implements Serializable {
    Node root;
    private double cost = 0;
    private ArrayList<Node> nodeList = new ArrayList<>();

    /**
     * recursive method for adding
     * @param current
     * @param item
     * @return
     */
    private Node recursiveAdd(Node current, Item item){
        if(current == null){
            return new Node(item); //if null then add the node
        }
        if(item.itemId < current.key.itemId){ //if the value is less than the node go to left
            current.left = recursiveAdd(current.left, item);
        } else if(item.itemId > current.key.itemId){  //if the value is greater than the node go to right
           current.right = recursiveAdd(current.right, item);
        } else {
          return current; //if equal then don't add
        }
        return current;
    }

    /**
     * recursive method for checking if an item exists in the binary tree or not
     * @param current
     * @param itemId
     * @return
     */
    private boolean recursiveContains(Node current, int itemId){
        if(current == null){
            return false; //if empty binary tree then return false
        }
        if(itemId == current.key.itemId){
            return true; //if the value and node matches return true
        }
        if(itemId < current.key.itemId){ //if the value is less than the node go to left
            return recursiveContains(current.left, itemId);
        } else { //if the value is greater than the node go to right
            return recursiveContains(current.right, itemId);
        }
    }

    /**
     * recursive method for deleting a item
     * @param current
     * @param item
     * @return
     */
    private Node recursiveDelete(Node current, Item item){
        if(current == null){
            return null; //if the binary tree is empty don't do anything
        }
        if(item.itemId == current.key.itemId){ //if the value and node matches then there are 3 cases
            if(current.left == null && current.right == null){ //if the node has noo left and right child then delete it
               return null;
            }
            if(current.right == null){ //if the node has a left node then return it
                return current.left;
            }
            if(current.left == null){ //if the node has a right node then return it
                return current.right;
            }
            Item small = smallestNode(current.right); //find the smallest between the node
            current.key = small; //set the node value to the smallest node
            current.right = recursiveDelete(current.right, small); //set the right of the node
            return current;
        }
        if(item.itemId < current.key.itemId){ //if the value is less than the node then go to left
            current.left = recursiveDelete(current.left, item);
            return current;
        }
        current.right = recursiveDelete(current.right, item);//set the right of the node
        return current;
    }

    /**
     * recursive method to find the smallest node
     * @param root
     * @return
     */
    private Item smallestNode(Node root){
        if(root.left == null){
           return root.key; //if there is no left child then return the node
        } else {
           return smallestNode(root.left); //go to left
        }
    }

    /**
     * recursive method to return items of a node
     * @param current
     * @param itemId
     * @return
     */
    private Node recursiveReturnItem(Node current, int itemId){
        if(itemId == current.key.itemId){ //if item id matches with the node then return item
            return current;
        }
        if(itemId < current.key.itemId){ //if the value is less than the node then go to left
            return recursiveReturnItem(current.left, itemId);
        } else { //if the value is greater than the node go to right
            return recursiveReturnItem(current.right, itemId);
        }
    }

    /**
     * recursive method for preorder traversal
     * @param node
     */
    public void traversePreOrder(Node node){
        if(node != null){
            node.key.printDetails(); //print the node
            traversePreOrder(node.left); //go to left
            traversePreOrder(node.right);// go to right
        }
    }
    public void traversePostOrder(Node node){
        if(node != null) {
            traversePostOrder(node.left);//go to left
            traversePostOrder(node.right);// go to right
            node.key.printDetails(); //print the node
        }
    }
    public void traverseInOrder(Node node, String condition){
        if(node != null) {
            traverseInOrder(node.left,condition);//go to left
            if(condition.equals("print")) { //if the condition is print then print the item
                node.key.printDetails();  //print the node
            } else if(condition.equals("balance")) {//if the condition is balance then store the item in the arraylist
                nodeList.add(node);
            }
            traverseInOrder(node.right,condition);//go to right
        }

    }

    /**
     * recursive method to balance the binary tree
     * @param nodeList
     * @param start
     * @param end
     * @return
     */
    private Node recursiveBuildBalance(ArrayList<Node> nodeList, int start, int end){
        if(start > end){ //if the start is greater than the end then end the method
            return null;
        }
        int mid = (start + end) / 2; //find middle of the array list
        Node node = nodeList.get(mid); //get the middle node from the arraylist
        node.left = recursiveBuildBalance(nodeList, start , mid - 1);//take the left half of the arraylist
        node.right = recursiveBuildBalance(nodeList,mid+1, end); //take the right half of the arraylist
        return node;
    }

    /**
     * method for balancing the binary tree
     * @param node
     * @return
     */
    public Node buildBalancedTree(Node node){
       traverseInOrder(node, "balance"); //traverse inorder through the binary tree
       int n = nodeList.size();
       return recursiveBuildBalance(nodeList,0,n-1);
    }

    /**
     * method for returning the item, searching by itemId
     * @param itemId
     * @return
     */
    public Item returnItem(int itemId){
        return recursiveReturnItem(root, itemId).key;
    }

    /**
     * method for adding in the binary tree
     * @param item
     */
    public void add(Item item){
        root = recursiveAdd(root, item);
    }

    /**
     * method to check if an item exists in the binary tree or not
     * @param itemId
     * @return
     */
    public boolean contains(int itemId){
        return recursiveContains(root, itemId);
    }

    /**
     * method for deleting from the binary tree
     * @param item
     */
    public void delete(Item item){
        root =  recursiveDelete(root, item);
    }

    /**
     * method for printing in preorder
     */
    public void printPreOrder(){
        traversePreOrder(root);
    }
    /**
     * method for printing in postorder
     */
    public void printPostOrder(){
        traversePostOrder(root);
    }
    /**
     * method for printing in inorder
     */
    public void printInOrder(){
        traverseInOrder(root, "print");
    }

    /**
     * recursive method to calculate the total cost of the items in the binary tree
     * @param node
     */
    public void calculateItemCostTraverse(Node node){
        //using preorder traversal
        if(node != null){
            for(int i = 0; i < node.key.stock; i++) {
                cost += node.key.price; //add the cost of each stock of the item
            }
           calculateItemCostTraverse(node.left);
           calculateItemCostTraverse(node.right);
        }
    }

    /**
     * method for returning the total cost
     * @return
     */
    public double totalCost(){
        cost = 0;
        calculateItemCostTraverse(root);
        return cost;
    }
}

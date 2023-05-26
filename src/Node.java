import java.io.Serializable;

public class Node implements Serializable {
    Item key;
    Node left, right;

    /**
     * Constructor
     * @param item
     */
    public Node(Item item) {
        key = item;
        left = null;
        right = null;
    }
}
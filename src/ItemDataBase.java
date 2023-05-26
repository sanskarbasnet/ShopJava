public class ItemDataBase {
    BinarySearchTree itemsDatabase = new BinarySearchTree();

    /**
     *add in database
     * @param item
     */
    public void add(Item item){
        itemsDatabase.add(item);
    }

    /**
     * delete in database
     * @param item
     */
    public void delete(Item item){
        itemsDatabase.delete(item);
    }
}

import java.io.Serializable;

public class Item implements Serializable {
  long itemId;
  String name;
  double price;
  int stock;

  /**
   * Constructor
   * @param name
   * @param itemId
   * @param price
   */
  public Item(String name, long itemId, double price){
      this.name = name;
      this.price = price;
      this.itemId = itemId;
      this.stock = 0;
  }

  /**
   * Constructor
   * @param itemId
   */
  public Item(long itemId){
    this.itemId = itemId;
  }

  /**
   * add stock
   */
  public void addStock(){
    stock++;
  }

  /**
   * remove stock
   */
  public void removeStock(){
    if(stock > 0){
      stock--;
    } else {
      System.out.println("No more stock available for "+ name);
    }
  }

  /**
   * method responsible for printing item details
   */
  public void printDetails(){
    System.out.println("##########################");
    System.out.println("Item Name: " + name);
    System.out.println("Item Id: " + itemId);
    System.out.println("Price: " + price);
    System.out.println("In stock: " + stock);
  }
}

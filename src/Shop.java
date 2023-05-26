import java.awt.*;
import java.util.Scanner;

public class Shop {
    ItemDataBase database = new ItemDataBase();

    /**
     * director method for shop
     */
    public void launch(){
        printMenu();
    }

    /**
     * method responsible for printing menu
     */
    public void printMenu(){
        System.out.println("####################################");
        System.out.println("#        Welcome To the Shop       #");
        System.out.println("####################################");
        System.out.println("[1] Add Item");
        System.out.println("[2] Delete Item");
        System.out.println("[3] Search Item");
        System.out.println("[4] Print Item List");
        System.out.println("[5] Calculate Total Cost");
        System.out.println("[6] Exit and Save");
        int opt;
        do{
            System.out.print("Choose an option(1-6): ");
           opt = Genio.getInteger();
            if(opt < 1 || opt > 6){
                System.out.println("Enter a valid option"); //validating user input
            }
        } while(opt < 1 || opt > 6); //directing user to the work he wants to do
        switch (opt){
            case 1: addItem(); break;
            case 2: deleteItem(); break;
            case 3: searchItem(); break;
            case 4: printItemList(); break;
            case 5: calculateCost(); break;
            case 6: Saver.save("data.ser", database.itemsDatabase); System.exit(0);break; //saving the database in a file
        }

    }

    /**
     * method responsible for letting the user add items
     */
    public void addItem(){
        String name;
        int itemId;
        double price;
        System.out.println("####################################");
        System.out.println("#      You are now in add page     #");
        System.out.println("####################################");
        //asking for item details
        System.out.print("Enter Item's Name: ");
        name = Genio.getString();
        System.out.print("Enter Item ID: ");
        itemId = Genio.getInteger();
        System.out.print("Enter Price $: ");
        price = Genio.getDouble();

        if(database.itemsDatabase.contains(itemId)) { //if the database contains the item
            Item item = database.itemsDatabase.returnItem(itemId);
            database.delete(item); //delete the item as adding the item before deleting will create a new node in the tree
            item.addStock(); //adding the stock of the item as it is already in the shop
            database.add(item); //adding the item with the revised stock
        } else {
            Item newItem = new Item(name, itemId, price); //if the item is not in the shop then add it to the database
            newItem.addStock();
            database.add(newItem);
        }
        char opt;
        do { //asking user if he wants to continue
            System.out.print("Do you want to add another item?(Y/N): ");
            opt = Genio.getCharacter();
            if (opt == 'y' || opt == 'Y') {
                addItem();
            } else if (opt == 'n' || opt == 'N') {
                printMenu();
                break;
            } else {
                System.out.println("Please enter a valid choice!");
            }
        }while (opt != 'y' || opt != 'Y' || opt != 'n' || opt != 'N'); //validating user input
    }

    /**
     * method responsible for letting the user delete an item
     */
    public void deleteItem(){
        int itemId;
        //printing item lists so user can see the item id before deleting
        database.itemsDatabase.printInOrder();
        System.out.println("####################################");
        System.out.println("#    You are now in delete page    #");
        System.out.println("####################################");
//asking for item id to delete
        System.out.print("Enter Item ID: ");
        itemId = Genio.getInteger();
        if(database.itemsDatabase.contains(itemId)) {
            Item item = database.itemsDatabase.returnItem(itemId);
            database.itemsDatabase.delete(item); // if there is only one stock of the item then removing it completely from the database
            if (item.stock > 1) {
                item.removeStock(); //if there is more than one stock then removing one stock of the item and adding the revised item to the database
                database.add(item);
            }
        } else { //if item not in the database then printing error
            System.out.println("The item you are trying to remove isn't in the stock");
        }
        char opt;
        //asking is user wants to continue
        do {
            System.out.print("Do you want to delete another item?(Y/N): ");
            opt = Genio.getCharacter();
            if (opt == 'y' || opt == 'Y') {
                deleteItem();
            } else if (opt == 'n' || opt == 'N') {
                printMenu();
                break;
            } else {
                System.out.println("Please enter a valid choice!"); ///validating user input
            }
        }while (opt != 'y' || opt != 'Y' || opt != 'n' || opt != 'N');
    }

    /**
     * method responsible for letting the user search for an item
     */
    public void searchItem(){
        int itemId;
        System.out.println("####################################");
        System.out.println("#    You are now in search page    #");
        System.out.println("####################################");
        System.out.print("Enter Item ID: "); //asking for item id to search
        itemId = Genio.getInteger();
        if(database.itemsDatabase.contains(itemId)) {
            System.out.println("Item found!"); //if item is found then printing found message
            database.itemsDatabase.returnItem(itemId).printDetails();
        } else {
            System.out.println("The item you are trying to find isn't in the stock"); //if item is not found then printing error
        }
        char opt;
        //asking if user wants to continue
        do {
            System.out.print("Do you want to search another item?(Y/N): ");
            opt = Genio.getCharacter();
            if (opt == 'y' || opt == 'Y') {
                searchItem();
            } else if (opt == 'n' || opt == 'N') {
                printMenu();
                break;
            } else {
                System.out.println("Please enter a valid choice!"); //validating user input
            }
        }while (opt != 'y' || opt != 'Y' || opt != 'n' || opt != 'N');
    }

    /**
     * method responsible for letting the user print item list
     */
    public void printItemList() {
        System.out.println("####################################");
        System.out.println("[1] Print Pre Order");
        System.out.println("[2] Print Post Order");
        System.out.println("[3] Print in numerical order (Inorder)");
        int i;
        while(true){
        System.out.print("Enter a option (1-3): ");
        i = Genio.getInteger();
        if(i < 1 || i > 3){
            System.out.println("Enter a valid option (1-3)");
            continue;
        }
        break;
       }
        if(i == 1){
            database.itemsDatabase.printPreOrder();// printing in preorder
        } else if (i == 2) {
            database.itemsDatabase.printPostOrder();// printing in post order
        } else {
            database.itemsDatabase.printInOrder();// printing in inorder
        }
        char opt;
        //asking the user if he wants to continue
        do {
            System.out.print("Do you want to print the list again?(Y/N): ");
            opt = Genio.getCharacter();
            if (opt == 'y' || opt == 'Y') {
                printItemList();
            } else if (opt == 'n' || opt == 'N') {
                printMenu();
                break;
            } else {
                System.out.println("Please enter a valid choice!");// validating user input
            }
        }while (opt != 'y' || opt != 'Y' || opt != 'n' || opt != 'N');
    }

    /**
     * method responsible for calculating total cost of the items
     */
    public void calculateCost(){
        System.out.println("#######################################################");
        System.out.println("The total cost of the items in your shop is: $" + database.itemsDatabase.totalCost()); //printing total cost
        char opt;
        //asking the user if he wants to continue
        do {
            System.out.print("Do you want to print the total cost again?(Y/N): ");
            opt = Genio.getCharacter();
            if (opt == 'y' || opt == 'Y') {
                calculateCost();
            } else if (opt == 'n' || opt == 'N') {
                printMenu();
                break;
            } else {
                System.out.println("Please enter a valid choice!"); //validating user input
            }
        }while (opt != 'y' || opt != 'Y' || opt != 'n' || opt != 'N');
    }
}

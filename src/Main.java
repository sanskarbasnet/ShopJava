import java.io.File;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Saver saver = new Saver();
        Shop shop = new Shop();
        //loading database from file
        if(new File("data.ser").length() != 0) {
            shop.database.itemsDatabase = saver.load("data.ser");
        }
        shop.launch();
    }
}

import java.io.*;


public class Saver {
    /**
     * method responsible for saving in file
     * @param filepath
     * @param object
     */
    public static void save(String filepath, BinarySearchTree object)  {

        try {
            FileOutputStream fileOutputStream  = new FileOutputStream(filepath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * method for loading file
     * @param filepath
     * @return
     */
    public static BinarySearchTree load(String filepath)  {
        BinarySearchTree output;

        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); {
            output = (BinarySearchTree) objectInputStream.readObject();
        }
    } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
      return output;

    }
}

package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Laden
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String selectMenu = "0";
        ManajemenItem manager = new ManajemenItem();

        while (!selectMenu.equals("6")) {
            System.out.println("Welcome to CRUD program");
            System.out.println(">> MENU <<");
            System.out.println("1. Create Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. Show Item");
            System.out.println("5. Search Item");
            System.out.println("6. Exit");
            System.out.print("Select Menu : ");
            selectMenu = input.readLine();
            switch (selectMenu) {
                case "1":
                    manager.addItem();
                    break;
                case "2":
                    manager.updateItem();
                    break;
                case "3":
                    manager.deleteItem();
                    break;
                case "4":
                    manager.displayItem();
                    break;
                case "5":
                    System.out.println("Menu 5");
                    break;
                case "6":
                    manager.end();
                    break;
                default:
                    System.out.println(">> Wrong input <<");
                    break;
            }
        }
    }

}

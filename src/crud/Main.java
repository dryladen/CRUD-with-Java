package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        ArrayList<Item> dataItem = new ArrayList<Item>();
        String selectMenu = "0";
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
                    try {

                        Item newItem = new Item();
                        System.out.print("Input Name   : ");
                        newItem.setName(input.readLine());
                        System.out.print("Input Price  : ");
                        newItem.setPrice(Float.parseFloat(input.readLine()));
                        System.out.print("Input Amount : ");
                        newItem.setAmount(Integer.parseInt(input.readLine()));
                        dataItem.add(newItem);
                    } catch (NumberFormatException e) {
                        System.out.println("Input Error !!");
                    }
                    break;
                case "2":
                    System.out.println("Menu 2");
                    break;
                case "3":
                    System.out.println("Menu 3");
                    break;
                case "4":
                    int i = 1;
                    for (Item item : dataItem) {
                        System.out.println("Item " + i);
                        System.out.println("Name   : " + item.getName());
                        System.out.println("Price  : " + item.getPrice());
                        System.out.println("Amount : " + item.getAmount());
                        System.out.println();
                        i -= -1;
                    }
                    break;
                case "5":
                    System.out.println("Menu 5");
                    break;
                case "6":
                    break;
                default:
                    System.out.println(">> Wrong input <<");
                    break;
            }
        }
    }

}

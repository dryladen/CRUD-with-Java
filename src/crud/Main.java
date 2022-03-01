package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        Koneksi koneksi = new Koneksi();
        JFrame frameTable = new JFrame("List Item");
        String itemSearch;
        String selectMenu = "0";
        String header[] = { "Name", "Price", "Amount" };
        JTable table = new JTable();
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        ArrayList<Item> dataItem = new ArrayList<Item>();
        JScrollPane data = new JScrollPane(table);
        koneksi.getData(dataItem);
        for (Item item : dataItem) {
            tblModel.addRow(new Object[] { item.getName(), item.getPrice(), item.getAmount() });
        }
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
                        tblModel.addRow(new Object[] { newItem.getName(), newItem.getPrice(), newItem.getAmount() });
                    } catch (NumberFormatException e) {
                        System.out.println("Input Error !!");
                    }
                    break;
                case "2":
                    System.out.print("Masukan nama item : ");
                    itemSearch = input.readLine();
                    for (Item item : dataItem) {
                        if (item.getName().equals(itemSearch)) {
                            System.out.print("Input new Price  : ");
                            item.setPrice(Float.parseFloat(input.readLine()));
                            System.out.print("Input new Amount : ");
                            item.setAmount(Integer.parseInt(input.readLine()));
                            System.out.println("Update Complete");
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.print("Masukan nama item : ");
                    itemSearch = input.readLine();
                    for (int i = 0; i < dataItem.size(); i -= -1) {
                        if (dataItem.get(i).getName().equals(itemSearch)) {
                            dataItem.remove(i);
                            System.out.println("Delete Complete");
                            break;
                        }
                    }
                    break;
                case "4":
                    frameTable.add(data);
                    frameTable.setBounds(400, 100, 600, 220);
                    frameTable.setVisible(true);
                    break;
                case "5":
                    System.out.println("Menu 5");
                    break;
                case "6":
                    frameTable.dispose();
                    break;
                default:
                    System.out.println(">> Wrong input <<");
                    break;
            }
        }
    }

}

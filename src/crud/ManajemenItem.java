package crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManajemenItem {
    private BufferedReader input;
    private DefaultTableModel tblModel;
    private ArrayList<Item> dataItem;
    private JTable table;
    private String itemSearch;
    private Koneksi koneksi;
    private JScrollPane data;
    private JFrame frameTable;
    private int lastID = 1;

    public ManajemenItem() {
        input = new BufferedReader(new InputStreamReader(System.in));
        tblModel = new DefaultTableModel();
        dataItem = new ArrayList<Item>();
        frameTable = new JFrame("List Item");
        koneksi = new Koneksi();
        table = new JTable();
        data = new JScrollPane(table);
        this.tblModel.setColumnIdentifiers(new String[] { "Id", "Name", "Price", "Amount" });
        this.table.setModel(this.tblModel);
        koneksi.getData(dataItem);
        for (Item item : dataItem) {
            tblModel.addRow(new Object[] { item.getId(), item.getName(), item.getPrice(), item.getAmount() });
            this.lastID++;
        }
    }

    public void addItem() throws IOException {
        try {
            Item newItem = new Item();
            System.out.print("Input Name   : ");
            newItem.setName(input.readLine());
            System.out.print("Input Price  : ");
            newItem.setPrice(Float.parseFloat(input.readLine()));
            System.out.print("Input Amount : ");
            newItem.setAmount(Integer.parseInt(input.readLine()));
            koneksi.addItem(newItem.getName(), newItem.getPrice(), newItem.getAmount());
            this.tblModel.addRow(
                    new Object[] { this.lastID, newItem.getName(), newItem.getPrice(), newItem.getAmount() });
        } catch (NumberFormatException e) {
            System.out.println("Input Error !!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem() throws IOException {
        System.out.print("Masukan nama item : ");
        this.itemSearch = input.readLine();
        for (Item item : this.dataItem) {
            if (item.getName().equals(this.itemSearch)) {
                System.out.print("Input new Price  : ");
                item.setPrice(Float.parseFloat(input.readLine()));
                System.out.print("Input new Amount : ");
                item.setAmount(Integer.parseInt(input.readLine()));
                System.out.println("Update Complete");
                koneksi.updateItem(item.getName(), item.getPrice(), item.getAmount(), item.getId());
                break;
            }
        }
    }

    public void deleteItem() throws IOException {
        System.out.print("Masukan nama item : ");
        this.itemSearch = input.readLine();
        for (int i = 0; i < dataItem.size(); i -= -1) {
            if (this.dataItem.get(i).getName().equals(this.itemSearch)) {
                this.dataItem.remove(i);
                System.out.println("Delete Complete");
                break;
            }
        }
    }

    public void displayItem() {
        frameTable.add(data);
        frameTable.setBounds(400, 100, 600, 220);
        frameTable.setVisible(true);
    }

    public void end() {
        frameTable.dispose();
    }
}

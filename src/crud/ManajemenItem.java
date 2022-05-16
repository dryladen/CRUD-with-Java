package crud;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManajemenItem {
    private DefaultTableModel tblModel;
    private ArrayList<Item> dataItem;
    private JTable table;
    private String itemSearch;
    private Koneksi koneksi;
    private JScrollPane data;
    private JFrame frameTable;
    private int lastID = 1;

    public ManajemenItem() {
        frameTable = new JFrame("List Item");
        tblModel = new DefaultTableModel();
        table = new JTable();
        koneksi = new Koneksi();
        dataItem = new ArrayList<Item>();
        frameTable.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        koneksi.getData(dataItem);
    }

    public void menu() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(true);
        String menu[] = { "1. Create Item", "2. Update Item", "3.Delete Item", "4. Show Item", "5. Search", "6. Exit" };
        String selectMenu = "0";
        while (!selectMenu.equals("6. Exit")) {
            selectMenu = (String) JOptionPane.showInputDialog(null, "Login", "Welcome to CRUD program",
                    JOptionPane.INFORMATION_MESSAGE, null, menu, "1. Create Item");
            switch (selectMenu) {
                case "1. Create Item":
                    addItem();
                    break;
                case "2. Update Item":
                    updateItem();
                    break;
                case "3.Delete Item":
                    deleteItem();
                    break;
                case "4. Show Item":
                    clearTable();
                    displayItem(this.dataItem);
                    break;
                case "5. Search":
                    searchItem();
                    break;
                case "6. Exit":
                    end();
                    break;
                default:
                    System.out.println(">> Wrong input <<");
                    break;
            }
        }
    }

    public void addItem() throws IOException {
        try {
            Item newItem = new Item();
            newItem.setName(JOptionPane.showInputDialog("Input Name"));
            newItem.setPrice(Float.parseFloat(JOptionPane.showInputDialog("Input Price")));
            newItem.setAmount(Integer.parseInt(JOptionPane.showInputDialog("Input Amount")));
            koneksi.addItem(newItem.getName(), newItem.getPrice(), newItem.getAmount());
            this.lastID++;
            this.tblModel.addRow(
                    new Object[] { this.lastID, newItem.getName(), newItem.getPrice(), newItem.getAmount() });
        } catch (NumberFormatException e) {
            System.out.println("Input Error !!");
        }
    }

    public void updateItem() throws IOException {
        this.itemSearch = JOptionPane.showInputDialog(null, "Input ID");
        for (int i = 0; i <= dataItem.size(); i -= -1) {
            if (dataItem.get(i).getId() == Integer.parseInt(this.itemSearch)) {
                dataItem.get(i).setPrice(Float.parseFloat(JOptionPane.showInputDialog("Input Price")));
                dataItem.get(i).setAmount(Integer.parseInt(JOptionPane.showInputDialog("Input Amount")));
                koneksi.updateItem(dataItem.get(i).getName(), dataItem.get(i).getPrice(), dataItem.get(i).getAmount(),
                        dataItem.get(i).getId());
                tblModel.setValueAt(dataItem.get(i).getPrice(), i, 2);
                tblModel.setValueAt(dataItem.get(i).getAmount(), i, 3);
                JOptionPane.showMessageDialog(null, "Update Complete", "Info", 1);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Item tidak ditemukan", "Info", 1);
    }

    public void deleteItem() throws IOException {
        this.itemSearch = JOptionPane.showInputDialog(null, "Input ID");
        for (int i = 0; i <= dataItem.size(); i -= -1) {
            if (this.dataItem.get(i).getId() == Integer.parseInt(itemSearch)) {
                if (i == dataItem.size() - 1) {
                    this.lastID = dataItem.get(i).getId();
                    koneksi.deleteItem(dataItem.get(i).getId());
                    dataItem.remove(i);
                    tblModel.removeRow(i);
                } else {
                    koneksi.deleteItem(dataItem.get(i).getId());
                    dataItem.remove(i);
                    tblModel.removeRow(i);
                    this.lastID = dataItem.get(dataItem.size() - 1).getId();
                }
                JOptionPane.showMessageDialog(null, "Delete Complete", "Information", 1);
                break;
            }
        }
    }

    public void displayItem(ArrayList<Item> dataItem) {
        this.tblModel.setColumnIdentifiers(new String[] { "Id", "Name", "Price", "Amount" });
        this.table.setModel(this.tblModel);
        data = new JScrollPane(table);
        // add row
        for (Item item : dataItem) {
            tblModel.addRow(new Object[] { item.getId(), item.getName(), item.getPrice(), item.getAmount() });
            this.lastID = item.getId();
        }
        // display to table
        frameTable.add(data);
        frameTable.setBounds(400, 50, 600, 220);
        frameTable.setVisible(true);
    }

    public void clearTable() {
        this.tblModel.setRowCount(0);
    }

    public void end() {
        frameTable.dispose();
    }

    public int shellSort(ArrayList<Item> dataItem) {
        int n = dataItem.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                Item temp = dataItem.get(i);
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && dataItem.get(j - gap).getId() > temp.getId(); j -= gap) {
                    dataItem.set(j, dataItem.get(j - gap));
                }
                // put temp (the original a[i]) in its correct
                // location
                dataItem.set(j, temp);
            }
        }
        return 0;
    }

    public void searchItem() {
        this.itemSearch = JOptionPane.showInputDialog(null, "Input ID");
        binarySearch(dataItem, 0, dataItem.size() - 1, Integer.parseInt(itemSearch));
        // JOptionPane.showMessageDialog(null, "Item tidak ditemukan", "Info", 1);
    }

    public int binarySearch(ArrayList<Item> data, int l, int r, int id) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            // If the element is present at the
            // middle itself
            if (data.get(mid).getId() == id) {
                ArrayList<Item> dataCari = new ArrayList<>();
                clearTable();
                dataCari.add(data.get(mid));
                displayItem(dataCari);
                return mid;
            }
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (data.get(mid).getId() > id)
                return binarySearch(data, l, mid - 1, id);
            // Else the element can only be present
            // in right subarray
            return binarySearch(data, mid + 1, r, id);
        }
        // We reach here when element is not present
        // in array
        JOptionPane.showMessageDialog(null, "Item tidak ditemukan", "Info", 1);
        return -1;
    }
}

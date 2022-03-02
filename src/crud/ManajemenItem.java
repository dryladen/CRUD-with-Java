package crud;

// import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManajemenItem {
    // private BufferedReader input;
    private DefaultTableModel tblModel;
    private ArrayList<Item> dataItem;
    private JTable table;
    private String itemSearch;
    private Koneksi koneksi;
    private JScrollPane data;
    private JFrame frameTable;
    private int lastID = 1;

    public ManajemenItem() {
        // input = new BufferedReader(new InputStreamReader(System.in));
        tblModel = new DefaultTableModel();
        dataItem = new ArrayList<Item>();
        frameTable = new JFrame("List Item");
        koneksi = new Koneksi();
        table = new JTable();
        data = new JScrollPane(table);
        this.tblModel.setColumnIdentifiers(new String[] { "Id", "Name", "Price", "Amount" });
        this.table.setModel(this.tblModel);
        frameTable.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        koneksi.getData(dataItem);
        for (Item item : dataItem) {
            tblModel.addRow(new Object[] { item.getId(), item.getName(), item.getPrice(), item.getAmount() });
            this.lastID = item.getId();
        }
    }

    public void menu() throws IOException {
        JDialog.setDefaultLookAndFeelDecorated(true);
        String menu[] = { "1. Create Item", "2. Update Item", "3.Delete Item", "4. Show Item", "5. Search", "6. Exit" };
        String selectMenu = "0";
        while (!selectMenu.equals("6")) {
            selectMenu = (String) JOptionPane.showInputDialog(null, "Login", "Welcome to CRUD program",
                    JOptionPane.INFORMATION_MESSAGE, null, menu, "1. Create Item");
            // System.out.println("Welcome to CRUD program");
            // System.out.println(">> MENU <<");
            // System.out.println("1. Create Item");
            // System.out.println("2. Update Item");
            // System.out.println("3. Delete Item");
            // System.out.println("4. Show Item");
            // System.out.println("5. Search Item");
            // System.out.println("6. Exit");
            // System.out.print("Select Menu : ");
            // selectMenu = input.readLine();
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
                    displayItem();
                    break;
                case "5. Search":
                    System.out.println("Menu 5");
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
            // System.out.print("Input Name : ");
            // System.out.print("Input Price : ");
            // newItem.setPrice(Float.parseFloat(input.readLine()));
            // System.out.print("Input Amount : ");
            // newItem.setAmount(Integer.parseInt(input.readLine()));
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
        // System.out.print("Masukan id item : ");
        // this.itemSearch = input.readLine();
        this.itemSearch = JOptionPane.showInputDialog(null, "Input ID");
        for (int i = 0; i <= dataItem.size(); i -= -1) {
            if (dataItem.get(i).getId() == Integer.parseInt(this.itemSearch)) {
                // System.out.print("Input new Price : ");
                // dataItem.get(i).setPrice(Float.parseFloat(input.readLine()));
                // System.out.print("Input new Amount : ");
                // dataItem.get(i).setAmount(Integer.parseInt(input.readLine()));
                // System.out.println("Update Complete");
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
        // System.out.print("Masukan id item : ");
        // this.itemSearch = input.readLine();
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

    public void displayItem() {
        frameTable.add(data);
        frameTable.setBounds(400, 100, 600, 220);
        frameTable.setVisible(true);
    }

    public void end() {
        frameTable.dispose();
    }
}

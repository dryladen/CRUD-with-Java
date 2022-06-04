package crud;

import java.io.IOException;
import javax.swing.JOptionPane;

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
        Admin manager = new Admin();
        Pembeli pembeli = new Pembeli();
        String menuAwal = (String) JOptionPane.showInputDialog(null, "Login",
                "Information",
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[] { "Admin", "Pembeli" }, "Admin");
        if (menuAwal == null) {
            JOptionPane.showMessageDialog(null, "Exit Program", "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (menuAwal.equals("Admin")) {
            manager.menu();
        } else if (menuAwal.equals("Pembeli")) {
            pembeli.menu();
        }
    }
}

package crud;

import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;

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
        ManajemenItem manager = new ManajemenItem();
        String input = (String) JOptionPane.showInputDialog(null, "Login", "Information",
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[] { "Admin", "Pembeli" }, "Admin");
        if (input.equals("Admin")) {
            manager.menu();
        } else if (input.equals("Pembeli")) {
            JOptionPane.showMessageDialog(null, "Anjayyyy", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

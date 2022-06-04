package crud;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Pembeli {
    private ArrayList<Akun> dataAkun;
    private Koneksi koneksi;

    public Pembeli() {
        koneksi = new Koneksi();
        dataAkun = new ArrayList<>();
        koneksi.getDataUser(dataAkun);
    }

    public void menu() {
        // ! Mengambil data dari database
        // ! Menu
        String menuUser = (String) JOptionPane.showInputDialog(null, "Login",
                "Information",
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[] { "Login", "Register" }, "Login");
        if (menuUser == null) {
            return;
        }
        switch (menuUser) {
            case "Login":
                break;
            case "Register":
                break;
            default:
                System.out.println("Pilihan tidak tersedia");
                break;
        }
        JOptionPane.showMessageDialog(null, "Anjayyyy", "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void login() {
        String username = JOptionPane.showInputDialog(null, "Username",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        String password = JOptionPane.showInputDialog(null, "Password",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        for (Akun akun : dataAkun) {
            if (akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login Berhasil", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                akun.menu();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Login Gagal", "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void register() {
        String username = JOptionPane.showInputDialog(null, "Username",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        for (Akun akun : dataAkun) {
            if (akun.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(null, "Username sudah ada", "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        String password = JOptionPane.showInputDialog(null, "Password",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        String nama = JOptionPane.showInputDialog(null, "Nama",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        String saldo = JOptionPane.showInputDialog(null, "Saldo",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        int lastID = dataAkun.get(dataAkun.size() - 1).getId();
        Akun akun = new Akun(lastID++, username, password, nama, Double.parseDouble(saldo));
        dataAkun.add(akun);
        koneksi.addDataUser(akun);
        JOptionPane.showMessageDialog(null, "Register Berhasil", "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

}

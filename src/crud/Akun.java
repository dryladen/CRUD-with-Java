package crud;

import javax.swing.JOptionPane;

public class Akun {
    private int id;
    private String username, password, nama;
    private double saldo;

    public Akun() {
    }

    public Akun(int id, String username, String password, String nama, Double saldo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.saldo = saldo;
    }

    public void menu() {
        String menuUtama = (String) JOptionPane.showInputDialog(null, "Menu Utama",
                "Information",
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[] { "Beli", "Lihat Saldo", "Keluar" }, "Beli");
        if (menuUtama == null) {
            return;
        }
        switch (menuUtama) {
            case "Beli":
                break;
            case "Lihat Saldo":
                break;
            case "Keluar":
                break;
            default:
                System.out.println("Pilihan tidak tersedia");
                break;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}

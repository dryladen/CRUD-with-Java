package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.File;

public class Koneksi {
    // ! Nama database yang digunakan & pastikan database sudah dibuat
    private final String url = "jdbc:sqlite:databaseitem.db";
    private PreparedStatement pst;
    private Connection koneksi;
    private ResultSet result;
    private Statement stm;
    private String sql;

    public boolean isDatabaseExists(String dbFilePath) {
        File database = new File(dbFilePath);
        return database.exists();
    }

    public Connection getKoneksi() { // ! menghubungkan ke database
        String getFilePath = new File("").getAbsolutePath();
        String fileAbsolutePath = getFilePath.concat("\\databaseitem.db");
        if (isDatabaseExists(fileAbsolutePath)) {
            try {
                this.koneksi = DriverManager.getConnection(url);
            } catch (SQLException ex) {
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error koneksi database : " + ex);
            }
        }
        return koneksi;
    }

    public ArrayList<Item> getData(ArrayList<Item> item) {
        try {
            Connection cn = getKoneksi();
            stm = cn.createStatement();
            result = stm.executeQuery("SELECT * FROM Item");
            while (result.next()) {
                item.add(new Item(result.getInt(1), result.getString(2), result.getFloat(3), result.getInt(4)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } finally {
            try {
                result.close();
                stm.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error set data : " + ex);
            }
        }
        return item;
    }

    public void addItem(Item item) {
        try {
            sql = "INSERT INTO Item (nama,price,amount) VALUES ('%s','%f','%d')";
            sql = String.format(sql,
                    item.getName(), item.getPrice(), item.getAmount());
            Connection cn = getKoneksi();
            pst = cn.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void updateItem(Item item) {
        try {
            sql = "UPDATE Item SET nama=?,price=?,amount=? WHERE id=?";
            Connection cn = getKoneksi();
            pst = cn.prepareStatement(sql);
            pst.setString(1, item.getName());
            pst.setFloat(2, item.getPrice());
            pst.setInt(3, item.getAmount());
            pst.setInt(4, item.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public void deleteItem(int id) {
        try {
            String sql = "DELETE FROM Item WHERE id=?";
            Connection cn = getKoneksi();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public ArrayList<Akun> getDataUser(ArrayList<Akun> akun) {
        try {
            Connection cn = getKoneksi();
            stm = cn.createStatement();
            result = stm.executeQuery("SELECT * FROM User");
            while (result.next()) {
                akun.add(new Akun(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getDouble(5)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } finally {
            try {
                result.close();
                stm.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error set data : " + ex);
            }
        }
        return akun;
    }

    public void addDataUser(Akun akun) {
        try {
            sql = "INSERT INTO User (username,password,nama,saldo) VALUES ('%s','%s','%s','%f')";
            sql = String.format(sql,
                    akun.getUsername(), akun.getPassword(), akun.getNama(), akun.getSaldo());
            Connection cn = getKoneksi();
            pst = cn.prepareStatement(sql);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
    }
}

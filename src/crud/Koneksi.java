package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.File;

public class Koneksi {
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

    public Connection getKoneksi() { // menghubungkan ke database
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
        // } else {
        // createNewDatabase();
        // try {
        // koneksi = DriverManager.getConnection(url);
        // } catch (SQLException ex) {
        // Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        // JOptionPane.showMessageDialog(null, "Error create database : "+ ex);
        // }
        // }
        return koneksi;
    }

    public ArrayList<Item> getData(ArrayList<Item> item) {
        try {
            Connection cn = getKoneksi();
            stm = cn.createStatement();
            result = stm.executeQuery("SELECT * FROM Item");
            while (result.next()) {
                item.add(new Item(result.getInt(4), result.getString(1), result.getFloat(2), result.getInt(3)));
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

    public void addItem(String nama, Float price, int amount) {

    }

    public void updateItem(String nama, Float price, int amount, int id) {
        try {
            sql = "UPDATE Item SET nama='?',price=?,amount=? WHERE id=?";
            // Connection cn = koneksi.getKoneksi();
            pst = koneksi.prepareStatement(sql);
            pst.setString(1, nama);
            pst.setFloat(2, price);
            pst.setInt(3, amount);
            pst.setInt(4, id);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
            }
        }
    }
    // private void createNewDatabase(){
    // Connection conn;
    // try {
    // Class.forName("org.sqlite.JDBC");
    // conn = DriverManager.getConnection(url);
    // if(conn != null){
    // try {
    // sql = "CREATE TABLE \"kategoriTable\" (\n" +
    // " \"idKategori\" INTEGER NOT NULL,\n" +
    // " \"namaKategori\" TEXT DEFAULT NULL,\n" +
    // " \"tanggalKategori\" TEXT DEFAULT NULL,\n" +
    // " \"deskripsiKategori\" TEXT DEFAULT NULL,\n" +
    // " PRIMARY KEY(\"idKategori\" AUTOINCREMENT)\n" +
    // ")";
    // Statement kategoriTable = conn.createStatement();
    // kategoriTable.execute(sql);
    // sql = "CREATE TABLE \"kegiatanTable\" (\n" +
    // " \"idKegiatan\" INTEGER NOT NULL,\n" +
    // " \"namaKegiatan\" TEXT DEFAULT NULL,\n" +
    // " \"tanggalKegiatan\" TEXT DEFAULT NULL,\n" +
    // " \"deskripsiKegiatan\" TEXT DEFAULT NULL,\n" +
    // " \"idKategori\" INTEGER NOT NULL,\n" +
    // " \"isCheck\" INTEGER DEFAULT 0,\n" +
    // " FOREIGN KEY(\"idKategori\") REFERENCES
    // \"kategoriTable\"(\"idKategori\"),\n" +
    // " PRIMARY KEY(\"idKegiatan\" AUTOINCREMENT)\n" +
    // ")";
    // Statement kegiatanTable = conn.createStatement();
    // kegiatanTable.execute(sql);
    // sql = "CREATE TABLE \"lokasiTable\" (\n" +
    // " \"getX\" INTEGER DEFAULT NULL,\n" +
    // " \"getY\" INTEGER DEFAULT NULL,\n" +
    // " \"width\" INTEGER DEFAULT NULL,\n" +
    // " \"height\" INTEGER DEFAULT NULL\n" +
    // ")";
    // Statement lokasiTable = conn.createStatement();
    // lokasiTable.execute(sql);
    // sql = "INSERT INTO lokasiTable (getX,getY,width,height) VALUES
    // (0,0,312,386)";
    // PreparedStatement pst = conn.prepareStatement(sql);
    // pst.execute();
    // } catch (SQLException ex){
    // Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
    // JOptionPane.showMessageDialog(null, "Create database error : "+ ex);
    // }
    // }
    // } catch (SQLException | ClassNotFoundException ex) {
    // Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
    // JOptionPane.showMessageDialog(null, "Create databases error : "+ ex);
    // }
    // }
}

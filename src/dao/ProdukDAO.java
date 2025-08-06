/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.modelProduk;
import view.Produk;

/**
 *
 * @author leocr
 */
public class ProdukDAO {

    public Boolean simpanProduk(modelProduk produk) throws Exception {
        if (!checkProduk(produk.getKode())) {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "INSERT INTO produk (kode_produk, nama_produk, harga_produk,stok_produk)"
                    + "VALUES(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, produk.getKode());
            pst.setString(2, produk.getNama());
            pst.setDouble(3, produk.getHarga());
            pst.setInt(4, produk.getStok());

            pst.executeUpdate();
            return true;
        } else {
            return false;
        }

    }

    public List<modelProduk> getAllProduk() {
        List<modelProduk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                modelProduk p = new modelProduk(
                        rs.getString("kode_produk"),
                        rs.getString("nama_produk"),
                        rs.getDouble("harga_produk"),
                        rs.getInt("stok_produk")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean checkProduk(String kode) {
        String sql = "SELECT kode_produk FROM produk WHERE kode_produk = ?";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkTransaksi(String kode) {
        String sql = "SELECT * FROM detail_transaksi WHERE kode_produk = ?";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hapusProduk(String kode) throws Exception {
        String sql = "DELETE FROM produk WHERE kode_produk = ?";
        if (!checkTransaksi(kode)) {
            try {
                java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, kode);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Kode Produk : " + kode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Produk : " + kode + "!");
                e.printStackTrace(); // ← sudah benar sekarang
            }
        } else {
//            return false;
            JOptionPane.showMessageDialog(null, "Terdapat Transaksi pada Produk : " + kode + "!");
        }

    }

    public void editProduk(modelProduk produk) throws Exception {
        String sql = "UPDATE produk SET nama_produk = ?, harga_produk = ?, stok_produk = ?"
                + " WHERE kode_produk = ?";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, produk.getNama());
            pst.setDouble(2, produk.getHarga());
            pst.setInt(3, produk.getStok());
            pst.setString(4, produk.getKode());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Mengedit Produk : " + produk.getKode());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mengedit Produk : " + produk.getKode() + "!");
            System.out.println(e.getMessage());
        }

    }

}

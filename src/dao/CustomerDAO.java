/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.modelCustomer;

/**
 *
 * @author leocr
 */
public class CustomerDAO {

    public Boolean simpanCustomer(modelCustomer customer) throws Exception {
        if (!checkCustomer(customer.getKode())) {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "INSERT INTO wilayah"
                    + " (provinsi,kota,kecamatan,kelurahan,kode_pos)"
                    + "VALUES(?,?,?,?,?)";
            java.sql.PreparedStatement pstwilayah = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstwilayah.setString(1, customer.getProvinsi());
            pstwilayah.setString(2, customer.getKota());
            pstwilayah.setString(3, customer.getKecamatan());
            pstwilayah.setString(4, customer.getKelurahan());
            pstwilayah.setString(5, customer.getKodepos());

            pstwilayah.executeUpdate();

            ResultSet rs = pstwilayah.getGeneratedKeys();
            int idwilayah = 0;
            if (rs.next()) {
                idwilayah = rs.getInt(1);
            }

            String sql2 = "INSERT INTO customer"
                    + " (kode_customer,nama_customer,alamat_lengkap,id_wilayah)"
                    + "VALUES(?,?,?,?)";
            java.sql.PreparedStatement pstcustomer = conn.prepareStatement(sql2);

            pstcustomer.setString(1, customer.getKode());
            pstcustomer.setString(2, customer.getNama());
            pstcustomer.setString(3, customer.getAlamat());
            pstcustomer.setInt(4, idwilayah);
            pstcustomer.executeUpdate();

            return true;
        } else {
            return false;
        }

    }

    public List<modelCustomer> getAllProduk() {
        List<modelCustomer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer JOIN wilayah ON customer.id_wilayah = wilayah.id_wilayah";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                modelCustomer c = new modelCustomer(
                        rs.getString("kode_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("alamat_lengkap"),
                        rs.getString("provinsi"),
                        rs.getString("kota"),
                        rs.getString("kecamatan"),
                        rs.getString("kelurahan"),
                        rs.getString("kode_pos")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Boolean checkCustomer(String kode) {
        String sql = "SELECT kode_customer FROM customer WHERE kode_customer = ?";
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
        String sql = "SELECT * FROM detail_transaksi WHERE kode_customer = ?";
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

    public void hapusCustomer(String kode) throws Exception {
        String sql = "DELETE FROM customer WHERE kode_customer = ?";
        if (!checkTransaksi(kode)) {
            try {
                java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, kode);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Kode Customer : " + kode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Customer : " + kode + "!");
                e.printStackTrace(); // ← sudah benar sekarang
            }
        } else {
//            return false;
            JOptionPane.showMessageDialog(null, "Terdapat Transaksi pada Customer : " + kode + "!");
        }

    }

    public int getIdWilayah(String kode) {
        int idWilayah = -1; // default -1 jika tidak ditemukan
        String sql = "SELECT id_wilayah FROM customer WHERE kode_customer = ?";

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idWilayah = rs.getInt("id_wilayah");
            }

            
        } catch (Exception e) {
            System.err.println("Error mengambil id_wilayah: " + e.getMessage());
        }

        return idWilayah;
    }

    public void editCustomer(modelCustomer customer) throws Exception {
        String sqlwilayah= "UPDATE wilayah SET provinsi = ?, kota = ?, kecamatan = ?, kelurahan = ?, kode_pos = ?"
                + " WHERE id_wilayah = ?";
        String sqlcustomer = "UPDATE customer SET nama_customer = ?, alamat_lengkap = ?"
                + " WHERE kode_customer = ?";
        
        try {
            int idwilayah = getIdWilayah(customer.getKode());
            
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pstwilayah = conn.prepareStatement(sqlwilayah);
            pstwilayah.setString(1, customer.getProvinsi());
            pstwilayah.setString(2, customer.getKota());
            pstwilayah.setString(3, customer.getKecamatan());
            pstwilayah.setString(4, customer.getKelurahan());
            pstwilayah.setString(5, customer.getKodepos());
            pstwilayah.setInt(6, idwilayah);
            pstwilayah.executeUpdate();
            
            java.sql.PreparedStatement pstcustomer = conn.prepareStatement(sqlcustomer);
            pstcustomer.setString(1, customer.getNama());
            pstcustomer.setString(2, customer.getAlamat());
            pstcustomer.setString(3, customer.getKode());
            pstcustomer.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Berhasil Mengedit Customer : " + customer.getNama());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mengedit Customer : " + customer.getKode() + "!");
            System.out.println(e.getMessage());
        }

    }
}

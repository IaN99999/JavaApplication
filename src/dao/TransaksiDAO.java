/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.modelDetailTransaksi;
import model.modelTransaksi;

/**
 *
 * @author leocr
 */
public class TransaksiDAO {

    public String generateNoInvoice() throws SQLException {
        String prefix = "INV";
        LocalDate today = LocalDate.now(); // Ambil tanggal hari ini
        String tahunBulan = today.format(DateTimeFormatter.ofPattern("yyMM")); // Contoh: 2507
        String kodePrefix = prefix + "/" + tahunBulan + "/";

        int nomorUrut = 1; // default
        java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
        try {
            String sql = "SELECT MAX(SUBSTRING(no_invoice, 10, 4)) AS max_nomor "
                    + "FROM transaksi WHERE SUBSTRING(no_invoice, 5, 4) = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tahunBulan);

            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getString("max_nomor") != null) {
                nomorUrut = Integer.parseInt(rs.getString("max_nomor")) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String nomorFormat = String.format("%04d", nomorUrut);
        return kodePrefix + nomorFormat;
    }

    public List<String> getAllKodeCustomer() {
        List<String> listKode = new ArrayList<>();

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "SELECT kode_customer FROM customer";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listKode.add(rs.getString("kode_customer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listKode;
    }

    public Map<String, String> getDataCustomer(String kodeCustomer) {
        Map<String, String> data = new HashMap<>();

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "SELECT nama_customer, alamat_lengkap FROM customer WHERE kode_customer = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kodeCustomer);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                data.put("nama", rs.getString("nama_customer"));
                data.put("alamat", rs.getString("alamat_lengkap"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public List<modelTransaksi> getAllTransaksi() {
        List<modelTransaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi "
                + "JOIN customer ON transaksi.kode_customer = customer.kode_customer";

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                modelTransaksi p = new modelTransaksi(
                        rs.getString("no_invoice"),
                        rs.getString("kode_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("alamat_lengkap"),
                        rs.getDate("tgl_invoice").toLocalDate(),
                        rs.getDouble("total")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void simpanTransaksi(modelTransaksi transaksi) throws Exception {

        java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
        String sql = "INSERT INTO transaksi (no_invoice, kode_customer, tgl_invoice, total)"
                + " VALUES (?, ?, ?, ?)";
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, transaksi.getnoInvoice());
        pst.setString(2, transaksi.getkodeCustomer());
        pst.setDate(3, java.sql.Date.valueOf(transaksi.gettanggalInvoice()));
        pst.setDouble(4, transaksi.gettotal());

        pst.executeUpdate();
    }

    public void editTransaksi(modelTransaksi transaksi) throws Exception {
        String sql = "UPDATE transaksi SET kode_customer= ?, tgl_invoice = ?"
                + " WHERE no_invoice = ?";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, transaksi.getkodeCustomer());
            pst.setDate(2, java.sql.Date.valueOf(transaksi.gettanggalInvoice()));
            pst.setString(3, transaksi.getnoInvoice());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Mengedit Produk : " + transaksi.getnoInvoice());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mengedit Produk : " + transaksi.getnoInvoice() + "!");
            System.out.println(e.getMessage());
        }

    }

    public Boolean checkTransaksi(String kode) {
        String sql = "SELECT * FROM detail_transaksi WHERE no_invoice = ?";
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

    public void hapusTransaksi(String kode) throws Exception {
        String sql = "DELETE FROM transaksi WHERE no_invoice = ?";
        if (!checkTransaksi(kode)) {
            try {
                java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, kode);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Invoice : " + kode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Invoice : " + kode + "!");
                e.printStackTrace(); 
            }
        } else {

            JOptionPane.showMessageDialog(null, "Terdapat Transaksi pada Invoice : " + kode + "!");
        }

    }

    public List<String> getAllKodeProduk() {
        List<String> listKode = new ArrayList<>();

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "SELECT kode_produk FROM produk";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                listKode.add(rs.getString("kode_produk"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listKode;
    }

    public Map<String, String> getDataProduk(String kodeproduk) {
        Map<String, String> data = new HashMap<>();

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "SELECT nama_produk, harga_produk FROM produk WHERE kode_produk = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kodeproduk);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                data.put("nama", rs.getString("nama_produk"));
                data.put("harga", rs.getString("harga_produk"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public List<modelDetailTransaksi> getAllDetailTransaksi() {
        List<modelDetailTransaksi> list = new ArrayList<>();
        String sql = "SELECT * FROM detail_transaksi "
                + "JOIN produk ON detail_transaksi.kode_produk = produk.kode_produk";

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                modelDetailTransaksi p = new modelDetailTransaksi(
                        rs.getInt("id_detail"),
                        rs.getString("no_invoice"),
                        rs.getString("kode_produk"),
                        rs.getString("nama_produk"),
                        rs.getInt("qty"),
                        rs.getDouble("harga_produk"),
                        rs.getDouble("disc1"),
                        rs.getDouble("disc2"),
                        rs.getDouble("disc3")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int ambilStok(String kode) throws SQLException {
        int stok = 0; 

        String sql = "SELECT stok_produk FROM produk WHERE kode_produk = ?";
        try {
            java.sql.Connection conn = controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, kode);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                stok = rs.getInt("stok_produk");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return stok;
    }

    public void simpanDetailTransaksi(modelDetailTransaksi detailtransaksi) throws Exception {
        if (detailtransaksi.getqty() <= ambilStok(detailtransaksi.getkodeProduk())) {

            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            String sql = "INSERT INTO detail_transaksi (no_invoice, kode_produk, qty, disc1, disc2, disc3, "
                    + "harga_net, jumlah)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, detailtransaksi.getnoInvoice());
            pst.setString(2, detailtransaksi.getkodeProduk());
            pst.setInt(3, detailtransaksi.getqty());
            pst.setDouble(4, detailtransaksi.getdisc1());
            pst.setDouble(5, detailtransaksi.getdisc2());
            pst.setDouble(6, detailtransaksi.getdisc3());
            pst.setDouble(7, detailtransaksi.getharga_net());
            pst.setDouble(8, detailtransaksi.getjumlah());
            pst.executeUpdate();

            kurangStok(detailtransaksi.getkodeProduk(), detailtransaksi.getqty());
//            updateTotalTransaksi(detailtransaksi.getnoInvoice());
            JOptionPane.showMessageDialog(null, "Berhasil di Simpan");
        } else {
            JOptionPane.showMessageDialog(null, "Stok tidak cukup");
        }

    }

    public void kurangStok(String kode, int qty) {

        String sql = "UPDATE produk SET stok_produk = ?"
                + " WHERE kode_produk = ?";
        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            int stok_sekarang = ambilStok(kode);
            stok_sekarang = stok_sekarang - qty;
            pst.setInt(1, stok_sekarang);
            pst.setString(2, kode);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateTotalTransaksi(String noInvoice) {
        double total = 0.0;

        String sqlDetail = "SELECT qty, harga_produk, disc1, disc2, disc3 FROM detail_transaksi join produk "
                + "ON detail_transaksi.kode_produk = produk.kode_produk"
                + " WHERE no_invoice = ?";
        String sqlUpdate = "UPDATE transaksi SET total = ? WHERE no_invoice = ?";

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();

            java.sql.PreparedStatement pstDetail = conn.prepareStatement(sqlDetail);
            pstDetail.setString(1, noInvoice);
            ResultSet rs = pstDetail.executeQuery();

            while (rs.next()) {
                int qty = rs.getInt("qty");
                double harga = rs.getDouble("harga_produk");
                double disc1 = rs.getDouble("disc1");
                double disc2 = rs.getDouble("disc2");
                double disc3 = rs.getDouble("disc3");

                double afterDisc1 = harga - (harga * disc1 / 100);
                double afterDisc2 = afterDisc1 - (afterDisc1 * disc2 / 100);
                double afterDisc3 = afterDisc2 - (afterDisc2 * disc3 / 100);
                double hargaNet = afterDisc3;

                double jumlah = hargaNet * qty;

                total += jumlah;
            }

            java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);
            pstUpdate.setDouble(1, total);
            pstUpdate.setString(2, noInvoice);
            pstUpdate.executeUpdate();

            System.out.println("Total transaksi berhasil diperbarui: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hapusDetailTransaksi(int idDetail) {
        String sqlGetDetail = "SELECT kode_produk, qty FROM detail_transaksi WHERE id_detail = ?";
        String sqlUpdateStok = "UPDATE produk SET stok_produk = stok_produk + ? WHERE kode_produk = ?";
        String sqlDeleteDetail = "DELETE FROM detail_transaksi WHERE id_detail = ?";

        try {
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();

            // 1. Ambil data detail transaksi
            java.sql.PreparedStatement pstGet = conn.prepareStatement(sqlGetDetail);
            pstGet.setInt(1, idDetail);
            ResultSet rs = pstGet.executeQuery();

            if (rs.next()) {
                String kodeProduk = rs.getString("kode_produk");
                int qty = rs.getInt("qty");

                // 2. Update stok di tabel produk
                java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateStok);
                pstUpdate.setInt(1, qty);
                pstUpdate.setString(2, kodeProduk);
                pstUpdate.executeUpdate();

                // 3. Hapus detail transaksi
                java.sql.PreparedStatement pstDelete = conn.prepareStatement(sqlDeleteDetail);
                pstDelete.setInt(1, idDetail);
                pstDelete.executeUpdate();

                pstUpdate.close();
                pstDelete.close();
            }
            
            JOptionPane.showMessageDialog(null, "Berhasil Menghapus Transaksi : " + idDetail);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Transaksi : " + idDetail);
        }
    }
    
    public int ambilQtyLama(int idDetail) {
        int qty = 0;
        try {
            String sql = "SELECT qty FROM detail_transaksi WHERE id_detail = ?";
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idDetail);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                qty = rs.getInt("qty");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }
    
    public void updateStokProduk(String kodeProduk, int stokBaru) {
        try {
            String sql = "UPDATE produk SET stok_produk = ? WHERE kode_produk = ?";
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, stokBaru);
            pst.setString(2, kodeProduk);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateDetailTransaksi(modelDetailTransaksi model) {
        try {
            String sql = "UPDATE detail_transaksi SET qty = ?, disc1 = ?, disc2 = ?, disc3 = ?,harga_net = ?, jumlah = ? WHERE id_detail = ?";
            java.sql.Connection conn = (java.sql.Connection) controller.connection.connectDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, model.getqty());
            pst.setDouble(2, model.getdisc1());
            pst.setDouble(3, model.getdisc2());
            pst.setDouble(4, model.getdisc3());
            pst.setDouble(5, model.getharga_net());
            pst.setDouble(6, model.getjumlah());
            pst.setInt(7, model.getIddetail());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

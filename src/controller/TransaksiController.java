/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TransaksiDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import model.modelDetailTransaksi;
import model.modelTransaksi;

/**
 *
 * @author leocr
 */
public class TransaksiController {

    TransaksiDAO dao;

    public TransaksiController() {
        dao = new TransaksiDAO();
    }

    public String generateInv() throws SQLException {
        return dao.generateNoInvoice();
    }

    public List<String> ambilSemuaKodeCustomer() {
        return dao.getAllKodeCustomer();
    }

    public Map<String, String> getCustomer(String kode) {
        return dao.getDataCustomer(kode);
    }

    public List<modelTransaksi> ambilSemuaTransaksi() {
        return dao.getAllTransaksi();
    }

    public Boolean simpan(String noinv, String kodeCustomer, String namaCustomer,
            String alamatCustomer, String tanggal, String totalStr) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // ubah jika perlu
            LocalDate tglInvoice = LocalDate.parse(tanggal, formatter);
            double total = Double.parseDouble(totalStr);

            modelTransaksi trx = new modelTransaksi(noinv, kodeCustomer, namaCustomer, alamatCustomer, tglInvoice, total);

            dao.simpanTransaksi(trx);
            dao.updateTotalTransaksi(noinv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hapus(String kode) throws Exception {
        dao.hapusTransaksi(kode);
    }

    public void edit(String noinv, String kodeCustomer, String namaCustomer,
            String alamatCustomer, String tanggal, String totalStr) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // ubah jika perlu
        LocalDate tglInvoice = LocalDate.parse(tanggal, formatter);
        double total = Double.parseDouble(totalStr);
        modelTransaksi trx = new modelTransaksi(noinv, kodeCustomer, namaCustomer, alamatCustomer, tglInvoice, total);
        dao.editTransaksi(trx);
    }

    public List<String> ambilSemuaKodeProduk() {
        return dao.getAllKodeProduk();
    }

    public Map<String, String> getProduk(String kode) {
        return dao.getDataProduk(kode);
    }

    public List<modelDetailTransaksi> ambilSemuaDetailTransaksi() {
        return dao.getAllDetailTransaksi();
    }

    public Boolean simpandetail(String noInvoice, String kodeProduk, String namaProduk,
            int qty, double harga, double disc1, double disc2, double disc3) throws Exception {
        try {
            modelDetailTransaksi trx = new modelDetailTransaksi(noInvoice, kodeProduk,
                    namaProduk, qty, harga, disc1, disc2, disc3);

            dao.simpanDetailTransaksi(trx);
            dao.updateTotalTransaksi(noInvoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hapusdetail(int id_detail, String noInvoice) throws Exception {
        dao.hapusDetailTransaksi(id_detail);
        dao.updateTotalTransaksi(noInvoice);
    }

    public boolean updateDetailDanStok(
            String idDetail,
            String noInvoice,
            String kodeProduk,
            String namaProduk,
            String harga,
            String disc1,
            String disc2,
            String disc3,
            String qty
    ) throws SQLException {

        int id_detail = Integer.parseInt(idDetail);
        double hrg = Double.parseDouble(harga);
        double d1 = Double.parseDouble(disc1);
        double d2 = Double.parseDouble(disc2);
        double d3 = Double.parseDouble(disc3);
        int jumlahBaru = Integer.parseInt(qty);

        modelDetailTransaksi model = new modelDetailTransaksi(
                Integer.parseInt(idDetail),
                noInvoice,
                kodeProduk,
                namaProduk,
                Integer.parseInt(qty),
                Double.parseDouble(harga),
                Double.parseDouble(disc1),
                Double.parseDouble(disc2),
                Double.parseDouble(disc3)
        );

     
        TransaksiDAO dao = new TransaksiDAO();

        int qtyLama = dao.ambilQtyLama(id_detail);
        int stokSekarang = dao.ambilStok(kodeProduk);

        int stokBaru = stokSekarang + qtyLama - jumlahBaru;

        if (stokBaru < 0) {
            JOptionPane.showMessageDialog(null, "Stok tidak mencukupi untuk Edit!");
            return false;
        }

        dao.updateDetailTransaksi(model);
        dao.updateStokProduk(kodeProduk, stokBaru);
        dao.updateTotalTransaksi(noInvoice);
        JOptionPane.showMessageDialog(null, "Berhasil Edit");
        return true;
    }

}

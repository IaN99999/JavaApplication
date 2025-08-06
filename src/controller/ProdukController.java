/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProdukDAO;
import java.util.List;
import javax.swing.JOptionPane;
import model.modelProduk;

/**
 *
 * @author leocr
 */
public class ProdukController {

    ProdukDAO dao;

    public ProdukController() {
        dao = new ProdukDAO();
    }

    public Boolean simpan(String kode, String nama, String harga, String stok) throws Exception {
        double hrg = Double.parseDouble(harga);
        int stk = Integer.parseInt(stok);
        modelProduk p = new modelProduk(kode, nama, hrg, stk);
//        dao.simpanProduk(p);
        boolean success = dao.simpanProduk(p);
        if (success) {
            JOptionPane.showMessageDialog(null, "Berhasil disimpan");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Kode produk sudah ada!");
            return false;
        }

    }

    public void hapus(String kode) throws Exception {
        dao.hapusProduk(kode);
    }

    public void edit(String kode, String nama, String harga, String stok) throws Exception {
        double hrg = Double.parseDouble(harga);
        int stk = Integer.parseInt(stok);
        modelProduk p = new modelProduk(kode, nama, hrg, stk);
        dao.editProduk(p);
    }

    public List<modelProduk> ambilSemuaProduk() {
        return dao.getAllProduk();
    }
}

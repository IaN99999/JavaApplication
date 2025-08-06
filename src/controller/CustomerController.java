/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import java.util.List;
import javax.swing.JOptionPane;
import model.modelCustomer;


/**
 *
 * @author leocr
 */
public class CustomerController {
    CustomerDAO dao;

    public CustomerController() {
        dao = new CustomerDAO();
    }

    public Boolean simpan(String kode, String nama, String alamat, String provinsi, 
            String kota, String kecamatan, String kelurahan, String kode_pos) throws Exception {
        modelCustomer p = new modelCustomer(kode, nama, alamat, provinsi, kota, kecamatan, kelurahan, kode_pos);
//        dao.simpanProduk(p);
        boolean success = dao.simpanCustomer(p);
        if (success) {
            JOptionPane.showMessageDialog(null, "Berhasil disimpan");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Kode Customer sudah ada!");
            return false;
        }

    }

    public void hapus(String kode) throws Exception {
        dao.hapusCustomer(kode);
    }

    public void edit(String kode, String nama, String alamat, String provinsi, 
            String kota, String kecamatan, String kelurahan, String kode_pos) throws Exception {
        modelCustomer p = new modelCustomer(kode, nama, alamat, provinsi, kota, kecamatan, kelurahan, kode_pos);
        dao.editCustomer(p);
    }

    public List<modelCustomer> ambilSemuaProduk() {
        return dao.getAllProduk();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leocr
 */
public class modelDetailTransaksi {

    private int id_detail;
    private String noInvoice;
    private String kodeProduk;
    private String namaProduk;
    private int qty;
    private double harga;
    private double disc1;
    private double disc2;
    private double disc3;
    private double harga_net;
    private double jumlah;

    public modelDetailTransaksi(int id_detail, String noInvoice, String kodeProduk, String namaProduk,
            int qty, double harga, double disc1, double disc2, double disc3) {
        this.id_detail = id_detail;
        this.noInvoice = noInvoice;
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.qty = qty;
        this.disc1 = disc1;
        this.disc2 = disc2;
        this.disc3 = disc3;
        this.harga = harga;

        this.harga_net = hitungHargaNet(harga, disc1, disc2, disc3);
        this.jumlah = this.harga_net * qty;

    }
    
    public modelDetailTransaksi( String noInvoice, String kodeProduk, String namaProduk,
            int qty, double harga, double disc1, double disc2, double disc3) {
//        this.id_detail = id_detail;
        this.noInvoice = noInvoice;
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.qty = qty;
        this.disc1 = disc1;
        this.disc2 = disc2;
        this.disc3 = disc3;
        this.harga = harga;

        this.harga_net = hitungHargaNet(harga, disc1, disc2, disc3);
        this.jumlah = this.harga_net * qty;

    }

    public int getIddetail() {
        return id_detail;
    }
    public String getnoInvoice() {
        return noInvoice;
    }

    public String getkodeProduk() {
        return kodeProduk;
    }

    public String getnamaProduk() {
        return namaProduk;
    }

    public int getqty() {
        return qty;
    }

    public double getharga() {
        return harga;
    }

    public double getdisc1() {
        return disc1;
    }

    public double getdisc2() {
        return disc2;
    }

    public double getdisc3() {
        return disc3;
    }
    public double getharga_net(){
        return harga_net;
    }
    public double getjumlah(){
        return jumlah;
    }

    private double hitungHargaNet(double harga, double disc1, double disc2, double disc3) {
        double h1 = harga - (harga * disc1 / 100);
        double h2 = h1 - (h1 * disc2 / 100);
        double h3 = h2 - (h2 * disc3 / 100);
        return h3;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leocr
 */
public class modelCustomer {
    private String kode;
    private String nama;
    private String alamat_lengkap;
    private String provinsi;
    private String kota;
    private String kecamatan;
    private String kelurahan;
    private String kode_pos;
    
    public modelCustomer(String kode, String nama, String alamat_lengkap, String provinsi, String kota,
            String kecamatan, String kelurahan, String kode_pos) {
        this.kode = kode;
        this.nama = nama;
        this.alamat_lengkap = alamat_lengkap;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.kode_pos = kode_pos;
    }
    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat_lengkap; }
    public String getProvinsi() { return provinsi; }
    public String getKota() { return kota; }
    public String getKecamatan(){ return kecamatan; }
    public String getKelurahan() { return kelurahan; }
    public String getKodepos() { return kode_pos; }
    
}

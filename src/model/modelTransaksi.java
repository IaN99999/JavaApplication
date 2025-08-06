/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author leocr
 */
public class modelTransaksi {

    private String noInvoice;
    private String kodeCustomer;
    private String namaCustomer;
    private String alamatCustomer;
    private LocalDate tanggalInvoice;
    private double total;

    public modelTransaksi(String noInvoice, String kodeCustomer, String namaCustomer,
            String alamatCustomer, LocalDate tanggalInvoice, double total) {
        this.noInvoice = noInvoice;
        this.kodeCustomer = kodeCustomer;
        this.namaCustomer = namaCustomer;
        this.alamatCustomer = alamatCustomer;
        this.tanggalInvoice = tanggalInvoice;
        this.total = total;
    }

    public String getnoInvoice() {
        return noInvoice;
    }

    public String getkodeCustomer() {
        return kodeCustomer;
    }

    public String getnamaCustomer() {
        return namaCustomer;
    }

    public String getalamatCustomer() {
        return alamatCustomer;
    }

    public LocalDate gettanggalInvoice() {
        return tanggalInvoice;
    }

    public double gettotal() {
        return total;
    }
}

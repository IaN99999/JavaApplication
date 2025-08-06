/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.TransaksiController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.modelDetailTransaksi;
import model.modelTransaksi;

/**
 *
 * @author leocr
 */
public class Transaksi extends javax.swing.JFrame {

    private TransaksiController controller;
    JTextField id_detail = new JTextField();

    public Transaksi() {
        initComponents();
        controller = new TransaksiController();
        kode_customer.removeAllItems();
        kode_customer.addItem("Pilih Kode...");
        List<String> kodecust = controller.ambilSemuaKodeCustomer();

        for (String kode : kodecust) {
            kode_customer.addItem(kode);
        }

        kode_produk.removeAllItems();
        kode_produk.addItem("Pilih Kode...");
        List<String> kodepro = controller.ambilSemuaKodeProduk();

        for (String kode : kodepro) {
            kode_produk.addItem(kode);
        }

        tampil_data();
        tampil_data_detail();

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if (row >= 0) {
                    no_inv.setText(jTable1.getValueAt(row, 0).toString());
                    kode_customer.setSelectedItem(jTable1.getValueAt(row, 1).toString());
                    nama_customer.setText(jTable1.getValueAt(row, 2).toString());
                    alamat.setText(jTable1.getValueAt(row, 3).toString());
                    LocalDate tanggal = (LocalDate) jTable1.getValueAt(row, 4);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    tanggal_inv.setText(tanggal.format(formatter));
                    no_inv_detail.setText(jTable1.getValueAt(row, 0).toString());
                }
            }
        });

        id_detail.setEditable(false);  // Tidak bisa diubah user
        id_detail.setEnabled(false);   // Tidak bisa difokus / dipilih
        id_detail.setVisible(false);   // Tidak muncul di GUI

        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable2.getSelectedRow();
                if (row >= 0) {
                    id_detail.setText(jTable2.getValueAt(row, 0).toString());
                    no_inv_detail.setText(jTable2.getValueAt(row, 1).toString());
                    kode_produk.setSelectedItem(jTable2.getValueAt(row, 2).toString());
                    nama_produk.setText(jTable2.getValueAt(row, 3).toString());
                    qty.setText(jTable2.getValueAt(row, 4).toString());
                    harga.setText(jTable2.getValueAt(row, 5).toString());
                    disc1.setText(jTable2.getValueAt(row, 6).toString());
                    disc2.setText(jTable2.getValueAt(row, 7).toString());
                    disc3.setText(jTable2.getValueAt(row, 8).toString());
                }
            }
        });

    }

    public void tampil_data() {

        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("No Invoice");
        tabel.addColumn("Kode Customer");
        tabel.addColumn("Nama Customer");
        tabel.addColumn("Alamat");
        tabel.addColumn("Tanggal Invoice");
        tabel.addColumn("Total");
        try {
            List<modelTransaksi> list = controller.ambilSemuaTransaksi();
            for (modelTransaksi p : list) {
                tabel.addRow(new Object[]{
                    p.getnoInvoice(),
                    p.getkodeCustomer(),
                    p.getnamaCustomer(),
                    p.getalamatCustomer(),
                    p.gettanggalInvoice(),
                    p.gettotal()
                });
            }
            jTable1.setModel(tabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void tampil_data_detail() {

        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("Id");
        tabel.addColumn("No Invoice");
        tabel.addColumn("Kode Produk");
        tabel.addColumn("Nama Produk");
        tabel.addColumn("QTY");
        tabel.addColumn("Harga");
        tabel.addColumn("Disc1");
        tabel.addColumn("Disc2");
        tabel.addColumn("Disc3");
        tabel.addColumn("Harga Net");
        tabel.addColumn("Jumlah");
        try {
            List<modelDetailTransaksi> list = controller.ambilSemuaDetailTransaksi();
            for (modelDetailTransaksi p : list) {
                tabel.addRow(new Object[]{
                    p.getIddetail(),
                    p.getnoInvoice(),
                    p.getkodeProduk(),
                    p.getnamaProduk(),
                    p.getqty(),
                    p.getharga(),
                    p.getdisc1(),
                    p.getdisc2(),
                    p.getdisc3(),
                    p.getharga_net(),
                    p.getjumlah()
                });
            }
            jTable2.setModel(tabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        no_inv = new javax.swing.JTextField();
        kode_customer = new javax.swing.JComboBox<>();
        nama_customer = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        tanggal_inv = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btn_generate_noinv = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        no_inv_detail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        kode_produk = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        nama_produk = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        disc1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        disc2 = new javax.swing.JTextField();
        disc3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_simpan_detail = new javax.swing.JButton();
        btn_edit_detail = new javax.swing.JButton();
        btn_hapus_detail = new javax.swing.JButton();
        btn_clear_detail = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Transaksi");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("No Invoice");

        jLabel4.setText("Kode Customer");

        jLabel5.setText("Nama Customer");

        jLabel6.setText("Alamat");

        jLabel7.setText("Tanggal Invoice");

        no_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_invActionPerformed(evt);
            }
        });

        kode_customer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kode_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_customerActionPerformed(evt);
            }
        });

        tanggal_inv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggal_invActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btn_generate_noinv.setText("Generate No Invoice");
        btn_generate_noinv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generate_noinvActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        jLabel3.setText("No Invoice");

        jLabel8.setText("Kode Produk");

        kode_produk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kode_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_produkActionPerformed(evt);
            }
        });

        jLabel9.setText("Nama Produk");

        jLabel10.setText("QTY");

        jLabel11.setText("Harga");

        jLabel12.setText("Diskon 1");

        jLabel13.setText("Diskon 2");

        jLabel14.setText("Diskon 3");

        btn_simpan_detail.setText("Simpan");
        btn_simpan_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_detailActionPerformed(evt);
            }
        });

        btn_edit_detail.setText("Edit");
        btn_edit_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_detailActionPerformed(evt);
            }
        });

        btn_hapus_detail.setText("Hapus");
        btn_hapus_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_detailActionPerformed(evt);
            }
        });

        btn_clear_detail.setText("Clear");
        btn_clear_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clear_detailActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setText("Detail Transaksi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(no_inv)
                                            .addComponent(kode_customer, 0, 154, Short.MAX_VALUE)
                                            .addComponent(nama_customer)
                                            .addComponent(alamat)
                                            .addComponent(tanggal_inv)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_generate_noinv)
                                        .addGap(32, 32, 32)
                                        .addComponent(btn_simpan))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_edit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_hapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_clear)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(disc3)
                                            .addComponent(disc2)
                                            .addComponent(no_inv_detail)
                                            .addComponent(kode_produk, 0, 149, Short.MAX_VALUE)
                                            .addComponent(nama_produk)
                                            .addComponent(qty)
                                            .addComponent(harga)
                                            .addComponent(disc1)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_simpan_detail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_edit_detail)
                                        .addGap(12, 12, 12)
                                        .addComponent(btn_clear_detail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(btn_hapus_detail)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(jLabel15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(no_inv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kode_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(nama_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tanggal_inv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_generate_noinv)
                            .addComponent(btn_simpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit)
                            .addComponent(btn_hapus)
                            .addComponent(btn_clear)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(no_inv_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kode_produk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nama_produk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(disc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(disc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(disc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan_detail)
                            .addComponent(btn_edit_detail)
                            .addComponent(btn_hapus_detail)
                            .addComponent(btn_clear_detail)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void no_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_invActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_invActionPerformed

    private void btn_generate_noinvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generate_noinvActionPerformed
        try {
            String noinv = controller.generateInv();
            no_inv.setText(noinv);
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String tanggalStr = today.format(formatter);
            tanggal_inv.setText(tanggalStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Generate Invoice");
            System.out.println(e.getMessage());

        }
    }//GEN-LAST:event_btn_generate_noinvActionPerformed

    private void btn_simpan_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_detailActionPerformed
        try {
            boolean berhasil = controller.simpandetail(
                    no_inv.getText(),
                    (String) kode_produk.getSelectedItem(),
                    nama_produk.getText(),
                    Integer.parseInt(qty.getText()),
                    Double.parseDouble(harga.getText()),
                    Double.parseDouble(disc1.getText()),
                    Double.parseDouble(disc2.getText()),
                    Double.parseDouble(disc3.getText())
            );

            if (berhasil) {
                clearFormdetail();
                tampil_data_detail();
                tampil_data();

            } else {
                JOptionPane.showMessageDialog(null, "Gagal di Simpan");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal di Simpan");
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btn_simpan_detailActionPerformed

    private void btn_edit_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_detailActionPerformed
        try {
            controller.updateDetailDanStok(
                    id_detail.getText(),
                    no_inv.getText(),
                    (String) kode_produk.getSelectedItem(),
                    nama_produk.getText(),
                    harga.getText(),
                    disc1.getText(),
                    disc2.getText(),
                    disc3.getText(),
                    qty.getText()
            );
            clearFormdetail();
            tampil_data_detail();
            tampil_data();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_edit_detailActionPerformed

    private void btn_hapus_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_detailActionPerformed
        try {
            int dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Ingin Menghapus Transaksi : " + no_inv.getText(),
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                controller.hapusdetail(Integer.parseInt(id_detail.getText()), no_inv_detail.getText());
                clearForm();
                tampil_data_detail();
                tampil_data();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapus_detailActionPerformed

    private void btn_clear_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clear_detailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clear_detailActionPerformed

    private void kode_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_customerActionPerformed
        String selectedkode = (String) kode_customer.getSelectedItem();

        if (selectedkode != null && !selectedkode.equals("Pilih Kode...")) {
            Map<String, String> data = controller.getCustomer(selectedkode);
            if (!data.isEmpty()) {
                nama_customer.setText(data.get("nama"));
                alamat.setText(data.get("alamat"));
            }
        }
    }//GEN-LAST:event_kode_customerActionPerformed

    private void clearFormdetail() {
        no_inv_detail.setText("");
        kode_produk.setSelectedIndex(0);
        nama_produk.setText("");
        qty.setText("");
        harga.setText("");
        disc1.setText("");
        disc2.setText("");
        disc3.setText("");
    }

    private void tanggal_invActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggal_invActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggal_invActionPerformed

    private void clearForm() {
        no_inv.setText("");
        kode_customer.setSelectedIndex(0);
        nama_customer.setText("");
        alamat.setText("");
        tanggal_inv.setText("");
    }

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        try {
            boolean berhasil = controller.simpan(
                    no_inv.getText(),
                    (String) kode_customer.getSelectedItem(),
                    nama_customer.getText(),
                    alamat.getText(),
                    tanggal_inv.getText(),
                    "0"
            );

            if (berhasil) {
                clearForm();
                tampil_data();
                JOptionPane.showMessageDialog(null, "Berhasil di Simpan");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal di Simpan");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal di Simpan");
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clearForm();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        try {
            controller.edit(
                    no_inv.getText(),
                    (String) kode_customer.getSelectedItem(),
                    nama_customer.getText(),
                    alamat.getText(),
                    tanggal_inv.getText(),
                    "0"
            );
            clearForm();
            tampil_data();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        try {
            int dialogResult = JOptionPane.showConfirmDialog(
                    null,
                    "Ingin Menghapus Invoice : " + no_inv.getText(),
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                controller.hapus(no_inv.getText());
                clearForm();
                tampil_data();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void kode_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_produkActionPerformed
        String selectedkode = (String) kode_produk.getSelectedItem();

        if (selectedkode != null && !selectedkode.equals("Pilih Kode...")) {
            Map<String, String> data = controller.getProduk(selectedkode);
            if (!data.isEmpty()) {
                nama_produk.setText(data.get("nama"));
                harga.setText(data.get("harga"));
//                System.out.println(data);
            }
        }
    }//GEN-LAST:event_kode_produkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_clear_detail;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_edit_detail;
    private javax.swing.JButton btn_generate_noinv;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_hapus_detail;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_simpan_detail;
    private javax.swing.JTextField disc1;
    private javax.swing.JTextField disc2;
    private javax.swing.JTextField disc3;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> kode_customer;
    private javax.swing.JComboBox<String> kode_produk;
    private javax.swing.JTextField nama_customer;
    private javax.swing.JTextField nama_produk;
    private javax.swing.JTextField no_inv;
    private javax.swing.JTextField no_inv_detail;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField tanggal_inv;
    // End of variables declaration//GEN-END:variables
}

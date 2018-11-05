/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LatihController;
import Controller.ListPelatihan;
import Model.Training.NeuralNetwork.Pelatihan;
import java.awt.Color;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M.Hakim Amransyah
 */
public class PanelKelolaPelatihan extends javax.swing.JPanel {
    
    private LatihController controller_pelatihan;
    private ListPelatihan hasil_pelatihan;
    
    public PanelKelolaPelatihan(LatihController controller_pelatihan) {
        this.controller_pelatihan = controller_pelatihan;
        initComponents();
        try {
            this.setDataPelatihan(this.controller_pelatihan.bacaDataPelatihan());
        } catch (SQLException ex) {
            Logger.getLogger(PanelPelatihan.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setBarisTerpililh(0);
        this.centeringPelatihanTable();
        this.optimasiBobot(false);
    }
    
    public Pelatihan ambilPelatihanTerpilih(){
        Pelatihan pelatihan_terpilih = null; 
        int id = (int) this.tabel_pelatihan.getValueAt(this.tabel_pelatihan.getSelectedRow(), 0);
        pelatihan_terpilih = this.hasil_pelatihan.getPelatihanTerpilih(id);
        return pelatihan_terpilih;
    }
    
    public PanelKelolaPelatihan disableKonfigurasidanButton(){
        this.konfigurasi.hide();
        this.control1.hide();
        this.tabel_pelatihan.getColumnModel().getColumn(3).setWidth(0);
        this.tabel_pelatihan.getColumnModel().getColumn(3).setMaxWidth(0);
        this.tabel_pelatihan.getColumnModel().getColumn(3).setMinWidth(0);
        return this;
    }
    
    private void centeringPelatihanTable(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
       for(int i=0;i<this.tabel_pelatihan.getColumnCount()-1;i++){
           this.tabel_pelatihan.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
       }    
    }
    
    public void setDataPelatihan(ListPelatihan hasil_pelatihan){
        this.hasil_pelatihan = hasil_pelatihan;
        DefaultTableModel model_tabel_pelatihan = (DefaultTableModel) this.tabel_pelatihan.getModel();
        model_tabel_pelatihan.setRowCount(0);
        for(Model.Training.NeuralNetwork.Pelatihan pelatihan : this.hasil_pelatihan.getHasil_pelatihan()){
           model_tabel_pelatihan.addRow(new Object[]{pelatihan.getId_pelatihan(),pelatihan.getMse(),pelatihan.getKonfigurasi().getTipe_algoritma()});            
        }
        this.setBarisTerpililh(0);
    }
    
    public void setBarisTerpililh(int index_baris){
        if(index_baris > 0 || index_baris == 0 && this.hasil_pelatihan.getHasil_pelatihan().size() != 0){
           this.tabel_pelatihan.setRowSelectionInterval(index_baris,index_baris);
           this.setDeksripsiBarisTerpilih((int) this.tabel_pelatihan.getValueAt(index_baris,0),"bplm");   
        }else if(this.hasil_pelatihan.getHasil_pelatihan().size() == 0){
            this.label_id_pelatihan.setText("__");
            this.label_epoch.setText("__");
            this.label_hidden_neuron.setText("__");
            this.label_input_neuron.setText("__");
            this.label_learning_rate.setText("__");
            this.label_pa.setText("__");
            this.label_error.setText("__");
            this.label_sarang.setText("__");
            this.label_generasi.setText("__");
            this.label_mse.setText("__");
            this.waktu_eksekusi.setText("__");
            this.akurasi_validasi.setText("__");
            this.label_tipe_algoritma.setText("__");
        }
    }
    
    private void optimasiBobot(boolean cond){
        if(cond == true){
            this.sarang.enable();
            this.sarang.setBackground(Color.WHITE);
            this.generasi.enable();
            this.generasi.setBackground(Color.WHITE);
            this.Pa.enable();
            this.Pa.setBackground(Color.WHITE);
        }
        else{
            this.sarang.disable();
            this.sarang.setBackground(Color.GRAY);
            this.generasi.disable();
            this.generasi.setBackground(Color.GRAY);
            this.Pa.disable();
            this.Pa.setBackground(Color.GRAY);
        }
    }
    
    private void setLabelKonfigurasiCS(boolean cond){
        this.label_pa.setVisible(cond);
        this.probability_label.setVisible(cond);
        this.label_sarang.setVisible(cond);
        this.sarang_label.setVisible(cond);
        this.label_generasi.setVisible(cond);
        this.generasi_label.setVisible(cond);
    }
    
    private void setDeksripsiBarisTerpilih(int id_pelatihan,String tipe){
        Model.Training.NeuralNetwork.Pelatihan pelatihan_terpilih;
        pelatihan_terpilih = this.hasil_pelatihan.getPelatihanTerpilih(id_pelatihan);
        this.label_tipe_algoritma.setText(pelatihan_terpilih.getKonfigurasi().getTipe_algoritma());
        this.label_input_neuron.setText(pelatihan_terpilih.getKonfigurasi().getNeuron_input_layer()-1+"");
        this.label_hidden_neuron.setText(pelatihan_terpilih.getKonfigurasi().getNeuron_hidden_layer()+"");
        this.label_id_pelatihan.setText(pelatihan_terpilih.getId_pelatihan()+"");
        this.label_epoch.setText(pelatihan_terpilih.getKonfigurasi().getEpoch()+"");
        this.label_learning_rate.setText(pelatihan_terpilih.getKonfigurasi().getLearning_rate()+"");
        this.label_error.setText(pelatihan_terpilih.getKonfigurasi().getError()+"");
        this.label_mse.setText(pelatihan_terpilih.getMse()+"");
        this.waktu_eksekusi.setText(pelatihan_terpilih.getWaktu_eksekusi()+" detik");
        this.akurasi_validasi.setText(pelatihan_terpilih.getAkurasi_validasi()+" %");
        if(pelatihan_terpilih.getKonfigurasi().getTipe_algoritma().equalsIgnoreCase("CSLM") || pelatihan_terpilih.getKonfigurasi().getTipe_algoritma().equalsIgnoreCase("unsaved CSLM")){
            this.setLabelKonfigurasiCS(true);
            this.label_pa.setText(pelatihan_terpilih.getKonfigurasi().getPa()+"");
            this.label_sarang.setText(pelatihan_terpilih.getKonfigurasi().getSarang()+"");
            this.label_generasi.setText(pelatihan_terpilih.getKonfigurasi().getMaksimum_generasi()+"");
        }else{
            this.setLabelKonfigurasiCS(false);
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

        opsi_optimasi = new javax.swing.ButtonGroup();
        PanelLatih1 = new javax.swing.JPanel();
        konfigurasi = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        BPLM = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NH = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        neuron_hidden_layer = new javax.swing.JTextField();
        LR = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        learning_rate = new javax.swing.JTextField();
        epoch_panel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        epoch = new javax.swing.JTextField();
        error_panel = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        error = new javax.swing.JTextField();
        Optimize = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        optimasi_dengan_cs = new javax.swing.JRadioButton();
        javax.swing.JRadioButton nilai_random_bobot_Awal = new javax.swing.JRadioButton();
        CS = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        sarang_panel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        sarang = new javax.swing.JTextField();
        generasi_panel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        generasi = new javax.swing.JTextField();
        Pa_panel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Pa = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        doPelatihan = new javax.swing.JButton();
        tittle_configuration = new javax.swing.JLabel();
        pelatihan = new javax.swing.JPanel();
        LatihPanel = new javax.swing.JPanel();
        pelatihan_part_1 = new javax.swing.JPanel();
        hasil = new javax.swing.JPanel();
        box_training = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hasil_training_dalem_box = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_pelatihan = new javax.swing.JTable();
        control1 = new javax.swing.JPanel();
        simpanPelatihan = new javax.swing.JButton();
        hapus_pelatihan = new javax.swing.JButton();
        keterangan = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        label_id_pelatihan = new javax.swing.JLabel();
        label_tipe_algoritma = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        sarang_label = new javax.swing.JLabel();
        generasi_label = new javax.swing.JLabel();
        probability_label = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        label_akurasi_validasi = new javax.swing.JLabel();
        label_epoch = new javax.swing.JLabel();
        label_hidden_neuron = new javax.swing.JLabel();
        label_learning_rate = new javax.swing.JLabel();
        label_error = new javax.swing.JLabel();
        label_sarang = new javax.swing.JLabel();
        label_generasi = new javax.swing.JLabel();
        label_pa = new javax.swing.JLabel();
        waktu_eksekusi = new javax.swing.JLabel();
        label_mse = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        label_input_neuron = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        akurasi_validasi = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        PanelLatih1.setBackground(new java.awt.Color(51, 153, 102));
        PanelLatih1.setLayout(new java.awt.BorderLayout());

        konfigurasi.setBackground(new java.awt.Color(51, 153, 102));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(0, 26, 51));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        BPLM.setBackground(new java.awt.Color(0, 26, 51));
        BPLM.setLayout(new javax.swing.BoxLayout(BPLM, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel14.setBackground(new java.awt.Color(0, 38, 77));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Backpropagation Levenberg Marquardt");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BPLM.add(jPanel14);

        NH.setBackground(new java.awt.Color(0, 38, 77));
        NH.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        NH.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(71, 107, 107));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Neuron Hidden Layer");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        NH.add(jPanel6, java.awt.BorderLayout.LINE_START);

        neuron_hidden_layer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        neuron_hidden_layer.setText("10");
        neuron_hidden_layer.setToolTipText("Jumlah Neuron Hidden Layer");
        NH.add(neuron_hidden_layer, java.awt.BorderLayout.CENTER);

        BPLM.add(NH);

        LR.setBackground(new java.awt.Color(0, 38, 77));
        LR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LR.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(71, 107, 107));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Learning rate");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        LR.add(jPanel7, java.awt.BorderLayout.LINE_START);

        learning_rate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        learning_rate.setText("0.2");
        LR.add(learning_rate, java.awt.BorderLayout.CENTER);

        BPLM.add(LR);

        epoch_panel.setBackground(new java.awt.Color(0, 38, 77));
        epoch_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        epoch_panel.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(71, 107, 107));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Epoch");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        epoch_panel.add(jPanel8, java.awt.BorderLayout.LINE_START);

        epoch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        epoch.setText("500");
        epoch_panel.add(epoch, java.awt.BorderLayout.CENTER);

        BPLM.add(epoch_panel);

        error_panel.setBackground(new java.awt.Color(0, 38, 77));
        error_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        error_panel.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(71, 107, 107));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Minimum Error");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        error_panel.add(jPanel9, java.awt.BorderLayout.LINE_START);

        error.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        error.setText("0.0001");
        error_panel.add(error, java.awt.BorderLayout.CENTER);

        BPLM.add(error_panel);

        Optimize.setBackground(new java.awt.Color(0, 38, 77));
        Optimize.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Optimize.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(71, 107, 107));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gunakan");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Cuckoo Search");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        Optimize.add(jPanel10, java.awt.BorderLayout.LINE_START);

        opsi_optimasi.add(optimasi_dengan_cs);
        optimasi_dengan_cs.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        optimasi_dengan_cs.setText("ya");
        optimasi_dengan_cs.setName("optimasi_dengan_cs"); // NOI18N
        optimasi_dengan_cs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optimasi_dengan_csoptimasiBobot(evt);
            }
        });

        opsi_optimasi.add(nilai_random_bobot_Awal);
        nilai_random_bobot_Awal.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        nilai_random_bobot_Awal.setSelected(true);
        nilai_random_bobot_Awal.setText("tidak");
        nilai_random_bobot_Awal.setName("nilai_random_bobot_Awal"); // NOI18N
        nilai_random_bobot_Awal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nilai_random_bobot_AwaloptimasiBobot(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optimasi_dengan_cs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nilai_random_bobot_Awal)
                .addGap(9, 9, 9))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optimasi_dengan_cs)
                    .addComponent(nilai_random_bobot_Awal))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        Optimize.add(jPanel5, java.awt.BorderLayout.CENTER);

        BPLM.add(Optimize);

        jPanel4.add(BPLM);

        CS.setBackground(new java.awt.Color(0, 38, 77));
        CS.setLayout(new javax.swing.BoxLayout(CS, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel15.setBackground(new java.awt.Color(0, 38, 77));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cuckoo Search");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        CS.add(jPanel15);

        sarang_panel.setBackground(new java.awt.Color(0, 38, 77));
        sarang_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sarang_panel.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(71, 107, 107));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sarang");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        sarang_panel.add(jPanel11, java.awt.BorderLayout.LINE_START);

        sarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sarang.setText("15");
        sarang.setToolTipText("Jumlah Neuron Hidden Layer");
        sarang_panel.add(sarang, java.awt.BorderLayout.CENTER);

        CS.add(sarang_panel);

        generasi_panel.setBackground(new java.awt.Color(0, 38, 77));
        generasi_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        generasi_panel.setLayout(new java.awt.BorderLayout());

        jPanel12.setBackground(new java.awt.Color(71, 107, 107));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Maksimum Generasi");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        generasi_panel.add(jPanel12, java.awt.BorderLayout.LINE_START);

        generasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        generasi.setText("50");
        generasi_panel.add(generasi, java.awt.BorderLayout.CENTER);

        CS.add(generasi_panel);

        Pa_panel.setBackground(new java.awt.Color(0, 38, 77));
        Pa_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Pa_panel.setLayout(new java.awt.BorderLayout());

        jPanel13.setBackground(new java.awt.Color(71, 107, 107));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Probability");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Pa_panel.add(jPanel13, java.awt.BorderLayout.LINE_START);

        Pa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Pa.setText("0.25");
        Pa_panel.add(Pa, java.awt.BorderLayout.CENTER);

        CS.add(Pa_panel);

        jPanel4.add(CS);

        jScrollPane1.setViewportView(jPanel4);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3);

        jPanel19.setLayout(new java.awt.BorderLayout());

        doPelatihan.setBackground(new java.awt.Color(0, 0, 102));
        doPelatihan.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        doPelatihan.setForeground(new java.awt.Color(255, 255, 255));
        doPelatihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/training.png"))); // NOI18N
        doPelatihan.setText("Latih Jaringan");
        doPelatihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        doPelatihan.setFocusable(false);
        doPelatihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doPelatihanActionPerformed(evt);
            }
        });
        jPanel19.add(doPelatihan, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel19);

        tittle_configuration.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tittle_configuration.setForeground(new java.awt.Color(255, 255, 255));
        tittle_configuration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/setting.png"))); // NOI18N
        tittle_configuration.setText("Konfigurasi Algoritma");

        javax.swing.GroupLayout konfigurasiLayout = new javax.swing.GroupLayout(konfigurasi);
        konfigurasi.setLayout(konfigurasiLayout);
        konfigurasiLayout.setHorizontalGroup(
            konfigurasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(konfigurasiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(konfigurasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tittle_configuration)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        konfigurasiLayout.setVerticalGroup(
            konfigurasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(konfigurasiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tittle_configuration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelLatih1.add(konfigurasi, java.awt.BorderLayout.LINE_START);

        pelatihan.setBackground(new java.awt.Color(0, 102, 102));
        pelatihan.setLayout(new javax.swing.BoxLayout(pelatihan, javax.swing.BoxLayout.PAGE_AXIS));

        LatihPanel.setLayout(new java.awt.BorderLayout());

        pelatihan_part_1.setLayout(new javax.swing.BoxLayout(pelatihan_part_1, javax.swing.BoxLayout.LINE_AXIS));

        hasil.setLayout(new javax.swing.BoxLayout(hasil, javax.swing.BoxLayout.PAGE_AXIS));

        box_training.setBackground(new java.awt.Color(0, 102, 102));
        box_training.setLayout(new javax.swing.BoxLayout(box_training, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel18.setBackground(new java.awt.Color(0, 102, 102));
        jPanel18.setPreferredSize(new java.awt.Dimension(609, 50));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/draft.png"))); // NOI18N
        jLabel2.setText("Daftar Hasil Pelatihan");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(360, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        box_training.add(jPanel18);

        hasil_training_dalem_box.setBackground(new java.awt.Color(0, 102, 102));

        tabel_pelatihan.setBackground(new java.awt.Color(0, 153, 153));
        tabel_pelatihan.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tabel_pelatihan.setForeground(new java.awt.Color(255, 255, 255));
        tabel_pelatihan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "2", "3", null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "MSE", "Tipe", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_pelatihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabel_pelatihan.setGridColor(new java.awt.Color(0, 0, 0));
        tabel_pelatihan.setRowHeight(30);
        tabel_pelatihan.setRowMargin(3);
        tabel_pelatihan.setSelectionBackground(new java.awt.Color(255, 255, 102));
        tabel_pelatihan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabel_pelatihan.getTableHeader().setReorderingAllowed(false);
        tabel_pelatihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pelatihanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_pelatihan);

        javax.swing.GroupLayout hasil_training_dalem_boxLayout = new javax.swing.GroupLayout(hasil_training_dalem_box);
        hasil_training_dalem_box.setLayout(hasil_training_dalem_boxLayout);
        hasil_training_dalem_boxLayout.setHorizontalGroup(
            hasil_training_dalem_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );
        hasil_training_dalem_boxLayout.setVerticalGroup(
            hasil_training_dalem_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hasil_training_dalem_boxLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        box_training.add(hasil_training_dalem_box);

        hasil.add(box_training);

        control1.setBackground(new java.awt.Color(0, 102, 102));
        control1.setPreferredSize(new java.awt.Dimension(328, 43));
        control1.setLayout(new java.awt.GridLayout(1, 0));

        simpanPelatihan.setBackground(new java.awt.Color(102, 0, 102));
        simpanPelatihan.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        simpanPelatihan.setForeground(new java.awt.Color(255, 255, 255));
        simpanPelatihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        simpanPelatihan.setText("Simpan Pelatihan");
        simpanPelatihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanPelatihan.setFocusPainted(false);
        simpanPelatihan.setMaximumSize(new java.awt.Dimension(169, 38));
        simpanPelatihan.setMinimumSize(new java.awt.Dimension(164, 38));
        simpanPelatihan.setPreferredSize(new java.awt.Dimension(164, 38));
        simpanPelatihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanPelatihanActionPerformed(evt);
            }
        });
        control1.add(simpanPelatihan);

        hapus_pelatihan.setBackground(new java.awt.Color(102, 0, 102));
        hapus_pelatihan.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        hapus_pelatihan.setForeground(new java.awt.Color(255, 255, 255));
        hapus_pelatihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        hapus_pelatihan.setText("hapus pelatihan");
        hapus_pelatihan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapus_pelatihan.setFocusPainted(false);
        hapus_pelatihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus_pelatihanActionPerformed(evt);
            }
        });
        control1.add(hapus_pelatihan);

        hasil.add(control1);

        pelatihan_part_1.add(hasil);

        keterangan.setBackground(new java.awt.Color(51, 153, 102));
        keterangan.setMaximumSize(new java.awt.Dimension(500, 32767));
        keterangan.setPreferredSize(new java.awt.Dimension(300, 557));

        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel17.setBackground(new java.awt.Color(51, 153, 102));
        jPanel17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Id Percobaan");

        label_id_pelatihan.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        label_id_pelatihan.setForeground(new java.awt.Color(255, 255, 255));
        label_id_pelatihan.setText("___");

        label_tipe_algoritma.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        label_tipe_algoritma.setForeground(new java.awt.Color(255, 255, 255));
        label_tipe_algoritma.setText("_____");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Jumlah Hidden Neuron");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Learning Rate");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Epoch");

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Minimum Error");

        sarang_label.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        sarang_label.setForeground(new java.awt.Color(255, 255, 255));
        sarang_label.setText("Sarang");

        generasi_label.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        generasi_label.setForeground(new java.awt.Color(255, 255, 255));
        generasi_label.setText("Maksimum Generasi");

        probability_label.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        probability_label.setForeground(new java.awt.Color(255, 255, 255));
        probability_label.setText("Probability Rate");

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Waktu Eksekusi ");

        label_akurasi_validasi.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_akurasi_validasi.setForeground(new java.awt.Color(255, 255, 255));
        label_akurasi_validasi.setText("Akurasi Validasi");

        label_epoch.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_epoch.setForeground(new java.awt.Color(255, 255, 255));
        label_epoch.setText("__");

        label_hidden_neuron.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_hidden_neuron.setForeground(new java.awt.Color(255, 255, 255));
        label_hidden_neuron.setText("__");

        label_learning_rate.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_learning_rate.setForeground(new java.awt.Color(255, 255, 255));
        label_learning_rate.setText("__");

        label_error.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_error.setForeground(new java.awt.Color(255, 255, 255));
        label_error.setText("__");

        label_sarang.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_sarang.setForeground(new java.awt.Color(255, 255, 255));
        label_sarang.setText("__");

        label_generasi.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_generasi.setForeground(new java.awt.Color(255, 255, 255));
        label_generasi.setText("__");

        label_pa.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_pa.setForeground(new java.awt.Color(255, 255, 255));
        label_pa.setText("__");

        waktu_eksekusi.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        waktu_eksekusi.setForeground(new java.awt.Color(255, 255, 255));
        waktu_eksekusi.setText("__");

        label_mse.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_mse.setForeground(new java.awt.Color(255, 255, 255));
        label_mse.setText("__");

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Jumlah Input Neuron");

        label_input_neuron.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        label_input_neuron.setForeground(new java.awt.Color(255, 255, 255));
        label_input_neuron.setText("__");

        jLabel28.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("MSE");

        akurasi_validasi.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        akurasi_validasi.setForeground(new java.awt.Color(255, 255, 255));
        akurasi_validasi.setText("__");
        akurasi_validasi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_tipe_algoritma)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label_id_pelatihan))
                            .addComponent(label_mse)
                            .addComponent(jLabel28))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(label_akurasi_validasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(akurasi_validasi))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_input_neuron))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_hidden_neuron))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_learning_rate))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(waktu_eksekusi))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(probability_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_pa))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(generasi_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_generasi))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(sarang_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_sarang))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_error))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label_epoch)))
                        .addGap(44, 44, 44))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addComponent(label_id_pelatihan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_tipe_algoritma)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(label_input_neuron))
                .addGap(14, 14, 14)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(label_hidden_neuron))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(label_learning_rate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(label_epoch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(label_error))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sarang_label)
                    .addComponent(label_sarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generasi_label)
                    .addComponent(label_generasi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(probability_label)
                    .addComponent(label_pa))
                .addGap(29, 29, 29)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(waktu_eksekusi))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_akurasi_validasi)
                    .addComponent(akurasi_validasi))
                .addGap(39, 39, 39)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_mse)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel17);

        javax.swing.GroupLayout keteranganLayout = new javax.swing.GroupLayout(keterangan);
        keterangan.setLayout(keteranganLayout);
        keteranganLayout.setHorizontalGroup(
            keteranganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keteranganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        keteranganLayout.setVerticalGroup(
            keteranganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keteranganLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pelatihan_part_1.add(keterangan);

        LatihPanel.add(pelatihan_part_1, java.awt.BorderLayout.CENTER);

        pelatihan.add(LatihPanel);

        PanelLatih1.add(pelatihan, java.awt.BorderLayout.CENTER);

        add(PanelLatih1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void optimasi_dengan_csoptimasiBobot(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optimasi_dengan_csoptimasiBobot
        // TODO add your handling code here:
        if(this.optimasi_dengan_cs.isSelected()){
            this.optimasiBobot(true);
        }
        else{
            this.optimasiBobot(false);
        }
    }//GEN-LAST:event_optimasi_dengan_csoptimasiBobot

    private void nilai_random_bobot_AwaloptimasiBobot(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nilai_random_bobot_AwaloptimasiBobot
        // TODO add your handling code here:
        if(this.optimasi_dengan_cs.isSelected()){
            this.optimasiBobot(true);
        }
        else{
            this.optimasiBobot(false);
        }
    }//GEN-LAST:event_nilai_random_bobot_AwaloptimasiBobot

    private boolean setInisialisasiAlgoritma(){
       HashMap<String, String> konfigurasi = new HashMap<String, String>();
       konfigurasi.put("Neuron_hidden_layer", this.neuron_hidden_layer.getText());
       konfigurasi.put("Learning_rate", this.learning_rate.getText());
       konfigurasi.put("Epoch", this.epoch.getText());
       konfigurasi.put("Error",this.error.getText());
       if(this.optimasi_dengan_cs.isSelected()){
          konfigurasi.put("Tipe_algoritma","CSLM");
          konfigurasi.put("Sarang",this.sarang.getText());
          konfigurasi.put("Generasi",this.generasi.getText());
          konfigurasi.put("Pa",this.Pa.getText());
       }
       else{ konfigurasi.put("Tipe_algoritma","LM");}
       return this.controller_pelatihan.inisialisasiKonfigurasi(konfigurasi);
    }
     
    private void doPelatihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doPelatihanActionPerformed
          if(this.setInisialisasiAlgoritma()){
            try {
                int id = 0;
                Pelatihan pelatihan = this.controller_pelatihan.mulaiPelatihan();
                if(pelatihan != null){
                        if(this.hasil_pelatihan.getHasil_pelatihan().size() == 0){
                            id = 1;
                        }else{
                            id = this.hasil_pelatihan.getHasil_pelatihan().get(0).getId_pelatihan()+1;
                        }    
                        pelatihan.setId_pelatihan(id);
                        this.hasil_pelatihan.tambah(pelatihan);
                        this.setDataPelatihan(hasil_pelatihan);
                        this.setBarisTerpililh(0);   
                    }
            } catch (SQLException ex) {
                Logger.getLogger(PanelKelolaPelatihan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           JOptionPane.showMessageDialog(null,"Parameter yang dimasukan salah","Oops!!",JOptionPane.ERROR_MESSAGE);   
        }   
    }//GEN-LAST:event_doPelatihanActionPerformed

    private void tabel_pelatihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pelatihanMouseClicked
        // TODO add your handling code here:
        this.setBarisTerpililh(this.tabel_pelatihan.getSelectedRow());
    }//GEN-LAST:event_tabel_pelatihanMouseClicked

    private void hapus_pelatihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus_pelatihanActionPerformed
        int id = 0;
        for(int i=0;i<this.tabel_pelatihan.getRowCount();i++){
            if(this.tabel_pelatihan.getValueAt(i,3)!=null && !this.tabel_pelatihan.getValueAt(i,3).toString().equalsIgnoreCase("false")){
              id = (int) this.tabel_pelatihan.getValueAt(i,0);
              this.controller_pelatihan.hapusDataPelatihan(id);
              this.hasil_pelatihan.hapusPelatihan(id);
            }
         }
        this.setDataPelatihan(hasil_pelatihan);
    }//GEN-LAST:event_hapus_pelatihanActionPerformed

    private void simpanPelatihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanPelatihanActionPerformed
        int id = 0;
        Pelatihan _tersimpan;
        for(int i=0;i<this.tabel_pelatihan.getRowCount();i++){
            if(this.tabel_pelatihan.getValueAt(i,3)!=null && !this.tabel_pelatihan.getValueAt(i,3).toString().equalsIgnoreCase("false")){
                id = (int) this.tabel_pelatihan.getValueAt(i,0);
                _tersimpan = this.hasil_pelatihan.getPelatihanTerpilih(id);
                try {
                    _tersimpan = this.controller_pelatihan.simpanHasilPelatihan(_tersimpan);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelKelolaPelatihan.class.getName()).log(Level.SEVERE, null, ex);
                }

                for(Pelatihan pelatihan : this.hasil_pelatihan.getHasil_pelatihan()){
                    if(pelatihan.getId_pelatihan() == _tersimpan.getId_pelatihan()){
                        this.hasil_pelatihan.ubahPelatihan(pelatihan.getId_pelatihan(),_tersimpan);
                    }
                }
            }
        }
        this.setDataPelatihan(hasil_pelatihan);
    }//GEN-LAST:event_simpanPelatihanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BPLM;
    private javax.swing.JPanel CS;
    private javax.swing.JPanel LR;
    private javax.swing.JPanel LatihPanel;
    private javax.swing.JPanel NH;
    private javax.swing.JPanel Optimize;
    private javax.swing.JTextField Pa;
    private javax.swing.JPanel Pa_panel;
    private javax.swing.JPanel PanelLatih1;
    private javax.swing.JLabel akurasi_validasi;
    private javax.swing.JPanel box_training;
    private javax.swing.JPanel control1;
    private javax.swing.JButton doPelatihan;
    private javax.swing.JTextField epoch;
    private javax.swing.JPanel epoch_panel;
    private javax.swing.JTextField error;
    private javax.swing.JPanel error_panel;
    private javax.swing.JTextField generasi;
    private javax.swing.JLabel generasi_label;
    private javax.swing.JPanel generasi_panel;
    private javax.swing.JButton hapus_pelatihan;
    private javax.swing.JPanel hasil;
    private javax.swing.JPanel hasil_training_dalem_box;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel keterangan;
    private javax.swing.JPanel konfigurasi;
    private javax.swing.JLabel label_akurasi_validasi;
    private javax.swing.JLabel label_epoch;
    private javax.swing.JLabel label_error;
    private javax.swing.JLabel label_generasi;
    private javax.swing.JLabel label_hidden_neuron;
    private javax.swing.JLabel label_id_pelatihan;
    private javax.swing.JLabel label_input_neuron;
    private javax.swing.JLabel label_learning_rate;
    private javax.swing.JLabel label_mse;
    private javax.swing.JLabel label_pa;
    private javax.swing.JLabel label_sarang;
    private javax.swing.JLabel label_tipe_algoritma;
    private javax.swing.JTextField learning_rate;
    private javax.swing.JTextField neuron_hidden_layer;
    private javax.swing.ButtonGroup opsi_optimasi;
    private javax.swing.JRadioButton optimasi_dengan_cs;
    private javax.swing.JPanel pelatihan;
    private javax.swing.JPanel pelatihan_part_1;
    private javax.swing.JLabel probability_label;
    private javax.swing.JTextField sarang;
    private javax.swing.JLabel sarang_label;
    private javax.swing.JPanel sarang_panel;
    private javax.swing.JButton simpanPelatihan;
    private javax.swing.JTable tabel_pelatihan;
    private javax.swing.JLabel tittle_configuration;
    private javax.swing.JLabel waktu_eksekusi;
    // End of variables declaration//GEN-END:variables
}

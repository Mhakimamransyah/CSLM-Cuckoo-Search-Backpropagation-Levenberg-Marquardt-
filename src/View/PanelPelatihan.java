/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.Data;
import Controller.DataController;
import Model.UnsurCuaca;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author M.Hakim Amransyah
 */
public class PanelPelatihan extends javax.swing.JFrame {
    
    private Data data_training;
    private Data data_validation;

    public PanelPelatihan(PanelKelolaPelatihan kelola_pelatihan) { 
        initComponents();
        this.PanelLatih.add(kelola_pelatihan);
    }

    private PanelPelatihan() {
     
    }
    
    public Data getData_training() {
        return this.data_training;
    }

    public Data getData_validation() {
        return this.data_validation;
    }
      
      
     public void setDataCuaca(Data data,String jenis_data){
      DefaultTableModel model_tabel_cuaca = null;
      switch(jenis_data){
          case "Data Latih" : 
              model_tabel_cuaca = (DefaultTableModel) this.Tabel_data_latih.getModel();
              break;
          case "Data Validasi": 
              model_tabel_cuaca = (DefaultTableModel) this.Tabel_data_validasi.getModel();
              break;
      }
      model_tabel_cuaca.setRowCount(0);
      model_tabel_cuaca.setColumnCount(data.getJumlahAtributData());
      for(UnsurCuaca atribut : data.getUnsurCuaca()){
          Object object[] = new Object[data.getJumlahAtributData()];
          object[0] =atribut.getTanggal();
          for(int i=1;i<object.length-1;i++){
              object[i] =atribut.getAtribut_value().get(i-1);
          }
          object[object.length-1] =atribut.getKeadaan_cuaca();
          model_tabel_cuaca.addRow(object);
      }
      this.setPieChart(data.getSebaran_label(),data.getJenis_data());
      this.headeringTabel(data.getHeader(),data.getJenis_data());
      this.centeringDataTable(data.getJenis_data());
    }
     
      private void centeringDataTable(String jenis_data){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        if(jenis_data != null&&jenis_data.equalsIgnoreCase("Data Latih")){
          for(int i=0;i<this.Tabel_data_latih.getColumnCount();i++){
             this.Tabel_data_latih.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
             this.Tabel_data_latih.getColumnModel().getColumn(i).setMinWidth(130);
          }    
        }
        else if(jenis_data != null&&jenis_data.equalsIgnoreCase("Data Validasi")){
          for(int i=0;i<this.Tabel_data_validasi.getColumnCount();i++){
            this.Tabel_data_validasi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            this.Tabel_data_validasi.getColumnModel().getColumn(i).setMinWidth(130);
          }    
        }
    }
    
    private void headeringTabel(String header[],String jenis_data){
        if(jenis_data != null&&jenis_data.equalsIgnoreCase("Data Latih")){
           if(header != null && header.length != 0){
               for(int i=0;i<header.length;i++){
                  this.Tabel_data_latih.getColumnModel().getColumn(i).setHeaderValue(header[i]);
               }   
           } 
        }
        else if(jenis_data != null&&jenis_data.equalsIgnoreCase("Data Validasi")){
           if(header != null && header.length != 0){
               for(int i=0;i<header.length;i++){
                  this.Tabel_data_validasi.getColumnModel().getColumn(i).setHeaderValue(header[i]);
               }   
           }  
        }
    }
     
     private void setPieChart(HashMap<String, Integer> kategori,String jenis){
      DefaultPieDataset dataset  = new DefaultPieDataset();
      dataset.setValue("Sangat Ringan",new Integer(kategori.get("Sangat_Ringan")));
      dataset.setValue("Ringan",new Integer(kategori.get("Ringan")));
      dataset.setValue("Sedang",new Integer(kategori.get("Sedang")));
      dataset.setValue("Lebat",new Integer(kategori.get("Lebat")));
      dataset.setValue("Sangat Lebat",new Integer(kategori.get("Sangat_Lebat")));
      JFreeChart chart = ChartFactory.createPieChart(jenis, dataset,true,true,false);
      PiePlot p = (PiePlot) chart.getPlot();
      ChartPanel CP = new ChartPanel(chart);
      this.chart_panel.removeAll();
      this.chart_panel.add(CP);
      this.chart_panel.validate();
    }
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optimize_option = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelData = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        piechart = new javax.swing.JPanel();
        chart_panel = new javax.swing.JPanel();
        propertidata = new javax.swing.JPanel();
        label_jumlah_data_training = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jumlah_data_training = new javax.swing.JLabel();
        label_jumlah_data_validasi = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jumlah_data_validation = new javax.swing.JLabel();
        tombol = new javax.swing.JPanel();
        lookTraining = new javax.swing.JButton();
        lookValidation = new javax.swing.JButton();
        Data = new javax.swing.JPanel();
        Data_cuaca = new javax.swing.JPanel();
        Data_training = new javax.swing.JScrollPane();
        Tabel_data_latih = new javax.swing.JTable();
        Data_validation = new javax.swing.JScrollPane();
        Tabel_data_validasi = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        lihatDataLatih = new javax.swing.JButton();
        lihatDataValidation = new javax.swing.JButton();
        PanelLatih = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pelatihan");
        setBackground(new java.awt.Color(51, 153, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTabbedPane1.setBackground(new java.awt.Color(51, 153, 102));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(811, 660));
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.setVerifyInputWhenFocusTarget(false);

        PanelData.setLayout(new java.awt.BorderLayout());

        jPanel20.setBackground(new java.awt.Color(0, 102, 102));
        jPanel20.setToolTipText("");
        jPanel20.setLayout(new java.awt.BorderLayout());

        piechart.setMaximumSize(new java.awt.Dimension(100, 222));
        piechart.setPreferredSize(new java.awt.Dimension(400, 35));
        piechart.setLayout(new javax.swing.BoxLayout(piechart, javax.swing.BoxLayout.PAGE_AXIS));

        chart_panel.setBackground(new java.awt.Color(0, 102, 102));
        chart_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        chart_panel.setMaximumSize(new java.awt.Dimension(400, 600));
        chart_panel.setMinimumSize(new java.awt.Dimension(4, 400));
        chart_panel.setPreferredSize(new java.awt.Dimension(400, 600));
        chart_panel.setLayout(new java.awt.BorderLayout());
        piechart.add(chart_panel);

        propertidata.setMaximumSize(new java.awt.Dimension(400, 300));
        propertidata.setMinimumSize(new java.awt.Dimension(200, 40));
        propertidata.setPreferredSize(new java.awt.Dimension(209, 380));
        propertidata.setLayout(new javax.swing.BoxLayout(propertidata, javax.swing.BoxLayout.PAGE_AXIS));

        label_jumlah_data_training.setBackground(new java.awt.Color(0, 153, 153));
        label_jumlah_data_training.setMinimumSize(new java.awt.Dimension(200, 35));
        label_jumlah_data_training.setPreferredSize(new java.awt.Dimension(600, 254));
        label_jumlah_data_training.setLayout(new javax.swing.BoxLayout(label_jumlah_data_training, javax.swing.BoxLayout.LINE_AXIS));

        jPanel21.setBackground(new java.awt.Color(51, 153, 102));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel21.setMaximumSize(new java.awt.Dimension(600, 70));
        jPanel21.setMinimumSize(new java.awt.Dimension(65, 70));
        jPanel21.setPreferredSize(new java.awt.Dimension(65, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/data (2).png"))); // NOI18N
        jLabel1.setText("Data training");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        label_jumlah_data_training.add(jPanel21);

        jPanel22.setBackground(new java.awt.Color(0, 102, 102));
        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel22.setMaximumSize(new java.awt.Dimension(200, 70));
        jPanel22.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel22.setPreferredSize(new java.awt.Dimension(200, 50));

        jumlah_data_training.setBackground(new java.awt.Color(255, 255, 255));
        jumlah_data_training.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jumlah_data_training.setForeground(new java.awt.Color(255, 255, 255));
        jumlah_data_training.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jumlah_data_training)
                .addGap(93, 93, 93))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jumlah_data_training)
                .addGap(44, 44, 44))
        );

        label_jumlah_data_training.add(jPanel22);

        propertidata.add(label_jumlah_data_training);

        label_jumlah_data_validasi.setBackground(new java.awt.Color(0, 153, 153));
        label_jumlah_data_validasi.setMinimumSize(new java.awt.Dimension(200, 30));
        label_jumlah_data_validasi.setPreferredSize(new java.awt.Dimension(600, 254));
        label_jumlah_data_validasi.setLayout(new javax.swing.BoxLayout(label_jumlah_data_validasi, javax.swing.BoxLayout.LINE_AXIS));

        jPanel23.setBackground(new java.awt.Color(51, 153, 102));
        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel23.setMaximumSize(new java.awt.Dimension(600, 70));
        jPanel23.setMinimumSize(new java.awt.Dimension(0, 70));
        jPanel23.setPreferredSize(new java.awt.Dimension(200, 50));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/data (2).png"))); // NOI18N
        jLabel17.setText("Data Validation");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        label_jumlah_data_validasi.add(jPanel23);

        jPanel24.setBackground(new java.awt.Color(0, 102, 102));
        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel24.setMaximumSize(new java.awt.Dimension(200, 70));
        jPanel24.setMinimumSize(new java.awt.Dimension(0, 50));
        jPanel24.setPreferredSize(new java.awt.Dimension(200, 50));

        jumlah_data_validation.setBackground(new java.awt.Color(255, 255, 255));
        jumlah_data_validation.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jumlah_data_validation.setForeground(new java.awt.Color(255, 255, 255));
        jumlah_data_validation.setText("0");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jumlah_data_validation)
                .addGap(92, 92, 92))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jumlah_data_validation)
                .addGap(44, 44, 44))
        );

        label_jumlah_data_validasi.add(jPanel24);

        propertidata.add(label_jumlah_data_validasi);

        piechart.add(propertidata);

        tombol.setBackground(new java.awt.Color(0, 102, 102));
        tombol.setMaximumSize(new java.awt.Dimension(400, 80));
        tombol.setMinimumSize(new java.awt.Dimension(209, 80));
        tombol.setPreferredSize(new java.awt.Dimension(209, 80));
        tombol.setLayout(new java.awt.GridLayout(1, 0));

        lookTraining.setBackground(new java.awt.Color(102, 0, 102));
        lookTraining.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lookTraining.setForeground(new java.awt.Color(255, 255, 255));
        lookTraining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/j_data.png"))); // NOI18N
        lookTraining.setText("Telusuri Data Latih");
        lookTraining.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lookTraining.setFocusable(false);
        lookTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookTrainingActionPerformed(evt);
            }
        });
        tombol.add(lookTraining);

        lookValidation.setBackground(new java.awt.Color(102, 0, 102));
        lookValidation.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lookValidation.setForeground(new java.awt.Color(255, 255, 255));
        lookValidation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/j_data.png"))); // NOI18N
        lookValidation.setText("Telusuri Data Validation");
        lookValidation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lookValidation.setFocusable(false);
        lookValidation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookValidationActionPerformed(evt);
            }
        });
        tombol.add(lookValidation);

        piechart.add(tombol);

        jPanel20.add(piechart, java.awt.BorderLayout.LINE_END);

        Data.setBackground(new java.awt.Color(0, 102, 102));

        Data_cuaca.setLayout(new java.awt.CardLayout());

        Data_training.setBackground(new java.awt.Color(51, 153, 102));

        Tabel_data_latih.setBackground(new java.awt.Color(51, 153, 102));
        Tabel_data_latih.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Tabel_data_latih.setForeground(new java.awt.Color(255, 255, 255));
        Tabel_data_latih.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabel_data_latih.setToolTipText("");
        Tabel_data_latih.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        Tabel_data_latih.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tabel_data_latih.setFillsViewportHeight(true);
        Tabel_data_latih.setGridColor(new java.awt.Color(255, 255, 255));
        Tabel_data_latih.setName(""); // NOI18N
        Tabel_data_latih.setRowHeight(30);
        Tabel_data_latih.setRowMargin(3);
        Tabel_data_latih.setSelectionBackground(new java.awt.Color(255, 255, 153));
        Tabel_data_latih.getTableHeader().setResizingAllowed(false);
        Tabel_data_latih.getTableHeader().setReorderingAllowed(false);
        Data_training.setViewportView(Tabel_data_latih);

        Data_cuaca.add(Data_training, "card2");

        Data_validation.setBackground(new java.awt.Color(51, 153, 102));

        Tabel_data_validasi.setBackground(new java.awt.Color(51, 153, 102));
        Tabel_data_validasi.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Tabel_data_validasi.setForeground(new java.awt.Color(255, 255, 255));
        Tabel_data_validasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabel_data_validasi.setToolTipText("");
        Tabel_data_validasi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        Tabel_data_validasi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tabel_data_validasi.setFillsViewportHeight(true);
        Tabel_data_validasi.setGridColor(new java.awt.Color(255, 255, 255));
        Tabel_data_validasi.setName(""); // NOI18N
        Tabel_data_validasi.setRowHeight(30);
        Tabel_data_validasi.setRowMargin(3);
        Tabel_data_validasi.setSelectionBackground(new java.awt.Color(255, 255, 153));
        Tabel_data_validasi.getTableHeader().setResizingAllowed(false);
        Tabel_data_validasi.getTableHeader().setReorderingAllowed(false);
        Data_validation.setViewportView(Tabel_data_validasi);

        Data_cuaca.add(Data_validation, "card2");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Data Unsur Cuaca");

        lihatDataLatih.setBackground(new java.awt.Color(51, 153, 102));
        lihatDataLatih.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lihatDataLatih.setForeground(new java.awt.Color(255, 255, 255));
        lihatDataLatih.setText("Data Latih");
        lihatDataLatih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lihatDataLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatDataLatihActionPerformed(evt);
            }
        });

        lihatDataValidation.setBackground(new java.awt.Color(51, 153, 102));
        lihatDataValidation.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        lihatDataValidation.setForeground(new java.awt.Color(255, 255, 255));
        lihatDataValidation.setText("Data Validation");
        lihatDataValidation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lihatDataValidation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatDataValidationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DataLayout = new javax.swing.GroupLayout(Data);
        Data.setLayout(DataLayout);
        DataLayout.setHorizontalGroup(
            DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DataLayout.createSequentialGroup()
                        .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Data_cuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DataLayout.createSequentialGroup()
                                .addComponent(lihatDataLatih, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lihatDataValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DataLayout.setVerticalGroup(
            DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DataLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Data_cuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lihatDataValidation, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(lihatDataLatih, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        jPanel20.add(Data, java.awt.BorderLayout.CENTER);

        PanelData.add(jPanel20, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Panel Data ", PanelData);

        PanelLatih.setBackground(new java.awt.Color(51, 153, 102));
        PanelLatih.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Panel Pelatihan dan Validasi", PanelLatih);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1326, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void lookTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookTrainingActionPerformed
        // TODO add your handling code here:
        this.data_training = this.telusuriData("Data Latih",this.Data_training);
        this.jumlah_data_training.setText(this.data_training.getJumlahData()+"");
        this.setDataCuaca(data_training,"Data Latih");
    }//GEN-LAST:event_lookTrainingActionPerformed


    private void lookValidationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookValidationActionPerformed
        // TODO add your handling code here:
        this.data_validation = this.telusuriData("Data Validasi",this.Data_validation);
        this.jumlah_data_validation.setText(this.data_validation.getJumlahData()+"");
        this.setDataCuaca(data_validation,"Data Validasi");
    }//GEN-LAST:event_lookValidationActionPerformed

    private Data telusuriData(String jenis_data,JScrollPane panel){
        Data data = null;
        DataController controller_data = new DataController();
        try {
            data = controller_data.pilihData(Data, jenis_data);
        } catch (IOException ex) {
            Logger.getLogger(PanelPelatihan.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setActiveCard(panel, data);
        return data;
    }
    
    private void setActiveCard(JScrollPane panel,Data data){
        if(data != null && data.getJumlahData() > 0){
            this.Data_cuaca.removeAll();
            this.Data_cuaca.add(panel);
            this.Data_cuaca.repaint();
            this.Data_cuaca.revalidate();
            this.setPieChart(data.getSebaran_label(),data.getJenis_data());   
        }else{
            JOptionPane.showMessageDialog(null,"Data Tidak Ditemukan","Oops",JOptionPane.ERROR_MESSAGE);   
        }
    }
    
    private void lihatDataLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatDataLatihActionPerformed
       this.setActiveCard(this.Data_training, data_training);
    }//GEN-LAST:event_lihatDataLatihActionPerformed

    private void lihatDataValidationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatDataValidationActionPerformed
       this.setActiveCard(this.Data_validation, data_validation);    
    }//GEN-LAST:event_lihatDataValidationActionPerformed

    
   
   
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
            java.util.logging.Logger.getLogger(PanelPelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelPelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelPelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPelatihan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelPelatihan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Data;
    private javax.swing.JPanel Data_cuaca;
    private javax.swing.JScrollPane Data_training;
    private javax.swing.JScrollPane Data_validation;
    private javax.swing.JPanel PanelData;
    private javax.swing.JPanel PanelLatih;
    private javax.swing.JTable Tabel_data_latih;
    private javax.swing.JTable Tabel_data_validasi;
    private javax.swing.JPanel chart_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jumlah_data_training;
    private javax.swing.JLabel jumlah_data_validation;
    private javax.swing.JPanel label_jumlah_data_training;
    private javax.swing.JPanel label_jumlah_data_validasi;
    private javax.swing.JButton lihatDataLatih;
    private javax.swing.JButton lihatDataValidation;
    private javax.swing.JButton lookTraining;
    private javax.swing.JButton lookValidation;
    private javax.swing.ButtonGroup optimize_option;
    private javax.swing.JPanel piechart;
    private javax.swing.JPanel propertidata;
    private javax.swing.JPanel tombol;
    // End of variables declaration//GEN-END:variables
}

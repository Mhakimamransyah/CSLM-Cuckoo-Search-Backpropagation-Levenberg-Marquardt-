/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Controller.NeuralNetwork.DaoPelatihan;
import Controller.NeuralNetwork.NNLearner;
import Model.Konfigurasi;
import Model.Training.NeuralNetwork.Pelatihan;
import View.PanelKelolaPelatihan;
import View.PanelPelatihan;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author M.Hakim Amransyah
 */
public class LatihController 
{ 
    private Konfigurasi konfigurasi_algoritma;
    private DaoPelatihan dao_pelatihan;
    private PanelKelolaPelatihan panel_kelola_pelatihan;
    private PanelPelatihan panel_pelatihan;
    
    public LatihController(){ 
        this.dao_pelatihan = new DaoPelatihan();    
        this.panel_kelola_pelatihan = new PanelKelolaPelatihan(this);
    }
    
    public void mulaiPanelPelatihan(){
        this.panel_pelatihan = new PanelPelatihan(this.panel_kelola_pelatihan);
        this.panel_pelatihan.setVisible(true);
    }
    
    public PanelKelolaPelatihan getPanel_kelola_pelatihan() {
        return panel_kelola_pelatihan.disableKonfigurasidanButton();
    }
      
    public void hapusDataPelatihan(int id_pelatihan){           
        try {      
            this.dao_pelatihan.hapusDataHasilPelatihan(id_pelatihan);
        } catch (SQLException ex) {
            Logger.getLogger(LatihController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Pelatihan simpanHasilPelatihan(Pelatihan pelatihan) throws SQLException{
       String tipe[] = pelatihan.getKonfigurasi().getTipe_algoritma().split(" ");
       pelatihan.getKonfigurasi().setTipe_algoritma(tipe[1]);
       this.dao_pelatihan.insertHasilPelatihan(pelatihan);
       return pelatihan;
    }
    
    public Pelatihan mulaiPelatihan() throws SQLException{
        int pengujian;
        Pelatihan hasil_pelatihan = null;
        Data data_latih = this.panel_pelatihan.getData_training();
        Data data_validasi = this.panel_pelatihan.getData_validation();
        if(data_latih != null && data_latih.getJumlahData() != 0 && data_validasi != null && data_validasi.getJumlahData() != 0){
           NNLearner learn = new NNLearner(this.konfigurasi_algoritma);
           hasil_pelatihan = learn.latihJaringan(data_latih.getHasilNormalisasiData(),data_validasi.getHasilNormalisasiData());    
           hasil_pelatihan.getKonfigurasi().setTipe_algoritma("unsaved "+hasil_pelatihan.getKonfigurasi().getTipe_algoritma());
           NNTesting validasi = new NNTesting(this.konfigurasi_algoritma.getNeuron_input_layer(),this.konfigurasi_algoritma.getNeuron_hidden_layer(),hasil_pelatihan.getBobot_akhir());
           hasil_pelatihan.setAkurasi_validasi(validasi.prediksiCuaca(data_validasi).getAkurasi());
        }else{
           JOptionPane.showMessageDialog(null,"Data cuaca yang digunakan belum lengkap","Oops",JOptionPane.WARNING_MESSAGE);   
        }
        return hasil_pelatihan;
    }
    
    
   
    public ListPelatihan bacaDataPelatihan() throws SQLException{
        return this.dao_pelatihan.bacaDataHasilPelatihan();
    }
     
    public boolean inisialisasiKonfigurasi(HashMap<String, String> konfigurasi){
        boolean isValid = true;
        this.konfigurasi_algoritma = new Konfigurasi();
        try{
            this.konfigurasi_algoritma.setNeuron_hidden_layer(Integer.parseInt(konfigurasi.get("Neuron_hidden_layer")));
            this.konfigurasi_algoritma.setLearning_rate(Double.parseDouble(konfigurasi.get("Learning_rate")));
            this.konfigurasi_algoritma.setEpoch(Integer.parseInt(konfigurasi.get("Epoch")));
            this.konfigurasi_algoritma.setError(Double.parseDouble(konfigurasi.get("Error")));  
            this.konfigurasi_algoritma.setTipe_algoritma(konfigurasi.get("Tipe_algoritma"));
            if(this.konfigurasi_algoritma.getTipe_algoritma().equalsIgnoreCase("CSLM")){
               this.konfigurasi_algoritma.setSarang(Integer.parseInt(konfigurasi.get("Sarang")));
               this.konfigurasi_algoritma.setMaksimum_generasi(Integer.parseInt(konfigurasi.get("Generasi")));
               this.konfigurasi_algoritma.setPa(Double.parseDouble(konfigurasi.get("Pa")));
            }
        }catch(Exception e){
            isValid = false;
        }
        return isValid;
    }
   
    
  
}

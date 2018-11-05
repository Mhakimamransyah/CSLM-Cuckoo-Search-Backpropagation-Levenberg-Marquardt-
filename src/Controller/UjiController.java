/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Training.NeuralNetwork.Pelatihan;
import View.PanelKelolaPelatihan;
import View.PanelPengujian;
import javax.swing.JOptionPane;

/**
 *
 * @author M.Hakim Amransyah
 */
public class UjiController {
    
    private NNTesting test;
    private PanelPengujian panel_pengujian;
    private LatihController controller_pelatihan;
    private PanelKelolaPelatihan panel_kelola_pelatihan;
    
    public UjiController(){
        this.controller_pelatihan = new LatihController();
        this.panel_kelola_pelatihan = this.controller_pelatihan.getPanel_kelola_pelatihan().disableKonfigurasidanButton();
    }
    
    public void mulaiPanelPengujian(){
        this.panel_pengujian = new PanelPengujian(this.panel_kelola_pelatihan,this);
        this.panel_pengujian.setVisible(true);
    }
    
    public Pelatihan muatHasilPelatihan(){
        return this.panel_kelola_pelatihan.ambilPelatihanTerpilih();
    }
    
    public MatriksConfusion prediksiCuaca(Data data,Pelatihan hasil_pelatihan){
        
        MatriksConfusion hasil_pengujian = null;     
        if(data == null || data.getJumlahData() == 0){
            JOptionPane.showMessageDialog(null,"Tidak ada data uji yang digunakan","Oops!!",JOptionPane.WARNING_MESSAGE);   
        }
        else if(hasil_pelatihan == null){
            JOptionPane.showMessageDialog(null,"Tidak ada pelatihan yang dimuat","Oops!!",JOptionPane.WARNING_MESSAGE);   
        }
        else if(hasil_pelatihan.getKonfigurasi().getNeuron_input_layer()-1 != data.getJumlahAtributData()-2){
            JOptionPane.showMessageDialog(null,"Perbedaan arsitektur yang digunakan","Oops!!",JOptionPane.WARNING_MESSAGE);   
        }
        else{
            this.test = new NNTesting(hasil_pelatihan.getKonfigurasi().getNeuron_input_layer(),hasil_pelatihan.getKonfigurasi().getNeuron_hidden_layer(),hasil_pelatihan.getBobot_akhir());
            hasil_pengujian = this.test.prediksiCuaca(data.getHasilNormalisasiData());
        }
       
        return hasil_pengujian;
    } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;

import Controller.CuckooSearch.CuckooSearch;
import Controller.Data;
import Model.Konfigurasi;
import Model.Training.NeuralNetwork.NeuralNetwork;
import Model.Training.NeuralNetwork.Pelatihan;
import java.sql.SQLException;

/**
 *
 * @author M.Hakim Amransyah
 */
public class NNLearner{
    
    private Konfigurasi konfigurasi;
    private Pelatihan pelatihan;
    private NeuralNetwork nn;
    
    public NNLearner(Konfigurasi konfigurasi){
        this.pelatihan = new Pelatihan();
        this.nn = new NeuralNetwork();
        this.konfigurasi = konfigurasi;
        this.pelatihan.setKonfigurasi(konfigurasi);
    }
 
    public Pelatihan latihJaringan(Data data_latih, Data validation) throws SQLException{            
        this.pelatihan.getKonfigurasi().setNeuron_input_layer(data_latih.getJumlahAtributData()-1);
        this.nn.setLearning_rate(this.konfigurasi.getLearning_rate());
        this.nn.inisialisasiJaringanSarafTiruan(data_latih.getJumlahAtributData()-1,this.pelatihan.getKonfigurasi().getNeuron_hidden_layer()+1);        
        switch(this.konfigurasi.getTipe_algoritma()){
            case "LM"  : 
                         long waktu_bplm    = System.currentTimeMillis();
                         this.learningInBackpropagationLevenbergMarquardt(data_latih);
                         this.pelatihan.setWaktu_eksekusi((double)(System.currentTimeMillis()-waktu_bplm)/1000);
                         break;
            case "CSLM": 
                         long waktu_cs    = System.currentTimeMillis();
                         this.learningInCuckooSearch(data_latih,validation);
                         this.pelatihan.setWaktu_eksekusi((double)(System.currentTimeMillis()-waktu_cs)/1000);
                         break;
        }      
        return this.pelatihan;     
    }
     
    private void learningInCuckooSearch(Data data_latih, Data validation){
         CuckooSearch cslm;
         cslm = new CuckooSearch(this.nn,this.konfigurasi,data_latih,validation);
         this.pelatihan = cslm.cuckooSearchNeuralNetworkWeight();
    }
    
    private void learningInBackpropagationLevenbergMarquardt(Data data_latih){
        BackpropagationLevenbergMarquardt bplm = new BackpropagationLevenbergMarquardt(data_latih,this.nn,this.pelatihan);      
        this.pelatihan =  bplm.trainWithBackpropagationLevenbergMarquardt();
    }
}
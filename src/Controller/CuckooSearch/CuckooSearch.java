/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.CuckooSearch;
import static cern.jet.stat.Gamma.gamma;
import Model.CuckooSearch.Sarang;
import Controller.Data;
import Controller.NeuralNetwork.BackpropagationLevenbergMarquardt;
import Model.Konfigurasi;
import Model.Training.NeuralNetwork.NeuralNetwork;
import Model.Training.NeuralNetwork.Pelatihan;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author M.Hakim Amransyah
 */
public class CuckooSearch{
    
    private int jumlah_telur;
    private NeuralNetwork nn;
    private double Pr;
    private ArrayList<Sarang> populasi;
    private int jumlah_sarang;
    private Konfigurasi konfigurasi;
    private Data data_latih;
    private Data data_validation;
    
    public CuckooSearch(NeuralNetwork nn,Konfigurasi konfigurasi, Data data_latih, Data validation){
        this.konfigurasi = konfigurasi;
        this.data_latih = data_latih;
        this.data_validation = validation;
        this.nn = nn;
        this.jumlah_telur = (this.nn.getInput_layer().getJumlah_neuron()*(this.nn.getHidden_layer().getJumlah_neuron()-1))+(5*this.nn.getHidden_layer().getJumlah_neuron());
        this.jumlah_sarang = this.konfigurasi.getSarang();
        this.populasi = new ArrayList<Sarang>(jumlah_sarang);
        this.Pr = this.konfigurasi.getPa();
    }
    
    public Pelatihan cuckooSearchNeuralNetworkWeight(){
        int generasi = 0;
        this.inisialisasiSarang();
        this.setFitnessPopulasiSarang();
        while(generasi < this.konfigurasi.getMaksimum_generasi()){                       
                Sarang sarang_optimal = this.getSarangOptimal();               
                Sarang sarang_baru    = this.getSarangLevyFlight(this.populasi.get(new Random().nextInt(this.populasi.size())),sarang_optimal);
                int index = new Random().nextInt(this.populasi.size());
                Sarang sarang_terpilih_acak = this.populasi.get(index);              
                if( sarang_baru.getFitness() >= sarang_terpilih_acak.getFitness()){
                    this.populasi.set(index, sarang_baru);   
                }
                this.urutkanPopulasiSarang();
                this.inisialisasiUlangSarangTidakOptimal();
            System.out.println("Generasi : "+generasi+" fitness: "+this.populasi.get(0).getFitness());
            generasi++;
        }
        System.out.println("");
        System.out.println("");
        this.nn = this.populasi.get(0).getSaranginNeuralNetwork(this.populasi.get(0), nn);
        return this.populasi.get(0).getBplm().getHasil_pelatihan_bplm();
    }
    
    public void cetakFitness(){
        for(Sarang sarang : this.populasi){
            System.out.println(sarang.getFitness()+" ");
        }
    }
    
     private Sarang getSarangOptimal(){
        int index_sarang_optimal = 0;
        for(int i=0;i<this.populasi.size();i++){
            if(this.populasi.get(i).getFitness() > this.populasi.get(index_sarang_optimal).getFitness()){
                index_sarang_optimal = i;
            }
        }     
        return this.populasi.get(index_sarang_optimal);
    }
     

     private void inisialisasiUlangSarangTidakOptimal(){
        double temp;
        int mulai = (int) Math.ceil(this.Pr*this.populasi.size());
        if(mulai == 0){ mulai = 1; }
        for(int i=mulai;i<this.populasi.size();i++){
          for(int j=0;j<this.populasi.get(i).getPopulasi_telur().size();j++){
              double batas_atas  = this.populasi.get(i).getBatas_atas();
              double batas_bawah = this.populasi.get(i).getBatas_bawah();
              temp = batas_bawah+(batas_atas - batas_bawah)*new Random().nextDouble();
              this.populasi.get(i).getPopulasi_telur().set(j, temp);
          }
          this.populasi.get(i).evaluasi(this.data_validation);
        }
    }
     
     private void urutkanPopulasiSarang(){
        Sarang temp;
        for(int i=this.populasi.size()-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(this.populasi.get(j).getFitness() < this.populasi.get(j+1).getFitness()){
                    temp = this.populasi.get(j);
                    this.populasi.set(j,this.populasi.get(j+1));
                    this.populasi.set(j+1, temp);
                }
            }
        }    
    }
   
    private void inisialisasiSarang(){
        Sarang sarang;
        BackpropagationLevenbergMarquardt bplm;
        Pelatihan pelatihan;
        for(int i=0;i<this.jumlah_sarang;i++){
            pelatihan = new Pelatihan();
            pelatihan.setKonfigurasi(this.konfigurasi);
            bplm = new BackpropagationLevenbergMarquardt(this.data_latih,this.nn,pelatihan);
            sarang = new Sarang(this.jumlah_telur,bplm);
            this.populasi.add(sarang);
        }
    } 
    
    private void setFitnessPopulasiSarang(){
       for(Sarang sarang : this.populasi){
         sarang.evaluasi(this.data_validation);
       }
    }  

   
    public void cetakPopulasiSarang(){
        int index_sarang=1;
        for(Sarang sarang : this.populasi){
            System.out.println("---Sarang "+index_sarang+"---");
            sarang.cetakTelur();
            System.out.println("-----------------------------");
            index_sarang++;
        } 
    }
    
    private Sarang getSarangLevyFlight(Sarang sarang_terpilih,Sarang sarang_optimal){
        double beta = 1.5;
        double stepsize,telur;
        double sigma = this.doSigma(beta);
        double step  = this.getStep(sigma, beta); 
        ArrayList<Double> telur_baru = new ArrayList<Double>();
        Pelatihan  pelatihan = new Pelatihan();
        pelatihan.setKonfigurasi(this.konfigurasi);
        BackpropagationLevenbergMarquardt bplm =  new BackpropagationLevenbergMarquardt(this.data_latih,this.nn,pelatihan);
        Sarang sarang_levy_flight = new Sarang(this.jumlah_telur,bplm);    
        
        for(int i=0;i<sarang_levy_flight.getPopulasi_telur().size();i++){
           stepsize = 0.01 * step * (sarang_terpilih.getPopulasi_telur().get(i)-sarang_optimal.getPopulasi_telur().get(i));  
           telur = sarang_terpilih.getPopulasi_telur().get(i)+stepsize*new Random().nextGaussian();
           if(telur > sarang_levy_flight.getBatas_atas()){
               telur = sarang_levy_flight.getBatas_atas();
           }else if(telur < sarang_levy_flight.getBatas_bawah()){
               telur = sarang_levy_flight.getBatas_bawah();
           }
           telur_baru.add(telur);
        }
        sarang_levy_flight.setPopulasi_telur(telur_baru);
        sarang_levy_flight.evaluasi(this.data_validation);
        return sarang_levy_flight;
    }
    
     private double getStep(double sigma,double beta){
       double u = new Random().nextGaussian()*sigma;
       double v = new Random().nextGaussian();
       return u/Math.pow(Math.abs(v), (1/beta));
    }
    
    private double doSigma(double beta){
        double term1 = gamma(beta+1)*Math.sin((Math.PI*beta)/2);
        double term2 = gamma((beta+1)/2)*beta*Math.pow(2,(beta-1)/2);
        return Math.pow((term1/term2),(1/beta));
    }
   
   
}

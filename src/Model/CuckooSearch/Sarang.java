/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CuckooSearch;

import Controller.Data;
import Controller.NNTesting;
import Controller.NeuralNetwork.BackpropagationLevenbergMarquardt;
import Model.Training.NeuralNetwork.NeuralNetwork;
import Model.Training.NeuralNetwork.Neuron;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Sarang{

    private double batas_atas = 2;
    private double batas_bawah = -2;
    private ArrayList<Double> populasi_telur;
    private int jumlah_telur;
    private double fitness;   
    private BackpropagationLevenbergMarquardt bplm;
    
    public Sarang(){
        
    }
    
    public Sarang(int jumlah_telur,BackpropagationLevenbergMarquardt bplm){
        this.jumlah_telur = jumlah_telur;
        this.bplm = bplm;
        this.populasi_telur = new ArrayList<Double>(jumlah_telur);
        this.inisialisasiPopulasiTelur();
    }
    
    
    public void evaluasi(Data validation){
        this.bplm.setNn(this.getSaranginNeuralNetwork(this,this.bplm.getNn()));
        this.bplm.trainWithBackpropagationLevenbergMarquardt(); 
        NNTesting validasi = new NNTesting(this.bplm.getHasil_pelatihan_bplm().getKonfigurasi().getNeuron_input_layer(),
                                                this.bplm.getHasil_pelatihan_bplm().getKonfigurasi().getNeuron_hidden_layer(),
                                                                             this.bplm.getHasil_pelatihan_bplm().getBobot_akhir());
        this.fitness = validasi.prediksiCuaca(validation).getAkurasi();
    }
    
    
    public double getFitness(){
        return this.fitness;
    }
    
    public BackpropagationLevenbergMarquardt getBplm() {
        return bplm;
    }
    
    public NeuralNetwork getSaranginNeuralNetwork(Sarang sarang,NeuralNetwork nn){
        int index = 0,index_neuron_hidden=1;
        ArrayList<Double> bobot;    
        
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            if(index_neuron_hidden != nn.getHidden_layer().getJumlah_neuron()){
                bobot = new ArrayList<Double>();
                for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                    bobot.add(sarang.getPopulasi_telur().get(index));
                    index++;
                }
                neuron_hidden.setBobot_masuk(bobot);
            }   
            index_neuron_hidden++;
        }
        
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            bobot = new ArrayList<Double>();
            for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
                bobot.add(sarang.getPopulasi_telur().get(index));
                index++;
            }
            neuron_hidden.setBobot_keluar(bobot);
        }
         
        return nn;
    }
    
    public ArrayList<Double> getPopulasi_telur() {
        return populasi_telur;
    }

    public double getBatas_atas() {
        return batas_atas;
    }

    public double getBatas_bawah() {
        return batas_bawah;
    }
    
    public void setPopulasi_telur(ArrayList<Double> populasi_telur) {
        this.populasi_telur = populasi_telur;
    }
    
    private void inisialisasiPopulasiTelur(){
        for(int i=0;i<this.jumlah_telur;i++){
           this.populasi_telur.add(this.batas_bawah+(this.batas_atas-this.batas_bawah)*new Random().nextDouble());
        }
    }
    
    public void cetakTelur(){
        int i=1;
        for(Double telur: this.populasi_telur){
            System.out.println("telur "+i+" : "+telur);
            i++;
        }
        System.out.println("fitness : "+this.getFitness());
    }
   
    
}

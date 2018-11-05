/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Konfigurasi {
   
   private int neuron_hidden_layer;
   private int neuron_input_layer;
   private double learning_rate;
   private double error;
   private int epoch;
   private String tipe_algoritma;
   private int sarang;
   private int maksimum_generasi;
   private double pa;

    public int getSarang() {
        return sarang;
    }

    public void setSarang(int sarang) {
        this.sarang = sarang;
    }

    public int getMaksimum_generasi() {
        return maksimum_generasi;
    }

    public void setMaksimum_generasi(int maksimum_generasi) {
        this.maksimum_generasi = maksimum_generasi;
    }

    public double getPa() {
        return pa;
    }

    public void setPa(double pa) {
        this.pa = pa;
    }

    public int getNeuron_hidden_layer() {
        return neuron_hidden_layer;
    }

    public void setNeuron_hidden_layer(int neuron_hidden_layer) {
        this.neuron_hidden_layer = neuron_hidden_layer;
    }

    public double getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(double learning_rate) {
        this.learning_rate = learning_rate;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public String getTipe_algoritma() {
        return tipe_algoritma;
    }

    public void setTipe_algoritma(String tipe_algoritma) {
        this.tipe_algoritma = tipe_algoritma;
    }
    
    public int getNeuron_input_layer() {
        return neuron_input_layer;
    }

    public void setNeuron_input_layer(int neuron_input_layer) {
        this.neuron_input_layer = neuron_input_layer;
    }
}

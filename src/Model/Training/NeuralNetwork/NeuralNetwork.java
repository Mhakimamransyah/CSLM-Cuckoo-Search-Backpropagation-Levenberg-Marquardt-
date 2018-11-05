/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Training.NeuralNetwork;

import Controller.NeuralNetwork.OutputLayer;
import Controller.NeuralNetwork.InputLayer;
import Controller.NeuralNetwork.HiddenLayer;
import Model.UnsurCuaca;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author M.Hakim Amransyah
 */

public class NeuralNetwork {
    
    private InputLayer input_layer;
    private HiddenLayer hidden_layer;
    private OutputLayer output_layer;
    private double learning_rate;
    private double error_data;
    
    
    public void setInputUnsurCuaca(UnsurCuaca baris_data){
       this.input_layer.setInputUnsurCuaca(baris_data);
    }
    
    public void setOutputUnsurCuaca(String keadaan_cuaca){
        this.output_layer.setOuputUnsurCuaca(keadaan_cuaca);
    }
    
    public double getError_data() {
        return error_data;
    }

    public void inisialisasiJaringanSarafTiruan(int jumlah_neuron_input, int jumlah_neuron_hidden){
        this.setInput_layer(this.getInisialisasiInputLayer(jumlah_neuron_input));
        this.setHidden_layer(this.getInisialisasiHiddenLayer(jumlah_neuron_hidden));
        this.setOutput_layer(this.getInisialisasiOutputLayer());
        this.inisialisasiBobotJaringan();
      //  this.cetakBobotNeuralnetwork();
    }
    
    public void setError_data(double error_data) {
        this.error_data = error_data;
    }

    public OutputLayer getOutput_layer() {
        return output_layer;
    }

    public void setOutput_layer(OutputLayer output_layer) {
        this.output_layer = output_layer;
    }
  
    public HiddenLayer getHidden_layer() {
        return hidden_layer;
    }

    public void setHidden_layer(HiddenLayer hidden_layer) {
        this.hidden_layer = hidden_layer;
    }

    public InputLayer getInput_layer() {
        return input_layer;
    }

    public void setInput_layer(InputLayer input_layer) {
        this.input_layer = input_layer;
    }
      
    private InputLayer getInisialisasiInputLayer(int jumlah_neuron_input){
        Neuron neuron;
        InputLayer input_layer = new InputLayer();
        ArrayList<Neuron> neuron_input_layer = new ArrayList<Neuron>();
        input_layer.setJumlah_neuron(jumlah_neuron_input);
        for(int i=0;i<input_layer.getJumlah_neuron();i++){ 
            neuron = new Neuron();
            neuron_input_layer.add(neuron);
        }
        input_layer.setNeuron(neuron_input_layer);
        return input_layer;
    }
    
    private HiddenLayer getInisialisasiHiddenLayer(int jmlh_neuron){
        Neuron neuron;
        HiddenLayer hidden_layer = new HiddenLayer();
        ArrayList<Neuron> neuron_hidden_layer = new ArrayList<Neuron>();
        hidden_layer.setJumlah_neuron(jmlh_neuron);
        for(int i=0;i<hidden_layer.getJumlah_neuron();i++){
            neuron = new Neuron();
            neuron_hidden_layer.add(neuron);
        }
        hidden_layer.setNeuron(neuron_hidden_layer);
        return hidden_layer;
    }
    
    private OutputLayer getInisialisasiOutputLayer(){
        Neuron neuron;
        OutputLayer output_layer = new OutputLayer();
        ArrayList<Neuron> neuron_output_layer = new ArrayList<Neuron>();
        output_layer.setJumlah_neuron(5);
        for(int i=0;i<output_layer.getJumlah_neuron();i++){
            neuron = new Neuron();
            neuron_output_layer.add(neuron);
        }
        output_layer.setNeuron(neuron_output_layer);
        return output_layer;
    }
    private void inisialisasiBobotJaringan(){
       
        //bobot ke hidden layer
        int index = 1;
        Random random = new Random();
        for(Neuron neuron_hidden : this.hidden_layer.getNeuron()){
           if(index != this.hidden_layer.getJumlah_neuron()){
             for(Neuron neuron_input : this.input_layer.getNeuron()){
                 neuron_hidden.getBobot_masuk().add(-2+(2-(-2))*random.nextDouble()); 
             }   
           }
           index++;
        }    
        //bobot ke output layer
        for(Neuron neuron_output : this.output_layer.getNeuron()){
            for(Neuron neuron_hidden : this.hidden_layer.getNeuron()){
               neuron_hidden.getBobot_keluar().add(-2+(2-(-2))*random.nextDouble());
            }
        }
    }
    
    public void cetakBobotNeuralnetwork(){
        this.hidden_layer.cetakBobot();
    }

    public double getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(double learning_rate) {
        this.learning_rate = learning_rate;
    }
    
    public double[] getBobotNeuralNetwork(){
        double bobot[] = new double[(this.input_layer.getJumlah_neuron()*(this.hidden_layer.getJumlah_neuron()-1))+
                                        (this.output_layer.getJumlah_neuron()*this.hidden_layer.getJumlah_neuron())];
        int index = 0,index_bobot;
        
        for(Neuron neuron_hidden : this.hidden_layer.getNeuron()){
            index_bobot = 0;
            if(neuron_hidden.getBobot_masuk().size() != 0){
                for(Neuron neuron_input : this.input_layer.getNeuron()){
                    bobot[index] = neuron_hidden.getBobot_masuk().get(index_bobot);
                    index++;
                    index_bobot++;
               }   
            }
        }
        
        for(Neuron neuron_hidden : this.hidden_layer.getNeuron()){
            index_bobot = 0;
            for(Neuron neuron_output : this.output_layer.getNeuron()){
                bobot[index] = neuron_hidden.getBobot_keluar().get(index_bobot);
                index++;
                index_bobot++;
            }
        }
        
        return bobot;
    }
}

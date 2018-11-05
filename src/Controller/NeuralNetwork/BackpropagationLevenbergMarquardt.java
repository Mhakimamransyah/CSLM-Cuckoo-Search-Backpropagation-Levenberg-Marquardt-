/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;

import Controller.Data;
import Model.Training.NeuralNetwork.NeuralNetwork;
import Model.Training.NeuralNetwork.Neuron;
import Model.Training.NeuralNetwork.Pelatihan;
import Model.UnsurCuaca;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class BackpropagationLevenbergMarquardt{
    
    private double jacobian[][];
    private Matriks matriks;
    private int epoch;
    private Data data_latih;
    private double minimum_error;
    private NeuralNetwork nn;
    private Pelatihan hasil_pelatihan_bplm;
         
    public BackpropagationLevenbergMarquardt(Data data_latih, NeuralNetwork nn, Pelatihan pelatihan){
        this.nn = nn;
        this.jacobian = new double[data_latih.getJumlahData()][(this.nn.getInput_layer().getJumlah_neuron()*(this.nn.getHidden_layer().getJumlah_neuron()-1))+(5*this.nn.getHidden_layer().getJumlah_neuron())];
        this.matriks = new Matriks();
        this.hasil_pelatihan_bplm = pelatihan;
        this.data_latih = data_latih;
        this.epoch = this.hasil_pelatihan_bplm.getKonfigurasi().getEpoch();
        this.minimum_error = this.hasil_pelatihan_bplm.getKonfigurasi().getError();
    }
    
    public void setHasil_pelatihan_bplm(Pelatihan hasil_pelatihan_bplm) {
        this.hasil_pelatihan_bplm = hasil_pelatihan_bplm;
    }
    
    public Pelatihan trainWithBackpropagationLevenbergMarquardt(){
        int epoch,baris_data_sekarang;
        double error, current_mse = this.minimum_error+1;
        double error_data[][] = new double[data_latih.getJumlahData()][1];
        
        epoch = 0;
        while(current_mse > this.minimum_error && epoch < this.epoch){
           error = 0;
           baris_data_sekarang = 0;
           for(UnsurCuaca baris_data : data_latih.getUnsurCuaca()){
               this.nn.setInputUnsurCuaca(baris_data);
               this.nn.setOutputUnsurCuaca(baris_data.getKeadaan_cuaca());
               this.feedFoward(nn);
               error = error + this.nn.getError_data();
               this.backpropagation(nn);
               this.bangunMatriksJacobian(nn, baris_data_sekarang);
               error_data[baris_data_sekarang][0] = nn.getError_data();
               baris_data_sekarang++;
           }
          
           current_mse = error/data_latih.getJumlahData();
           if(current_mse > this.minimum_error){
                 this.nn = this.perubahanBobotMatriks(nn,error_data);
           }
           epoch++;
       }
       this.hasil_pelatihan_bplm.setBobot_akhir(this.nn.getBobotNeuralNetwork());
       this.hasil_pelatihan_bplm.setMse(current_mse);
        
       return this.hasil_pelatihan_bplm;
    }
    
    public void setNn(NeuralNetwork nn) {
        this.nn = nn;
    }
    
    public NeuralNetwork getNn() {
        return nn;
    }
    
    public Pelatihan getHasil_pelatihan_bplm() {
        return hasil_pelatihan_bplm;
    }
    
   
     public void feedFoward(NeuralNetwork nn){
     
        double sum;
        double error_data;
        int i;
        //feed foward dari input ke hidden
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            i = 0;
            sum = 0;
            if(neuron_hidden.getBobot_masuk().size() != 0){
                for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                   sum = sum + (neuron_input.getInput()*neuron_hidden.getBobot_masuk().get(i));
                   i++;
                }
                neuron_hidden.setOutput(sum);
            }else{
                neuron_hidden.setBias(1);
            }      
        }
        //feed foward dari hidden ke output
        error_data = 0;
        i = 0;
        for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
            sum = 0;
            for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
               sum = sum + (neuron_hidden.getBobot_keluar().get(i)*neuron_hidden.getOutput());
            }
            i++;
            neuron_output.setOutput(sum);
            error_data = error_data + Math.pow((neuron_output.getOutput()-neuron_output.getTarget()), 2);
        }
        nn.setError_data((error_data)/5);
    }
     
    public void backpropagation(NeuralNetwork nn){
        double delta;
        // Menghitung faktor delta di output layer 
        delta = 0;
        for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
            delta = (neuron_output.getTarget()-neuron_output.getOutput())*(neuron_output.getOutput())*(1-neuron_output.getOutput());
            neuron_output.setDelta(delta);
        }
        
        //Menghitug faktor delta di hidden layer
        int i;
        double sum = 0;
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            i = 0;
            delta = 0;
            if(neuron_hidden.getBobot_masuk().size() != 0){
                for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
                  sum = sum + (neuron_output.getDelta()*neuron_hidden.getBobot_keluar().get(i));
                  i++;
                }
              delta = sum*neuron_hidden.getOutput()*(1-neuron_hidden.getOutput());
              neuron_hidden.setDelta(delta);    
            }
        }
        this.perubahanBobot(nn);
    }
    
    private void perubahanBobot(NeuralNetwork nn){
        int i;
        double temp_bobot;
        ArrayList<Double> bobot_baru;
        //Perubahan bobot hidden ke output               
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            i = 0;
            bobot_baru = new ArrayList<Double>();
            for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
                temp_bobot = neuron_hidden.getBobot_keluar().get(i) + 
                                (nn.getLearning_rate()*neuron_output.getDelta()*neuron_hidden.getOutput());
                bobot_baru.add(temp_bobot);
                i++;
            }
            neuron_hidden.setBobot_keluar(bobot_baru);
        }
       
        
        //Perubahan bobot input ke hidden
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            if(neuron_hidden.getBobot_masuk().size() != 0){
               i = 0;
               bobot_baru = new ArrayList<Double>();
               for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                    temp_bobot = neuron_hidden.getBobot_masuk().get(i) + 
                                    (nn.getLearning_rate()*neuron_hidden.getDelta()*neuron_input.getInput());
                    bobot_baru.add(temp_bobot);
                    i++;
               }
               neuron_hidden.setBobot_masuk(bobot_baru);   
            }
        }
    }
    
    
    public void bangunMatriksJacobian(NeuralNetwork nn, int baris_data){
        
        int kolom_jacobian;
        
        kolom_jacobian = 0;
        
       //set matriks jacobian input ke hidden layer
        double temp_value = 0;
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            if(neuron_hidden.getBobot_masuk().size() != 0){
               for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                    temp_value = (neuron_hidden.getDelta() * neuron_input.getInput())/nn.getError_data();
                    this.jacobian[baris_data][kolom_jacobian] = temp_value;
                    kolom_jacobian++;
               }   
            }
        }
        
        //set matriks jacobian hidden ke outputlayer
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
                temp_value = (neuron_output.getDelta()*neuron_hidden.getOutput())/nn.getError_data();
                this.jacobian[baris_data][kolom_jacobian] = temp_value;
                kolom_jacobian++;
            }
        } 
    }
    
    
    public NeuralNetwork perubahanBobotMatriks(NeuralNetwork nn,double[][] error){     

        double hessian[][] = this.matriks.getMatriksHessian(jacobian);
        double dumping[][] = this.matriks.getMatriksDumping(hessian, nn.getLearning_rate());
        double gradient[][] = this.matriks.getMatriksGradient(jacobian, error);
        double matriks_invers[][] = this.matriks.getMatriksInvers(hessian, dumping);
        double matriks_delta[][] = this.matriks.getMatriksDelta(matriks_invers, gradient);
              
        ArrayList<Double> bobot_baru;
        int index_baris=0,index_bobot;
        //update bobot input ke hidden
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            if(neuron_hidden.getBobot_masuk().size() != 0){
               bobot_baru = new ArrayList<Double>();
               index_bobot = 0;
               for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                    bobot_baru.add(neuron_hidden.getBobot_masuk().get(index_bobot)+matriks_delta[index_baris][0]);
                    index_baris++;
                    index_bobot++;
               }
               neuron_hidden.setBobot_masuk(bobot_baru);   
            }
        }
        //update bobot hidden ke output
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            bobot_baru = new ArrayList<Double>();
            index_bobot = 0; 
            for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
                bobot_baru.add(neuron_hidden.getBobot_keluar().get(index_bobot)+matriks_delta[index_baris][0]);
                index_baris++;
                index_bobot++;
            }
            neuron_hidden.setBobot_keluar(bobot_baru);
        }
        
        return nn;
    }
    
    public void cetakJacobian(){
        System.out.println("lebar : "+this.jacobian[0].length);
        this.matriks.cetakMatriks(this.jacobian);
    }
    
}

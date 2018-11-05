/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Training.NeuralNetwork;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Neuron {
    
    private ArrayList<Double> bobot_masuk;
    private ArrayList<Double> bobot_keluar;
    private double delta;
    private double output; // untuk hidden layer dan output layer
    private double target; // untuk output layer
    private double input; // untuk input layer
     // untuk bias di input layer

    public void setBias(double bias) {
        this.output = bias;
    }
       
    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
  
    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        // gunakan fungsi aktivasi sigmoid binner
        this.output = 1/(1+Math.exp(-output));
        
    }
    
    public Neuron(){
        this.bobot_masuk = new ArrayList<Double>();
        this.bobot_keluar = new ArrayList<Double>();
    }

    public ArrayList<Double> getBobot_keluar() {
        return bobot_keluar;
    }

    public void setBobot_keluar(ArrayList<Double> bobot_keluar) {
        this.bobot_keluar = bobot_keluar;
    }

    public ArrayList<Double> getBobot_masuk() {
        return bobot_masuk;
    }

    public void setBobot_masuk(ArrayList<Double> bobot_masuk) {
        this.bobot_masuk = bobot_masuk;
    }   
}

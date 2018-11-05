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
public abstract class Layer {
    
    private ArrayList<Neuron> neuron;
    private int jumlah_neuron;

    public ArrayList<Neuron> getNeuron() {
        return neuron;
    }

    public void setNeuron(ArrayList<Neuron> neuron) {
        this.neuron = neuron;
    }

    public int getJumlah_neuron() {
        return jumlah_neuron;
    }

    public void setJumlah_neuron(int jumlah_neuron) {
        this.jumlah_neuron = jumlah_neuron;
    }
    
    public void cetakBobot(){}
    
    
}

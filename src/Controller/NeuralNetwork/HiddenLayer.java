/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;

import Model.Training.NeuralNetwork.Layer;
import Model.Training.NeuralNetwork.Neuron;

/**
 *
 * @author M.Hakim Amransyah
 */
public class HiddenLayer extends Layer{
         
    public void cetakBobot(){   
        System.out.println("Bobot input ke hidden");
        for(Neuron neuron_hidden : this.getNeuron()){
            System.out.println(neuron_hidden.getBobot_masuk());
        }
        
        System.out.println("Bobot hidden ke output");
        for(Neuron neuron_hidden : this.getNeuron()){
            System.out.println(neuron_hidden.getBobot_keluar());
        }
        System.out.println("");
    }
}

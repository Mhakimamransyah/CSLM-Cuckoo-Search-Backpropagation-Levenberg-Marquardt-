/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;

import Model.Training.NeuralNetwork.Layer;
import Model.Training.NeuralNetwork.Neuron;
import Model.UnsurCuaca;

/**
 *
 * @author M.Hakim Amransyah
 */
public class InputLayer extends Layer{
   
    
    public void setInputUnsurCuaca(UnsurCuaca data_latih){
        
        int i=0;
        for(Neuron neuron_input : this.getNeuron()){
            if(i < this.getJumlah_neuron()-1){
              neuron_input.setInput(data_latih.getAtribut_value().get(i));    
            }
            else{
              neuron_input.setInput(1); //bias
            }
            i++;
        }
//        this.cetakInputUnsurCuaca();
    }
    
    private void cetakInputUnsurCuaca(){
        for(Neuron neuron : this.getNeuron()){
            System.out.print(neuron.getInput()+" ");
        }
        System.out.println("");
    }
}

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
public class OutputLayer extends Layer{
    
    public void setOuputUnsurCuaca(String aktual){
            switch(aktual){
                case "Sangat Ringan": 
                       this.getNeuron().get(0).setTarget(1);
                       this.getNeuron().get(1).setTarget(0);
                       this.getNeuron().get(2).setTarget(0);
                       this.getNeuron().get(3).setTarget(0);
                       this.getNeuron().get(4).setTarget(0);
                    break;
                case "Ringan"       : 
                       this.getNeuron().get(0).setTarget(0);
                       this.getNeuron().get(1).setTarget(1);
                       this.getNeuron().get(2).setTarget(0);
                       this.getNeuron().get(3).setTarget(0);
                       this.getNeuron().get(4).setTarget(0);
                    break;
                case "Sedang"       : 
                       this.getNeuron().get(0).setTarget(0);
                       this.getNeuron().get(1).setTarget(0);
                       this.getNeuron().get(2).setTarget(1);
                       this.getNeuron().get(3).setTarget(0);
                       this.getNeuron().get(4).setTarget(0);
                    break;
                case "Lebat"        : 
                       this.getNeuron().get(0).setTarget(0);
                       this.getNeuron().get(1).setTarget(0);
                       this.getNeuron().get(2).setTarget(0);
                       this.getNeuron().get(3).setTarget(1);
                       this.getNeuron().get(4).setTarget(0);
                    break;
                case "Sangat Lebat" : 
                       this.getNeuron().get(0).setTarget(0);
                       this.getNeuron().get(1).setTarget(0);
                       this.getNeuron().get(2).setTarget(0);
                       this.getNeuron().get(3).setTarget(0);
                       this.getNeuron().get(4).setTarget(1);
                    break;
            }
//         this.cetakTargetKeadaanCuaca();
    }
    
    public String getPrediksiKeadaanCuaca(){
        String keadaan_cuaca = "";
        int index_max=0;
        for(int i=0;i<this.getJumlah_neuron();i++){
//            System.out.print("|| "+this.getNeuron().get(i).getOutput());
            if(this.getNeuron().get(index_max).getOutput() < this.getNeuron().get(i).getOutput()){
                index_max = i;
            }
        }
        
        switch(index_max){
            case 0: keadaan_cuaca = "Sangat Ringan"; break;
            case 1: keadaan_cuaca = "Ringan"; break;
            case 2: keadaan_cuaca = "Sedang"; break;
            case 3: keadaan_cuaca = "Lebat"; break;
            case 4: keadaan_cuaca = "Sangat Lebat"; break;
        }
//        System.out.print(" prediksi cuaca : "+keadaan_cuaca);
        return keadaan_cuaca;
    }
    
    public void cetakTargetKeadaanCuaca(){
        System.out.print("Target : ");
        for(Neuron n : this.getNeuron()){
            System.out.print(n.getTarget()+" ");
        }
        System.out.println("");
    }
}

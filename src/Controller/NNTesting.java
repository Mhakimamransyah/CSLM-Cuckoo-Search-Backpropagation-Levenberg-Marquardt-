package Controller;


import Model.Training.NeuralNetwork.NeuralNetwork;
import Model.Training.NeuralNetwork.Neuron;
import Model.UnsurCuaca;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class NNTesting {
   
    private NeuralNetwork nn;
    private MatriksConfusion matriks_confusion;
    
    public NNTesting(int neuron_input_layer,int neuron_hidden_layer,double bobot_hasil_pelatihan[]){
        this.nn = new NeuralNetwork();
        this.inisialisasiBobotJaringanHasilPelatihan(neuron_input_layer, neuron_hidden_layer+1, bobot_hasil_pelatihan);
        this.matriks_confusion = new MatriksConfusion();
    }
    
   public MatriksConfusion prediksiCuaca(Data data_uji){

       for(UnsurCuaca baris_data : data_uji.getUnsurCuaca()){
              this.nn.setInputUnsurCuaca(baris_data);
              this.matriks_confusion.buildMatriksConfusion(this.getPrediksiKeadaanCuaca(baris_data),baris_data.getKeadaan_cuaca());
       } 
//       this.matriks_confusion.cetakMatriksConfusion();
//       System.out.println("presisi sangat ringan : "+this.matriks_confusion.getPersentasePresisi("Ringan"));
//       System.out.println("recall                : "+this.matriks_confusion.getPersentaseRecall("Ringan"));
//       System.out.println("akurasi               : "+this.matriks_confusion.getAkurasi());
       return this.matriks_confusion;
   }
    
    private String getPrediksiKeadaanCuaca(UnsurCuaca baris_data){
        double sum;
        String keadaan_cuaca = null;
        int i;        
        //propagasi maju       
        for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
            if(neuron_hidden.getBobot_masuk().size() != 0){
               i = 0;
               sum = 0;
               for(Neuron neuron_input : nn.getInput_layer().getNeuron()){
                 sum = sum + (neuron_input.getInput()*neuron_hidden.getBobot_masuk().get(i));
                 i++;
               }
               neuron_hidden.setOutput(sum);   
            }
            else{
                neuron_hidden.setBias(1);
            }
        }
        
        i = 0;
        for(Neuron neuron_output : nn.getOutput_layer().getNeuron()){
            sum = 0;
            for(Neuron neuron_hidden : nn.getHidden_layer().getNeuron()){
               sum = sum + (neuron_hidden.getBobot_keluar().get(i)*neuron_hidden.getOutput());
            }
            i++;
            neuron_output.setOutput(sum);
        }
        return nn.getOutput_layer().getPrediksiKeadaanCuaca();
    }
    
    private void inisialisasiBobotJaringanHasilPelatihan(int neuron_input, int neuron_hidden,double bobot_hasil_pelatihan[]){
        this.nn.inisialisasiJaringanSarafTiruan(neuron_input, neuron_hidden);
        this.terapkanBobotKeArsitektur(bobot_hasil_pelatihan);
//        this.nn.cetakBobotNeuralnetwork();
    }
    
    private void terapkanBobotKeArsitektur(double bobot_hasil_pelatihan[]){
        
        int index = 0,index_hidden_layer=1;
        ArrayList<Double> bobot;
        
        for(Neuron neuron_hidden : this.nn.getHidden_layer().getNeuron()){
            if(index_hidden_layer != this.nn.getHidden_layer().getJumlah_neuron()){
                bobot = new ArrayList<Double>();
                for(Neuron neuron_input : this.nn.getInput_layer().getNeuron()){
                  bobot.add(bobot_hasil_pelatihan[index]);
                  index++;
                }
                neuron_hidden.setBobot_masuk(bobot);   
            }
            index_hidden_layer++;
        }
        
        for(Neuron neuron_hidden : this.nn.getHidden_layer().getNeuron()){
            bobot = new ArrayList<Double>();
            for(Neuron neuron_output : this.nn.getOutput_layer().getNeuron()){
                bobot.add(bobot_hasil_pelatihan[index]);
                index++;
            }
            neuron_hidden.setBobot_keluar(bobot);
        }
    }
}
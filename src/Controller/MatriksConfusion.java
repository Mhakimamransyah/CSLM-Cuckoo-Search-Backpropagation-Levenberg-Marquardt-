/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.DecimalFormat;

/**
 *
 * @author M.Hakim Amransyah
 */
public class MatriksConfusion {
    
    private double[][] matriks_confusion;

    public void setMatriks_confusion(double[][] matriks_confusion) {
        this.matriks_confusion = matriks_confusion;
    }
    
    public MatriksConfusion(){
        this.matriks_confusion = new double[5][5];
    }
    
    public void cetakMatriksConfusion(){
        for(int i=0;i<this.matriks_confusion.length;i++){
            for(int j=0;j<this.matriks_confusion[0].length;j++){
                System.out.print(this.matriks_confusion[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public double getAkurasi(){
        DecimalFormat df = new DecimalFormat("#.##");
        double result,sum=0,akurasi=0;
        for(int i=0;i<this.matriks_confusion.length;i++){
            for(int j=0;j<this.matriks_confusion[0].length;j++){
                if(i == j){
                    akurasi = akurasi + this.matriks_confusion[i][j];
                }
                sum = sum + this.matriks_confusion[i][j];
            }
        }
        result = (akurasi/sum)*100;
        return Double.parseDouble(df.format(result).replaceAll(",","."));
    }
    
    public double getPersentaseRecall(String keadaan_cuaca){
        DecimalFormat df = new DecimalFormat("#.##");
        double result = 0;
        int index = this.getIndexMatriks(keadaan_cuaca);
        double sum = 0;
        for(int i=0;i<this.matriks_confusion.length;i++){
            sum = sum + this.matriks_confusion[i][index];
        }
        if(sum != 0){
            result = (this.matriks_confusion[index][index]/sum)*100;
        } 
        else if(sum == 0){
            result = 0;
        }
        return Double.parseDouble(df.format(result).replaceAll(",","."));
    }
    
    public double getFmeasure(String keadaan_cuaca){
        DecimalFormat df = new DecimalFormat("#.##");
        double presisi = this.getPersentasePresisi(keadaan_cuaca);
        double recall = this.getPersentaseRecall(keadaan_cuaca);
        double result = 0;
        if(presisi == 0 && recall == 0){
            result = 0;
        }else{
            result = Double.parseDouble(df.format((2*(recall*presisi))/(recall+presisi)).replaceAll(",","."));
        }
        return result;
    }
    
    
    public double getPersentasePresisi(String keadaan_cuaca){
        DecimalFormat df = new DecimalFormat("#.##");
        double result = 0;
        int index = this.getIndexMatriks(keadaan_cuaca);
        double sum = 0;
        for(int j=0;j<this.matriks_confusion[0].length;j++){
            sum = sum + this.matriks_confusion[index][j];
        }
        if(sum != 0){
            result = (this.matriks_confusion[index][index]/sum)*100;
        } 
        else if(sum == 0){
            result = 0;
        }
        return Double.parseDouble(df.format(result).replaceAll(",","."));
    }
    
    public double[][] getMatriksConfusion(){
        return this.matriks_confusion;
    }
    
    
    
    public void buildMatriksConfusion(String prediksi,String Aktual){
        int index_baris = this.getIndexMatriks(prediksi);
        int index_kolom = this.getIndexMatriks(Aktual);
        this.matriks_confusion[index_baris][index_kolom]++;
    }
    
    private int getIndexMatriks(String keadaan_cuaca){
        int index = 0;
        switch(keadaan_cuaca){
            case "Sangat Ringan": index=0; break;
            case "Ringan"       : index=1; break;
            case "Sedang"       : index=2; break;
            case "Lebat"        : index=3; break;
            case "Sangat Lebat" : index=4; break;
        }
        return index;
    }
       
}

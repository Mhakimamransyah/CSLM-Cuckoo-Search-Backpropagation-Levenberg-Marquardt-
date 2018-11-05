/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UnsurCuaca;

/**
 *
 * @author M.Hakim Amransyah
 */
public class PraprosesData {
    
    public Data pembersihanData(Data data){
        if(data.getJumlahData() > 0){
          for(int i=0;i<data.getUnsurCuaca().get(0).getAtribut_value().size();i++){
          for(UnsurCuaca unsur_cuaca : data.getUnsurCuaca()){
              if(unsur_cuaca.getAtribut_value().get(i) == 9999 || unsur_cuaca.getAtribut_value().get(i) == 8888){
                  unsur_cuaca.getAtribut_value().set(i,data.getMeanUnsurCuaca(i, unsur_cuaca.getKeadaan_cuaca()));
              }
          }
         }    
        }
        return data;
    }
     
    public String DiskritisasiData(double curah_hujan){
        String keadaan_cuaca = null; 
        if(curah_hujan < 5){
            keadaan_cuaca = "Sangat Ringan";
        }
        else if(curah_hujan >= 5 && curah_hujan < 20){
            keadaan_cuaca = "Ringan";
        }
        else if(curah_hujan >= 20 && curah_hujan < 50){
            keadaan_cuaca = "Sedang";
        }
        else if(curah_hujan >= 50 && curah_hujan < 100){
            keadaan_cuaca = "Lebat";
        }
        else if(curah_hujan >= 100 && curah_hujan != 9999 && curah_hujan != 8888) {
            keadaan_cuaca = "Sangat Lebat";
        }
        return keadaan_cuaca;
    }
    
    public Data normalisasiData(Data atribut_data){
        double max, min;
        for(int i=0;i<atribut_data.getUnsurCuaca().get(0).getAtribut_value().size();i++){
            max = -99999;
            min = 100000;
            for(UnsurCuaca unsur_cuaca : atribut_data.getUnsurCuaca()){
             if(max < unsur_cuaca.getAtribut_value().get(i)){
                 max = unsur_cuaca.getAtribut_value().get(i);
             }
             if(min > unsur_cuaca.getAtribut_value().get(i)){
                 min = unsur_cuaca.getAtribut_value().get(i);
             }
           }
           for(UnsurCuaca unsur_cuaca : atribut_data.getUnsurCuaca()){
               double norm_value = (unsur_cuaca.getAtribut_value().get(i) - min)/(max - min);
               unsur_cuaca.getAtribut_value().set(i, norm_value);
           } 
        }
        return atribut_data;
    }  
}

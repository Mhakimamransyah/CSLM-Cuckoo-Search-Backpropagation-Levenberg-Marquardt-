/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class UnsurCuaca {
   
    private String tanggal;
    private String keadaan_cuaca;
    private ArrayList<Double> atribut_value;
    
    public UnsurCuaca(){
        this.atribut_value = new ArrayList<Double>();
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeadaan_cuaca() {
        return keadaan_cuaca;
    }

    public void setKeadaan_cuaca(String keadaan_cuaca) {
        this.keadaan_cuaca = keadaan_cuaca;
    }

    public ArrayList<Double> getAtribut_value() {
        return atribut_value;
    }

    public void setAtribut_value(ArrayList<Double> atribut_value) {
        this.atribut_value = atribut_value;
    }
    
    public void tambahAtribut(double nilai){
        this.atribut_value.add(nilai);
    }
    
}

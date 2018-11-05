/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Training.NeuralNetwork;

import Model.Konfigurasi;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Pelatihan {
    
    private Konfigurasi konfigurasi;
    private int id_konfigurasi;
    private double akurasi_validasi;
    private double mse;
    private double[] bobot_akhir;
    private int id_pelatihan;
    private double waktu_eksekusi;


    public double getWaktu_eksekusi() {
        return waktu_eksekusi;
    }

    public void setWaktu_eksekusi(double waktu_eksekusi) {
        this.waktu_eksekusi = waktu_eksekusi;
    }
    
    public String getStringBobot_akhir(){
       String bobot = "";
       for(int i=0;i<this.bobot_akhir.length;i++){
           bobot = bobot +this.bobot_akhir[i]+",";
       }
       return bobot;
    }
    
    public double getAkurasi_validasi() {
        return akurasi_validasi;
    }

    public void setAkurasi_validasi(double akurasi_validasi) {
        this.akurasi_validasi = akurasi_validasi;
    }
    
    public double[] getBobot_akhir() {
        return bobot_akhir;
    }
    
    public void setBobot_akhir(String bobot_akhir){
        String bobot[] = bobot_akhir.split(",");
        this.bobot_akhir = new double[bobot.length];
        for(int i=0;i<bobot.length;i++){
            this.bobot_akhir[i] = Double.parseDouble(bobot[i]);
        }
    }

    public void setBobot_akhir(double[] bobot_akhir){
        this.bobot_akhir = bobot_akhir;
    }

    public int getId_konfigurasi() {
        return id_konfigurasi;
    }

    public void setId_konfigurasi(int id_konfigurasi) {
        this.id_konfigurasi = id_konfigurasi;
    }

    public Konfigurasi getKonfigurasi() {
        return konfigurasi;
    }

    public void setKonfigurasi(Konfigurasi konfigurasi) {
        this.konfigurasi = konfigurasi;
    }

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }
    
    public int getId_pelatihan() {
        return id_pelatihan;
    }

    public void setId_pelatihan(int id_pelatihan) {
        this.id_pelatihan = id_pelatihan;
    } 
    
}

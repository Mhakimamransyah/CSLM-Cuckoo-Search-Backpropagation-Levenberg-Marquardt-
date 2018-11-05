/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.UnsurCuaca;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Data {
 
    private String jenis_data;
    private ArrayList<UnsurCuaca> unsur_cuaca;
    private HashMap<String,Integer> sebaran_label;
    private PraprosesData praprosesdata;
    private String header[];
 
    public Data(){
        this.sebaran_label = new HashMap<String,Integer>();
        this.unsur_cuaca = new ArrayList<UnsurCuaca>();
        this.praprosesdata = new PraprosesData();
        jenis_data = null;
    }
    
    
    
    public String getJenis_data() {
        return jenis_data;
    }

    public void setJenis_data(String jenis_data) {
        this.jenis_data = jenis_data;
    }
    
    public String[] getHeader() {
        return header;
    }
    
    public void copyData(Data data){
        for(UnsurCuaca atribut : data.getUnsurCuaca()){
            this.unsur_cuaca.add(atribut);
        }
    }

    public void setHeader(String[] header) {
        this.header = header;
    }
    
    public ArrayList<UnsurCuaca> getUnsurCuaca() {
        return unsur_cuaca;
    }
    
    public void tambahUnsurCuaca(UnsurCuaca unsur_cuaca) {
        this.unsur_cuaca.add(unsur_cuaca);
    }
     
    public int getJumlahAtributData(){
        int jumlah = 0;
        if(this.unsur_cuaca.size() > 0){
            jumlah = this.unsur_cuaca.get(0).getAtribut_value().size()+2;
        }
        return jumlah;
    }
    
    public int getJumlahData(){
        return this.unsur_cuaca.size();
    }
    
    public void cetakData(){
        for(UnsurCuaca unsur_cuaca : this.unsur_cuaca){
            System.out.print(unsur_cuaca.getTanggal()+" ");
            for(int i=0;i<unsur_cuaca.getAtribut_value().size();i++){
                System.out.print(" "+unsur_cuaca.getAtribut_value().get(i));
            }
            System.out.println(" "+unsur_cuaca.getKeadaan_cuaca());
        }
    }
    
    public String getHasilDiskretisasiData(double curah_hujan){
        return this.praprosesdata.DiskritisasiData(curah_hujan);
    }
    
    public Data getHasilNormalisasiData(){
        return this.praprosesdata.normalisasiData(this);
    }
    
    public Data getHasilPembersihanData(){
        return this.praprosesdata.pembersihanData(this);
    }
    
    public HashMap<String, Integer> getSebaran_label() {
        this.setSebaranLabel();
        return sebaran_label;
    }
    
    private HashMap<String,Integer> setSebaranLabel(){
        int sangat_ringan = 0;
        int ringan = 0;
        int sedang = 0;
        int lebat = 0;
        int sangat_lebat = 0;
        
        for(UnsurCuaca unsur_cuaca : this.unsur_cuaca){
            if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase("Sangat Ringan")){
                sangat_ringan++;
            }else if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase("Ringan")){
                ringan++;
            }else if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase("Sedang")){
                sedang++;
            }else if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase("Lebat")){
                lebat++;
            }else if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase("Sangat Lebat")){
                sangat_lebat++;
            }
        }
        
        this.sebaran_label.put("Sangat_Ringan", sangat_ringan);
        this.sebaran_label.put("Ringan", ringan);
        this.sebaran_label.put("Sedang", sedang);
        this.sebaran_label.put("Lebat", lebat);
        this.sebaran_label.put("Sangat_Lebat", sangat_lebat);
        
        return sebaran_label;
    }
    
    public double getMeanUnsurCuaca(int index, String keadaan_cuaca){
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        double count = 0,enr=0;
        
        for(UnsurCuaca unsur_cuaca:this.unsur_cuaca){
            if(unsur_cuaca.getKeadaan_cuaca().equalsIgnoreCase(keadaan_cuaca) && unsur_cuaca.getAtribut_value().get(index)!=9999 && unsur_cuaca.getAtribut_value().get(index)!=8888){
                count++;
                enr = enr + unsur_cuaca.getAtribut_value().get(index) ;
            }
        }
        
        return Double.parseDouble(df.format(enr/count).replace(",","."));
    }
}

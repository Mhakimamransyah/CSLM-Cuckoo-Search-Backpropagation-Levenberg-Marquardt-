/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Training.NeuralNetwork.Pelatihan;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class ListPelatihan {
    
    private ArrayList<Pelatihan> hasil_pelatihan;
    
    public int getJumlahPelatihan(){
      return this.hasil_pelatihan.size();
    }
    
    public ListPelatihan(){
        this.hasil_pelatihan = new ArrayList<Pelatihan>();
    }
    
    public ArrayList<Pelatihan> getHasil_pelatihan() {
        return hasil_pelatihan;
    }

    public void setHasil_pelatihan(ArrayList<Pelatihan> hasil_pelatihan) {
        this.hasil_pelatihan = hasil_pelatihan;
    }
    
    public void tambah(Pelatihan pelatihan){
        this.hasil_pelatihan.add(pelatihan);
        this.sortHasilPelatihanById();
    }
    
    public void pelatihan_baru(Pelatihan pelatihan){
        if(pelatihan != null){
          pelatihan.getKonfigurasi().setTipe_algoritma("unsaved "+pelatihan.getKonfigurasi().getTipe_algoritma());
          this.hasil_pelatihan.add(pelatihan);
          if(this.hasil_pelatihan.size()!=0){
             pelatihan.setId_pelatihan(this.hasil_pelatihan.get(0).getId_pelatihan()+1);
          }
          else{
             pelatihan.setId_pelatihan(1);
          }
          this.sortHasilPelatihanById();   
        }
    }
    
    public void hapusPelatihan(int id){
       int index_pelatihan = 0;
                for(Pelatihan pelatihan : this.hasil_pelatihan){
                    if(pelatihan.getId_pelatihan() == id){
                        break;
                    }
                    index_pelatihan++;
                }
        this.hasil_pelatihan.remove(index_pelatihan);
        this.sortHasilPelatihanById();
    }
    
    public Pelatihan getPelatihanTerpilih(int id_pelatihan){
        Pelatihan pelatihan_terpilih = null;
        for(Pelatihan pelatihan : this.hasil_pelatihan){
            if(pelatihan.getId_pelatihan() == id_pelatihan){
                pelatihan_terpilih = pelatihan;
            }
        }
        return pelatihan_terpilih;
    }
    
    public void ubahPelatihan(int id, Pelatihan pelatihan){
        int index = 0;
        for(Pelatihan pelatihan_ : this.hasil_pelatihan){
            if(pelatihan.getId_pelatihan() == id){
                this.hasil_pelatihan.set(index, pelatihan_);
            }
            index++;
        }
    }
    
    private void sortHasilPelatihanById(){
        int jumlah_pelatihan = this.hasil_pelatihan.size();
        Pelatihan temp;
        for(int i=jumlah_pelatihan-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(this.hasil_pelatihan.get(j).getId_pelatihan() < this.hasil_pelatihan.get(j+1).getId_pelatihan()){
                    temp = this.hasil_pelatihan.get(j);
                    this.hasil_pelatihan.set(j,this.hasil_pelatihan.get(j+1));
                    this.hasil_pelatihan.set(j+1, temp);
                }
            }
        }
    }
     
}

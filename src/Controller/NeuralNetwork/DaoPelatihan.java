/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.NeuralNetwork;

import Controller.ListPelatihan;
import Model.Konfigurasi;
import Model.Database;
import Model.Training.NeuralNetwork.Pelatihan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class DaoPelatihan {
    
    private Database db;
    private ListPelatihan hasil_pelatihan;
    
    public DaoPelatihan(){
        this.db = new Database();
        this.hasil_pelatihan = new ListPelatihan();
        this.db.bukaKoneksi();
    }
    
    public void hapusDataHasilPelatihan(int id_pelatihan) throws SQLException{
        PreparedStatement ps;
        Connection conn;
        conn = this.db.getConn();
        
        ps = conn.prepareStatement("DELETE pelatihan,konfigurasi FROM pelatihan "
                                    + "INNER JOIN konfigurasi ON pelatihan.Id_konfigurasi = konfigurasi.Id_konfigurasi "
                                    + "WHERE pelatihan.Id_pelatihan = ?");
        ps.setInt(1,id_pelatihan);
        ps.executeUpdate();
    }
    
    public ListPelatihan bacaDataHasilPelatihan() throws SQLException{
        
        Konfigurasi konfigurasi;
        Pelatihan pelatihan;
         ArrayList<Pelatihan> list_pelatihan = new ArrayList<Pelatihan>();
        Connection conn;
        ResultSet rs;
        conn = this.db.getConn();
        PreparedStatement ps;
        
        ps = conn.prepareStatement("SELECT * FROM pelatihan INNER JOIN konfigurasi ON pelatihan.Id_konfigurasi = konfigurasi.Id_konfigurasi "
                + "ORDER BY Id_pelatihan DESC");
        rs = ps.executeQuery();
        
        while(rs.next()){           
            pelatihan = new Pelatihan();
            konfigurasi = new Konfigurasi();
            konfigurasi.setTipe_algoritma(rs.getString("Tipe_algoritma"));
            konfigurasi.setEpoch(rs.getInt("Epoch"));
            konfigurasi.setNeuron_input_layer(rs.getInt("Neuron_input"));
            konfigurasi.setNeuron_hidden_layer(rs.getInt("Neuron_hidden"));
            konfigurasi.setLearning_rate(rs.getDouble("Learning_rate"));
            konfigurasi.setError(rs.getDouble("Error"));
            konfigurasi.setSarang(rs.getInt("Sarang"));
            konfigurasi.setMaksimum_generasi(rs.getInt("Generasi"));
            konfigurasi.setPa(rs.getDouble("Pa"));
            pelatihan.setId_pelatihan(rs.getInt("Id_pelatihan"));
            pelatihan.setWaktu_eksekusi(rs.getDouble("Waktu_eksekusi_bplm"));
            pelatihan.setId_konfigurasi(rs.getInt("Id_konfigurasi"));
            pelatihan.setMse(rs.getDouble("MSE"));
            pelatihan.setAkurasi_validasi(rs.getDouble("Akurasi_validasi"));
            pelatihan.setBobot_akhir(rs.getString("Bobot_akhir"));
            pelatihan.setKonfigurasi(konfigurasi);
            this.hasil_pelatihan.tambah(pelatihan);
        }
        
        return this.hasil_pelatihan;
    }
    
    public void insertHasilPelatihan(Pelatihan pelatihan) throws SQLException{
        Connection conn;
        conn = this.db.getConn();
       
        conn.setAutoCommit(false);
        PreparedStatement ps_insert_to_konfigurasi;
        ps_insert_to_konfigurasi = conn.prepareStatement("INSERT INTO konfigurasi(Neuron_hidden,Neuron_input,Learning_rate,Epoch,Error,Sarang,Generasi,Pa)"
                + "VALUES(?,?,?,?,?,?,?,?)");
        ps_insert_to_konfigurasi.setString(1,pelatihan.getKonfigurasi().getNeuron_hidden_layer()+"");
        ps_insert_to_konfigurasi.setString(2,pelatihan.getKonfigurasi().getNeuron_input_layer()+"");
        ps_insert_to_konfigurasi.setString(3,pelatihan.getKonfigurasi().getLearning_rate()+"");
        ps_insert_to_konfigurasi.setString(4,pelatihan.getKonfigurasi().getEpoch()+"");
        ps_insert_to_konfigurasi.setString(5,pelatihan.getKonfigurasi().getError()+"");
        ps_insert_to_konfigurasi.setString(6,pelatihan.getKonfigurasi().getSarang()+"");
        ps_insert_to_konfigurasi.setString(7,pelatihan.getKonfigurasi().getMaksimum_generasi()+"");
        ps_insert_to_konfigurasi.setString(8,pelatihan.getKonfigurasi().getPa()+"");
        ps_insert_to_konfigurasi.executeUpdate();
       
        
        PreparedStatement ps_insert_to_pelatihan;
        ps_insert_to_pelatihan = conn.prepareStatement("INSERT INTO pelatihan(Id_pelatihan,Tipe_algoritma,MSE,Waktu_eksekusi_bplm,Bobot_akhir,Akurasi_validasi,Id_konfigurasi)"
                + "VALUES(?,?,?,?,?,?,(SELECT getLastInsertKonfigurasiId()))");
        ps_insert_to_pelatihan.setInt(1, pelatihan.getId_pelatihan());
        ps_insert_to_pelatihan.setString(2, pelatihan.getKonfigurasi().getTipe_algoritma());
        ps_insert_to_pelatihan.setString(3, pelatihan.getMse()+"");
        ps_insert_to_pelatihan.setDouble(4, pelatihan.getWaktu_eksekusi());
        ps_insert_to_pelatihan.setString(5, pelatihan.getStringBobot_akhir());
        ps_insert_to_pelatihan.setDouble(6, pelatihan.getAkurasi_validasi());
        ps_insert_to_pelatihan.executeUpdate();
        conn.setAutoCommit(true);
    }
}

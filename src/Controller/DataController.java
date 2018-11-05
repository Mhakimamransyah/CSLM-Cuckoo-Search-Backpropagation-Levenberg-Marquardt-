/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.UnsurCuaca;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author M.Hakim Amransyah
 */
public class DataController {     
    
    public Data pilihData(javax.swing.JPanel Data,String jenis) throws IOException{
        String data,data_array[],header[];
        UnsurCuaca unsur_cuaca;
        Data temp_data = new Data();
        JFileChooser file_chooser = new JFileChooser();
        file_chooser.setCurrentDirectory(new File("E:\\penelitian\\Program"));
        file_chooser.setFileFilter(new FileNameExtensionFilter("csv file", "csv"));
        if(file_chooser.showOpenDialog(Data) == JFileChooser.APPROVE_OPTION)
        {
            File f = file_chooser.getSelectedFile();
            if(this.validasiFormatFile(f.getName())){
                   try {
                    BufferedReader bf = new BufferedReader(new FileReader(f.getPath()));
                    header = bf.readLine().split(",");
                    temp_data.setHeader(header);
                    temp_data.setJenis_data(jenis);
                    while((data = bf.readLine())!=null){
                        unsur_cuaca  = new UnsurCuaca();
                        data_array = data.split(",");
                        unsur_cuaca.setTanggal(data_array[0]);
                        if(Double.parseDouble(data_array[header.length-1]) != 9999 && Double.parseDouble(data_array[header.length-1]) != 8888){
                          // enumerasi sebaris data
                          for(int i=1;i<data_array.length-1;i++){
                             unsur_cuaca.tambahAtribut(Double.parseDouble(data_array[i]));
                          }
                          unsur_cuaca.setKeadaan_cuaca(temp_data.getHasilDiskretisasiData(Double.parseDouble(data_array[header.length-1])));
                          temp_data.tambahUnsurCuaca(unsur_cuaca);
                        }           
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DataController.class.getName()).log(Level.SEVERE, null, ex);
                }   
           }else{
                    JOptionPane.showMessageDialog(null,"Jenis File Tidak Didukung","Duuh",JOptionPane.ERROR_MESSAGE);   
                }
        }
        return temp_data.getHasilPembersihanData();
    }
    
    
    private boolean validasiFormatFile(String nama_file){
        boolean valid = false;
        String format = nama_file.substring(nama_file.lastIndexOf(".")+1);
        if(format.equalsIgnoreCase("csv")){
            valid = true;
        }
        return valid;
    }  
}


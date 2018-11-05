
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;
import View.Home;
/**
 *
 * @author M.Hakim Amransyah
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.tampilkanHome(main);
    }
       
    private void tampilkanHome(Main main){
        Home home = new Home(this);
        home.setVisible(true);
    }
    
    public void tampilkanPanelPelatihan(){
       LatihController controller_pelatihan = new LatihController();
       controller_pelatihan.mulaiPanelPelatihan();
    }
    
    public void tampilkanPanelPengujian(){
        UjiController controller_pengujian = new UjiController();
        controller_pengujian.mulaiPanelPengujian();
    }

}

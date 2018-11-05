/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author M.Hakim Amransyah
 */
public class Database {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/prediksicuaca(2)";
// Database credentials
    private final String USER = "root";
    private final String PASS = "";
    private Connection conn = null;
    
    public void bukaKoneksi() {
        boolean flag = false;
        try {
                Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        
        if (!flag) {
        try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Connection getConn() {
        return conn;
    }
}

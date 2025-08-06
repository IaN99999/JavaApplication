/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author leocr
 */
public class connection {
    private static Connection mysqlconnect;
    public static Connection connectDB() throws SQLException{
        if(mysqlconnect == null){
            try{
                String DB = "jdbc:mysql://localhost:3306/test_masuya";  
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlconnect = (Connection)DriverManager.getConnection(DB,user,pass);
            }catch(Exception e) {
                
            }
        }return mysqlconnect;
    }
}

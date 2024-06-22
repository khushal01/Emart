/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emart.dbutil;


import java.sql.*;
;
import javax.swing.JOptionPane;

/**
 *
 * @author my family
 */
public class Dbconnection {
    
    private static Connection conn;
    
        static{
               try{
                   Class.forName("com.mysql.cj.jdbc.Driver");
                   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","grocery","grocery");
                   JOptionPane.showMessageDialog(null,"Connection opened successfully","Success",JOptionPane.INFORMATION_MESSAGE);
               }
               catch(ClassNotFoundException cfe){
                   JOptionPane.showMessageDialog(null, "Error in loading driver", "Driver Error", JOptionPane.ERROR_MESSAGE);
                   cfe.printStackTrace();
                   System.exit(1);
               }
               catch(SQLException sql){
                   JOptionPane.showMessageDialog(null, "Error in opening connection", "Error Message", JOptionPane.ERROR_MESSAGE);
                   sql.printStackTrace();
                   System.exit(1);
               }
               
              
        }
         public static Connection getConnection(){
                   return conn;
               }
         
         public static void closeConnection(){
             try{
                 conn.close();
                 JOptionPane.showMessageDialog(null,"Connection closed successfully","Success",JOptionPane.INFORMATION_MESSAGE);
             }
             catch(SQLException sql){
                   JOptionPane.showMessageDialog(null, "Error in closing connection", "Error Message", JOptionPane.ERROR_MESSAGE);
                   sql.printStackTrace();   
               }
           
         }
                 
    
}

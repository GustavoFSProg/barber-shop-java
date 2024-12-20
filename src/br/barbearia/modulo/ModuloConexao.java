/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.barbearia.modulo;
import java.sql.*;

/**
 *
 * @author oem
 */
public class ModuloConexao {
    
    
    public static Connection conector(){
    java.sql.Connection conexao = null;
    
    String driver = "com.mysql.jdbc.Driver";
    
    String url = "jdbc:mysql://localhost:3306/barbearia";
    
    String user = "root";
    
    String password = "";
    
    
    try{
    
    Class.forName(driver);
    
    conexao = DriverManager.getConnection(url,"root","");
    
               System.out.println("DEU CERTO");

            
            return conexao;

       }catch(Exception e){
           System.out.println("error::");
           System.out.println(e);

            return null;
    }
    
}
    
}

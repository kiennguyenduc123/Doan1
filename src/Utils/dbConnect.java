/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author Kiennguyen
 */

import java.sql.*;

public class dbConnect {
    public static Connection dbConnection() throws ClassNotFoundException, SQLException{
        String url = "jdbc:mysql://localhost:3306/doan1";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,user,password);
    } 
}

package com.gestion.gestionpatients.data;
import java.sql.*;
public class connectToDB {
    private static String Db_Url = "jdbc:mysql://localhost:3306/hopital?user=root&password=";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Db_Url);
        }catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }
}

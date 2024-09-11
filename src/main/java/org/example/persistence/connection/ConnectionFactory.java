package org.example.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

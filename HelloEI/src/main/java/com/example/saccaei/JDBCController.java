package com.example.saccaei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCController {
    String fichierSQL;
    Connection connection;

    public JDBCController(String fichierSQL) {
        this.fichierSQL = fichierSQL;
        this.connection = null;
    }

    public void getConnection() {
        //Load the driver class
        try {
            //Class.forName("org.sqlite.JDBC");
            //connection = DriverManager.getConnection("jdbc:sqlite:" + fichierSQL);
            connection = DriverManager.getConnection("jdbc:mysql:///guestbook?cloudSqlInstance=<sacca-331512:europe-west1:bdd-sacca>&user=<root>&password=<>", "root", "");
            //connection.setAutoCommit(false);
        } catch(Exception ex) {
            System.out.println("Impossible d'ouvrir la base de données!");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch(SQLException ex) {
            System.out.println("Erreur à la fermeture!");
        }
    }
}
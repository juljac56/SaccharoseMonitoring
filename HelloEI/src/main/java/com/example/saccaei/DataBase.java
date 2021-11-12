package com.example.saccaei;


import java.sql.*;

// Classe servant à se connecter à la BDD, grâce à JDBC

public class DataBase {
    public static Connection getConnection(){
        Connection conn = null;

        try{conn = DriverManager.getConnection("jdbc:mysql:///guestbook?cloudSqlInstance=<sacca-331512:europe-west1:bdd-sacca>&user=<root>&password=<>", "root", "");
            System.out.println("Connected !");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;}}

package com.example.saccaei;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BDDController extends JDBCController{

    public BDDController(String fichierSQL) {
        super(fichierSQL);
    }

    public Vector<Glycemie> getData(){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        this.getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery("SELECT* FROM Glycemie");
            while (rs.next()){
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getDate("date"), rs.getTime("time"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        }catch(SQLException exc){System.out.println(exc);}
        this.closeConnection();
        return data;
    }
}

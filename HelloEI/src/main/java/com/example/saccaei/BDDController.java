package com.example.saccaei;

import java.sql.PreparedStatement;
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
        try{
            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery("SELECT* FROM Glycemie ORDER BY date, heure ASC");
            while (rs.next()){
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        }catch(SQLException exc){System.out.println(exc);}
        this.closeConnection();
        return data;
    }

    public Vector<Glycemie> getDataDate(String date){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Glycemie WHERE date = ? ORDER BY date, heure ASC");
            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        }catch(SQLException exc){System.out.println(exc);}
        this.closeConnection();
        return data;
    }

    public Vector<Glycemie> getDataMonth(String dateDebut){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        this.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT id_glycemie, AVG(taux_glycemie) AS taux_glycemie, date, heure, id_patient FROM Glycemie WHERE date >= ? GROUP BY date ORDER BY date, heure ASC");
            statement.setString(1, dateDebut);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        }catch(SQLException exc){System.out.println(exc);}
        this.closeConnection();
        return data;
    }

}

package com.example.saccaei;

import java.sql.*;
import java.util.Vector;

public class BDDController extends JDBCController{

    public BDDController(String fichierSQL) {
        super(fichierSQL);
    }

    public Vector<Glycemie> getData(Integer patientID){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT* FROM Glycemie WHERE id_patient = ? ORDER BY date, heure ASC");
            statement.setInt(1, patientID);
            ResultSet rs =statement.executeQuery();
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

    public Vector<Glycemie> getDataDate(String date, Integer patientID){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Glycemie WHERE date = ? AND id_patient = ? ORDER BY date, heure ASC");
            statement.setString(1, date);
            statement.setInt(2, patientID);
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

    public String getNom(int id){
        String nom = "";
        String prenom = "";
        try{
            Connection conn = DataBase.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT nom,prenom FROM MEDECIN WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            nom = rs.getString(1);
            prenom = rs.getString(2);

            statement.close();
            connection.commit();
        }catch(SQLException exc){System.out.println(exc);}
        this.closeConnection();
        return prenom+" "+nom;
    }

    public Vector<Glycemie> getDataMonth(String dateDebut, Integer patientID){
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        this.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT id_glycemie, AVG(taux_glycemie) AS taux_glycemie, date, heure, id_patient FROM Glycemie WHERE date >= ? AND id_patient = ? GROUP BY date ORDER BY date, heure ASC");
            statement.setString(1, dateDebut);
            statement.setInt(2, patientID);
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

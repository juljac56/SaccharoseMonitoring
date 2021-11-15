package com.example.saccaei;

import java.sql.*;
import java.util.Vector;

public class BDDController extends JDBCController {

    public BDDController(String fichierSQL) {
        super(fichierSQL);
    }

    public Vector<Glycemie> getData(Integer patientID) {
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT* FROM Glycemie WHERE id_patient = ? ORDER BY date, heure ASC");
            statement.setInt(1, patientID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        } catch (SQLException exc) {
            System.out.println(exc);
        }
        this.closeConnection();
        return data;
    }

    public Vector<Glycemie> getDataDate(String date, Integer patientID) {
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Glycemie WHERE date = ? AND id_patient = ? ORDER BY date, heure ASC");
            statement.setString(1, date);
            statement.setInt(2, patientID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        } catch (SQLException exc) {
            System.out.println(exc);
        }
        this.closeConnection();
        return data;
    }

    public Vector<String>  getNom(int id) {
        String nom = "";
        String prenom = "";
        String limite = "";
        Vector<String> info = new Vector<String>();
        this.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT nom, prenom,limite FROM PATIENT WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                System.out.println("OKKKK");
            prenom = rs.getString(2);
            nom = rs.getString(1);
            limite =rs.getString(3);
            info.add(prenom);info.add(nom);info.add(limite);
            }

            statement.close();
            connection.commit();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.closeConnection();
        return info;
    }

    public Vector<Glycemie> getDataMonth(String dateDebut, Integer patientID) {
        this.getConnection();
        Vector<Glycemie> data = new Vector<Glycemie>();
        this.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Glycemie WHERE date >= ? AND id_patient = ? ORDER BY date, heure ASC");
            statement.setString(1, dateDebut);
            statement.setInt(2, patientID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Glycemie currentGlycemie = new Glycemie(rs.getInt("id_glycemie"), rs.getDouble("taux_glycemie"), rs.getString("date"), rs.getString("heure"));
                data.add(currentGlycemie);
            }
            statement.close();
            connection.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
        this.closeConnection();

        return data;
    }

    public boolean login(String user, String mdp) {
        this.getConnection();
        boolean bool= true;
        try {
            PreparedStatement ps = connection.prepareStatement("Select ID FROM MEDECIN WHERE username = ? AND mdp = ?");
            ps.setString(1, user);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
            } else {
                bool =false;
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        this.closeConnection();
        return bool;
    }

    public Double getLimite(int id){
        Double lim = Double.valueOf(100);

        this.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Select limite FROM PATIENT WHERE ID = ?");
            ps.setInt(1, id);
            System.out.println("erreur");
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
            System.out.println("get limit"+rs.getString(1));
            lim = Double.valueOf(rs.getString(1));}
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return lim;
    }

    public void modifierLimite(String newLimite) {
        this.getConnection();
        boolean bool= true;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PATIENT SET limite = ? WHERE ID = 1;");
            ps.setString(1, newLimite);
            int succes = ps.executeUpdate();
            if (succes ==1) {
                System.out.println("modif limite réussie");
            } else {
                System.out.println("modif limite non réussie");
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        this.closeConnection();
    }

}

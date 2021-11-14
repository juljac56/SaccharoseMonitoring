package com.example.saccaei;

public class Medecin {
    private Integer id_medecin;
    private String mail;
    private String nom;
    private String prenom;
    private String username;
    private String password;

    public Medecin(Integer id_medecin, String mail, String nom, String prenom, String username, String password){
        this.id_medecin = id_medecin;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
    }

    public Integer getId_medecin(){
        return this.id_medecin;
    }

    public String getMail(){
        return this.mail;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

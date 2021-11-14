package com.example.saccaei;

public class Patient {
    private Integer id_patient;
    private String nom;
    private String prenom;
    private Double limite;

    public Patient(Integer id_patient,String nom, String prenom, Double limite){
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.limite = limite;
    }

    public Integer getId_patient(){
        return this.id_patient;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public Double getLimite() { return this.limite;}

    public void setLimite(Double limite) { this.limite = limite;}
}

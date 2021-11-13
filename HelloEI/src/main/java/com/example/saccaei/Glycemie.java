package com.example.saccaei;

import java.util.Date;

public class Glycemie {
    private Integer id_glycemie;
    private Double taux_glycemie;
    private Date date;
    private java.sql.Time time;

    public Glycemie(Integer id_glycemie, Double taux_glycemie, Date date, java.sql.Time time){
        this.id_glycemie = id_glycemie;
        this.taux_glycemie = taux_glycemie;
        this.date = date;
        this.time = time;
    }

    public Integer getId_glycemie(){
        return this.id_glycemie;
    }

    public Double getTaux_glycemie(){
        return this.taux_glycemie;
    }

    public Date getDate(){
        return this.date;
    }

    public java.sql.Time getTime(){
        return this.time;
    }
}

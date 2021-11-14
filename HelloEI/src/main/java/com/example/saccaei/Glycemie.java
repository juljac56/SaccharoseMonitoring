package com.example.saccaei;

import java.util.Date;

public class Glycemie {
    private Integer id_glycemie;
    private Double taux_glycemie;
    private String date;
    private String time;

    public Glycemie(Integer id_glycemie, Double taux_glycemie, String date, String time){
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

    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }
}

package com.example.saccaei;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;


public class Linear implements Initializable {

    @FXML
    private LineChart lineChartDemo;

    public void initChart(LineChart lineChart) {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");

        //lineChartDemo
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);
    }

    public void fillChart(LineChart lineChart, Integer patientID){

        lineChart.setTitle("Glycémie");
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient 1");
        BDDController BDDGlycemie = new BDDController("C:\\CS\\2A\\ST5 Modèles de données\\EI\\BDD_Saccharose.db");
        Vector<Glycemie> data = BDDGlycemie.getData(patientID);
        Iterator<Glycemie> it = data.iterator();
        while(it.hasNext()){
            Glycemie currentGlycemie = it.next();
            System.out.println(currentGlycemie.getDate());
            series.getData().add(new XYChart.Data(currentGlycemie.getTime(), currentGlycemie.getTaux_glycemie()));
        }
        lineChart.getData().add(series);
    }

    public void fillChartDate(LineChart lineChart, String date, Integer patientID){
        lineChart.setTitle("Glycémie: " + date.toString());
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient" + patientID.toString());

        BDDController BDDGlycemie = new BDDController(("C:\\CS\\2A\\ST5 Modèles de données\\EI\\BDD_Saccharose.db"));
        Vector<Glycemie> data = BDDGlycemie.getDataDate(date, patientID);
        Iterator<Glycemie> it = data.iterator();
        while(it.hasNext()){
            Glycemie currentGlycemie = it.next();
            series.getData().add(new XYChart.Data(currentGlycemie.getTime().toString(), currentGlycemie.getTaux_glycemie()));
        }
        lineChart.getData().add(series);
    }

    public void fillChartMonth(LineChart lineChart, String dateDebut, Integer patientID){
        lineChart.setTitle("Evolution de la glycémie dépuis " + dateDebut);
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient" + patientID.toString());

        BDDController BDDGlycemie = new BDDController(("C:\\CS\\2A\\ST5 Modèles de données\\EI\\BDD_Saccharose.db"));
        Vector<Glycemie> allData = BDDGlycemie.getDataMonth(dateDebut, patientID);
        Vector<Glycemie> data = new Vector<Glycemie>();
        Iterator<Glycemie> it = allData.iterator();
        while(it.hasNext()){
            Glycemie currentGlycemie = it.next();
            series.getData().add(new XYChart.Data(currentGlycemie.getDate().toString(), currentGlycemie.getTaux_glycemie()));
        }
        lineChart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillChartMonth(lineChartDemo, "2021-11-01", 1);
    }
}
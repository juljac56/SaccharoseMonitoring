package com.example.saccaei;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;


public class Linear implements Initializable {

    public Stage stage;
    public Scene  scene;
    public Parent root;

    @FXML
    private LineChart lineChartDemo;

    @FXML
    private LineChart lineChartMois;

    @FXML
    TextField modifierLimite;

    @FXML
    Label alertLabel;

    @FXML
    TextField dateMain;

    @FXML
    TextField dateMonth;

    @FXML
    LineChart lineChartMonth;

    @FXML
    protected void fichePatient(ActionEvent event) {
        System.out.println("Tentative login");
        try {
            BDDController bdd = new BDDController("");
                Vector<String> infoFiche = bdd.getNom(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fiche.fxml"));
                root = loader.load();
                HelloController hc = loader.getController();
                hc.fichePatient(infoFiche.get(0),infoFiche.get(1), infoFiche.get(2));
            }
        catch (Exception e) {System.out.println(e);}
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    protected void goMois(ActionEvent event) {
        System.out.println("Tentative login");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mois.fxml"));
            root = loader.load();
            }
        catch (Exception e) {System.out.println(e);}
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    @FXML
    protected void goMain(ActionEvent event) {
        try {    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            root = loader.load();
        }
        catch (Exception e) {System.out.println(e);}
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshButton(ActionEvent event) {
        String date = dateMain.getText();
        fillChartDate(lineChartDemo, date, 1);
    }

    public void refreshMonth(ActionEvent event){
        String date = dateMonth.getText();
        fillChartMonth(lineChartMonth, date, 1);
    }


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

        lineChart.setTitle("Glyc??mie");
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient 1");
        BDDController BDDGlycemie = new BDDController("C:\\CS\\2A\\ST5 Mod??les de donn??es\\EI\\BDD_Saccharose.db");
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
        boolean depasse = false;
        lineChart.setTitle("Glyc??mie: " + date);
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient" + patientID.toString());

        BDDController BDDGlycemie = new BDDController(("C:\\CS\\2A\\ST5 Mod??les de donn??es\\EI\\BDD_Saccharose.db"));
        Double limite = BDDGlycemie.getLimite(1);
        Vector<Glycemie> data = BDDGlycemie.getDataDate(date, patientID);
        Iterator<Glycemie> it = data.iterator();
        System.out.println("month" +limite);
        while(it.hasNext()){
            Glycemie currentGlycemie = it.next();
            if (currentGlycemie.getTaux_glycemie() > limite){
                depasse = true;
                alertLabel.setText("Attention \n la limite du taux de glyc??mie \n a ??t?? d??pass??e ?? la date : " + currentGlycemie.getTime() );
            }
            series.getData().add(new XYChart.Data(currentGlycemie.getTime(), currentGlycemie.getTaux_glycemie()));
        }
        if (!depasse){alertLabel.setText("La limite \n du taux de glyc??mie \n n'a pas ??t?? d??pass??e \n sur la p??riode consid??r??e");}
        lineChart.getData().add(series);
    }

    public void fillChartMonth(LineChart lineChart, String dateDebut, Integer patientID){

        boolean depasse = false;
        lineChart.setTitle("Evolution de la glyc??mie d??puis " + dateDebut);
        XYChart.Series series = new XYChart.Series();
        series.setName("Patient" + patientID.toString());

        BDDController BDDGlycemie = new BDDController(("C:\\CS\\2A\\ST5 Mod??les de donn??es\\EI\\BDD_Saccharose.db"));
        Double limite = BDDGlycemie.getLimite(1);
        Vector<Glycemie> allData = BDDGlycemie.getDataMonth(dateDebut, patientID);
        Vector<Glycemie> data = new Vector<Glycemie>();
        Iterator<Glycemie> it = allData.iterator();
        System.out.println(limite);
        while(it.hasNext()){

            Glycemie currentGlycemie = it.next();
            if (currentGlycemie.getTaux_glycemie() > limite){
                depasse = true;
                alertLabel.setText("Attention \n la limite du taux de glyc??mie \n a ??t?? d??pass??e ?? la date : " + currentGlycemie.getTime() );
            }
            series.getData().add(new XYChart.Data(currentGlycemie.getDate()+" "+currentGlycemie.getTime(), currentGlycemie.getTaux_glycemie()));
        }
        if (!depasse){alertLabel.setText("La limite \n du taux de glyc??mie \n n'a pas ??t?? d??pass??e \n sur la p??riode consid??r??e");}
        lineChart.getData().add(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fillChartMonth(lineChartMois, "2021-11-01", 1);
        fillChartDate(lineChartDemo, "2021-11-1", 1);
    }
}
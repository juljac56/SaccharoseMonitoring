package com.example.saccaei;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class HelloController {
    public Stage stage;
    public Scene  scene;
    public Parent root;


    @FXML
    TextField username;

    @FXML
    TextField modifierLimite;
    @FXML
    PasswordField password;

    @FXML
    private Label prenomText;

    @FXML
    private Label nomText;

    @FXML
    private Label limiteText;

    @FXML
    protected void fichePatient( String prenom,String nom, String limite) {
        prenomText.setText(prenom);
        nomText.setText(nom);
        limiteText.setText(limite);
    }

    @FXML
    protected void loginOK(ActionEvent event) {
        System.out.println("Tentative login");
        try {
            String user = username.getText();
            String mdp = password.getText();
            BDDController bdd = new BDDController("");
            if (bdd.login(user,mdp)) {
                System.out.println("pas de connexion");
                root = FXMLLoader.load(getClass().getResource("login.fxml"));}
            else{
                System.out.println("authentification ok");
                Vector<String> infoFiche = bdd.getNom(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                root = loader.load();
                //root = FXMLLoader.load(getClass().getResource("fiche.fxml"));
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {System.out.println(e);}

    }

    @FXML
    protected void modifierLimite(ActionEvent event) {

        String newLimite = modifierLimite.getText();
        System.out.println("new limite"+ newLimite);
        if (newLimite.length() ==0){
            System.out.println("Rien a modifier");
        }
        else{
            BDDController bdd = new BDDController("");
            bdd.modifierLimite(newLimite);
            try {
                Vector<String> infoFiche = bdd.getNom(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fiche.fxml"));
                root = loader.load();
                HelloController hc = loader.getController();
                hc.fichePatient(infoFiche.get(0),infoFiche.get(1), infoFiche.get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}
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

    }

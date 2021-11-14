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

public class HelloController {
    public Stage stage;
    public Scene  scene;
    public Parent root;

    @FXML
    private Label welcomeText;

    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void loginOK(ActionEvent event) {
        System.out.println("Tentative login");
        try {

            Connection conn = DataBase.getConnection();
            String user = username.getText();
            String mdp = password.getText();

            // on cherche les id des auteurs du nouveau livre
            PreparedStatement ps = conn.prepareStatement("Select * FROM MEDECIN WHERE username = ? AND mdp = ?");
            ps.setString(1, user);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst() ) {
                System.out.println("pas de connexion");
                root = FXMLLoader.load(getClass().getResource("login.fxml"));}
            else{
                System.out.println("Connexion ok");
                root = FXMLLoader.load(getClass().getResource("linear.fxml"));
            }

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {System.out.println(e);}

    }
}
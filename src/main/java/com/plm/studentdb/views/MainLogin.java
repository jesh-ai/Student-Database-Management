package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainLogin {
    @FXML private AnchorPane anpMainLogin;
    @FXML private Label loginStatus;
    @FXML private TextField userID;
    @FXML private PasswordField userPassword;

    private final String predefinedUsername = "admin@plm.edu.ph";
    private final String predefinedPassword = "123456";

    public Parent mainView;
    public Pane pneBackgroundFade;


    @FXML public void initialize() {
        userID.requestFocus();
    }

    @FXML
    public void logInButton() {
        String enteredUsername = userID.getText();
        String enteredPassword = userPassword.getText();

        if (predefinedUsername.equals(enteredUsername) && predefinedPassword.equals(enteredPassword) || true) {
            loginStatus.setText("");
            mainView.toFront();
            anpMainLogin.toBack();
            pneBackgroundFade.setRotate(0);
        } else {
            loginStatus.setText("Invalid Username or Password!");
        }
    }

}
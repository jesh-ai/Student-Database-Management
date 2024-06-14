package com.plm.studentdb.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainLogin {
    @FXML private AnchorPane anpMainLogin;
    @FXML private TextField userID;
    @FXML private PasswordField userPassword;
    @FXML public Button logInButton;

    private final String predefinedUsername = "admin@plmcs.edu.ph"; //old: admin@plm.edu.ph
    private final String predefinedPassword = "PLMCS@admin2024"; //old: 123456

    public Parent mainView;
    public Pane pneBackgroundFade;

    @FXML public void initialize() {
        userID.requestFocus();
    }

    @FXML
    public void login() {
        String enteredUsername = userID.getText();
        String enteredPassword = userPassword.getText();

        if (predefinedUsername.equals(enteredUsername) && predefinedPassword.equals(enteredPassword) || true) {
            mainView.toFront();
            anpMainLogin.toBack();
            userID.setText(null);
            userPassword.setText(null);
            Dialogs.mainMessageDialog.close();
        } else {
            Dialogs.mainMessageDialog.show("Invalid Credentials", "Please check your username and password and try again.");
        }
    }
}
package tn.esprit.edu.controllers;

import tn.esprit.edu.animation.FadeInLeftTransition;
import tn.esprit.edu.animation.FadeInLeftTransition1;
import tn.esprit.edu.animation.FadeInRightTransition;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.edu.services.UserService;


public class Login implements Initializable {


    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnregister;
    @FXML

    private Label lblClose;
    Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            new FadeInRightTransition(btnregister).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });

    }

    @FXML
    public void LoginButton() throws IOException, SQLException {
        String styledefault = "-fx-border-color: green;";
        txtUsername.setStyle(styledefault);
        txtPassword.setStyle(styledefault);
        UserService us = new UserService();
        if(us.login(txtUsername.getText(),txtPassword.getText())==1){
            tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Menu.fxml"))));

        }

    }

    @FXML
    public void RegisterButton() throws IOException {
        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/"))));
    }

    public void setTxtUsername(String txtUsername) {
        this.txtUsername.setText(txtUsername);
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword.setText(txtPassword);
    }

    public Button getBtnLogin() {
        return btnLogin;
    }


}

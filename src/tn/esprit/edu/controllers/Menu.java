package tn.esprit.edu.controllers;

import javafx.util.Duration; 
import tn.esprit.edu.animation.FadeInLeftTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.esprit.edu.technique.StaticAccount;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class Menu implements Initializable {

    @FXML
    private ImageView LBimguser;
    @FXML
    private Label lblClose;
    @FXML
    private Button LBusers;
    @FXML
    private Button LBstatistique;
    @FXML
    private Button LBreclamation;
    @FXML
    private Button LBadvertising;
    @FXML
    private Button Lforum;
    @FXML
    private Text lblWelcome11;
    @FXML
    private Text lblWelcome111;
    @FXML
    private Text lblWelcome112;
    @FXML
    private Text lblWelcome113;
    @FXML
    private Label lbTitre;
    @FXML
    private Label LBnomuser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TrayNotification tr =new TrayNotification();
        tr.setAnimationType(AnimationType.POPUP);
        tr.setTitle("Zanimaux");
        tr.setNotificationType(NotificationType.SUCCESS);
        tr.setMessage("Bienvenue dans Zanimaux application");
        tr.showAndDismiss(Duration.seconds(1));
        LBnomuser.setText(StaticAccount.user.getPrenomUser());
        new FadeInLeftTransition(LBstatistique).play();
        new FadeInLeftTransition(LBusers).play();
        new FadeInLeftTransition(LBreclamation).play();
        new FadeInLeftTransition(LBadvertising).play();
        new FadeInLeftTransition(LBnomuser).play();
        new FadeInLeftTransition(lbTitre).play();
        new FadeInLeftTransition(LBimguser).play();
        new FadeInLeftTransition(Lforum).play();

        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });


    }


    @FXML
    private void ButtonUserAction(MouseEvent event) throws IOException {
       //n.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/reclamationAd.fxml"))));
    }
    @FXML
    private void ButtonForumAction(MouseEvent event) throws IOException {
       //n.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/reclamation.fxml"))));
    }
    @FXML
    private void ButtonStatistiqueAction(MouseEvent event) throws IOException {
        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Annonce.fxml"))));
    }

    @FXML
    private void ButtonReclamationAction(MouseEvent event) throws IOException {
       tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Produit.fxml"))));
    }

    @FXML
    private void ButtonPubAction(MouseEvent event) throws IOException {
       //n.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/reclamation.fxml"))));
    }

}
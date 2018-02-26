package tn.esprit.edu.controllers;
import tn.esprit.edu.animation.FadeInLeftTransition;
import tn.esprit.edu.animation.FadeInRightTransition;
import tn.esprit.edu.animation.FadeInTransition;
import tn.esprit.edu.animation.FadeInUpTransition;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tn.esprit.edu.technique.DataSource;

public class Splash implements Initializable {
    private static Splash instance;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    @FXML
    private Text Labelmessage;

    DataSource base;
    Stage stage;
    @FXML
    private ImageView imgLoading;

    public void closeSplash() {
        Platform.exit();
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            longStart();
        } catch (InterruptedException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            closeSplash();
        });

    }
    void longStart() throws InterruptedException, IOException {

        new FadeInLeftTransition(lblWelcome).play();
        new FadeInRightTransition(lblRudy).play();
        new FadeInTransition(vboxBottom).play();
        new FadeInTransition(Labelmessage).play();
        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        PauseTransition delay2 = new PauseTransition(Duration.seconds(4));
        if (!base.getInstance().databaseexist()) {
            delay2.setOnFinished(event -> Labelmessage.setText("Base de donnees introuvable"));
            delay.setOnFinished(event -> tn.esprit.edu.test.Template.getInstance().getStage().close());
        } else {
            delay2.setOnFinished(event -> Labelmessage.setText("connection etablie"));
            delay.setOnFinished(event -> {
                try {
                    tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Login.fxml"))));
                } catch (IOException ex) {
                    Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }



        delay.play();
        delay2.play();
    }

    public void messageInformation(String msg) {
        Labelmessage.setText(msg);
    }

    public static Splash getInstance() {
        if (instance == null) {
            instance = new Splash();
        }
        return instance;
    }
}


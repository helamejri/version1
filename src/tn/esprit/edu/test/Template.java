package tn.esprit.edu.test;

import java.io.IOException; 

import javax.mail.MessagingException;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.services.AnnonceService;
import tn.esprit.edu.services.CommentaireService;
import tn.esprit.edu.services.Liketableservice;
import tn.esprit.edu.services.Mailing;
import tn.esprit.edu.technique.StaticAccount;

public class Template extends Application {
    private Stage stage;
    private static Template instance;
    private Scene scene;


    public Template() throws IOException, InterruptedException {
        instance = this;
        
        
		new AnnonceService().supprimer30jrs();
    scene = new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Splash.fxml")));
     //scene = new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Annonce.fxml")));


    }


    public static Template getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
       // stage = new Stage();
        this.stage = stage;
        stage.setScene(this.scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    public void changescene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/tn/esprit/edu/Ressources/dog.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

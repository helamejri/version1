package tn.esprit.edu.controllers;

import java.io.IOException; 
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.edu.models.Categorie;
import tn.esprit.edu.models.StatModel;
import tn.esprit.edu.services.CategorieService;
import tn.esprit.edu.services.ProduitService;
import tn.esprit.edu.technique.StaticAccount;

public class StatController implements Initializable {
    @FXML
    private ListView<?> listMenuOfGestion;

    @FXML
    private Button btnBack;

    @FXML
    private Text userconnecte;

    @FXML
    private ImageView LBimguser;

    @FXML
    private ImageView imgLoad;

    @FXML
    private BarChart<String, Number> stats;

    @FXML
    NumberAxis xAxis;
    @FXML
    CategoryAxis yAxis;

    @FXML
    private Label lblClose;

    @FXML
    void back(ActionEvent event) throws IOException {

        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Produit.fxml"))));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userconnecte.setText(StaticAccount.user.getPrenomUser());
        lblClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
                System.out.println(0);
            }
        });
        stats.setTitle("");
        xAxis.setLabel("Nombre");
        xAxis.setAnimated(true);
        xAxis.setAutoRanging(true);
        yAxis.setLabel("Mois");
        yAxis.setAnimated(true);
        yAxis.setAutoRanging(true);
        List<Categorie> listeCat = new CategorieService().findAll();
        for (int i = 0; i < listeCat.size(); i++) {
            Categorie cat = listeCat.get(i);
            List<StatModel> liste = new ProduitService().listProduitMois(cat.getIdCategorie());
            XYChart.Series series1 = new XYChart.Series();
            series1.setName(cat.getNomCategorie());
            for (int k = 0; k < liste.size(); k++) {
                series1.getData().add(new XYChart.Data(liste.get(k).getLibmois(), liste.get(k).getIdCatCount()));
            }
            stats.getData().addAll(series1);
        }

    }
}

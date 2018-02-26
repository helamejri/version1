package tn.esprit.edu.controllers;

import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PanierController implements Initializable {
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
    private AnchorPane modif;

    @FXML
    private Button supprimer;

    @FXML
    private Label details;

    @FXML
    private ImageView image;

    @FXML
    private AnchorPane paneadduser1;

    @FXML
    private TableView<?> tableprod;

    @FXML
    private TableColumn<?, ?> idProduiti;

    @FXML
    private TableColumn<?, ?> vendeur;

    @FXML
    private TableColumn<?, ?> statut;

    @FXML
    private TableColumn<?, ?> dateajout;

    @FXML
    private TableColumn<?, ?> categoriecolumn;

    @FXML
    private TableColumn<?, ?> descriptioncolumn;

    @FXML
    private ComboBox<?> selecttri;

    @FXML
    private Label lblClose;

    @FXML
    private ProgressBar bar;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void triproduit(ActionEvent event) {

    }

    @FXML
    void suppr(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

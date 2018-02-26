
package tn.esprit.edu.controllers;

import javafx.application.Platform; 
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.esprit.edu.models.Categorie;
import tn.esprit.edu.models.Produit;
import tn.esprit.edu.services.CategorieService;
import tn.esprit.edu.services.PanierService;
import tn.esprit.edu.services.ProduitService;
import tn.esprit.edu.technique.StaticAccount;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

    @FXML
    private ListView<?> listMenuOfGestion;

    @FXML
    private Button btnBack;

    @FXML
    private Button panier;

    @FXML
    private Text userconnecte;

    @FXML
    private ImageView LBimguser;

    @FXML
    private ImageView imgLoad;

    @FXML
    private AnchorPane modif;

    @FXML
    private ImageView image;

    @FXML
    private Button upload;

    @FXML
    private Button stat;

    @FXML
    private Button update;

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private AnchorPane paneadduser1;

    @FXML
    private TableView<Produit> tableprod;

    private ObservableList<Produit> produits = FXCollections.observableArrayList();

    private Produit p;
    @FXML
    private TableColumn<Produit, String> idProduiti;

    @FXML
    private TextField choice;

    @FXML
    private Label affilabel;

    @FXML
    private TextField libelle;

    @FXML
    private TextArea description;

    @FXML
    private ComboBox<Categorie> selecttri;

    @FXML
    private TextField prix;

    @FXML
    private ComboBox<Categorie> categorie;

    @FXML
    private ComboBox<String> etat;

    @FXML
    private Label lblClose;

    @FXML
    private ProgressBar bar;

    @FXML
    private Button shopbutton;

    @FXML
    private TextField quantity;

    public ListView<?> getListMenuOfGestion() {
        return listMenuOfGestion;
    }

    public void setListMenuOfGestion(ListView<?> listMenuOfGestion) {
        this.listMenuOfGestion = listMenuOfGestion;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(Button btnBack) {
        this.btnBack = btnBack;
    }

    public Text getUserconnecte() {
        return userconnecte;
    }

    public void setUserconnecte(Text userconnecte) {
        this.userconnecte = userconnecte;
    }

    public ImageView getLBimguser() {
        return LBimguser;
    }

    public void setLBimguser(ImageView LBimguser) {
        this.LBimguser = LBimguser;
    }

    public ImageView getImgLoad() {
        return imgLoad;
    }

    public void setImgLoad(ImageView imgLoad) {
        this.imgLoad = imgLoad;
    }

    public AnchorPane getModif() {
        return modif;
    }

    public void setModif(AnchorPane modif) {
        this.modif = modif;
    }

    public Button getStat() {
        return stat;
    }

    public void setStat(Button stat) {
        this.stat = stat;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }

    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public AnchorPane getPaneadduser1() {
        return paneadduser1;
    }

    public void setPaneadduser1(AnchorPane paneadduser1) {
        this.paneadduser1 = paneadduser1;
    }

    public TableView<Produit> getTableRec() {
        return tableprod;
    }

    public void setTableRec(TableView<Produit> tableRec) {
        this.tableprod = tableRec;
    }

    public ObservableList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ObservableList<Produit> produits) {
        this.produits = produits;
    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public TextField getChoice() {
        return choice;
    }

    public void setChoice(TextField choice) {
        this.choice = choice;
    }

    public Label getAffilabel() {
        return affilabel;
    }

    public void setAffilabel(Label affilabel) {
        this.affilabel = affilabel;
    }

    public TextField getLibelle() {
        return libelle;
    }

    public void setLibelle(TextField libelle) {
        this.libelle = libelle;
    }

    public TextArea getDescription() {
        return description;
    }

    public void setDescription(TextArea description) {
        this.description = description;
    }

    public ComboBox<Categorie> getSelecttri() {
        return selecttri;
    }

    public void setSelecttri(ComboBox<Categorie> selecttri) {
        this.selecttri = selecttri;
    }

    public TextField getPrix() {
        return prix;
    }

    public void setPrix(TextField prix) {
        this.prix = prix;
    }

    public ComboBox<Categorie> getCategorie() {
        return categorie;
    }

    public void setCategorie(ComboBox<Categorie> categorie) {
        this.categorie = categorie;
    }

    public ComboBox<String> getEtat() {
        return etat;
    }

    public void setEtat(ComboBox<String> etat) {
        this.etat = etat;
    }

    public Label getLblClose() {
        return lblClose;
    }

    public void setLblClose(Label lblClose) {
        this.lblClose = lblClose;
    }

    public ProgressBar getBar() {
        return bar;
    }

    public void setBar(ProgressBar bar) {
        this.bar = bar;
    }


    @Override
    public void initialize(URL location, ResourceBundle rb) {

        List<Produit> listproduits;
        ProduitService ps = new ProduitService();
        System.out.println(ps.findAll().size());
        listproduits = ps.findAll();
        // listproduits.forEach(System.out::println);
        produits.clear();
        produits.addAll(listproduits);
        tableprod.setItems(produits);



        //idProduiti.setCellValueFactory(new PropertyValueFactory<Produit,String>("libelleProduit"));
        produits.forEach(System.out::println);
        lblClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
                System.out.println(0);
            }
        });

        //tableRec.getSelectionModel().selectedItemProperty().addListener((observable,oldValue, newValue)->afficheDetail(newValue));

        //tableRec.setItems(produits);
        //tableRec.getItems().forEach(System.out::println);

        ObservableList<String> cursors = FXCollections.observableArrayList("Vente", "Echange", "Autre");
        etat.setItems(cursors);
        CategorieService cs = new CategorieService();
        ObservableList<Categorie> data = FXCollections.observableArrayList(cs.findAll());
        categorie.getItems().clear();
        categorie.setItems(data);
        selecttri.getItems().clear();
        selecttri.setItems(data);
        quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        userconnecte.setText(StaticAccount.user.getPrenomUser());
        tableprod.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1 && tableprod.getSelectionModel().getSelectedItem().getUser().getIdUser() == StaticAccount.user.getIdUser()) {
                    libelle.setText(tableprod.getSelectionModel().getSelectedItem().getLibelleProduit());
                    description.setText(tableprod.getSelectionModel().getSelectedItem().getDescriptionProduit());
                    prix.setText(String.valueOf(tableprod.getSelectionModel().getSelectedItem().getPrixProduit()));

                    supprimer.setDisable(false);
                    modifier.setDisable(false);
                    panier.setDisable(true);
                }
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1 && tableprod.getSelectionModel().getSelectedItem().getUser().getIdUser() != StaticAccount.user.getIdUser() && tableprod.getSelectionModel().getSelectedItem().getEtatProduit().trim().equals("Vente")) {
                    panier.setDisable(false);

                } else
                    panier.setDisable(true);
            }
        });

    }

    public boolean verifier() {
        if (libelle.getText().isEmpty()) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Zanimaux");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir le champs libelle");
            tr.showAndDismiss(Duration.seconds(1));
        } else if (description.getText().isEmpty()) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Zanimaux");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir le champs description");
            tr.showAndDismiss(Duration.seconds(1));
        } else if (prix.getText().isEmpty()) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Zanimaux");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez remplir le champs prix");
            tr.showAndDismiss(Duration.seconds(1));
        } else if (categorie.getSelectionModel().getSelectedIndex() == -1) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Zanimaux");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez selectionner une categorie");
            tr.showAndDismiss(Duration.seconds(1));
        } else if (etat.getSelectionModel().getSelectedIndex() == -1) {
            TrayNotification tr = new TrayNotification();
            tr.setAnimationType(AnimationType.POPUP);
            tr.setTitle("Zanimaux");
            tr.setNotificationType(NotificationType.ERROR);
            tr.setMessage("Veuillez selectionner un etat ");
            tr.showAndDismiss(Duration.seconds(1));
        }
        return true;
    }


    @FXML
    void back(ActionEvent event) throws IOException {

        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Menu.fxml"))));

    }

    @FXML
    void cherche(KeyEvent event) {

    }

    @FXML
    void update(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Acune Selection");
            alert.setHeaderText("Aucun Produit");
            alert.setContentText("Veuillez Selectionner un Produit !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Modification Produit");
            alert.setContentText("Veuillez Confirmer la modification");
            alert.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    ProduitService ps = new ProduitService();
                    p.setEtatProduit(etat.getSelectionModel().getSelectedItem());
                    p.setDescriptionProduit(description.getText());
                    p.setPrixProduit(Float.valueOf(prix.getText()));
                    p.setCategorie(categorie.getSelectionModel().getSelectedItem());
                    p.setLibelleProduit(libelle.getText());
                    ps.update(p);
                    List<Produit> listproduits;
                    listproduits = ps.findAll();
                    produits.clear();
                    produits.addAll(listproduits);
                    tableprod.setItems(produits);
                }
            });
        }

    }

    @FXML
    void fonctiontriproduit(ActionEvent event) {
        if (selecttri.getSelectionModel().getSelectedIndex() == -1) {
            List<Produit> listproduits;
            ProduitService ps = new ProduitService();
            System.out.println(ps.findAll().size());
            listproduits = ps.findAll();
            // listproduits.forEach(System.out::println);
            produits.clear();
            produits.addAll(listproduits);
            tableprod.setItems(produits);
        } else {
            int i = selecttri.getSelectionModel().getSelectedItem().getIdCategorie();
            ProduitService ps = new ProduitService();
            List<Produit> lst;
            lst = ps.findProduitByCategorie(i);
            produits.clear();
            produits.addAll(lst);
            tableprod.setItems(produits);
        }
    }

    @FXML
    void statistique(ActionEvent event) throws IOException {
        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Stat.fxml"))));


    }

    @FXML
    void suppr(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Acune Selection");
            alert.setHeaderText("Aucun Produit");
            alert.setContentText("Veuillez Selectionner un Produit");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression Produit");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    ProduitService ps = new ProduitService();
                    ps.remove(p);
                    List<Produit> listproduits;
                    listproduits = ps.findAll();
                    produits.clear();
                    produits.addAll(listproduits);
                    tableprod.setItems(produits);
                }
            });
        }


    }

    @FXML
    void add(ActionEvent event) {
        if (verifier()) {
            p = new Produit();
            ProduitService ps = new ProduitService();
            p.setUser(StaticAccount.user);
            p.setLibelleProduit(libelle.getText());
            p.setCategorie(categorie.getSelectionModel().getSelectedItem());
            p.setPrixProduit(Float.valueOf(prix.getText()));
            p.setDescriptionProduit(description.getText());
            p.setEtatProduit(etat.getSelectionModel().getSelectedItem());
            ps.add(p);
            List<Produit> listproduits;
            listproduits = ps.findAll();
            produits.clear();
            produits.addAll(listproduits);
            tableprod.setItems(produits);


        }

    }

    @FXML
    void addtopanier(ActionEvent event) {
        Produit p = tableprod.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Acune Selection");
            alert.setHeaderText("Aucun Produit");
            alert.setContentText("Veuillez Selectionner un Produit pour l'ajouter a votre panier");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tn.esprit.edu.test.Template.getInstance().getStage());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Achat Produit");
            alert.setContentText("Veuillez Confirmer l'ajout à votre panier");
            alert.showAndWait().ifPresent(response ->
            {
                if (response == ButtonType.OK) {
                    PanierService ps = new PanierService();
                    ps.addProduittopanier(p);
                    TrayNotification tr = new TrayNotification();
                    tr.setAnimationType(AnimationType.SLIDE);
                    tr.setTitle("Zanimaux");
                    tr.setNotificationType(NotificationType.SUCCESS);
                    tr.setMessage("Ajout avec succes à votre panier :)");
                    tr.showAndDismiss(Duration.seconds(0.4f));
                }
            });
        }

    }

    @FXML
    void gotoshop(ActionEvent event) throws IOException {
        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Panier.fxml"))));

    }


}

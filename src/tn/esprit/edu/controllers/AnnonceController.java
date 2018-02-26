package tn.esprit.edu.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL; 
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.models.Commentaire;
import tn.esprit.edu.models.Reponse;
import tn.esprit.edu.models.Type;
import tn.esprit.edu.services.AnnonceService;
import tn.esprit.edu.services.CommentaireService;
import tn.esprit.edu.services.Mailing;
import tn.esprit.edu.services.ReponseService;
import tn.esprit.edu.technique.StaticAccount;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AnnonceController implements Initializable {
	File photo;
	@FXML
	private ListView<Pane> list;
	@FXML
	TextArea AnnonceText = new TextArea();
	@FXML
	TextField AnnonceName = new TextField();
	public AnnonceService As = new AnnonceService();
	public CommentaireService Cs = new CommentaireService();
	public ReponseService Rs = new ReponseService();
	
	private ObservableList<Pane> data = FXCollections.observableArrayList();
	@FXML
	ListView<String> TypeList = new ListView<>();
	ObservableList<String> items = FXCollections.observableArrayList("Perdu", "Trouver");
	@FXML
	AnchorPane anchorPane = new AnchorPane();
	@FXML
	JFXButton btnpublier = new JFXButton();
	@FXML
	ImageView imgview= new ImageView();
	@FXML
	JFXButton btnupload = new JFXButton();
	Stage stage;
	AnnonceControllerMethodes asm = new AnnonceControllerMethodes();
    Mailing mail = new Mailing();


	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TypeList.setItems(items);
		AnnonceName.setPromptText("Annonce Name");
		AnnonceText.setPromptText("Annonce Text");
		List<Annonce> lstann = As.listerAnnonce();
		lstann.forEach(a -> {

			Pane p = new Pane();
			p = createPaneAnnonce(a );
			data.add(p);
		});
		list.setItems(data);

		btnpublier.setOnAction((ActionEvent e) -> {
			Annonce annonce = new Annonce();
			if (AnnonceName.getText().trim().isEmpty() 
					) {

				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir le nom du l'annonce ");
				tr.showAndDismiss(Duration.seconds(1));

			} else if (AnnonceText.getText().trim().isEmpty()){
				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir le text du l'annonce ");
				tr.showAndDismiss(Duration.seconds(1));
			}else if (TypeList.getSelectionModel().isEmpty()) {
				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez choisir un type de l'annonce  ");
				tr.showAndDismiss(Duration.seconds(1));
			}else if (photo==null) {
					TrayNotification tr = new TrayNotification();
					tr.setAnimationType(AnimationType.POPUP);
					tr.setTitle("Zanimaux");
					tr.setNotificationType(NotificationType.ERROR);
					tr.setMessage("Veuillez choisir une photo pour l'annonce  ");
					tr.showAndDismiss(Duration.seconds(1));

			
			} else {
				annonce.setName(AnnonceName.getText());
				annonce.setText(AnnonceText.getText());
				annonce.setIduser(StaticAccount.user.getIdUser());
				
				if (TypeList.getSelectionModel().getSelectedItem().equals("Perdu")) {
					annonce.setType(Type.Perdu);
				} else {
					annonce.setType(Type.Trouver);

				}
				
				new AnnonceService().ajouterAnnonce(annonce);
				annonce.setId(new AnnonceService().getlastid());
				
				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.INFORMATION);
				tr.setMessage("Produit Ajouteer ");
				tr.showAndDismiss(Duration.seconds(1));
				data.add(createPaneAnnonce(annonce));
				list.setItems(data);
				mail.envoye("hela.mejri@esprit.tn", "annonce ajoute", "annonce ajoute");

			}
		});

	}

	public Pane createPaneAnnonce(Annonce a) {
		Pane fin = new Pane();
		AnnonceService ls = new AnnonceService();
		ObservableList<Pane> datacommentaire = FXCollections.observableArrayList();
		ListView<Pane> listcommentaire =new ListView<>();
		// *******Contenu Label*******
		Label AnnonceName = new Label();
		Label AnnonceText = new Label();
		
		AnnonceName.setText(a.getName());
		AnnonceText.setText(a.getText());
		AnnonceName.setWrapText(true);
		TextArea txt = new TextArea();
		txt.setPrefSize(100, 100);
		txt.setMinWidth(100);
		VBox VboxPane = new VBox();
		VBox VboxNamelText = new VBox();
		HBox HboxName = new HBox();
		HBox HboxTrouverPerdu = new HBox();
		HboxTrouverPerdu.getChildren().add(new Label("Type : "+a.getType().toString()));
		HBox HboxText = new HBox();
		HboxName.getChildren().add(new Label("Name Annonce : "));
		HboxName.getChildren().add(AnnonceName);
		HboxText.getChildren().add(new Label("Text Annonce : "));
		HboxText.getChildren().add(AnnonceText);
		VboxNamelText.getChildren().addAll(HboxName, HboxText, HboxTrouverPerdu);
		HBox HboxButtons = new HBox();
		JFXButton like = new JFXButton("like."+ls.getlikecount(a));		
		like.setOnAction((ActionEvent e) -> {
			ls.ajouterlike(a);
			like.setText("like."+ls.getlikecount(a));
		//	mail.envoye("jamel.mustapha94@gmail.com", "l'annonce "+a.getId()+" a �t� comment�", "commentaitre ajoute");
		});
		
		
		JFXButton dislike = new JFXButton("dislike."+ls.getdislikecount(a));
		dislike.setOnAction((ActionEvent e) -> {
			ls.ajouterdislike(a);
			dislike.setText("dislike."+ls.getdislikecount(a));
		});
		/* kamel ilike idslike et signaler */ 
		
		JFXButton signaler = new JFXButton("signaler."+ls.getsignalercount(a));		
		signaler.setOnAction((ActionEvent e) -> {
			ls.ajoutersignaler(a);
			signaler.setText("signaler."+ls.getsignalercount(a));
			if (ls.getsignalercount(a) == 10 ) {
				new AnnonceService().supprimerAnnonce(a);
				data.remove(fin);
				list.setItems(data);
			}
		});
		JFXButton supprimer = new JFXButton("supprimer");
		supprimer.setOnAction((ActionEvent e) -> {

			new AnnonceService().supprimerAnnonce(a);
			data.remove(fin);

			list.setItems(data);
		});

		HboxButtons.getChildren().addAll(like, dislike, signaler);
		
		if (a.getIduser() == StaticAccount.user.getIdUser()) {
			HboxButtons.getChildren().add(supprimer);
		}
		// lister les commentaires
		
		List<Commentaire> lstcomm = Cs.listerCommentaireAnnonce(a);
		JFXButton btncommenter = new JFXButton("commenter");

		lstcomm.forEach(c -> {

			Pane p = new Pane();
			p = createPaneCommentaire(c,listcommentaire,datacommentaire);
			datacommentaire.add(p);
			
		});
		
		listcommentaire.setItems(datacommentaire);
		
		VboxPane.getChildren().add(VboxNamelText);
		VboxPane.getChildren().add(HboxButtons);
		TextArea txtcommenter= new TextArea();
		txtcommenter.setPromptText("commenter");
		txtcommenter.setPrefSize(450, 100);
		listcommentaire.setPrefHeight(300);

		VboxPane.getChildren().add(listcommentaire);
		HBox commentairebox = new HBox();
		commentairebox.getChildren().addAll(txtcommenter,btncommenter);
		VboxPane.getChildren().add(commentairebox);
		
				
		
		
		

		fin.getChildren().add(VboxPane);

		btncommenter.setOnAction((ActionEvent e) -> {
			if (txtcommenter.getText().isEmpty()) {
				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir un commentaire ");
				tr.showAndDismiss(Duration.seconds(1));

			}else {
			Commentaire nvcommentaire= new Commentaire(txtcommenter.getText());
			
			nvcommentaire.setIdAnnonce(a.getId());
			
			listcommentaire.setItems(datacommentaire);
			Cs.ajouterCommentaire(nvcommentaire);
			nvcommentaire.setId(new CommentaireService().getlastid());

			datacommentaire.add(createPaneCommentaire(nvcommentaire,listcommentaire,datacommentaire));

			txtcommenter.setText("");
			}
			});
		
		fin.setPrefSize(500, 500);
		return fin;
	}	
	public Pane createPaneCommentaire(Commentaire c,ListView<Pane> listcommentaire ,ObservableList<Pane> datacommentaire) {
		
		System.out.println(c.getId()+" id from create pane");
		ObservableList<Pane> datareponse = FXCollections.observableArrayList();
		ListView<Pane> listreponse =new ListView<>();
		Pane paneCommentaire = new Pane();
		HBox Hboxcommentaire = new HBox();
		HBox Hboxbutton = new HBox();
		VBox VboxCommentaire = new VBox();
		
		JFXButton btnlike= new JFXButton("like."+Cs.getlikecount(c));
		JFXButton btndislike= new JFXButton("dislike."+Cs.getdislikecount(c));
		JFXButton btnsignaler= new JFXButton("signaler."+Cs.getsignalercount(c));
		JFXButton btnsupprimer= new JFXButton("supprimer");

		btnsignaler.setOnAction((ActionEvent e) -> {

			Cs.ajoutersignaler(c);
			btnsignaler.setText("signaler."+Cs.getsignalercount(c));
			if (Cs.getsignalercount(c) == 10 ) {
				new CommentaireService().supprimerCommentaire(c);
				datacommentaire.remove(paneCommentaire);

				listcommentaire.setItems(datacommentaire);
			}
		});
		btnsupprimer.setOnAction((ActionEvent e) -> {

			new CommentaireService().supprimerCommentaire(c);
			datacommentaire.remove(paneCommentaire);
			listcommentaire.setItems(datacommentaire);

		});
		btndislike.setOnAction((ActionEvent e) -> {

			Cs.ajouterdislike(c);
			btndislike.setText("dislike."+Cs.getdislikecount(c));

		});
		btnlike.setOnAction((ActionEvent e) -> {

			Cs.ajouterlike(c);
			btnlike.setText("like."+Cs.getlikecount(c));

		});
		
		Hboxcommentaire.getChildren().addAll(new Label("commentaire text :"), new Label(c.gettextCommentaire()));
		Hboxbutton.getChildren().addAll(btnlike,btndislike,btnsignaler,btnsupprimer);
		
		
		VboxCommentaire.getChildren().add(Hboxcommentaire);
		VboxCommentaire.getChildren().add(Hboxbutton);
		TextField txtreponse = new TextField();
		txtreponse.setPrefSize(100, 20);
		txtreponse.setPromptText("repondre");
		
		VBox Vboxrepondre = new VBox();
		HBox Hboxrepondre = new HBox();
		
		JFXButton btnrepondre = new JFXButton("repondre");
		
		

		listreponse.setPrefSize(250, 100);
		List<Reponse> lstrep = new ReponseService().listerReponseCommentaire(c);
		btnrepondre.setOnAction((ActionEvent e) -> {
		if (txtreponse.getText().isEmpty()) {
			TrayNotification tr = new TrayNotification();
			tr.setAnimationType(AnimationType.POPUP);
			tr.setTitle("Zanimaux");
			tr.setNotificationType(NotificationType.ERROR);
			tr.setMessage("Veuillez remplir une reponse");
			tr.showAndDismiss(Duration.seconds(1));

		}else {
			mail.envoye("hela.mejri@esprit.tn", "reponce ajoute au commentaire "+c.getId(), "annonce ajoute");
			Reponse reponse = new Reponse();
			reponse.setIdCommentaire(c.getId());
			reponse.setText(txtreponse.getText());
			datareponse.add(createPaneReponse(reponse,datareponse,listreponse));
			
			listreponse.setItems(datareponse);
			new ReponseService().ajouterReponse(reponse);
			reponse.setIdReponse(new ReponseService().getlastid());
			txtreponse.setText("");
			TrayNotification tr = new TrayNotification();
			tr.setAnimationType(AnimationType.POPUP);
			tr.setTitle("Zanimaux");
			tr.setNotificationType(NotificationType.SUCCESS);
			tr.setMessage("votre reponse a ete ajout� avec succes");
			tr.showAndDismiss(Duration.seconds(1));

		}
		
		
		
		});
		lstrep.forEach(r -> {

			Pane p = new Pane();
			p = createPaneReponse(r,datareponse,listreponse);
			datareponse.add(p);
		});
		
		listreponse.setItems(datareponse);

		
		Vboxrepondre.getChildren().add(listreponse);
		Hboxrepondre.getChildren().addAll(txtreponse,btnrepondre);
		Vboxrepondre.getChildren().add(Hboxrepondre);
		VboxCommentaire.getChildren().add(Vboxrepondre);
		paneCommentaire.getChildren().add(VboxCommentaire);
		
		return paneCommentaire;
	}

	public Pane createPaneReponse(Reponse c,ObservableList<Pane> datareponse,		ListView<Pane> listreponse) {
		
		Pane paneReponse = new Pane();
		HBox HboxReponse = new HBox();
		VBox VboxReponse = new VBox();
		JFXButton btnlike= new JFXButton("like."+Rs.getlikecount(c));
		JFXButton btndislike= new JFXButton("dislike."+Rs.getdislikecount(c));
		JFXButton btnsignaler= new JFXButton("signaler."+Rs.getsignalercount(c));
		JFXButton btnsupprimer= new JFXButton("supprimer ");
		
		btnsupprimer.setOnAction((ActionEvent e) -> {

			new ReponseService().supprimerReponse(c);
			datareponse.remove(paneReponse);
			listreponse.setItems(datareponse);

		});

		btnsignaler.setOnAction((ActionEvent e) -> {
			System.out.println("signaler sisisi");
			Rs.ajoutersignaler(c);			
			btnsignaler.setText("signaler."+Rs.getsignalercount(c));
			if (Rs.getsignalercount(c) == 10 ) {

				Rs.supprimerReponse(c);
				datareponse.remove(paneReponse);
				listreponse.setItems(datareponse);
			}

		});
		btndislike.setOnAction((ActionEvent e) -> {

			Rs.ajouterdislike(c);
			btndislike.setText("dislike."+Rs.getdislikecount(c));

		});
		btnlike.setOnAction((ActionEvent e) -> {

			Rs.ajouterlike(c);
			System.out.println("from button like " +c.getIdReponse());
			btnlike.setText("like."+Rs.getlikecount(c));

		});
		
		HboxReponse.getChildren().addAll(new Label("reponse text :"), new Label(c.getText()));
		VboxReponse.getChildren().add(HboxReponse);
		HBox hboxbuttonreponse = new HBox();
		
		hboxbuttonreponse.getChildren().add(btnlike);
		hboxbuttonreponse.getChildren().add(btndislike);
		hboxbuttonreponse.getChildren().add(btnsignaler);
		hboxbuttonreponse.getChildren().add(btnsupprimer);

		
		VboxReponse.getChildren().add(hboxbuttonreponse);
		paneReponse.getChildren().add(VboxReponse);

		return paneReponse;
	}
	@FXML
    private void uploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        photo = fileChooser.showOpenDialog(stage);

        if (photo != null) {
        	System.out.println(photo.getName());
        	try {
                String img = photo.toURI().toURL().toString();
                Image image = new Image(img);
                imgview.setImage(image);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     

}
	@FXML
    void back(ActionEvent event) throws IOException {

        tn.esprit.edu.test.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Menu.fxml"))));

    }
}

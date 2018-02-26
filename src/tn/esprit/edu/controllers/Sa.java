package tn.esprit.edu.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.models.Commentaire;
import tn.esprit.edu.models.Type;
import tn.esprit.edu.services.AnnonceService;
import tn.esprit.edu.services.CommentaireService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Sa implements Initializable {

	@FXML
	private AnchorPane anchorpane;
	@FXML
	private ListView<Pane> list;
	@FXML
	
	ListView<String> TypeList = new ListView<>();

	ObservableList<String> items = FXCollections.observableArrayList("Perdu", "Trouver");
	@FXML
	Button publier = new Button();

	@FXML
	TextArea AnnonceText = new TextArea();
	@FXML
	TextField AnnonceName = new TextField();
	ListView<Pane> commentairelist = new ListView<>();
	
	Pane pt = new Pane();
	Pane fin = new Pane();
	ObservableList<Pane> dataCommentaire = FXCollections.observableArrayList();
	
	List<Commentaire> listcommentaire = new ArrayList<>();
	private ObservableList<Pane> dataT = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle rb) {
		TypeList.setItems(items);
		AnnonceName.setPromptText("Annonce Name");
		AnnonceText.setPromptText("Annonce Text");

		
		Annonce annonce = new Annonce();

		List<Annonce> listannonce = new AnnonceService().listerAnnonce();

		listannonce.forEach(a -> {
			Pane p = new Pane();
			p = createPaneAnnonce(a);
			dataT.add(p);
		});
		list.setItems(dataT);

		publier.setOnAction((ActionEvent e) -> {
			if (AnnonceName.getText().trim().isEmpty() && AnnonceText.getText().trim().isEmpty()) {

				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.ERROR);
				tr.setMessage("Veuillez remplir les champs ");
				tr.showAndDismiss(Duration.seconds(1));

			} else {

				annonce.setName(AnnonceName.getText());
				annonce.setText(AnnonceText.getText());
				if (TypeList.getSelectionModel().getSelectedItem().equals("Perdu")) {
					annonce.setType(Type.Perdu);
				} else {
					annonce.setType(Type.Trouver);

				}
				new AnnonceService().ajouterAnnonce(annonce);
				TrayNotification tr = new TrayNotification();
				tr.setAnimationType(AnimationType.POPUP);
				tr.setTitle("Zanimaux");
				tr.setNotificationType(NotificationType.INFORMATION);
				tr.setMessage("Produit Ajouteer ");
				tr.showAndDismiss(Duration.seconds(1));
				
				dataT.add(createPaneAnnonce(annonce));
				
				list.setItems(dataT);

			}
		});

	}

	@FXML
	void back(ActionEvent event) throws IOException {

		tn.esprit.edu.test.Template.getInstance()
				.changescene(new Scene(FXMLLoader.load(getClass().getResource("/tn/esprit/edu/gui/Menu.fxml"))));

	}

	public Pane createPaneAnnonce(Annonce a) {
		pt.setPrefSize(475, 100);
		// *******Contenu Label*******
		Label AnnonceName = new Label();
		Label AnnonceText = new Label();
		AnnonceName.setText(a.getName());
		AnnonceText.setText(a.getText());
		AnnonceName.setWrapText(true);

		Button suprimerAnnonce = new Button();
		suprimerAnnonce.setText("X");

		suprimerAnnonce.setOnAction((ActionEvent e) -> {
			new AnnonceService().supprimerAnnonce(a);
			dataT.remove(fin);

			list.setItems(dataT);
		});

		TextArea txt = new TextArea();
		txt.setPrefSize(100, 100);
		txt.setMinWidth(100);

		VBox VboxNamelText = new VBox();
		HBox HboxName = new HBox();
		HBox HboxSupprime = new HBox();
		HBox HboxText = new HBox();

		HboxName.getChildren().add(new Label("Name Annonce : "));
		HboxName.getChildren().add(AnnonceName);

		HboxText.getChildren().add(new Label("Text Annonce : "));
		HboxText.getChildren().add(AnnonceText);
		HboxSupprime.getChildren().add(suprimerAnnonce);

		VboxNamelText.getChildren().addAll(HboxName, HboxText, HboxSupprime);

//		VboxNamelText.getChildren().add(createPaneCommentaire(a));
//		Button commenter = new Button("commenter");
//		commenter.setOnAction((ActionEvent e) -> {
//			System.out.println(a.getId());
//			Commentaire comm = new Commentaire(txt.getText(), a.getId());
//
//			new CommentaireService().ajouterCommentaire(comm);
//
//			commentairelist.refresh();
//		});

		HBox b = new HBox();
//		b.getChildren().add(txt);
//		b.getChildren().add(commenter);
//		VboxNamelText.getChildren().add(b);
		fin.getChildren().add(VboxNamelText);
		return fin;
	}

	public ListView<Pane> createPaneCommentaire(Annonce a) {

		listcommentaire = new CommentaireService().listerCommentaireAnnonce(a);

		if (listcommentaire != null) {
			listcommentaire.forEach(z -> {
				System.out.println(z.getTextCommentaire());
				Pane commentairePane = new Pane();
				Label Lcommentaire = new Label();
				Lcommentaire.setText(z.getTextCommentaire());
				HBox commentaireBox = new HBox();
				commentaireBox.getChildren().add(Lcommentaire);
				TextArea txtrepondre = new TextArea();
				txtrepondre.setPrefSize(100, 50);
				txtrepondre.setMinWidth(100);

				commentaireBox.getChildren().add(txtrepondre);
				commentaireBox.getChildren().add(new Button("repondre"));

				commentairePane.getChildren().add(commentaireBox);
				dataCommentaire.add(commentairePane);

			});
		} else {
			System.out.println("null");
		}
		commentairelist.setPrefSize(475, 200);
		commentairelist.setItems(dataCommentaire);

		return commentairelist;

	}

}

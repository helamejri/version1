package tn.esprit.edu.services;

import java.util.List;

import tn.esprit.edu.models.Commentaire;

public interface CommentaireServiceInterface {

	public boolean ajouterCommentaire(Commentaire obb);
	public boolean supprimerCommentaire(Commentaire obb);
	public boolean modifierCommentaire(Commentaire obb);
	public List<Commentaire> listerCommentaire();
	
	
}

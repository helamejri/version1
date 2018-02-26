package tn.esprit.edu.services;

import java.util.List;

import tn.esprit.edu.models.Annonce;  


public interface AnnonceServiceInterface {

	public boolean ajouterAnnonce(Annonce obb);
	public boolean supprimerAnnonce(Annonce obb);
	public boolean modifierAnnonce(Annonce obb);
	public List<Annonce> listerAnnonce();
	
}

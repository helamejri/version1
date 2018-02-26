package tn.esprit.edu.services;

import java.util.List;

import tn.esprit.edu.models.Reponse; 


public interface ReponseServiceInterface {

	public boolean ajouterReponse(Reponse obb);
	public boolean supprimerReponse(Reponse obb);
	public boolean modifierReponse(Reponse obb);
	public List<Reponse> listerReponse();
	
}

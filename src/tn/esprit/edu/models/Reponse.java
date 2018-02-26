package tn.esprit.edu.models;

import java.sql.Timestamp;

public class Reponse {

	private int IdReponse;
	private Timestamp DateResponse ;
	private String text  ; 
	private int idCommentaire ;
	public Reponse() {
			}

	
	public Reponse(String text) {
		super();
		this.text = text;
	}
	public int getIdReponse() {
		return IdReponse;
	}
	public void setIdReponse(int idReponse) {
		IdReponse = idReponse;
	}
	public Timestamp getDateResponse() {
		return DateResponse;
	}
	public void setDateResponse(Timestamp dateResponse) {
		DateResponse = dateResponse;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	} 
	
	
	
	
	
	
	
	
}

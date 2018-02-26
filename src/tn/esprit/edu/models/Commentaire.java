package tn.esprit.edu.models;

import java.sql.Timestamp;

public class Commentaire {
	private int id;
	private String textCommentaire;
	private int idAnnonce;
	private Timestamp dateCommentaire; 
	
	public Commentaire(String textCommentaire) {
		this.textCommentaire = textCommentaire;
	}
	public Commentaire(String textCommentaire,int idAnnonce) {
		this.textCommentaire = textCommentaire;
		this.idAnnonce=idAnnonce;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public String gettextCommentaire() {
		return textCommentaire;
	}

	public void settextCommentaire(String commentaire) {
		this.textCommentaire = commentaire;
	}

	public int getIdAnnonce() {
		return idAnnonce;
	}

	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}

	public String getTextCommentaire() {
		return textCommentaire;
	}

	public void setTextCommentaire(String textCommentaire) {
		this.textCommentaire = textCommentaire;
	}

	public Timestamp getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Timestamp dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

}

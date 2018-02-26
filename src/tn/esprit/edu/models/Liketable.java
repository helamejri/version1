package tn.esprit.edu.models;

public class Liketable {

	
	private int id;
	private int idannonce ;
	private int idreponse ;
	private int idcommentaire ;
	private int likecount ;
	private int dislikecount ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdannonce() {
		return idannonce;
	}
	public void setIdannonce(int idannonce) {
		this.idannonce = idannonce;
	}
	public int getIdreponse() {
		return idreponse;
	}
	public void setIdreponse(int idreponse) {
		this.idreponse = idreponse;
	}
	public int getIdcommentaire() {
		return idcommentaire;
	}
	public void setIdcommentaire(int idcommentaire) {
		this.idcommentaire = idcommentaire;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getDislikecount() {
		return dislikecount;
	}
	public void setDislikecount(int dislikecount) {
		this.dislikecount = dislikecount;
	}
	
	
}

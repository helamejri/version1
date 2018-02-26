package tn.esprit.edu.models;


import java.sql.Timestamp;

import com.mysql.jdbc.Blob;
 
public class Annonce {

	private int id;
	private String text;
	private String name;
	private Type type;
	private Timestamp dateDePartage;
	private int iduser ;
	private Blob image;
	
	public Annonce() {
	}
	
	public Annonce(int id, String text, String name) {
		super();
		
		this.text = text;
		this.name = name;
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Timestamp getDateDePartage() {
		return dateDePartage;
	}

	public void setDateDePartage(Timestamp StringDePartage) {
		this.dateDePartage = StringDePartage;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
}

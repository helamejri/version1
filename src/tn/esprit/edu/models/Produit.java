package tn.esprit.edu.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Produit {
    private int idProduit;
    private String libelleProduit;
    private LocalDateTime dateajout;
    private String descriptionProduit;
    private String etatProduit;
    private byte[] imageProduit;
    private float prixProduit;
    private int stock;
    private User user;
    private Categorie categorie;


    public Produit(int idProduit, String libelleProduit, LocalDateTime dateajout, String descriptionProduit, String etatProduit, byte[] imageProduit, float prixProduit, User user, Categorie categorie) {
        this.idProduit = idProduit;
        this.libelleProduit = libelleProduit;
        this.dateajout = dateajout;
        this.descriptionProduit = descriptionProduit;
        this.etatProduit = etatProduit;
        this.imageProduit = imageProduit;
        this.prixProduit = prixProduit;
        this.user = user;
        this.categorie = categorie;
    }

    public Produit(int idProduit, String libelleProduit, LocalDateTime dateajout, String descriptionProduit, String etatProduit, float prixProduit, User user, Categorie categorie) {
        this.idProduit = idProduit;
        this.libelleProduit = libelleProduit;
        this.dateajout = dateajout;
        this.descriptionProduit = descriptionProduit;
        this.etatProduit = etatProduit;
        this.prixProduit = prixProduit;
        this.user = user;
        this.categorie = categorie;
    }


    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", libelleProduit='" + libelleProduit + '\'' +
                ", dateajout=" + dateajout +
                ", descriptionProduit='" + descriptionProduit + '\'' +
                ", etatProduit='" + etatProduit + '\'' +
                ", imageProduit=" + Arrays.toString(imageProduit) +
                ", prixProduit=" + prixProduit +
                ", user=" + user +
                ", categorie=" + categorie +
                '}';
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public Produit() {
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public LocalDateTime getDateajout() {
        return dateajout;
    }

    public void setDateajout(LocalDateTime dateajout) {
        this.dateajout = dateajout;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getEtatProduit() {
        return etatProduit;
    }

    public void setEtatProduit(String etatProduit) {
        this.etatProduit = etatProduit;
    }

    public byte[] getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(byte[] imageProduit) {
        this.imageProduit = imageProduit;
    }

}

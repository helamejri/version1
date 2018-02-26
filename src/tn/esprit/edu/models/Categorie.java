package tn.esprit.edu.models;

public class Categorie {
    private int idCategorie;
    private String nomCategorie;

    @Override
    public String toString() {
        return  nomCategorie;

    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {
    }

    public Categorie(int idCategorie, String nomCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
    }
}

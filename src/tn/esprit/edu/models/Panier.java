package tn.esprit.edu.models;

import java.util.List;

public class Panier {
    private int idPanier;
    private List<Produit> porduit;

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public List<Produit> getPorduit() {
        return porduit;
    }

    public void setPorduit(List<Produit> porduit) {
        this.porduit = porduit;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "idPanier=" + idPanier +
                ", porduit=" + porduit +
                '}';
    }
}

package tn.esprit.edu.services;

import tn.esprit.edu.models.Produit;
import tn.esprit.edu.technique.DataSource;
import tn.esprit.edu.technique.StaticAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PanierService {
    private Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;

    public PanierService() {
        dataSource = DataSource.getInstance();

    }

    public void addProduittopanier(Produit p) {
        String req = "insert into panier (idProduit,idUser) values (" +p.getIdProduit()+","+ StaticAccount.user.getIdUser()+")";
        System.out.println(req);
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduitfromPanier(Produit p) throws SQLException {
        String req = "DELETE FROM panier WHERE idProduit = ? AND idUser ="+StaticAccount.user.getIdUser();
        try {
            int i = p.getIdProduit();
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1,i );
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }


    }
}

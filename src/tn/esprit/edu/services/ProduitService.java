package tn.esprit.edu.services;

import tn.esprit.edu.iservices.Iservices;
import tn.esprit.edu.models.Categorie;
import tn.esprit.edu.models.Produit;
import tn.esprit.edu.models.StatModel;
import tn.esprit.edu.technique.DataSource;
import tn.esprit.edu.technique.StaticAccount;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProduitService implements Iservices<Produit, Integer> {
    private Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;

    public ProduitService() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void add(Produit produit) {
        String req = "INSERT INTO pidev.produit (idProduit,libelleProduit,descriptionProduit,etatProduit,prixProduit,idUser,idCategorie,stock) VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1, produit.getIdProduit());
            ps.setString(2, produit.getLibelleProduit());
            ps.setString(3, produit.getDescriptionProduit());
            ps.setString(4, produit.getEtatProduit());
//            Blob image = null;
//            if(produit.getImageProduit()!=null){
//                image = connection.createBlob();
//                image.setBytes(1,produit.getImageProduit());
//            }
//            ps.setBlob(5,image);
            ps.setFloat(5, produit.getPrixProduit());
            ps.setInt(6, StaticAccount.user.getIdUser());
            ps.setInt(7, produit.getCategorie().getIdCategorie());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Produit produit) {

        String req = "UPDATE produit SET libelleProduit =?,descriptionProduit =?,etatProduit =?,prixProduit =?,idCategorie=?,stock =? WHERE idProduit=?";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1, produit.getLibelleProduit());
            ps.setString(2, produit.getDescriptionProduit());
            ps.setString(3, produit.getEtatProduit());
            ps.setFloat(4, produit.getPrixProduit());
            ps.setInt(5, produit.getCategorie().getIdCategorie());
            ps.setInt(6,produit.getStock());
            Blob image = null;
//            if (evenement.getAffiche() != null) {
//                image = connection.createBlob();
//                image.setBytes(1, evenement.getAffiche());
//            }
//            ps.setBlob(7, image);

            ps.setInt(7, produit.getIdProduit());
            System.out.println(req);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remove(Produit produit) {

        String reqSql = "DELETE FROM produit WHERE idProduit = ?";
        try {
            int i = produit.getIdProduit();
            ps = dataSource.getConnection().prepareStatement(reqSql);
            this.ps.setInt(1, i);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error lors de la supression" + ex.getMessage());
        }

    }

    @Override
    public List<Produit> findAll() {
        String req = "SELECT * FROM pidev.produit";
        List<Produit> produits = new ArrayList<>();
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(rs.getInt("idProduit"));
                produit.setLibelleProduit(rs.getString("libelleProduit"));
                produit.setDescriptionProduit(rs.getString("descriptionProduit"));
                produit.setEtatProduit(rs.getString("etatProduit"));
                produit.setPrixProduit(rs.getFloat("prixProduit"));
                produit.setUser(new UserService().findById(rs.getInt("idUser")));
                produit.setCategorie(new CategorieService().findById(rs.getInt("idCategorie")));
                produit.setDateajout(rs.getTimestamp("dateajout").toLocalDateTime());
                produit.setStock(rs.getInt("stock"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public Produit findById(Integer idpr) {
        String reqSql = "SELECT * FROM produit WHERE produit.idProduit = ?";
        Produit produit = new Produit();
        try {
            ps = dataSource.getConnection().prepareStatement(reqSql);
            ps.setInt(1, idpr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produit.setIdProduit(rs.getInt("idProduit"));
                produit.setLibelleProduit(rs.getString("libelleProduit"));
                produit.setDescriptionProduit(rs.getString("descriptionProduit"));
                produit.setEtatProduit(rs.getString("etatProduit"));
                produit.setPrixProduit(rs.getFloat("prixProduit"));
                produit.setDateajout(rs.getTimestamp("dateajout").toLocalDateTime());
                produit.setUser(new UserService().findById(rs.getInt("idUser")));
                produit.setCategorie(new CategorieService().findById(rs.getInt("idCategorie")));
                produit.setStock(rs.getInt("stock"));
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return produit;
    }

    public List<Produit> findProduitByCategorie(int id_categorie) {
        String req = "SELECT * FROM produit WHERE idCategorie= ?";

        List<Produit> produits = new ArrayList<Produit>();
        try {

            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1, id_categorie);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(rs.getInt("idProduit"));
                produit.setLibelleProduit(rs.getString("libelleProduit"));
                produit.setDescriptionProduit(rs.getString("descriptionProduit"));
                produit.setEtatProduit(rs.getString("etatProduit"));
                produit.setPrixProduit(rs.getFloat("prixProduit"));
                produit.setDateajout(rs.getTimestamp("dateajout").toLocalDateTime());
                produit.setUser(new UserService().findById(rs.getInt("idUser")));
                produit.setCategorie(new CategorieService().findById(rs.getInt("idCategorie")));
                produit.setStock(rs.getInt("stock"));
//                Blob image = rs.getBlob("affiche");
//                if (image != null) {
//                    byte[] imageBytes = image.getBytes(1, (int) image.length());
//                    event.setAffiche(imageBytes);
//                }

                produits.add(produit);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produits;
    }

    public int nouveauProdId() {
        int id = 0;
        try {
            String req = "SELECT max(pidev.produit.idProduit) AS id FROM produit ";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("idProduit");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (id + 1);
    }

    public List<StatModel> listProduitMois(int id) {

        String req = "SELECT count(pidev.produit.idCategorie) nb,pidev.produit.idCategorie id,month(pidev.produit.dateajout) mois "
                + " FROM produit WHERE idCategorie=? GROUP BY idCategorie,month(dateajout) ORDER BY month(dateajout) ASC";
        List<StatModel> liste = new ArrayList<>();
        try {

            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StatModel st = new StatModel();
                st.setIdCatCount(rs.getInt("nb"));
                st.setIdcat(rs.getInt("id"));
                LocalDate dt = Year.now().atMonth(rs.getInt("mois")).atDay(12);
                String dateInFrench = dt.format(DateTimeFormatter.ofPattern("MMM", Locale.FRENCH));
                st.setLibmois(dateInFrench);
                st.setMois(rs.getInt("mois"));
                liste.add(st);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public void updatestock(Produit p) {
        String req = "UPDATE produit SET stock =? WHERE idProduit=?";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1, p.getStock());
            ps.setInt(2, p.getIdProduit());
            System.out.println(req);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getstock(Produit p) {
        String req = "SELECT stock FROM produit WHERE idProduit = ?";
        int stock = 0;
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setInt(1, p.getIdProduit());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getInt("stock");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }


}
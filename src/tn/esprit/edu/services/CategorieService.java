package tn.esprit.edu.services;

import tn.esprit.edu.iservices.Iservices;
import tn.esprit.edu.models.Categorie;
import tn.esprit.edu.technique.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements Iservices<Categorie, Integer> {
    private Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;

    public CategorieService() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void add(Categorie categorie) {
        String req = "INSERT INTO pidev.categorie (nomCategorie) VALUES (?)";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1, categorie.getNomCategorie());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Categorie categorie) {
        String req = "UPDATE categorie SET nomCategorie =? WHERE categorie.idCategorie=?";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1, categorie.getNomCategorie());
            ps.setInt(2, categorie.getIdCategorie());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void remove(Categorie categorie) {

        String reqSql = "DELETE FROM categorie WHERE idCategorie = ?";
        try {
            int i = findById(categorie.getIdCategorie()).getIdCategorie();
            ps = dataSource.getConnection().prepareStatement(reqSql);
            this.ps.setInt(1, i);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error lors de la supression" + ex.getMessage());
        }

    }

    @Override
    public List<Categorie> findAll() {
        String req = "SELECT * FROM pidev.categorie";
        List<Categorie> categories = new ArrayList<>();
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Categorie categorie;
                categorie = new Categorie(rs.getInt(1), rs.getString(2));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Categorie findById(Integer idcategorie) {
        String reqSql = "SELECT * FROM categorie WHERE categorie.idCategorie = ?";
        Categorie categorie = new Categorie();
        try {
            ps = dataSource.getConnection().prepareStatement(reqSql);
            ps.setInt(1, idcategorie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorie.setIdCategorie(rs.getInt("idCategorie"));
                categorie.setNomCategorie(rs.getString("nomCategorie"));
            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return categorie;
    }

    public Categorie findCategorieByName(String name) {
        String reqsql = "SELECT * FROM categorie WHERE nomCategorie = ?";
        Categorie categorie = new Categorie();
        try {
            ps = dataSource.getConnection().prepareStatement(reqsql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorie.setIdCategorie(rs.getInt("idCategorie"));
                categorie.setNomCategorie(rs.getString("nomCategorie"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }
}

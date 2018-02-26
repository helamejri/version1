package tn.esprit.edu.services;

import tn.esprit.edu.iservices.Iservices;
import tn.esprit.edu.models.User;
import tn.esprit.edu.technique.DataSource;
import tn.esprit.edu.technique.StaticAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements Iservices<User, Integer> {
    private Connection connection;
    private PreparedStatement ps;
    private DataSource dataSource;

    public UserService() throws SQLException {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void add(User user) {
        String req = "INSERT INTO pidev.user (nomUser,prenomUser,fonctionUser,login,motdepasse) VALUES (?,?,?,?,?)";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1, user.getNomUser());
            ps.setString(2, user.getPrenomUser());
            ps.setInt(3, user.getFonctionUser());
            ps.setString(4,user.getLogin());
            ps.setString(5,user.getMotedepasse());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(User user) {
        String req = "UPDATE user SET nomUser =?,prenomUser =?,fonctionUser =?,motdepasse =? WHERE user.idUser=?";
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1, user.getNomUser());
            ps.setString(2, user.getPrenomUser());
            ps.setInt(3, user.getFonctionUser());
            ps.setString(4,user.getMotedepasse());
            ps.setInt(5, user.getIdUser());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remove(User user) {

        String reqSql = "DELETE FROM user WHERE idUser = ?";
        try {
            int i = user.getIdUser();
            ps = dataSource.getConnection().prepareStatement(reqSql);
            this.ps.setInt(1, i);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error lors de la supression" + ex.getMessage());
        }
    }


    @Override
    public List<User> findAll() {
        String req = "SELECT * FROM pidev.user";
        List<User> users = new ArrayList<>();
        try {
            ps = dataSource.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                User user;
                //user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
               // user.setLogin(rs.getString(5));
               // users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User findById(Integer iduser) {

        String reqSql = "SELECT * FROM user WHERE user.idUser = ?";
        User user = new User();
        try {
            ps = dataSource.getConnection().prepareStatement(reqSql);
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setIdUser(rs.getInt("idUser"));
                user.setPrenomUser(rs.getString("prenomUser"));
                user.setNomUser(rs.getString("nomUser"));
                user.setFonctionUser(rs.getInt("fonctionUser"));
                user.setLogin(rs.getString("login"));
                user.setMotedepasse(rs.getString("motdepasse"));

            }
        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        return user;
    }

    public User findUserbyName(String name) {
        String reqsql = "SELECT * FROM user WHERE nomUser = ?";
        User user = new User();
        try {
            ps = dataSource.getConnection().prepareStatement(reqsql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setIdUser(rs.getInt("idUser"));
                user.setNomUser(rs.getString("nomUser"));
                user.setLogin(rs.getString("login"));
                user.setMotedepasse(rs.getString("motedepasse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public int login(String login,String password){
        String req = "SELECT * FROM user WHERE login = ?";
        try{
            ps = dataSource.getConnection().prepareStatement(req);
            ps.setString(1,login);
            ResultSet rs = ps.executeQuery();
            if(rs.last()){
                if(password.equals(rs.getString("motdepasse"))){
                    StaticAccount.user.setIdUser(rs.getInt("idUser"));
                    StaticAccount.user.setNomUser(rs.getString("nomUser"));
                    StaticAccount.user.setPrenomUser(rs.getString("prenomUser"));
                    StaticAccount.user.setFonctionUser(rs.getInt("fonctionUser"));
                    StaticAccount.user.setMotedepasse(rs.getString("motdepasse"));
                    StaticAccount.user.setLogin(rs.getString("login"));
                    System.out.println(StaticAccount.user.toString());
                    return 1;
                }
                else{
                    System.out.println("identifians invalides");;
                }
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }



}


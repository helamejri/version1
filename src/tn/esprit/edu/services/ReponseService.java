package tn.esprit.edu.services;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.models.Commentaire;
import tn.esprit.edu.models.Reponse;
import tn.esprit.edu.technique.DataSource;


public class ReponseService implements ReponseServiceInterface {

	Connection connection;
	PreparedStatement ps;
	DataSource dataSource;
	Statement stmt;

	public ReponseService() {

		dataSource = DataSource.getInstance();
		connection = DataSource.getInstance().getConnection();
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean ajouterReponse(Reponse Reponse) {

		String req = "INSERT INTO pidev.Reponse (textreponse,idCommentaire,dateReponse) VALUES (?,?,?)";
		try {

			ps = dataSource.getConnection().prepareStatement(req);

			ps.setString(1, Reponse.getText());
			ps.setInt(2, Reponse.getIdCommentaire());
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean supprimerReponse(Reponse Reponse) {
		String req = "DELETE FROM `Reponse` WHERE `id`=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setInt(1, Reponse.getIdReponse());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean modifierReponse(Reponse Reponse) {
		String req = "UPDATE `Reponse` \r\n" + "    SET `text`=?  WHERE Reponse.idReponse=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setString(1, Reponse.getText());
			ps.setInt(2, Reponse.getIdReponse());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public List<Reponse> listerReponse() {
		List<Reponse> Reponses = new ArrayList<>();
		Reponse c = null;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from Reponse");

			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				c = new Reponse(rs.getString("text"));
				Reponses.add(c);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Reponses;
	}

	public List<Reponse> listerReponseCommentaire(Commentaire a) {
		List<Reponse> Reponses = new ArrayList<>();
		Reponse c = null;
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(
					"SELECT `id`, `textreponse`, `idCommentaire`, `datereponse` FROM `reponse` WHERE `idCommentaire`=? ORDER by `dateReponse` ASC");
			ps.setInt(1, a.getId());
			
			ps.execute();
			
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				
				c = new Reponse(rs.getString("textreponse"));
				c.setIdReponse(rs.getInt("id"));
				
				Reponses.add(c);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return Reponses;
	}

	public boolean supprimerReponseAnnonce(Commentaire commentaire) {
		String req = "DELETE FROM `Reponse` WHERE `idCommenataire`=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setInt(1, commentaire.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void ajouterlike(Reponse reponse) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				if (reponse.getIdReponse() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `reponse` SET `likecount`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("likecount") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterdislike(Reponse reponse) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			System.out.println(reponse.getIdReponse());
			while (rs.next()) {
				if (reponse.getIdReponse() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `reponse` SET `dislikecount`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("dislikecount") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajoutersignaler(Reponse reponse) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				if (reponse.getIdReponse() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `reponse` SET `signaler`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("signaler") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getsignalercount(Reponse reponse) {
		int signaler = 0;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse where id=?");
			ps.setInt(1, reponse.getIdReponse());
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				signaler = rs.getInt("signaler");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return signaler;
	}

	public int getlikecount(Reponse reponse) {
		int signaler = 0;
		System.out.println("getlikecount "+reponse.getIdReponse());
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse where id=?");
			ps.setInt(1, reponse.getIdReponse());
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				signaler = rs.getInt("likecount");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return signaler;
	}

	public int getdislikecount(Reponse reponse) {
		int signaler = 0;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from reponse where id=?");
			ps.setInt(1, reponse.getIdReponse());
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				signaler = rs.getInt("dislikecount");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return signaler;
	}
	public int getlastid() {
		int id = 0;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT MAX(id) FROM reponse");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				id = rs.getInt("MAX(id)");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}
}

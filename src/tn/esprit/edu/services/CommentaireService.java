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
import tn.esprit.edu.technique.DataSource;

public class CommentaireService implements CommentaireServiceInterface {

	Connection connection;
	PreparedStatement ps;
	DataSource dataSource;
	Statement stmt;

	public CommentaireService() {

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
	public boolean ajouterCommentaire(Commentaire commentaire) {

		String req = "INSERT INTO pidev.commentaire (commentaire,idAnnonce,dateCommentaire) VALUES (?,?,?)";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setString(1, commentaire.gettextCommentaire());
			ps.setInt(2, commentaire.getIdAnnonce());
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean supprimerCommentaire(Commentaire commentaire) {
		String req = "DELETE FROM `commentaire` WHERE `id`=?";
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

	@Override
	public boolean modifierCommentaire(Commentaire commentaire) {
		String req = "UPDATE `commentaire` \r\n" + "    SET `commentaire`=?  WHERE commentaire.id=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setString(1, commentaire.gettextCommentaire());
			ps.setInt(2, commentaire.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public List<Commentaire> listerCommentaire() {
		List<Commentaire> commentaires = new ArrayList<>();
		Commentaire c = null;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire");

			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				c = new Commentaire(rs.getString("commentaire"));

				commentaires.add(c);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		return commentaires;
	}

	public List<Commentaire> listerCommentaireAnnonce(Annonce a) {
		List<Commentaire> commentaires = new ArrayList<>();
		Commentaire c = null;

		try {
			PreparedStatement ps =

					this.connection.prepareStatement(
							"SELECT `id`, `commentaire`, `idAnnonce`, `dateCommentaire` FROM `commentaire` WHERE	`idAnnonce`=? ORDER by `dateCommentaire` ASC");

			ps.setInt(1, a.getId());

			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				c = new Commentaire(rs.getString("commentaire"));
				c.setId(rs.getInt("id"));
				c.setIdAnnonce(rs.getInt("idAnnonce"));
				c.setDateCommentaire(rs.getTimestamp("dateCommentaire"));
				commentaires.add(c);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		return commentaires;
	}

	public boolean supprimerCommentaireAnnonce(Annonce annonce) {
		String req = "DELETE FROM `commentaire` WHERE `idAnnonce`=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setInt(1, annonce.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void ajouterlike(Commentaire commentaire) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				if (commentaire.getId() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `commentaire` SET `likecount`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("likecount") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterdislike(Commentaire commentaire) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			System.out.println(commentaire.getId());
			while (rs.next()) {
				if (commentaire.getId() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `commentaire` SET `dislikecount`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("dislikecount") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajoutersignaler(Commentaire commentaire) {

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				if (commentaire.getId() == rs.getInt("id")) {

					PreparedStatement ps1 = this.connection
							.prepareStatement("UPDATE `commentaire` SET `signaler`=? WHERE `id`=?");

					ps1.setInt(1, rs.getInt("signaler") + 1);
					ps1.setInt(2, rs.getInt("id"));

					ps1.executeUpdate();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getsignalercount(Commentaire commentaire) {
		int signaler = 0;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire where id=?");
			ps.setInt(1, commentaire.getId());
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

	public int getlikecount(Commentaire commentaire) {
		int signaler = 0;
		System.out.println(commentaire.getId()+"id from like comment");
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire where id=?");
			ps.setInt(1, commentaire.getId());
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

	public int getdislikecount(Commentaire commentaire) {
		int signaler = 0;

		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from commentaire where id=?");
			ps.setInt(1, commentaire.getId());
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
			PreparedStatement ps = this.connection.prepareStatement("SELECT MAX(id) FROM commentaire");
			ps.executeQuery();

			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				id = rs.getInt("MAX(id)");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("id from last id comment "+id);

		return id;
	}
}

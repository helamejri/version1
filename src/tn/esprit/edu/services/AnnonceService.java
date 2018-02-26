package tn.esprit.edu.services;

import java.sql.Connection;     
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.derby.tools.sysinfo;

import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.models.Commentaire;
import tn.esprit.edu.models.Type;
import tn.esprit.edu.technique.DataSource;
import tn.esprit.edu.technique.StaticAccount;


public class AnnonceService implements AnnonceServiceInterface {

	Connection connection;
	PreparedStatement ps;
	DataSource dataSource;
	 Statement stmt;
	public AnnonceService() {

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
	public boolean ajouterAnnonce(Annonce annonce) {
		String req = 
"INSERT INTO `annonce`(`name`, `annonceText`, `Type`, `dateDePartage`, `idUser`) VALUES (?,?,?,?,?)";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setString(1, annonce.getName());
			ps.setString(2, annonce.getText());
			ps.setString(3, annonce.getType().toString());
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			
			ps.setInt(5, StaticAccount.user.getIdUser());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean supprimerAnnonce(Annonce annonce) {
		String req = "DELETE FROM `annonce` WHERE annonce.id=?";
		
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setInt(1, annonce.getId());
			new CommentaireService().supprimerCommentaireAnnonce(annonce);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean modifierAnnonce(Annonce annonce) {
		String req = "UPDATE `annonce` \r\n" + "    SET `name`=?,`annonceText`=?,`Type`=? ,"
				+ "`dateDePartage`=? WHERE annonce.id=?";
		try {

			ps = dataSource.getConnection().prepareStatement(req);
			ps.setString(1, annonce.getName());
			ps.setString(2, annonce.getText());
			ps.setString(3, annonce.getType().toString());
			ps.setTimestamp(4, annonce.getDateDePartage());
			ps.setInt(5, annonce.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Annonce> listerAnnonce() {
		List<Annonce> annonces = new ArrayList<>();
		Annonce c = null;
  	  	
		try {
			 PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
		      
		      ps.execute();
		      ResultSet rs = ps.getResultSet();
		      while (rs.next()) {
		    	  	c = new Annonce();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setText(rs.getString("annonceText"));
					c.setIduser(rs.getInt("idUser"));
					if (rs.getString("Type").equals("Perdu")) {
						c.setType(Type.Perdu);
					} else {
						c.setType(Type.Trouver);

					}
		        
		        /*
			while (res.next()) {
				
				if (res.getString("Type").equals("perdu")) {
					c.setType(Type.Perdu);

				} else {
					c.setType(Type.Trouver);
				}*/
				annonces.add(c);

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		return annonces;
	}

	public List<Commentaire> findCommentsAnnonce(Annonce annonce) {
		String req = "select * from commentaire where commentaire.idAnnonce=?";
		List<Commentaire> commentaires = new ArrayList<>();
		try {
			ps = dataSource.getConnection().prepareStatement(req);
			ps.setInt(1, annonce.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Commentaire commentaire;

				commentaire = new Commentaire(rs.getString(2));
				commentaires.add(commentaire);
			}
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return commentaires;
	}
	public void ajouterlike(Annonce annonce) {
		

		
		try {
		PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
    	  	if (annonce.getId()==rs.getInt("id")) {
    	  		
    	  		PreparedStatement 
    	  		ps1 = this.connection.prepareStatement("UPDATE `annonce` SET `likecount`=? WHERE `id`=?");
    	  		
    			ps1.setInt(1, rs.getInt("likecount")+1);
    			ps1.setInt(2, rs.getInt("id"));
    			
    			ps1.executeUpdate();
			}
		}
		
} 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
}
public void ajouterdislike(Annonce annonce) {
		

		
		try {
		PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
		ps.executeQuery();
		
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
    	  	if (annonce.getId()==rs.getInt("id")) {
    	  		
    	  		PreparedStatement 
    	  		ps1 = this.connection.prepareStatement("UPDATE `annonce` SET `dislikecount`=? WHERE `id`=?");
    	  		
    			ps1.setInt(1, rs.getInt("dislikecount")+1);
    			ps1.setInt(2, rs.getInt("id"));
    			
    			ps1.executeUpdate();
			}
		}
		
} 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
}
public void ajoutersignaler(Annonce annonce) {
	

	
	try {
	PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
	ps.executeQuery();
	
	ResultSet rs = ps.getResultSet();
	while (rs.next()) {
	  	if (annonce.getId()==rs.getInt("id")) {
	  		
	  		PreparedStatement 
	  		ps1 = this.connection.prepareStatement("UPDATE `annonce` SET `signalercount`=? WHERE `id`=?");
	  		
			ps1.setInt(1, rs.getInt("signalercount")+1);
			ps1.setInt(2, rs.getInt("id"));
			
			ps1.executeUpdate();
		}
	}
	
} 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }
}
public int getsignalercount(Annonce annonce) {
	int signaler= 0;

	
	try {
	PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce where id=?");
	ps.setInt(1, annonce.getId());
	ps.executeQuery();

	ResultSet rs = ps.getResultSet();
	while (rs.next()) {
	signaler= rs.getInt("signalercount");
	}
	
}catch (Exception e) {
	// TODO: handle exception
}
	return signaler;
}
public int getlikecount(Annonce annonce) {
	int signaler= 0;

	
	try {
	PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce where id=?");
	ps.setInt(1, annonce.getId());
	ps.executeQuery();
	
	ResultSet rs = ps.getResultSet();
	while (rs.next()) {
	signaler= rs.getInt("likecount");
	}
	
}catch (Exception e) {
	// TODO: handle exception
}
	return signaler;
}
public int getdislikecount(Annonce annonce) {
	int signaler= 0;

	
	try {
	PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce where id=?");
	ps.setInt(1, annonce.getId());
	ps.executeQuery();

	ResultSet rs = ps.getResultSet();
	while (rs.next()) {
	signaler= rs.getInt("dislikecount");
	}
	
}catch (Exception e) {
	// TODO: handle exception
}
	return signaler;
}



public void supprimer30jrs() {
	Annonce annonce = new Annonce();
	
		try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
			ps.executeQuery();
			
			ResultSet rs = ps.getResultSet();
			
			while (rs.next()) {
				annonce.setId(rs.getInt("id"));
				annonce.setDateDePartage(rs.getTimestamp("dateDePartage"));
				Timestamp tmpTimeStamp;
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("dateDePartage").getTime());
				cal.add(Calendar.SECOND, 120);
				tmpTimeStamp = new Timestamp(cal.getTime().getTime());
				System.out.println("date de parteage ; "+rs.getTimestamp("dateDePartage"));
				System.out.println("timestamp ; "+tmpTimeStamp);
				System.out.println("date now "+ new Timestamp(new Date().getTime()));

				if (new Timestamp(new Date().getTime()).after(tmpTimeStamp) && !commentexist(annonce)) {
									new AnnonceService().supprimerAnnonce(annonce);	
				}
				
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

}
public boolean commentexist(Annonce annonce) {
	PreparedStatement ps1;
	boolean hh =  false;
	try {
		ps1 = this.connection.prepareStatement("SELECT * from commentaire where idAnnonce=?");
		ps1.setInt(1, annonce.getId());
		ps1.executeQuery();
		ResultSet rs1 = ps1.getResultSet();
		if (!rs1.next()) {
						
		}else {
					
		hh=true;
			

		}
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return hh;
	}

public int getlastid() {
	int id = 0;

	try {
		PreparedStatement ps = this.connection.prepareStatement("SELECT MAX(id) FROM annonce");
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



	// 30 jrs

}

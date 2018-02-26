package tn.esprit.edu.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tn.esprit.edu.models.Annonce;
import tn.esprit.edu.models.Commentaire;
import tn.esprit.edu.models.Liketable;
import tn.esprit.edu.models.Reponse;
import tn.esprit.edu.technique.DataSource;

public class Liketableservice {
	Connection connection;
	PreparedStatement ps;
	DataSource dataSource;
	 Statement stmt;
	public boolean ajouterlike(Annonce object) {
		

			
			try {
			PreparedStatement ps = this.connection.prepareStatement("SELECT * from annonce");
			ps.setInt(1, ((Annonce) object).getId());
			ps.executeUpdate();
			
			ResultSet rs = ps.getResultSet();
			System.out.println("ddd");
			while (rs.next()) {
	    	  	
				System.out.println(rs.getInt("likecount"));
			}
			 } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	            return false;
	        }
		
//		else if (object instanceof Commentaire) {
//			String req2 = "INSERT INTO `liketable` "
//					+ "(`idannonce`, `idreponse`, `idcommentaire`, `likecount`, `dislikecount`) "
//					+ "VALUES ( null, NULL, ?, NULL, NULL);";
//			try {
//			ps = dataSource.getConnection().prepareStatement(req2);
//			ps.setInt(1, ((Commentaire) object).getId());
//			ps.executeUpdate();
//			 } 
//	        catch (SQLException e) 
//	        {
//	            e.printStackTrace();
//	            return false;
//	        }
//
//		}else if (object instanceof Reponse) {
//			String req2 = "INSERT INTO `liketable` "
//					+ "(`idannonce`, `idreponse`, `idcommentaire`, `likecount`, `dislikecount`) "
//					+ "VALUES ( null, ?, null, NULL, NULL);";
//			try {
//			ps = dataSource.getConnection().prepareStatement(req2);
//			ps.setInt(1, ((Reponse) object).getIdReponse());
//			ps.executeUpdate();
//			 } 
//	        catch (SQLException e) 
//	        {
//	            e.printStackTrace();
//	            return false;
//	        }
//
//		}
//        
        return true;
    }

	public boolean ajouterdislike(Object obb) {

		
		
		
		return false;
	}
	
	
	public boolean exist(Object obb) {

		boolean exist = false ;
		try {
			 PreparedStatement ps = 
	this.connection.prepareStatement("SELECT  `idannonce`, `idreponse`, `idcommentaire` FROM `liketable`");
		      
		      ps.execute();
		      ResultSet rs = ps.getResultSet();
		      
		      while (rs.next()) {
		    	 
		    	if (obb instanceof Annonce) {
				
		    	if (((Annonce) obb).getId()==rs.getInt("idannonce")){
		    		exist = true;
				}else if(((Reponse) obb).getIdReponse()==rs.getInt("idreponse")) {
					exist = true;
				}else if(((Commentaire) obb).getId()==rs.getInt("idcommentaire")) {
					exist = true;
				}else exist=false;

		    	
				
		    	
		    	
		    	
		    	}

			}
		} catch (SQLException ex) {
			Logger.getLogger(Annonce.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exist;
	}

	



}

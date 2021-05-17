package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Crud {
	Savepoint savepoint;
	public void create(ConnexionBD cBD, String nom) {
		String insert = "INSERT INTO `etudiant`(`id`, `nom`) VALUES (null,'"+ nom +"')";
		try {
			cBD.getStatement().executeUpdate(insert);
			System.out.println("Insertion reussi");
		} catch (SQLException e) {
			System.out.println("Error Insert !!! " + e.getMessage());
		}
	}
	public void read(ConnexionBD cBD) {
		String sql = "SELECT * FROM `etudiant`";
		try {
			ResultSet rs = cBD.getStatement().executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				System.out.println("ID: " + id + " -- " + "Nom: " + nom);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(ConnexionBD cBD,String nom,int id) throws SQLException {
		String query = "UPDATE `etudiant` SET `nom`= ? WHERE `ID` = ?";
		PreparedStatement pr = cBD.getConnection().prepareStatement(query);
		pr.setString(1, nom);
		pr.setInt(2, id);
		pr.executeUpdate();
	}
	
	public void delete(ConnexionBD cBD,String nom) throws SQLException {
		String delete = "DELETE FROM `etudiant` WHERE `nom` = '"+ nom +"'";
		cBD.getStatement().executeUpdate(delete);
	}
	
	public void insertAutoComit(ConnexionBD cBD,String nom) throws SQLException
    {
         try {
             cBD.getConnection().setAutoCommit(false);
             
             savepoint = cBD.getConnection().setSavepoint("Savepoint-1");
             System.out.println("Savepoint-1");
             String insert = "INSERT INTO `etudiant`(`id`, `nom`) VALUES (null,'"+ nom +"')";
             cBD.getStatement().executeUpdate(insert);
             
             String insert1 = "INSERT IN `etudiant`(`id`, `nom`) VALUES (null,'"+ nom +"')";
             cBD.getStatement().executeUpdate(insert1);
             cBD.getConnection().commit();
             
         } catch (SQLException ex) {
        	 cBD.getConnection().rollback(savepoint);
             System.out.println("RollBack");
         }
        




}
}

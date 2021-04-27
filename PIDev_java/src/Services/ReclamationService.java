/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Reclamation;
import interfaces.iServiceReclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DataSource;

/**
 *
 * @author Djap_ii
 */
public class ReclamationService implements iServiceReclamation<Reclamation>{
    
        private Connection con = DataSource.getInstance().getCnx();
@Override
    public String getEmail(int iduser){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `email` FROM `user` WHERE `id`=" + iduser);

            if(rs.next())
            {
                String s = new String();
                s=rs.getString("email");
                return s;
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return null;
    }
        
        
    @Override
    public void Ajouter(Reclamation e) {
        try{
		PreparedStatement preparedStmt = con.prepareStatement("insert into reclamation (title,type,date_reclamation,description_reclamation,status,email,id_user) values (?,?,?,?,?,?,?)");
		
                preparedStmt.setString(1,e.getTitle());
                preparedStmt.setString(2,e.getType());
                preparedStmt.setString(3,e.getDateRec());
                preparedStmt.setString(4,e.getDescRec());
		preparedStmt.setString(5,e.getStatus());
		preparedStmt.setString(6,e.getEmail());
                preparedStmt.setInt(7,1);
              
                preparedStmt.execute();
                System.out.println("Insertion Avec Succes");
            }
        catch (SQLException ex) { ex.printStackTrace();}
    }    

    @Override
    public void Supprimer(Reclamation e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from reclamation where id= ?");
	    preparedStmt.setInt(1,e.getId());
            preparedStmt.executeUpdate();
            } 
        catch (SQLException ex) {System.err.println(ex.getMessage());}
    }

    @Override
    public void Modifier(Reclamation e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update reclamation set title=? ,type=? ,description_reclamation=? where id=?");
	    preparedStmt.setString(1,e.getTitle());
	   preparedStmt.setString(2,e.getType());
	   preparedStmt.setString(3,e.getDescRec());
            preparedStmt.setInt(4,e.getId());
	   
            preparedStmt.execute();
            } 
        catch (SQLException ex) {System.err.println(ex.getMessage());}
    }

    public ArrayList<Reclamation> Lister(int uid) {
        ArrayList<Reclamation> res = new ArrayList<Reclamation>();
    try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM reclamation WHERE id_user = " + uid;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String title=rs.getString("title");
                String type = rs.getString("type");
                String dateRec = rs.getString("date_reclamation");
                String descRec = rs.getString("description_reclamation");
                String status = rs.getString("status");
                String email=rs.getString("email");
                
               
                
                Reclamation R = new Reclamation (id,title,type,dateRec,descRec,status,email);
                res.add(R);
            }
            rs.close();
            } 
        catch (SQLException e) {System.err.println(e.getMessage());}
        return res;
    }    
    
     public ArrayList<Reclamation> Lister() {
        ArrayList<Reclamation> res = new ArrayList<Reclamation>();
    try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM reclamation ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String title=rs.getString("title");
                String type = rs.getString("type");
                String dateRec = rs.getString("date_reclamation");
                String descRec = rs.getString("description_reclamation");
                String status = rs.getString("status");
                String email=rs.getString("email");
                
               
                
                Reclamation R = new Reclamation (id,title,type,dateRec,descRec,status,email);
                res.add(R);
            }
            rs.close();
            } 
        catch (SQLException e) {System.err.println(e.getMessage());}
        return res;
    }    

}

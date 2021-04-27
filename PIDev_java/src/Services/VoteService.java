/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Entity.Formation;
import Entity.Votes;
import interfaces.iService_votes;
import utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */



public class VoteService implements iService_votes <Votes>{

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    
    
    
    public int getVotes(Formation v) throws SQLException {
        int i = 0;
        cnx = DataSource.getInstance().getCnx();
        String req = "select Count(post_id) as x from post_like where post_id="+v.getId();
        Statement st=cnx.createStatement();
        rs= st.executeQuery(req);
        if(rs.next()){
            return rs.getInt("x");
        }
        try {
            
            pst=cnx.prepareStatement(req);
            pst.setInt(1,v.getId());
            pst.executeUpdate();
            
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    
    
    
    
    
  
    
    public void Add(Formation v) throws SQLException {

        
        String req = "insert into post_like (post_id) values((select id from video where id='"+v.getId()+"'))";
        cnx = DataSource.getInstance().getCnx();
        //Statement st=cnx.createStatement();
       // rs= st.executeQuery(req);
        try {

 
            pst=cnx.prepareStatement(req);
            pst.executeUpdate();
            
           


       } catch (SQLException ex) {
                Logger.getLogger(VoteService.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    
     
            
    
    public void delete(Formation v) {
        String req = "delete from post_like where post_id=?";
        cnx = DataSource.getInstance().getCnx();
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,v.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
    
  /*  public Boolean find(Formation v, User u) {
        String req = "select * from votes where video_id=? and user_id=?";
        try {
            System.out.println(v.getId());
            System.out.println(u.getId());
            System.out.println(connection);
            
            
            ste = cnx.createStatement();
            ste.executeUpdate(req);
           

            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());


            pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
    
    
    
    
    @Override
    public void add(Votes vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Votes vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVotes(Votes vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean find(Votes vo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}


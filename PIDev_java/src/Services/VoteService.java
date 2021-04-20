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

/**
 *
 * @author User
 */



public class VoteService implements iService_votes <Votes>{

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    
    
    
    public int getVotes(Formation v) {
        int i = 0;
        String req = "select Count(post_id) from post_like where post_id=?";
       
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
    
    
    
    
    
    
  /*  lezim ba3ed integration tet7al bich ya9ra user 
    
    public void Add(User u, Formation v) {

        String req = "insert into post_like values((select id from video where id=?),(select id from user where id=?))";
        try {
            System.out.println(v.getId());
            System.out.println(u.getId());
            System.out.println(connection);
            
            
            ste = cnx.createStatement();
            ste.executeUpdate(req);
           

            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
*/
    
    public void delete(Formation v, User u) {
        String req = "delete from post_like where post_id=? and user_id=?";

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


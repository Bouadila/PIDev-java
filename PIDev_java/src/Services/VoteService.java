/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Entity.formation;
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

/*

public class VoteService {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public VoteService() {
        connection = DataSource.getInstance().getCnx();
    }

    public void Add(User u, formation v) {

        String req = "insert into post_like values((select id from video where id=?),(select id from user where id=?))";
        try {
            
            pst = connection.prepareStatement(req);

            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(formation v, User u) {
        String req = "delete from post_like where video_id=? and user_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean find(formation v, User u) {
        String req = "select * from post_like where video_id=? and user_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
 
    public int getVotes(formation v) {
        int i = 0;
        String req = "select Count(video_id) from post_like where video_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            rs = pst.executeQuery();
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public List<formation> OrderedByVotes() {
        String req = "SELECT  * FROM post_like v "
                + "inner join video  on(video.id=v.video_id) "
                + "inner join user on(video.owner=user.id)"
                + " GROUP by v.video_id "
                + "ORDER by count(v.video_id)"
                + " DESC ";
        List<formation> l = new ArrayList<>();
        try {
           ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                l.add(new formation(rs.getInt("video.id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("user.id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("sexe"),
                                rs.getString("adresse"),
                                rs.getString("name"),
                                rs.getString("first_name"),
                                rs.getString("telephone_number"),
                                rs.getString("bio"),
                                rs.getString("roles"),
                                rs.getDate("birthday"),
                                rs.getString("profile_pic"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    public List<formation> OrderedByNewest() {
        String req = "SELECT  * FROM video "
                 
                + "inner join user on(video.owner=user.id)"
                
                + "order by video.publish_date"
                + " DESC ";
        List<formation> l = new ArrayList<>();
        try {
           ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                l.add(new formation(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                       new User(rs.getInt("user.id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("sexe"),
                                rs.getString("adresse"),
                                rs.getString("name"),
                                rs.getString("first_name"),
                                rs.getString("telephone_number"),
                                rs.getString("bio"),
                                rs.getString("roles"),
                                rs.getDate("birthday"),
                                rs.getString("profile_pic")))); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    
}
*/

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */


/*
public class FormationService {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public FormationService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public List<formation> getAll() {
        String req = "select * from video inner join user on(video.owner=user.id) ";
        List<formation> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new formation(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("id"),
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
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<formation> getVideos(int owner) {
        String req = "select * from video inner join user on(video.owner=user.id) where owner=" + owner;
        List<formation> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                list.add(new formation(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("id"),
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
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean AddVideo(formation v) {
        String url = "";
        String find = "select url from video where url = '" + v.getUrl() + "'";

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(find);
            while (rs.next()) {
                url = rs.getString("url");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (v.getUrl().equals(url)) {
            System.out.println("Formation exitst !");
            return false;
        } else {
            String sql = "insert into video(url,title,publish_date,description,domaine,owner) values('" + v.getUrl() + "','" + v.getTitle() + "','" + v.getPublish_date() + "','" + v.getOwner().getId() + "')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
     public void delete(formation v){
        String req="delete from video where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,v.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

*/
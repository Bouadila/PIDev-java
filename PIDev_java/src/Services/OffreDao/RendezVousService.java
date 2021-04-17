/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.OffreDao;

import Entity.Contrat;
import Entity.RendezVous;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author brahm
 */
public class RendezVousService {
    Connection cnx = DataSource.getInstance().getCnx();
    public void add(RendezVous entity) {
        String req ="INSERT INTO `rendezvous`( `candidature_id`, `titre`, `start`, "
                + "`end`, `description`, `all_day`, `background_color`, `border_color`,"
                + " `text_color`, `accepte`, `room`)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, entity.getIdCandidature());
            ps.setString(2, entity.getTitre());
            ps.setDate(3, (Date) entity.getStart());
            ps.setDate(4, (Date) entity.getEnd());
            ps.setString(5,entity.getDescription());
            ps.setBoolean(6, entity.isAllDay());
            ps.setString(7, entity.getBackgroundColor());
            ps.setString(8, entity.getBorderColor());
            ps.setString(9, entity.getTextColor());
            ps.setBoolean(10, entity.isAccepte());
            ps.setString(11, entity.getRoom());
            int i = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void edite(RendezVous entity) {
        try {
        PreparedStatement ps = cnx.prepareStatement("UPDATE `rendezvous` SET `candidature_id`=?,`titre`=?,`start`=?,"
                + "`end`=?,`description`=?,`all_day`=?,`background_color`=?,`border_color`=?,"
                + "`text_color`=?,`accepte`=?,`room`=? WHERE `id`= ?");
        ps.setInt(1, entity.getIdCandidature());
            ps.setString(2, entity.getTitre());
            ps.setDate(3, (Date) entity.getStart());
            ps.setDate(4, (Date) entity.getEnd());
            ps.setString(5,entity.getDescription());
            ps.setBoolean(6, entity.isAllDay());
            ps.setString(7, entity.getBackgroundColor());
            ps.setString(8, entity.getBorderColor());
            ps.setString(9, entity.getTextColor());
            ps.setBoolean(10, entity.isAccepte());
            ps.setString(11, entity.getRoom());
            ps.setInt(12, entity.getId());
        int i = ps.executeUpdate();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }
     public void delete(RendezVous entity) {
         try {
        Statement stmt = cnx.createStatement();
        int i = stmt.executeUpdate("DELETE FROM `rendezvous` WHERE id=" + entity.getId());
        } 
         catch (SQLException ex) {
            ex.printStackTrace();
          }
    }
     public ArrayList<RendezVous> getAll() {
        List <RendezVous> rdvs = new ArrayList <RendezVous>();
        try {
            String sql ="SELECT * FROM `rendezvous`";
            PreparedStatement ps;
            ps = cnx.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                RendezVous rdv = new RendezVous();
                rdv.setId(rs.getInt("id"));
                rdv.setIdCandidature(rs.getInt("candidature_id"));
                rdv.setTitre(rs.getString("titre"));
                rdv.setStart(rs.getDate("start"));
                rdv.setEnd(rs.getDate("end"));
                rdv.setDescription(rs.getString("description"));
                rdv.setAllDay(rs.getBoolean("all_day"));
                rdv.setBackgroundColor(rs.getString("background_color"));
                rdv.setBorderColor(rs.getString("border_color"));
                rdv.setTextColor(rs.getString("text_color"));
                rdv.setAccepte(rs.getBoolean("accepte"));
                rdv.setRoom(rs.getString("room"));
                rdvs.add(rdv);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<RendezVous>) rdvs;
            }
     public RendezVous getOne(int id) {
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `rendezvous` WHERE id=" + id);

            if(rs.next())
            {
                RendezVous rdv = new RendezVous();
                rdv.setId(rs.getInt("id"));
                rdv.setIdCandidature(rs.getInt("candidature_id"));
                rdv.setTitre(rs.getString("titre"));
                rdv.setStart(rs.getDate("start"));
                rdv.setEnd(rs.getDate("end"));
                rdv.setDescription(rs.getString("description"));
                rdv.setAllDay(rs.getBoolean("all_day"));
                rdv.setBackgroundColor(rs.getString("background_color"));
                rdv.setBorderColor(rs.getString("border_color"));
                rdv.setTextColor(rs.getString("text_color"));
                rdv.setAccepte(rs.getBoolean("accepte"));
                rdv.setRoom(rs.getString("room"));
                return rdv;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return null;
    }
    
}

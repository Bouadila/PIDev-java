/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.OffreDao;

import Entity.Contrat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author brahm
 */
public class ContratService implements IService<Contrat> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(Contrat entity) {
        String req ="INSERT INTO `contrat`(`description`, `type`) VALUES (?,?)";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, entity.getDescription());
            ps.setString(2, entity.getType());
            int i = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edite(Contrat entity) {
        try {
        PreparedStatement ps = cnx.prepareStatement("UPDATE `contrat` SET `description`=?,`type`=? WHERE id=?");
        ps.setString(1, entity.getDescription());
        ps.setString(2, entity.getType());
        ps.setInt(3, entity.getId());
        int i = ps.executeUpdate();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    }

    @Override
    public void delete(Contrat entity) {
         try {
        Statement stmt = cnx.createStatement();
        int i = stmt.executeUpdate("DELETE FROM `contrat` WHERE id=" + entity.getId());
        } 
         catch (SQLException ex) {
            ex.printStackTrace();
          }
    }

    @Override
    public ArrayList<Contrat> getAll() {
        List <Contrat> Contrats = new ArrayList <Contrat>();
        try {
            String sql ="SELECT * FROM `contrat`";
            PreparedStatement ps;
            ps = cnx.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                Contrat c = new Contrat();
                c.setId(rs.getInt("id"));
                c.setDescription(rs.getString("description"));
                c.setType(rs.getString("type"));
                Contrats.add(c);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<Contrat>) Contrats;
            }

    @Override
    public Contrat getOne(int id) {
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `contrat` WHERE id=" + id);

            if(rs.next())
            {
                Contrat c = new Contrat();

                c.setId( rs.getInt("id") );
                c.setDescription(rs.getString("description"));
                c.setType(rs.getString("type"));
                return c;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return null;
    }
    
}

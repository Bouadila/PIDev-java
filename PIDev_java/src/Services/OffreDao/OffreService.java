/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.OffreDao;

import Entity.Contrat;
import Entity.Offre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author brahm
 */
public class OffreService {
    Connection cnx = DataSource.getInstance().getCnx();
    
    
     public int addOffreAndGetItsId(Offre offre, Contrat contrat , int idUser) throws SQLException{
        
        String sql="INSERT INTO `offre`( `contrat_id`, `entreprise_id`, `description`, "
                + "`salaire`, `date_depo`, `date_expiration`, `nombre_place`, `post`, `objectif`, `competences`, "
                + "`domaine`, `experience_min`, `experience_max`, `flag_supprimer`, `flag_expirer`) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String generatedColumns[] = { "ID" };
        PreparedStatement ps = cnx.prepareStatement(sql, generatedColumns);
         ps.setInt(1, contrat.getId());
            ps.setInt(2, idUser);
            ps.setString(3, offre.getDescription());
            ps.setInt(4,offre.getSalaire());
            // new java.sql.Date(affiliate.getDate().getTime())
            ps.setDate(5, new java.sql.Date(offre.getDateDepot().getTime()));
            ps.setDate(6, new java.sql.Date( offre.getDateExpiration().getTime()));
            ps.setInt(7, offre.getNombrePlace());
            ps.setString(8, offre.getPost());
            ps.setString(9, offre.getObjectif());
            ps.setString(10, offre.getCompetences());
            ps.setString(11, offre.getDomaine());
            ps.setInt(12, offre.getExperienceMin());
            ps.setInt(13, offre.getExperienceMax());
            ps.setBoolean(14, offre.isFlagSupprimer());
            ps.setBoolean(15, offre.isFlagExpirer());
            int i = ps.executeUpdate();
         try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating quiz failed, no ID obtained.");
            }
        }
    }
    
    
    
     public void add(Offre offre, Contrat contrat) {
        String req ="INSERT INTO `offre`( `contrat_id`, `entreprise_id`, `description`, "
                + "`salaire`, `date_depo`, `date_expiration`, `nombre_place`, `post`, `objectif`, `competences`, "
                + "`domaine`, `experience_min`, `experience_max`, `flag_supprimer`, `flag_expirer`) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, contrat.getId());
            ps.setInt(2, 1);
            ps.setString(3, offre.getDescription());
            ps.setInt(4,offre.getSalaire());
            // new java.sql.Date(affiliate.getDate().getTime())
            ps.setDate(5, new java.sql.Date(offre.getDateDepot().getTime()));
            ps.setDate(6, new java.sql.Date( offre.getDateExpiration().getTime()));
            ps.setInt(7, offre.getNombrePlace());
            ps.setString(8, offre.getPost());
            ps.setString(9, offre.getObjectif());
            ps.setString(10, offre.getCompetences());
            ps.setString(11, offre.getDomaine());
            ps.setInt(12, offre.getExperienceMin());
            ps.setInt(13, offre.getExperienceMax());
            ps.setBoolean(14, offre.isFlagSupprimer());
            ps.setBoolean(15, offre.isFlagExpirer());
            int i = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public void edite(Offre offre, Contrat contrat) {
         System.out.println(offre.getId());
        try {
        PreparedStatement ps = cnx.prepareStatement("UPDATE `offre` SET "
                + "`contrat_id`=?,"
                + "`description`=?,"
                + "`salaire`=?,"
                + "`date_expiration`=?,"
                + "`nombre_place`=?,"
                + "`post`=?,"
                + "`objectif`=?,"
                + "`competences`=?,"
                + "`domaine`=?,"
                + "`experience_min`=?,"
                + "`experience_max`=? "
                + "WHERE `id`=?");
            ps.setInt(1, contrat.getId());
            ps.setString(2, offre.getDescription());
            ps.setInt(3,offre.getSalaire());
            ps.setDate(4, new java.sql.Date( offre.getDateExpiration().getTime()));
            ps.setInt(5, offre.getNombrePlace());
            ps.setString(6, offre.getPost());
            ps.setString(7, offre.getObjectif());
            ps.setString(8, offre.getCompetences());
            ps.setString(9, offre.getDomaine());
            ps.setInt(10, offre.getExperienceMin());
            ps.setInt(11, offre.getExperienceMax());
            ps.setInt(12, offre.getId());
        int i = ps.executeUpdate();
            System.out.println(i);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }
     public void delete(Offre offre) {
        try {
        Statement stmt = cnx.createStatement();
        int i = stmt.executeUpdate("DELETE FROM `offre` WHERE id=" + offre.getId());
        } 
         catch (SQLException ex) {
            ex.printStackTrace();
          }
    }
     public ArrayList<Offre> getAll() {
        List <Offre> offres = new ArrayList <Offre>();
        try {
            String sql ="SELECT * FROM offre INNER JOIN contrat ON offre.contrat_id= contrat.id ORDER BY offre.date_depo DESC";
            PreparedStatement ps;
            ps = cnx.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                Offre o = new Offre();
                Contrat c = new Contrat();
                o.setId(rs.getInt("offre.id"));
                o.setPost(rs.getString("offre.post"));
                o.setObjectif(rs.getString("offre.objectif"));
                o.setCompetences(rs.getString("offre.competences"));
                o.setDescription(rs.getString("offre.description"));
                o.setDomaine(rs.getString("offre.domaine"));
                o.setSalaire(rs.getInt("offre.salaire"));
                o.setQuiz(rs.getInt("offre.quiz_id"));
                o.setNombrePlace(rs.getInt("offre.nombre_place"));
                o.setDateDepot(rs.getDate("offre.date_depo"));
                o.setDateExpiration(rs.getDate("offre.date_expiration"));
                o.setExperienceMin(rs.getInt("offre.experience_min"));
                o.setExperienceMax(rs.getInt("offre.experience_max"));
                o.setFlagExpirer(rs.getBoolean("offre.flag_expirer"));
                o.setFlagSupprimer(rs.getBoolean("offre.flag_supprimer"));
                c.setId(rs.getInt("contrat.id"));
                c.setDescription(rs.getString("contrat.description"));
                c.setType(rs.getString("contrat.type"));
                o.setContrat(c);
                offres.add(o);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<Offre>) offres;
        }
     public Offre getOne(int id) {
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offre INNER JOIN contrat ON offre.contrat_id= contrat.id WHERE offre.id=" + id);

            if(rs.next())
            {
                Offre o = new Offre();
                Contrat c = new Contrat();
                o.setId(rs.getInt("offre.id"));
                o.setPost(rs.getString("offre.post"));
                o.setObjectif(rs.getString("offre.objectif"));
                o.setCompetences(rs.getString("offre.competences"));
                o.setDescription(rs.getString("offre.description"));
                o.setDomaine(rs.getString("offre.domaine"));
                o.setSalaire(rs.getInt("offre.salaire"));
                o.setNombrePlace(rs.getInt("offre.nombre_place"));
                o.setDateDepot(rs.getDate("offre.date_depo"));
                o.setDateExpiration(rs.getDate("offre.date_expiration"));
                o.setExperienceMin(rs.getInt("offre.experience_min"));
                o.setExperienceMax(rs.getInt("offre.experience_max"));
                o.setFlagExpirer(rs.getBoolean("offre.flag_expirer"));
                o.setFlagSupprimer(rs.getBoolean("offre.flag_supprimer"));
                o.setQuiz(rs.getInt("offre.quiz_id"));
                c.setId(rs.getInt("contrat.id"));
                c.setDescription(rs.getString("contrat.description"));
                c.setType(rs.getString("contrat.type"));
                o.setContrat(c);
                return o;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return null;
    }
     public void addQuizzToOffre(int idQuiz,int id){
         try {
        PreparedStatement ps = cnx.prepareStatement("UPDATE `offre` SET "
                + "`quiz_id`=? "
                + "WHERE `id`=?");
            ps.setInt(1,idQuiz );
            ps.setInt(2, id);
        int i = ps.executeUpdate();
            System.out.println(i);
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
     }

}

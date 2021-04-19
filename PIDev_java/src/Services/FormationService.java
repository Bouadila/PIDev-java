/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entity.User;
import Entity.Formation;
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
import interfaces.iService_formation;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author User
 */



public class FormationService implements iService_formation<Formation>{

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public FormationService() {
        
   
        try {
            cnx = DataSource.getInstance().getCnx();
            ste = cnx.createStatement();
           
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
    /* hedhy ba3ed login 7aseb user connecter
    public List<formation> getVideos(int owner) {
        String req = "select * from video inner join user on(video.owner=user.id) where owner=" + owner;
        List<formation> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                list.add(new Formation(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
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
*/
    
    
    
    
    
    
    
    
    @Override
    public boolean ajouterVideo(Formation v) {
        
        Notifications notificationConfirm = Notifications.create()
        .title("Ajout Complete")
        .text("Ajouter dans la base de donnée")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.TOP_RIGHT);
        
         
        Notifications notificationError = Notifications.create()
        .title("Url existant")
        .text("Cet Url existe déja")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.TOP_RIGHT);
         
         
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
            notificationError.darkStyle();
            notificationError.showError();
            return false;
        } else {
            String sql = "insert into video(url,title,publish_date,description,domaine) values('" + v.getUrl() + "','" + v.getTitle() + "','" + v.getPublish_date() + "','" + v.getDescription() + "','" + v.getDomaine() + "')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);
                notificationConfirm.darkStyle();
                notificationConfirm.showConfirm();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
    
    
    
    
    
    
    
     public void supprimerVideo(Formation v){

      Notifications notificationDelete = Notifications.create()
        .title("Formation supprimer")
        .text("Votre formation est supprimer avec succée")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.TOP_RIGHT);
            String req="delete from video where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,v.getId());
            pst.executeUpdate();
            notificationDelete.darkStyle();
            notificationDelete.showConfirm();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     


      
    

     
     
     
     public ObservableList<Formation> getAll() {
        String req = "select * from video";
        ObservableList<Formation> FormationList = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Formation v = new Formation();
                v.setId(rs.getInt("id"));
                v.setTitle(rs.getString("title"));
                v.setUrl(rs.getString("url"));
                v.setDescription(rs.getString("description"));
                v.setDomaine(rs.getString("domaine"));
                //v.setPublish_date(rs.getTimestamp(5));

                FormationList.add(v);
               
                //list.add(new Formation(rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"), rs.getString("description"), rs.getString("domaine")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FormationList;
    }
     
     
     
    
     
     
     
     
     
     
    @Override
    public void modifierVideo(Formation v) {
           
        
        
        try {
            String requete = "update video set url='" + v.getUrl() + "', title='" + v.getTitle()+
                    "',publish_date='" + v.getPublish_date()+ "',description='" + v.getDescription()+ "',domaine='" 
                    + v.getDomaine() +"' where id="+v.getId();
     
                
           ste = cnx.createStatement();
            ste.executeUpdate(requete);
            
            System.out.println(v.getId());
            System.out.println("Formation modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
   
    }

    
    
   
    @Override
    public String getVideoById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Formation> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Formation> getAllList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     
     
     
     

    
     
     
     
  
}


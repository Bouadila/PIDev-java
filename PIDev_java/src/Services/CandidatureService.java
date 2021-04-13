/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author A.L.I.C.E
 */

import Entity.User;
import Entity.Candidature;
import interfaces.iService_candidature;
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
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CandidatureService implements iService_candidature<Candidature>{

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CandidatureService() {
        
   
        try {
            cnx = DataSource.getInstance().getCnx();
            ste = cnx.createStatement();
           
        } catch (SQLException ex) {
            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }     
    
    @Override
    public boolean ajouterCandidature(Candidature c) {
        //int candidat = ;
        //int offre = ;
        //String find = "select url from video where url = '" + v.getUrl() + "'";

//        try {
//            ste = cnx.createStatement();
//            rs = ste.executeQuery(find);
//            while (rs.next()) {
//                url = rs.getString("url");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        if (c.getCandidat().equals(candidat) && c.getOffre().equals(offre)) {
//            System.out.println("Formation exitst !");
//            return false;
//        } else {
            String sql = "insert into candidature(nom,prenom,sexe,email,date_naiss,num,status,diplome,cv,date_candidature,candidat) values('" + c.getNom() + "','" + c.getPrenom() + "','"+ c.getSexe() + "','" + c.getEmail() + "','" + c.getDate_naiss() + "','" + c.getStatus() + "','" + c.getDiplome() + "','" + c.getCv() + "','" + c.getDate_candidature() + "','" + c.getCandidat() + "')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
            }
     //   }

        return false;
    }

    @Override
    public ArrayList<Candidature> afficherCandidature() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Candidature> getAll() {
        String req = "select * from candidature";
        ObservableList<Candidature> CandidatureList = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                Candidature c = new Candidature();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setSexe(rs.getString("sexe"));
                c.setEmail(rs.getString("email"));
                c.setDate_naiss(rs.getDate("date_naiss"));
                c.setNum(rs.getInt("num"));
                c.setStatus(rs.getString("status"));
                c.setDiplome(rs.getString("diplome"));
                c.setCv(rs.getString("cv"));
                c.setDate_candidature(rs.getDate("date_candidature"));
                CandidatureList.add(c);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CandidatureList;
    }
     
     
     

}

     
  



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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    
      public int addConAndGetItsId(Candidature c) throws SQLException{
        
        String sql="insert into candidature(nom,prenom,email,date_naiss,num,status,diplome,cv,date_candidature,dispo,lettre_motiv,candidat_id,offre_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String generatedColumns[] = { "ID" };
        PreparedStatement statement = cnx.prepareStatement(sql, generatedColumns);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getPrenom());
        statement.setString(3, c.getEmail());
        statement.setDate(4, c.getDate_naiss());
        statement.setInt(5, c.getNum());
        statement.setString(6, c.getStatus() );
        statement.setString(7, c.getDiplome() );
        statement.setString(8, c.getCv() );
        statement.setDate(9, c.getDate_candidature() );
        statement.setDate(10, c.getDispo() );
        statement.setString(11, c.getLettre_motiv() );
        statement.setInt(12,UserSession.getIdSession());
        statement.setInt(13, c.getOffre_id());
        statement.executeUpdate();
         try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating quiz failed, no ID obtained.");
            }
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
            String sql = "insert into candidature(nom,prenom,email,date_naiss,num,status,diplome,cv,date_candidature,dispo,lettre_motiv,candidat_id,offre_id) values('" +c.getNom() + "','" + c.getPrenom() + "','" + c.getEmail()+ "','"+ c.getDate_naiss() +"','"+c.getNum()+"','" + c.getStatus() + "','" + c.getDiplome() +"','"+ c.getCv() + "','" + c.getDate_candidature()+ "','" + c.getDispo() + "','" + c.getLettre_motiv() + "'," + UserSession.getIdSession()+ "," + c.getOffre_id() + ")";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
            }
     //   }
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
                c.setEmail(rs.getString("email"));
                c.setDate_naiss(rs.getDate("date_naiss"));
                c.setNum(rs.getInt("num"));
                c.setStatus(rs.getString("status"));
                c.setDiplome(rs.getString("diplome"));
                c.setCv(rs.getString("cv"));
                c.setLettre_motiv(rs.getString("lettre_motiv"));
                c.setDispo(rs.getDate("dispo"));
                c.setDate_candidature(rs.getDate("date_candidature"));
                CandidatureList.add(c);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CandidatureList;
    }
    
    public List<Candidature> getCandById() throws SQLException{
        
        List<Candidature> listCand = new ArrayList();
        int candidat_id = UserSession.getIdSession();
        String sql="SELECT * FROM candidature where candidat_id ="+candidat_id;
        Statement st=cnx.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            int num = res.getInt("num");
            String status = res.getString("status");
            String diplome = res.getString("diplome");
            String cv = res.getString("cv");
            Date date_candidature = res.getDate("date_candidature");
            Date dispo = res.getDate("dispo");
            String lettre_motiv = res.getString("lettre_motiv");
            int offre_id = res.getInt("offre_id");
            candidat_id = res.getInt("candidat_id");
            Candidature cand = new Candidature (id,num,status,diplome,cv,date_candidature,dispo,lettre_motiv,candidat_id,offre_id);
            listCand.add(cand);
        }
            return listCand;
    }
    
   
    
    public List<Candidature> getCand() throws SQLException{
        
        List<Candidature> listCand = new ArrayList();
        String sql="SELECT * FROM candidature";
        Statement st=cnx.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            int num = res.getInt("num");
            String status = res.getString("status");
            String diplome = res.getString("diplome");
            String cv = res.getString("cv");
            Date date_candidature = res.getDate("date_candidature");
            Date dispo = res.getDate("dispo");
            String lettre_motiv = res.getString("lettre_motiv");
            Candidature cand = new Candidature (id,num,status,diplome,cv,date_candidature,dispo,lettre_motiv);
            listCand.add(cand);
        }
            return listCand;
    }

    
      public void supprimerCandidature(Candidature c){

     
            String req="delete from candidature where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,c.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
    
    

    public void modifierCandidature(Candidature c) {
           
       
        try {

            String requete = "update candidature set num='"+c.getNum()+"',status='"+c.getStatus()+"',diplome='"+c.getDiplome()+"' where id="+c.getId();
     
            
           ste = cnx.createStatement();
            ste.executeUpdate(requete);
            
            System.out.println(c.getId());
            System.out.println("Candidature modifiée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void notifsuccess(String message){
        String title = "Congratulations";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
    public static void notiferror(String message){
        String title = "Failed";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndDismiss(javafx.util.Duration.seconds(3));
    }
    

      
//    public static sendMail(String recepient) {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        
//        String myAccountEmail = "pidevtestad@gmail.com";
//        String password ="pidevtestad123456";
//        
//        Session session = Session.getInstance(properties, new Authenticator(){
//            
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//       
//        Message message = prepareMessage(session, myAccountEmail, recepient);
//        Transport.send(message);
//    }
//    
//    private static prepareMessage(Session session, String myAccountEmail, String recepient){
//        
//        Message message = new MimeMessage(session);
//        try {
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.addRecipient(message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Votre candidature a été ajouté");
//            message.setText("Votre candidature a été ajouté avec success");
//            
//        } catch (MessagingException e) {
//            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, e);
//        }
//            
//    }
    
    @Override
    public ObservableList<Candidature> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


   
     
     
     

}

     
  



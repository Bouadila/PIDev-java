/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import static Services.UserSession.setIdSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
 import java.util.Properties;    
import javafx.scene.paint.Color;
import javax.mail.*;    
import javax.mail.internet.*;  
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserResetPwdController implements Initializable {

    @FXML
    private Label inscrireUser;
    @FXML
    private Label check;
    @FXML
    private Button log;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtcode;
    @FXML
    private Button log1;
    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink offre;
    @FXML
    private Hyperlink demande;
    @FXML
    private Hyperlink annonce;
    @FXML
    private Hyperlink login1;
    @FXML
    private Hyperlink quiz;
    Connection con = DataSource.getInstance().getCnx();

    int randomCode;
           
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Register(MouseEvent event) {
    }


    @FXML
    private void goToAcceuil(ActionEvent event) {
    }

    @FXML
    private void goToProfil(ActionEvent event) {
    }

    @FXML
    private void goTooffre(ActionEvent event) {
    }

    @FXML
    private void goTodemande(ActionEvent event) {
    }

    @FXML
    private void goToannoce(ActionEvent event) {
    }

    @FXML
    private void goToLogin(ActionEvent event) {
    }

    @FXML
    private void quiz(ActionEvent event) {
        
    }


    @FXML
    private void actEnvoyer(javafx.scene.input.MouseEvent event) throws SQLException {
              //Get properties object    
               String req = "SELECT *  FROM user Where email = '"+txtemail.getText()+"'";
       
       PreparedStatement ps = con.prepareStatement(req);
        
       ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
                 JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");
      
        } else {           

            int k = rs.getInt("id");
            setIdSession(k);
                      Random rand =new Random();
        randomCode=rand.nextInt(999999);
        String host ="smtp.gmail.com" ;
        String from ="pidevtestad@gmail.com" ;
        String password ="pidevtestad123456" ;
        String to =txtemail.getText();
        String sub ="Mot de passe oublier " ;
        String msg ="Ton code est : " +randomCode ;

          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.starttls.enable", "true");    
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

          props.put("mail.smtp.port", "587");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });  
                     System.out.println("message en cour successfully");    

          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");
                 JOptionPane.showMessageDialog(null, "code envoyer a l'email");

          } catch (MessagingException e) {
                         System.out.println("message didn't sent successfully");    
                 JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");

              throw new RuntimeException(e);}    
              
        }     
}    
        

    @FXML
    private void ActVerifier(javafx.scene.input.MouseEvent event) throws IOException {
        if(txtcode.getText().equals(""+randomCode))
        {
            User rs=new User (txtemail.getText());
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserReset.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
        
        else{
                         JOptionPane.showMessageDialog(null, "code incorrect");}
      


    }
    
    
}

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
    private Button profil;
    @FXML
    private Button login1;
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
    private void Register(MouseEvent event) throws IOException {
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }



    @FXML
    private void goToProfil(ActionEvent event) throws IOException{
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }


    @FXML
    private void goToLogin(ActionEvent event) {
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
           message.setContent("<!doctype html>\n"
                    + "<html>\n"
                    + "  <head>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width\" />\n"
                    + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                    + "    <title>Mot de passe oublier</title>\n"
                    + "    <style>\n"
                    + "\n"
                    + "      body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        font-family: sans-serif;\n"
                    + "        -webkit-font-smoothing: antialiased;\n"
                    + "        font-size: 14px;\n"
                    + "        line-height: 1.4;\n"
                    + "        margin: 0;\n"
                    + "        padding: 0;\n"
                    + "        -ms-text-size-adjust: 100%;\n"
                    + "        -webkit-text-size-adjust: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      table {\n"
                    + "        border-collapse: separate;\n"
                    + "        mso-table-lspace: 0pt;\n"
                    + "        mso-table-rspace: 0pt;\n"
                    + "        width: 100%; }\n"
                    + "        table td {\n"
                    + "          font-family: sans-serif;\n"
                    + "          font-size: 14px;\n"
                    + "          vertical-align: top; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "      .body {\n"
                    + "        background-color: #f6f6f6;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .container {\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto !important;\n"
                    + "        /* makes it centered */\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px;\n"
                    + "        width: 580px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .content {\n"
                    + "        box-sizing: border-box;\n"
                    + "        display: block;\n"
                    + "        margin: 0 auto;\n"
                    + "        max-width: 580px;\n"
                    + "        padding: 10px; \n"
                    + "      }\n"
                    + "\n"
                    + "      .main {\n"
                    + "        background: #ffffff;\n"
                    + "        border-radius: 3px;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "\n"
                    + "      .wrapper {\n"
                    + "        box-sizing: border-box;\n"
                    + "        padding: 20px; \n"
                    + "      }\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "      .footer {\n"
                    + "        clear: both;\n"
                    + "        margin-top: 10px;\n"
                    + "        text-align: center;\n"
                    + "        width: 100%; \n"
                    + "      }\n"
                    + "        .footer td,\n"
                    + "        .footer p,\n"
                    + "        .footer span,\n"
                    + "        .footer a {\n"
                    + "          color: #999999;\n"
                    + "          font-size: 12px;\n"
                    + "          text-align: center; \n"
                    + "      }\n"
                    + "\n"
                    + "    </style>\n"
                    + "  </head>\n"
                    + "  <body class=\"\">\n"
                    + "    <span class=\"preheader\"></span>\n"
                    + "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n"
                    + "      <tr>\n"
                    + "        <td class=\"container\">\n"
                    + "          <div class=\"content\">\n"
                    + "\n"
                    + "\n"
                    + "            <table role=\"presentation\" class=\"main\">\n"
                    + "\n"
                    + "              <tr>\n"
                    + "                <td class=\"wrapper\">\n"
                    + "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                    <tr>\n"
                    + "                      <td>\n"
                    + "                          <p> <img src=\"https://i.ibb.co/tmMX6Hn/recruitini-logo.png?fbclid=IwAR2tFKmk1cpPbl36C3txf49oGSWoSIiWMUlvLUUOyzMhlEWH8a-00RXKtW8\" style=\"display: block;margin-left: auto;margin-right: auto; width: 50%\"> </p>\n"
                    + "                        <span style=\"font-weight: bold\">Chèr(e) client </span>\n"
                    + "                         \n"
                    + "                        <p>Afin de pouvoir réinitialiser votre mot de passe, nous devons valider votre adresse e-mail.</p>\n"
                    + "                        <p> Récrire simplement le code envoyer.</p>\n"
                    + "                        "
                    + " <span style=\"font-weight: bold;color: red\">" + msg + "  </span>\n"
                    + "<p>Pour plus d'information n'hésitez pas à nous contacter sur : </p>\n"
                    + "                           <a href=\"mailto:recrutini@gmail.com?subject=réinitialiser%20mot%20de%20passe\">\n"
                    + "                            <img src=\"https://upload.wikimedia.org/wikipedia/commons/archive/4/4e/20160129092800%21Gmail_Icon.png\" height=\"30\" width=\"30\" style=\"border: none;display: inline-block;color: white;\"></a>\n"
                    + "                          <a href=\"https://www.facebook.com/bureau.alphaservice/\" target=\"_blank\"> <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Facebook-icon-1.png/900px-Facebook-icon-1.png\" height=\"30\" width=\"30\"> </a>\n"
                    + "                          \n"
                    + "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n"
                    + "                          <tbody>\n"
                    + "                            <tr>\n"
                    + "                              <td align=\"left\">\n"
                    + "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                                  <tbody>\n"
                    + "                                  </tbody>\n"
                    + "                                </table>\n"
                    + "                              </td>\n"
                    + "                            </tr>\n"
                    + "                          </tbody>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "\n"
                    + "            </table>\n"
                    + "\n"
                    + "            <div class=\"footer\">\n"
                    + "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block\">\n"
                    + "                    <span class=\"apple-link\">2202 Residence El Amen,Ariana</span>\n"
                    + "                   \n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "                <tr>\n"
                    + "                  <td class=\"content-block powered-by\">\n"
                    + "                    Agence <a href=\"http://htmlemail.io\">Recrutini</a>.\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "              </table>\n"
                    + "            </div>\n"
                    + "\n"
                    + "          </div>\n"
                    + "        </td>\n"
                    + "      </tr>\n"
                    + "    </table>\n"
                    + "  </body>\n"
                    + "</html>", "text/html; charset=utf-8");

           //send message  
           Transport.send(message); 
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

    @FXML
    private void offre(MouseEvent event) {
    }

    @FXML
    private void rendezVous(MouseEvent event) {
    }
    
    
}

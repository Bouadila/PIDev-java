/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import Services.UserService;
import Services.UserSession;
import static Services.UserSession.setIdSession;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import utils.DataSource;
 import org.json.JSONArray;
  import org.json.JSONObject;
import org.json.JSONString;
import static org.json.XMLTokener.entity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javafx.concurrent.Task;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserAddController implements Initializable{

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Label login;
    @FXML
    private ToggleGroup Roles;
 
    @FXML
    private Button profil;
    @FXML
    private Button login1;
    @FXML
    private ComboBox<String> tfSpecial;
    static int randomCodee;
    private FileChooser fileChooser ;
    private File file ;
    private Stage stage ;
    @FXML
    private AnchorPane anchorPane ;
    private final Desktop desktop =Desktop.getDesktop();
    private Image image ;
    private String imgp;
    private URI imguriUri;
    private FileInputStream fis ;


    /**
     * Initializes the controller class.
     */
           Connection con = DataSource.getInstance().getCnx();
    @FXML
    private RadioButton roleCandidat;
    @FXML
    private RadioButton roleEmployeur;
           ObservableList<String> list_gov
            = FXCollections.observableArrayList(
                    "Ariena",
                    "Tunis",
                    "Sfax",
                    "Nabeul",
                    "Sousse");
  
             ObservableList<String> list_spec
            = FXCollections.observableArrayList(
                    "Informatique",
                    "Math",
                    "Immobiliér",
                    "Marketing",
                    "Médecin");
           
           
    @FXML
    private ComboBox<String> tfGover;
    @FXML
    private Button txtimgchose;
    @FXML
    private ImageView imageview;
    @FXML
    private Label lb_image;
//    Thread myTaskThread;
//    Thread myRunnableThread;
//    Timer myTimer;
//    MyTask myTask;

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
//        fileChooser =new FileChooser();
//        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("image","*.png","*.jpg","*.gif") 
//        );
//         inscrireUser = new MyTask();
//         myTaskThread = new Thread(inscrireUser);
//         myTaskThread.start();
                boolean RG = tfGover.getSelectionModel().isEmpty();
                boolean RS = tfSpecial.getSelectionModel().isEmpty();


        if (RG) {
            tfGover.setValue("Choisir Votre Governorat");
        }
        if (RS) {
            tfSpecial.setValue("Choisir Votre Spécialiter");
        }
        tfGover.setItems(list_gov);
        tfSpecial.setItems(list_spec);
 }    


    @FXML
    private void inscrireUser(ActionEvent event) throws SQLException, IOException  {
     String leRole="";
             int etat = 0;
             
            if(roleCandidat.isSelected()){
            leRole="Candidat";   
        }
        if(roleEmployeur.isSelected()){
            leRole="Employeur";
        }                 
        String mail = tfEmail.getText();
        String user = tfNom.getText();
        String pwd = tfPassword.getText();
        String gov = tfGover.getValue();
        String spec = tfSpecial.getValue();
        String pren = tfPrenom.getText();
//        fis = new FileInputStream(file) ;
//        String img = tfImg.getText();
String profilePic = lb_image.getText();

        String password8 = BCrypt.hashpw(tfPassword.getText(),BCrypt.gensalt(13));
        password8 = password8.replaceFirst("2a", "2y") ;
             
        if(mail.equals("") || user.equals("") || pwd.equals("") || leRole.equals("") || gov.equals("Choisir Votre Governorat") || spec.equals("Choisir Votre Spécialiter") || pren.equals("") ||profilePic.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" S'il Vous Plait vérifer touts les champs .");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            alert.showAndWait();
        }
        if (imgp!=null) {
            try {
                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Desktop\\PIDev-java\\PIDev_java\\src\\image\\" + imgp));
                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Documents\\pidev\\ProjPiDev\\public\\uploads\\image\\" + imgp));
            } catch (IOException ex) {
            }
        }
        String req1 = "SELECT email  FROM user Where email = '"+tfEmail.getText()+"'";
               PreparedStatement ps1 = con.prepareStatement(req1);
               ResultSet rs1 = ps1.executeQuery();

        if(rs1.next())
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" cette adress mail existe deja .");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
        }

        else{

      if (leRole=="Employeur")
      {   String req ="INSERT INTO `user` (`email`, `password`, `name`, `roles`, `prenom`, `gover`, `img`, `special`,`activation_token`, `etat`) VALUES ( '"+tfEmail.getText()+"','"+password8+"','"+tfNom.getText()+"','[\"Employeur\"]','"+tfPrenom.getText()+"','"+tfGover.getValue()+"','"+lb_image.getText()+"','"+tfSpecial.getValue()+"','22' ,'"+etat+"');";
      PreparedStatement  ps = con.prepareStatement(req);
//      ps.setBinaryStream(7, fis, file.length());
      
      ps.executeUpdate();
      
      }   
      else if (leRole=="Candidat") 
            { String req ="INSERT INTO `user` (`email`, `password`, `name`, `roles`, `prenom`, `gover`, `img`, `special`, `activation_token`, `etat`) VALUES ( '"+tfEmail.getText()+"','"+password8+"','"+tfNom.getText()+"','[\"Candidat\"]','"+tfPrenom.getText()+"','"+tfGover.getValue()+"','"+imageview.getImage()+"','"+tfSpecial.getValue()+"','22' ,'"+etat+"');";
 PreparedStatement  ps = con.prepareStatement(req);
        ps.executeUpdate();
      }
      
        
                    if (leRole.equals("Candidat")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        
                         String req = "SELECT *  FROM user Where email = '"+tfEmail.getText()+"'";
       
       PreparedStatement ps = con.prepareStatement(req);
        
       ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
                 JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");
      
        } else {           
            int k = rs.getInt("id");
            setIdSession(k);
                      Random rand =new Random();
        randomCodee=rand.nextInt(999999);
        String host ="smtp.gmail.com" ;
        String from ="pidevtestad@gmail.com" ;
        String password ="pidevtestad123456" ;
        String to =tfEmail.getText();
        String sub ="Activer compte " ;
        String msg ="Ton code est : " +randomCodee ;

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
                    + "    <title>Activez votre compte</title>\n"
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
                    + "                        <span style=\"font-weight: bold\">Chèr(e) </span>\n"
                    + "                        <span style=\"font-weight: bold;color: red\">" + tfPrenom.getText() + " , </span>\n"
                    + "                         \n"
                    + "                        <p>Afin de pouvoir activer votre compte, nous devons valider votre adresse e-mail.</p>\n"
                    + "                        <p> Récrire simplement le code envoyer.</p>\n"
                    + "                        "
                    + " <span style=\"font-weight: bold;color: red\">" + msg + "  </span>\n"
                    + "<p>Pour plus d'information n'hésitez pas à nous contacter sur : </p>\n"
                    + "                           <a href=\"mailto:recrutini@gmail.com?subject=Compte%20de%20" + tfPrenom.getText() + "\">\n"
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
           System.out.println("message sent successfully");
                 JOptionPane.showMessageDialog(null, "code envoyer a l'email");
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserActiveCode.fxml")));
                    stage.setScene(scene);
                    stage.show();
          } catch (MessagingException e) {
                         System.out.println("message didn't sent successfully");    
                         JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");
                         throw new RuntimeException(e);}                 
        }
                
            }
         if (leRole.equals("Employeur")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String req = "SELECT *  FROM user Where email = '"+tfEmail.getText()+"'";      
            PreparedStatement ps = con.prepareStatement(req);       
            ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");      
        } else {           
            int k = rs.getInt("id");
            setIdSession(k);
            Random rand =new Random();
            randomCodee=rand.nextInt(999999);
            String host ="smtp.gmail.com" ;
            String from ="pidevtestad@gmail.com" ;
            String password ="pidevtestad123456" ;
            String to =tfEmail.getText();
            String sub ="Activer compte " ;
            String msg ="Ton code est : " +randomCodee ;
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
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
                    + "    <title>Activez votre compte</title>\n"
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
                    + "                        <span style=\"font-weight: bold\">Chèr(e) </span>\n"
                    + "                        <span style=\"font-weight: bold;color: red\">" + tfPrenom.getText() + " , </span>\n"
                    + "                         \n"
                    + "                        <p>Afin de pouvoir activer votre compte, nous devons valider votre adresse e-mail.</p>\n"
                    + "                        <p> Récrire simplement le code envoyer.</p>\n"
                    + "                        "
                    + " <span style=\"font-weight: bold;color: red\">" + msg + "  </span>\n"
                    + "<p>Pour plus d'information n'hésitez pas à nous contacter sur : </p>\n"
                    + "                           <a href=\"mailto:recrutini@gmail.com?subject=Compte%20de%20" + tfPrenom.getText() + "\">\n"
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
           System.out.println("message sent successfully");
                 JOptionPane.showMessageDialog(null, "code envoyer a l'email");
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserActiveCode.fxml")));
                    stage.setScene(scene);
                    stage.show();
          } catch (MessagingException e) {
                         System.out.println("message didn't sent successfully");    
                 JOptionPane.showMessageDialog(null, "cette adress email n'existe pas");

              throw new RuntimeException(e);}                
        }
      }
    }                                                 
  }



    @FXML
    private void goToProfil(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }


    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    private Date tfDate(Timestamp time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void register(MouseEvent event) throws IOException {
    }


    @FXML
    private void goToLogin2(MouseEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void tfGover(MouseEvent event) {
        }

    @FXML
    private void tfSpecial(MouseEvent event) {
    }
    
         private boolean Isemail = false;

    @FXML
    private void imgchose(ActionEvent event) {
//  stage = (Stage)anchorPane.getScene().getWindow();
//  file =fileChooser.showOpenDialog(stage);
//  if(file != null){
//    System.out.println(""+file.getAbsolutePath());
//    image =new Image(file.getAbsoluteFile().toURI().toString());    
//    imageview.setPreserveRatio(true);}
    FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
 
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imgp = file.getName();
            imguriUri = file.toURI();
            lb_image.setText(imgp);
        }
    }
    

    @FXML
    private void offre(MouseEvent event) {
    }

    @FXML
    private void rendezVous(MouseEvent event) {
    }
 
}

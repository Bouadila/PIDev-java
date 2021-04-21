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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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


/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserAddController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfImg;
    @FXML
    private Label login;
    @FXML
    private ToggleGroup Roles;
 
    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink login1;
    @FXML
    private ComboBox<String> tfSpecial;
    static int randomCodee;

    /**
     * Initializes the controller class.
     */
           Connection con = DataSource.getInstance().getCnx();
    @FXML
    private RadioButton roleCandidat;
    @FXML
    private RadioButton roleEmployeur;
    @FXML
    private Hyperlink offre;
    @FXML
    private Hyperlink demande;
    @FXML
    private Hyperlink annonce;
    @FXML
    private Hyperlink quiz;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        
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

        // TODO
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
        String img = tfImg.getText();
        
        String password8 = BCrypt.hashpw(tfPassword.getText(),BCrypt.gensalt(13));
        password8 = password8.replaceFirst("2a", "2y") ;
             
        if(mail.equals("") || user.equals("") || pwd.equals("") || leRole.equals("") || gov.equals("Choisir Votre Governorat") || spec.equals("Choisir Votre Spécialiter") || pren.equals("") || img.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" S'il Vous Plait vérifer touts les champs .");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            alert.showAndWait();
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
      { String req ="INSERT INTO `user` (`email`, `password`, `name`, `roles`, `prenom`, `gover`, `img`, `special`,`activation_token`, `etat`) VALUES ( '"+tfEmail.getText()+"','"+password8+"','"+tfNom.getText()+"','[\"Employeur\"]','"+tfPrenom.getText()+"','"+tfGover.getValue()+"','"+tfImg.getText()+"','"+tfSpecial.getValue()+"','22' ,'"+etat+"');";
   PreparedStatement  ps = con.prepareStatement(req);
        ps.executeUpdate();
      }   
      else if (leRole=="Candidat") 
            { String req ="INSERT INTO `user` (`email`, `password`, `name`, `roles`, `prenom`, `gover`, `img`, `special`, `activation_token`, `etat`) VALUES ( '"+tfEmail.getText()+"','"+password8+"','"+tfNom.getText()+"','[\"Candidat\"]','"+tfPrenom.getText()+"','"+tfGover.getValue()+"','"+tfImg.getText()+"','"+tfSpecial.getValue()+"','22' ,'"+etat+"');";
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
                
        }
            }
        }
                                                   
    }


    @FXML
    private void goToAcceuil(ActionEvent event) {
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
    private void goTooffre(ActionEvent event) {
    }

    @FXML
    private void goTodemande(ActionEvent event) {
    }

    @FXML
    private void goToannoce(ActionEvent event) {
    }

    @FXML
    private void quiz(ActionEvent event) {
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
    }

   
  
}

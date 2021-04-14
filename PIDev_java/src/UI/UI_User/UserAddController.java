/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import Services.UserService;
import Services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.DataSource;

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
 
       
                    
                    

                
        if(mail.equals("") || user.equals("") || pwd.equals("") || leRole.equals("") || gov.equals("Choisir Votre Governorat") || spec.equals("Choisir Votre Spécialiter") || pren.equals("") || img.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("IL y'a des donnés sont encore vides !\n S'il Vous Plait vérifer touts les champs .");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//          stage.initStyle(StageStyle.UNDECORATED);

            alert.showAndWait();
        }
//      else if(("SELECT email from `users` WHERE `email`= "+mail)!="")
//        {
//                 Alert alert = new Alert(Alert.AlertType.ERROR);
//                 alert.setTitle("Error");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Cette adresse email déjà existe !  .\n S'il vous plait choisir un autre adresse e-mail.");
//                 Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//
//                 alert.showAndWait();
//            }

        else{

//      try {
//                        formateur.setMotDePass(CryptographieMOOC.getCryptage().encrypt(pwd1.getText()));
//                    } catch (Exception ex) {
//                        Logger.getLogger(MyAccountCoachFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
      
        String req ="INSERT INTO `users` (`id`, `email`, `password`, `name`, `roles`, `prenom`, `gover`, `img`, `special`, `etat`) VALUES (NULL, '"+tfEmail.getText()+"','"+tfPassword.getText()+"','"+tfNom.getText()+"','"+leRole+"','"+tfPrenom.getText()+"','"+tfGover.getValue()+"','"+tfImg.getText()+"','"+tfSpecial.getValue()+"','"+etat+"');";
        PreparedStatement  ps = con.prepareStatement(req);
        ps.executeUpdate();
        
                    if (leRole.equals("Candidat")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compte Candidat");
            alert.setHeaderText("Votre Compte est ajouté !");
            alert.setContentText("retour à la page de connexion");

                  alert.showAndWait(); 
          
                 Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                    if (leRole.equals("Employeur")){
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compte Employeur");
            alert.setHeaderText("Votre Compte est ajouté !");
            alert.setContentText("retour à la page de connexion");

                  alert.showAndWait(); 
          
                 Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
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
    
     
    
}

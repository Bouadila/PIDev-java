/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Services.UserSession;
import UI.OffreUI.OffreFXMLController;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.security.crypto.bcrypt.BCrypt;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UseChangeController implements Initializable {

    @FXML
    private TextField nvEmail;
    @FXML
    private Label checkemail;
    @FXML
    private PasswordField oldpw;
    @FXML
    private PasswordField nvpw;
    @FXML
    private Label checkpw;
    @FXML
    private ComboBox<String> nvSpecial;
    @FXML
    private TextField nvNom;
    @FXML
    private TextField nvPrenom;
    @FXML
    private Label checkSpecial;
    @FXML
    private Label checkGover;
    @FXML
    private Label checkimg;
    @FXML
    private Label checknom;
    @FXML
    private Label checkprenom;
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
    private Button profil;
    @FXML
    private ComboBox<String> tfnvGover;

    ObservableList<String> list_nv_gov
            = FXCollections.observableArrayList(
                    "Ariena",
                    "Tunis",
                    "Sfax",
                    "Nabeul",
                    "Sousse");
   
     ObservableList<String> list_nv_spec
            = FXCollections.observableArrayList(
                    "Informatique",
                    "Math",
                    "Immobiliér",
                    "Marketing",
                    "Médecin");
    @FXML
    private PasswordField nvpwverif;
    @FXML
    private Button txtimgchose;
    @FXML
    private Label lb_image;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean NRG = tfnvGover.getSelectionModel().isEmpty();
        boolean NRV = nvSpecial.getSelectionModel().isEmpty();
        
          if (NRG) {
            tfnvGover.setValue("Choisir Votre Governorat");
        }
        if (NRV) {
            nvSpecial.setValue("Choisir Votre Spécialiter");
        }
        nvSpecial.setItems(list_nv_spec);

        tfnvGover.setItems(list_nv_gov);
        // TODO
    }    

    @FXML
    private void changeEmail(MouseEvent event) throws SQLException {  
if(nvEmail.getText().equals("")){
            checkemail.setTextFill(Color.RED);
            checkemail.setText("Email est vide!\n ");}

        
else{
        String req2 ="UPDATE `user` SET `email` = '"+nvEmail.getText()+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
       checkemail.setTextFill(Color.GREEN);
            checkemail.setText("Changement d'Email avec succès !");}
    }

    @FXML
    private void changePW(MouseEvent event) throws SQLException      
 {
        String oldpassword="";
        String pp = "";      
        String request0 ="SELECT * from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
           
        if (rs0.next()) {
            oldpassword = rs0.getString("password");
        }
        
              pp =rs0.getString("password");    
              pp = pp.replaceFirst("2y", "2a");            
              if (BCrypt.checkpw(oldpw.getText(),pp))
             {   
            if(nvpw.getText().equals("")){
            checkpw.setTextFill(Color.RED);
            checkpw.setText("Nouveau mdp est vide!\n ");}
            else if(nvpw.getText().equals(nvpwverif.getText())){
                 String passwordnew = BCrypt.hashpw(nvpw.getText(),BCrypt.gensalt(13));
                 passwordnew = passwordnew.replaceFirst("2a", "2y") ;
            String req ="UPDATE `user` SET `password` = '"+passwordnew+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
            PreparedStatement ps = con.prepareStatement(req);
            ps.executeUpdate();     
            checkpw.setTextFill(Color.GREEN);
            checkpw.setText("Changement de mdp avec succès !");    
            }
            else{
                checkpw.setTextFill(Color.RED);
            checkpw.setText("Verification est incorrect!\n ");}
            }
        
        else {
           checkpw.setTextFill(Color.TOMATO);
            checkpw.setText("Mot de passe incorrect !");
        } 
 
 }
  @FXML
    private void changeSpecial(MouseEvent event) throws SQLException {
         if(nvSpecial.getValue().equals("Choisir Votre Spécialiter")){
            checkSpecial.setTextFill(Color.RED);
            checkSpecial.setText("Specialité est vide!\n ");}
        else{
           String req2 ="UPDATE `user` SET `special` = '"+nvSpecial.getValue()+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
       checkSpecial.setTextFill(Color.GREEN);
       checkSpecial.setText("Changement de Specialité avec succès !");}
    }

    @FXML
    private void changeGover(MouseEvent event) throws SQLException {
         if(tfnvGover.getValue().equals("Choisir Votre Governorat")){
            checkGover.setTextFill(Color.RED);
            checkGover.setText("Governorat est vide!\n ");}
        else{
          String req2 ="UPDATE `user` SET `gover` = '"+tfnvGover.getValue()+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
       checkGover.setTextFill(Color.GREEN);
       checkGover.setText("Changement de governorat avec succès !");}
    }

    @FXML
    private void changeImg(MouseEvent event) throws SQLException {

        if(lb_image.getText().equals("")){
            checkimg.setTextFill(Color.RED);
            checkimg.setText("Image est vide!\n ");}
        else{
            if (imgp!=null) {
            try {
                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Desktop\\PIDev-java\\PIDev_java\\src\\image\\" + imgp));
                Files.copy(Paths.get(imguriUri), Paths.get("C:\\Users\\USER\\Documents\\pidev\\ProjPiDev\\public\\uploads\\image\\" + imgp));
            } catch (IOException ex) {
            }
        }
          String req2 ="UPDATE `user` SET `img` = '"+lb_image.getText()+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
       checkimg.setTextFill(Color.GREEN);
       checkimg.setText("Changement d'image avec succès !");}
    }

    @FXML
    private void changeNom(MouseEvent event) throws SQLException {
          if(nvNom.getText().equals("")){
            checknom.setTextFill(Color.RED);
            checknom.setText("Nom est vide!\n ");}
        else{
         String req2 ="UPDATE `user` SET `name` = '"+nvNom.getText()+"' WHERE `user`.`id` = "+UserSession.getIdSession()
                        +";";
        java.sql.PreparedStatement ps3 = con.prepareStatement(req2);
        ps3.executeUpdate();
        checknom.setTextFill(Color.GREEN);
            checknom.setText("Changement de nom avec succès !");}
    }
    

    @FXML
    private void changePrenom(MouseEvent event) throws SQLException {
         if(nvPrenom.getText().equals("")){
            checkprenom.setTextFill(Color.RED);
            checkprenom.setText("Prenom est vide!\n ");}
        else{
          String req2 ="UPDATE `user` SET `prenom` = '"+nvPrenom.getText()+"' WHERE `user`.`id` = "+UserSession.getIdSession()
                        +";";
        java.sql.PreparedStatement ps3 = con.prepareStatement(req2);
        ps3.executeUpdate();
        checkprenom.setTextFill(Color.GREEN);
            checkprenom.setText("Changement de prenom avec succès !");}
    }
    
    private void goToAcceuil(ActionEvent event) throws IOException, SQLException {
            String role="";
         String request0 ="SELECT * from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next())
        {
            role = rs0.getString("roles");
        }
        
        if (role.equals("Candidat")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ProfileChauffeur.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
        if (role.equals("Employeur")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
    }
 
    @FXML
    private void goToProfil(ActionEvent event) throws IOException, SQLException {
         String role="";
         String request0 ="SELECT *,SUBSTR(roles,3,5) as rol from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next())
        {
            role = rs0.getString("rol");
        }
        
        if (role.equals("Candi")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserCandidatAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
        if (role.equals("Emplo")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
    }


    private void goToLogin(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }



    @FXML
    private void goTochange(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UseChange.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goTodesactiv(MouseEvent event) throws SQLException, IOException {
           int etatDes= '1';
         String req2 ="UPDATE `user` SET `etat` = '"+'1'+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);      
        ps2.executeUpdate();
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void tfnvGover(MouseEvent event) {
    }

    @FXML
    private void nvSpecial(MouseEvent event) {
    }

    @FXML
    private void imgchose(ActionEvent event) {
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
    private void rendezVous(MouseEvent event) {
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/RendezVousFXML.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
    }

    @FXML
    private void offre(ActionEvent event) throws SQLException, IOException {
        
       String role = "";
        String request0 = "SELECT * from `user` WHERE `user`.`id` = " + UserSession.getIdSession() + ";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next()) {
            role = rs0.getString("roles");
        }

        if (role.equals("[\"Candidat\"]")) {
                    Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreCandidatFXML.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        }
        if (role.equals("[\"Employeur\"]")) {
            Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
        }
    }

    

    @FXML
    private void reclamation(ActionEvent event) {
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
    }

    @FXML
    private void apropos(ActionEvent event) {
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/AboutUs.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
    }

    @FXML
    private void formation(ActionEvent event) {
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_formation/Formations.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);

    }
    
}

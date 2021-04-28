/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserAfficheController implements Initializable {

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
    private Hyperlink quiz;
    @FXML
    private Label tfNomPrenom;
    @FXML
    private Label tfmail;
    @FXML
    private Label tfspecialite;
    @FXML
    private Label tfgoverno;

    /**
     * Initializes the controller class.
     */
         Connection con = DataSource.getInstance().getCnx();
    @FXML
    private ImageView imgaffiche;
    @FXML
    private ImageView profilePic;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {
            setLabelProfileUser();
        } 
catch (SQLException ex) {
            Logger.getLogger(UserAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }    
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
    private void goToAcceuil(ActionEvent event) throws SQLException ,IOException {
        String role="";
         String request0 ="SELECT * from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next()) {
            role = rs0.getString("roles");
        }
        
        if (role.equals("Candidat")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserCandidatAffiche.fxml")));
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
    private void goToProfil(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
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
    private void setLabelProfileUser() throws SQLException {
       String fullName="";
         String email="";
         String specia="";
         String gover="";

         
        String request0 ="SELECT * from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();

        if (rs0.next()) {
            String a = rs0.getString("name");
            String b = rs0.getString("prenom");
            fullName = "Nom et prénom : "+b+" "+a;
            String c = rs0.getString("gover");
            String d = rs0.getString("special");
            String e = rs0.getString("email");
            String f = rs0.getString("img");
            Rectangle clip = new Rectangle( profilePic.getFitWidth(), profilePic.getFitHeight() );
        clip.setArcWidth(300);
        clip.setArcHeight(400);
        profilePic.setEffect(new DropShadow(20, Color.BLACK));
        profilePic.setClip(clip);
        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profilePic.snapshot(parameters, null);     
                     profilePic.setImage(new Image("/image/"+f));
                     tfmail.setText("Email : "+e);
                     tfgoverno.setText("Governorat : "+c);
                     tfspecialite.setText("Specialité : "+d);
//                   imgaffiche.setImage(""+f);
        }
                     tfNomPrenom.setText(fullName);   
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
    
   
    
}

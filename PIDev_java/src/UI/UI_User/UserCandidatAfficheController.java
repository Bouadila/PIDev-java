/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Services.UserSession;
import UI.OffreUI.OffreFXMLController;
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
import javafx.scene.control.Button;
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
import org.controlsfx.control.Rating;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserCandidatAfficheController implements Initializable {

    @FXML
    private Button profil;
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
    private Rating rating;
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

    
    

    private void goToAcceuil(ActionEvent event) throws SQLException ,IOException {
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

    @FXML
    private void setLabelProfileUser() throws SQLException {
       String fullName="";
         String email="";
         String specia="";
         String gover="";
int rat =0;
    int total=0;
         
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
             String f = rs0.getString("color");
            rat= rat+rs0.getInt("color");
            String h = rs0.getString("img");
            Rectangle clip = new Rectangle( profilePic.getFitWidth(), profilePic.getFitHeight() );
        clip.setArcWidth(200);
        clip.setArcHeight(200);
        profilePic.setEffect(new DropShadow(20, Color.BLACK));
        profilePic.setClip(clip);
        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profilePic.snapshot(parameters, null);     
        profilePic.setImage(new Image("file:///C:/Users/Bou3dila/Documents/Esprit/ProjPiDev/ProjPiDev/public/uploads/image/"+h));
                     tfmail.setText("Email : "+e);
                     tfgoverno.setText("Governorat : "+c);
                     tfspecialite.setText("Specialité : "+d);
//                     rating.setContextMenu(rat);
//                 rating.set();

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

    @FXML
    private void gorating(MouseEvent event) throws SQLException {
        System.out.println("rating :" +rating.getRating());
       String req2 ="UPDATE `user` SET `color` = '"+rating.getRating()+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
        
    }

//      private void goToAcceuil(ActionEvent event) throws SQLException, IOException {
//        String role = "";
//        String request0 = "SELECT * from `user` WHERE `user`.`id` = " + UserSession.getIdSession() + ";";
//        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
//        ResultSet rs0 = ps0.executeQuery();
//        if (rs0.next()) {
//            role = rs0.getString("roles");
//        }
//
//        if (role.equals("Candidat")) {
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserCandidatAffiche.fxml")));
//            stage.setScene(scene);
//            stage.show();
//        }
//        if (role.equals("Employeur")) {
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAffiche.fxml")));
//            stage.setScene(scene);
//            stage.show();
//        }
//    }

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
    private void offre(ActionEvent event) throws SQLException {
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
            System.out.println(ex.getMessage());
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
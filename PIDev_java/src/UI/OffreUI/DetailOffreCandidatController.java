/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.UserSession;
import UI.UI_candidature.AjouterCandidatureController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class DetailOffreCandidatController implements Initializable {

    @FXML
    private Label lb_Post;
    @FXML
    private Text lb_domaine;
    @FXML
    private Circle img;
    @FXML
    private Text lb_objectif;
    @FXML
    private Label lb_depo;
    @FXML
    private Label lb_expiration;
    @FXML
    private Text lb_salaire;
    @FXML
    private Label leb_min;
    @FXML
    private Label lb_max;
    @FXML
    private Text lb_competence;
    @FXML
    private Text lb_description;

    private Offre offre;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Offre offre) {
        this.offre = offre;
        this.lb_Post.setText(offre.getPost());
        this.lb_competence.setText(offre.getCompetences());
        this.lb_description.setText(offre.getDescription());
        this.lb_objectif.setText(offre.getObjectif());
        this.lb_domaine.setText(offre.getDomaine());
        this.lb_salaire.setText(offre.getSalaire() + "DT");
        this.lb_depo.setText(offre.getDateDepot().toString());
        this.lb_expiration.setText(offre.getDateExpiration().toString());
        this.leb_min.setText(String.valueOf(offre.getExperienceMin()));
        this.lb_max.setText(String.valueOf(offre.getExperienceMax()));
        Image image = new Image("/Images/Offre-emploi.jpg");
        this.img.setFill(new ImagePattern(image));
        //lb_id.setText(offre.getDescription());

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
    private void Postuler(MouseEvent event) throws IOException {

        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_candidature/AjouterCandidature.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();

        Scene scene = new Scene(loader.load());
        AjouterCandidatureController conController = loader.getController();
        conController.loadData(offre);
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
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }
       Connection con = DataSource.getInstance().getCnx();

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

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserCandidatAffiche.fxml")));
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

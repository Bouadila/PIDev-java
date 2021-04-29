/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_Reclamation;

import Entity.Reclamation;
import Services.ReclamationService;
import Services.UserSession;
import UI.OffreUI.OffreFXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAffichageController implements Initializable {

    @FXML
    private ListView<VBox> lvRec;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> lr = rs.Lister(UserSession.getIdSession());
        
        for(Reclamation rec: lr){
            VBox vb = new VBox();
            vb.getChildren().addAll(new Label(rec.getTitle()),new Label(rec.getTitle()),new Label(rec.getStatus()));
            lvRec.getItems().add(vb);
        }
        
        lvRec.setOnMouseClicked(e -> {
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichageDetail.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    //Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ReclamationAffichageDetailController ReclamationDetailController = loader.getController();
                try {
                    
                    ReclamationDetailController.loadData(lr.get(lvRec.getSelectionModel().getSelectedIndex()));
                } catch (Exception ex) {
                    
                }
                stage.setScene(scene);
    }    );    
    
    }

//    @FXML
//    private void offre(MouseEvent event) {
//    }

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

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class DetailOffreController implements Initializable {

    @FXML
    private Label lb_Post;
    @FXML
    private Text lb_objectif;
    @FXML
    private Text lb_domaine;
    //private ImageView img;
    @FXML
    private Text lb_description;
    @FXML
    private Text lb_competence;
    @FXML
    private Text lb_salaire;
    @FXML
    private Label lb_depo;
    @FXML
    private Label lb_expiration;
    @FXML
    private Label leb_min;
    @FXML
    private Label lb_max;
    //private ImageView logo;
    @FXML
    private Circle img;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Offre o =new Offre();
    public void initData(Offre offre){
        this.lb_Post.setText(offre.getPost());
        this.lb_competence.setText(offre.getCompetences());
        this.lb_description.setText(offre.getDescription());
        this.lb_objectif.setText(offre.getObjectif());
        this.lb_domaine.setText(offre.getDomaine());
        this.lb_salaire.setText(offre.getSalaire()+"DT");
        this.lb_depo.setText(offre.getDateDepot().toString());
        this.lb_expiration.setText(offre.getDateExpiration().toString());
        this.leb_min.setText(String.valueOf(offre.getExperienceMin()));
        this.lb_max.setText(String.valueOf(offre.getExperienceMax()));
         Image image = new Image("/Images/Offre-emploi.jpg");
         this.img.setFill(new ImagePattern(image));
        //lb_id.setText(offre.getDescription());
        o = new OffreService().getOne(offre.getId());
        System.out.println(o.toString());
    }

    @FXML
    private void updateOffre(MouseEvent event) {

            FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/UI/OffreUI/ModifirOffreFXML.fxml"));
                try {
                    loader.load();
                 } catch (IOException ex) {
                 ex.getMessage();
                  //Logger.getLogger(DetailOffreControlle.class.getName()).log(Level.SEVERE, null, ex);
                }
            ModifirOffreFXMLController updateController = loader.getController();
            updateController.initData(o);
            Stage stage = new Stage();
            Parent parent = loader.getRoot();
            stage.setScene(new Scene(parent));               
            stage.show();
    }
    
    
@FXML
    private void deleteOffre(MouseEvent event) {
          Alert alert = new Alert(AlertType.CONFIRMATION, "Supprimer  " + o.getPost() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    OffreService os =new OffreService();
                    os.delete(o);
                Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                   scene = new Scene(loader.load());
                 } catch (IOException ex) {
                    ex.getMessage();
                }
                stage.setScene(scene);
                            
                }
    }

    @FXML
    private void backToListe(MouseEvent event) {
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

    private void offre(MouseEvent event) {
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

//     @FXML
//    private void offre(MouseEvent event) {
//        Node node = (Node) event.getSource();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
//                Stage stage = (Stage) node.getScene().getWindow();
//                Scene scene = null;  
//                try {
//                    scene = new Scene(loader.load());
//                } catch (IOException ex) {
//                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                 stage.setScene(scene);
//    }

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
    private void offre(ActionEvent event) throws SQLException {
        String role = "";
        String request0 = "SELECT * from `user` WHERE `user`.`id` = " + UserSession.getIdSession() + ";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next()) {
            role = rs0.getString("roles");
        }

        if (role.equals("Candidat")) {
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
        if (role.equals("Employeur")) {
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
    
}

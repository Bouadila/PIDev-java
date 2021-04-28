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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserDetaiAffichBacklController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox vbox;
    @FXML
    private Button acceuil;
    @FXML
    private Button comptes;
    @FXML
    private Button logout;
    private Label tfNomPrenom;
    Connection con = DataSource.getInstance().getCnx();
    @FXML
    private Label tfPrenom;
    @FXML
    private Label tfSpecial;
    @FXML
    private Label tfGover;
    @FXML
    private Label tfMail;
    @FXML
    private Label tfrole;
    @FXML
    private Label tfetat;
    @FXML
    private Label tfNom;
    @FXML
    private ImageView profilePic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
        
     User u =new User();
    public void initData(User user) throws SQLException{
    
        this.tfNom.setText("Nom : " +user.getName());
        this.tfPrenom.setText("Prenom : " +user.getPrenom());
                this.tfSpecial.setText("Specialité : " +user.getSpecial());
        this.tfGover.setText("Governorat : " +user.getGover());
        this.tfMail.setText("Email : " +user.getEmail());
        if(user.getRoles().equals("[\"Employeur\"]"))
        {
                    this.tfrole.setText("Role :Employeur ");
        }
        else if(user.getRoles().equals("[\"Candidat\"]")){
        this.tfrole.setText("Role :Candidat ");

                }
        else {
         this.tfrole.setText("Role :admin ");

        }
        if(user.getEtat()==0)
        { this.tfetat.setText("Etat du compte : Activer" );}
        else{
                    this.tfetat.setText("Etat du compte : Désactiver" );

        }
        Rectangle clip = new Rectangle( profilePic.getFitWidth(), profilePic.getFitHeight() );
        clip.setArcWidth(200);
        clip.setArcHeight(200);
        profilePic.setClip(clip);
        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profilePic.snapshot(parameters, null);     
         this.profilePic.setImage(new Image("/image/"+user.getImg()));

//        this.lb_competence.setText(user.getCompetences());
//        this.lb_description.setText(offre.getDescription());
//        this.lb_objectif.setText(offre.getObjectif());
//        this.lb_domaine.setText(offre.getDomaine());
//        this.lb_salaire.setText(offre.getSalaire()+"DT");
//        this.lb_depo.setText(offre.getDateDepot().toString());
//        this.lb_expiration.setText(offre.getDateExpiration().toString());
//        this.leb_min.setText(String.valueOf(offre.getExperienceMin()));
//        this.lb_max.setText(String.valueOf(offre.getExperienceMax()));
//         Image image = new Image("/Images/9.jpg");
//         this.logo.setImage(image);
        //lb_id.setText(offre.getDescription());
        u = new UserService().getOne(user.getId());
    }

    @FXML
    private void GoToAllContact(MouseEvent event) {
    }

    @FXML
    private void GoToAllComment(MouseEvent event) {
    }

    @FXML
    private void acceuilClicks(ActionEvent event) {
    }

    @FXML
    private void CompteClicks(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAfficheBack.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void labelcompte(MouseEvent event) {
    }

    
}

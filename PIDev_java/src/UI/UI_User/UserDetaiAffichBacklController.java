/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import Services.UserService;
import Services.UserSession;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
     User u =new User();
    public void initData(User user) throws SQLException{
//        this.lb_Post.setText(offre.getPost());
//        this.lb_competence.setText(offre.getCompetences());
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
//        //lb_id.setText(offre.getDescription());
//        o = new OffreService().getOne(offre.getId());
//        System.out.println(o.toString());
//    }
    
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
    private void CompteClicks(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void labelcompte(MouseEvent event) {
    }

    
}

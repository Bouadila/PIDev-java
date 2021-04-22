/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class DetailOffreController implements Initializable {

    @FXML
    private Text lb_Post;
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
    @FXML
    private ImageView logo;

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
         Image image = new Image("/Images/9.jpg");
         this.logo.setImage(image);
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
}

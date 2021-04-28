/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import UI.UI_candidature.AjouterCandidatureController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private void offre(MouseEvent event) {
    }

    @FXML
    private void rendezVous(MouseEvent event) {
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

}

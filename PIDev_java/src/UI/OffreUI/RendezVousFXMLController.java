/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.RendezVous;
import Services.OffreDao.RendezVousService;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class RendezVousFXMLController implements Initializable {

    @FXML
    private ListView<HBox> rdvListe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
loadDate();
    }    

    @FXML
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
    }

    @FXML
    private void refreshData(MouseEvent event) {
    }

    @FXML
    private void AddData(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/UI/OffreUI/AjouterRendezVousFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {
        for ( int i = 0; i < new RendezVousService().getAll().size(); i++){
            RendezVous rdv = new RendezVousService().getAll().get(i);
            Label lb_desc = new Label(rdv.getTitre());
            HBox hb = new HBox();
            //ImageView imageView = new ImageView("/Images/9.jpg");
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll( lb_desc);
            rdvListe.getItems().add(hb);
            
        }
        
    }
    
}

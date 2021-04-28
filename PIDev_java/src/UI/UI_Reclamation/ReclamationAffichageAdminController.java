/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_Reclamation;

import Entity.Reclamation;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAffichageAdminController implements Initializable {

    @FXML
    private ListView<VBox> lvRec;
    @FXML
    private VBox vbox;
    @FXML
    private Button acceuil;
    @FXML
    private Button comptes;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> lr = rs.Lister();
        
        for(Reclamation rec: lr){
            VBox vb = new VBox();
            vb.getChildren().addAll(new Label(rec.getTitle()),new Label(rec.getTitle()),new Label(rec.getStatus()));
            lvRec.getItems().add(vb);
        }
        
        lvRec.setOnMouseClicked(e -> {
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichageDetailAdmin.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    //Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ReclamationAffichageDetailAdminController ReclamationDetailController = loader.getController();
                try {
                    
                    ReclamationDetailController.loadData(lr.get(lvRec.getSelectionModel().getSelectedIndex()));
                } catch (Exception ex) {
                    
                }
                stage.setScene(scene);
    }    );
    
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
}

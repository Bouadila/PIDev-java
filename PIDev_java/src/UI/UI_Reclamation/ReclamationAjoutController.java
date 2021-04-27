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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAjoutController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDesc;
    @FXML
    private Button btnInsert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
    }    

    @FXML
    private void handleButtonInsert(ActionEvent event) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date=String.valueOf(now).replace("T", " ");
        
        
        ReclamationService rs = new ReclamationService();
        Reclamation Rc=new Reclamation(tfTitle.getText(),"Reclamation",date,tfDesc.getText(),"Non approuvé",rs.getEmail(1));
        rs.Ajouter(Rc);
        
        Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ReclamationAffichageController ReclamationAffController = loader.getController();
                    
                    stage.setScene(scene);
    }
    
}

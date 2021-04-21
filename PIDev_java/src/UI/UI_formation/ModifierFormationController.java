/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierFormationController implements Initializable {

    
     @FXML
    private TextField tfUrlMod;

    @FXML
    private TextField tfTitreMod;

    @FXML
    private TextField tfDomaineMod;

    @FXML
    private TextArea tfDescriptionMod;

    @FXML
    private Button btn_gotoAfficherFormation;

    
    
    
    
    
    
    
    
    
    @FXML
    void btn_gotoAfficherFormaModif(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AfficherFormation.fxml"));
        Stage Window = (Stage) btn_gotoAfficherFormation.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

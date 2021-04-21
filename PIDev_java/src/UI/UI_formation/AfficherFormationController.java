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
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherFormationController implements Initializable {

    
     @FXML
    private Button btn_gotoAjoutForm;

    @FXML
    private Button btn_deleteFormation;

    @FXML
    private Button btn_gotoModifForm;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    
    @FXML
    void btn_gotoAjouterForma(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
        Stage Window = (Stage) btn_gotoAjoutForm.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }
    
    
    @FXML
    void btn_gotoModifierForma(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/ModifierFormation.fxml"));
        Stage Window = (Stage) btn_gotoModifForm.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }
    
    
}

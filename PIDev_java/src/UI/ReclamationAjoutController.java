/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAjoutController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDesc;
    @FXML
    private Button btnInsert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonInsert(ActionEvent event) {
    }
    
}

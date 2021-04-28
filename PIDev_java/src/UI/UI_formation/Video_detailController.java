/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Services.FormationService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Video_detailController implements Initializable {

    @FXML
    private WebView webView_Forma;
    @FXML
    private FontAwesomeIconView id_back;


    /**
     * Initializes the controller class.
     */
    
    
   
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
                       

                       

        
        
    }    
    
    public void initData(String url){
         String content_Url = "<iframe width=\"700\" height=\"480\" src=\""+url+"\" frameborder=\"0\" allowfullscreen></iframe>";


                        WebEngine webEngine = webView_Forma.getEngine();
                        webEngine.loadContent(content_Url);
    }

 
       
    @FXML
    private void btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_back_list(javafx.scene.input.MouseEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/Formations.fxml"));
        Stage Window = (Stage) id_back.getScene().getWindow();
        Window.setScene(new Scene(root));
    }

   
    
}

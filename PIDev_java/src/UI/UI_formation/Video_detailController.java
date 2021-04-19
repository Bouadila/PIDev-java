/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Video_detailController implements Initializable {

    @FXML
    private WebView webView_Forma;

    /**
     * Initializes the controller class.
     */
    
    
   
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
                       

                       
//                        StackPane root = new StackPane();
//                        root.getChildren().add(webView_Forma);
//
//                        Scene scene = new Scene(root, 300, 250);
//
//                       
//                      
//                          
//                     
//                      Stage Window = (Stage) url1.getScene().getWindow();
//                      Window.setScene(new Scene(root));
                        

                  
                      
                    /*    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                        }*/
        
        
    }    
    
    public void initData(String url){
         String content_Url = "<iframe width=\"700\" height=\"500\" src=\""+url+"\" frameborder=\"0\" allowfullscreen></iframe>";


                        WebEngine webEngine = webView_Forma.getEngine();
                        webEngine.loadContent(content_Url);
    }
    
}

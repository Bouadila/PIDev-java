/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Services.FormationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;






/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterFormationController implements Initializable {


    
    @FXML
    private TextField tfUrl;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfDomaine;

    @FXML
    private TextArea tfDescription;

    @FXML
    private Button btnAjoutForma;
    
    @FXML
    private Button btn_gotoAfficherForma;

    DropShadow shadow = new DropShadow();
    
    @FXML
    void btn_Ajout_Formation(ActionEvent event) {
        
         
       
                
                
        
        
        btnAjoutForma.setOnAction(e->{
            
        String url = "https://www.youtube.com/embed/";
        String code = tfUrl.getText().substring(tfUrl.getText().length() - 11);
        url = url + code;
        
        
        Formation v = new Formation();
        v.setVotes("0");
        
        v.setUrl(url);
        v.setTitle(tfTitle.getText());
        Timestamp time= new Timestamp(System.currentTimeMillis());
        v.setPublish_date(time);
        System.out.println(time); 
        v.setDomaine(tfDomaine.getText());
        v.setDescription(tfDescription.getText());
        
        new FormationService().ajouterVideo(v);
                
        
        Stage stage = (Stage) btn_gotoAfficherForma.getScene().getWindow();
            //stage.close();
        });
        

    }
  
    
    
    
     @FXML
    void btn_gotoAfficherForm(ActionEvent event) throws IOException {
       
              
        
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AfficherFormation.fxml"));
        Stage Window = (Stage) btn_gotoAfficherForma.getScene().getWindow();
        Window.setScene(new Scene(root));
        
        
 
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
   


    void setTextField(String title, String url, String description, String domaine) {


    }

     @FXML
    private void btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
    
}
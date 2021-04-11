/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Services.FormationService;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FormationsController implements Initializable {

    @FXML
    private VBox VBox_formations;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ArrayList<Formation> formations= new FormationService().getAllList();
        
         System.out.println(formations);
        
        for(int i = 0; i<formations.size();i++) {
        
             
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            
            Label url1= new Label();
            Label description1= new Label();
            Label titre1= new Label();
            Label domaine1= new Label();
            Label publish_date1= new Label();
            
            
            
            
            
            Button btn1=new Button("Like");
            
            
            
            description1.setText(formations.get(i).getDescription());
            titre1.setText(formations.get(i).getTitle());
            url1.setText(formations.get(i).getUrl());
            domaine1.setText(formations.get(i).getDomaine());
            publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
          
            
           
           
            
            
            VBox forma1=new VBox();
            forma1.getChildren().addAll(titre1,url1,description1,publish_date1,domaine1,btn1);
            
            
            HBox formhor=new HBox();
            
            formhor.getChildren().addAll(forma1);
            
            VBox_formations.getChildren().add(formhor);
            
            
            
            
            
         
             btn1.setOnAction((ActionEvent e) -> {
                 
                 
              
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Formation");
                 alert.setHeaderText("Formation supprimé !");
                 alert.setContentText("Go Back To The Homepage");
                 
                 alert.showAndWait();
            });
        
        
        
    }    
    
    }
}

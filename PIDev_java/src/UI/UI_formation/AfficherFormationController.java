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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    
    @FXML
    private VBox VBox_formations;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ArrayList<Formation> formations= new FormationService().getAll();
        
        //System.out.println(formations);
        
        for(int i = 0; i<formations.size();i++) {
        
             
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            
            Label url1= new Label();
            Label description1= new Label();
            Label titre1= new Label();
            Label domaine1= new Label();
            Label publish_date1= new Label();
            
            
            Label description2= new Label();
            Label titre2= new Label();
            Label url2= new Label();
            Label domaine2= new Label();
            Label publish_date2= new Label();
            
            
            Button btn1=new Button("Like");
            Button btn2=new Button("Like");
            
            
            description1.setText(formations.get(i).getDescription());
            titre1.setText(formations.get(i).getTitle());
            url1.setText(formations.get(i).getUrl());
            domaine1.setText(formations.get(i).getDomaine());
            publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
          
            
            description2.setText(formations.get(i).getDescription());
            titre2.setText(formations.get(i).getTitle());
            url2.setText(formations.get(i).getUrl());
            domaine2.setText(formations.get(i).getDomaine());
            publish_date2.setText(formatter.format(formations.get(i).getPublish_date()));
           
            
            
            VBox forma1=new VBox();
            forma1.getChildren().addAll(titre1,url1,description1,publish_date1,domaine1,btn1);
            VBox forma2=new VBox();
            forma2.getChildren().addAll(titre2,url2,description2,publish_date2,domaine2,btn2);
            
            HBox formhor=new HBox();
            
            formhor.getChildren().addAll(forma1,forma2);
            
            VBox_formations.getChildren().add(formhor);
            
            
            
            
            
         
             btn1.setOnAction((ActionEvent e) -> {
                 Formation v = new Formation();
                 new FormationService().supprimerVideo(v);
                 
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Formation");
                 alert.setHeaderText("Formation supprimé !");
                 alert.setContentText("Go Back To The Homepage");
                 
                 alert.showAndWait();
            });
             
             
             
             btn2.setOnAction((ActionEvent e) -> {
                 Formation v = new Formation();
                 new FormationService().supprimerVideo(v);
                 
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Formation");
                 alert.setHeaderText("Formation supprimé !");
                 alert.setContentText("Go Back To The Homepage");
                 
                 alert.showAndWait();
            });
        
             
             
             
             
     }
            
            
           
            
            
            
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

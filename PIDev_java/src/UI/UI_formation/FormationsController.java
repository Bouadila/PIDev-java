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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class FormationsController implements Initializable {

    @FXML
    private VBox VBox_formations;
    
    
     private  Formation v;
     

     //String content_Url = "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/9bZkp7q19f0\" frameborder=\"0\" allowfullscreen></iframe>";

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           


        ObservableList<Formation> formations = new FormationService().getAll();
        
         System.out.println(formations);
        
        for(int i = 0; i<formations.size();i++) {
        
            v = formations.get(i);
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            

            
            Hyperlink url1= new Hyperlink();
            TextField id= new TextField();
            Label description1= new Label();
            Label titre1= new Label();
            Label domaine1= new Label();
            Label publish_date1= new Label();
            
            id.setVisible(false);
            
            
            
            Button btn1=new Button("Like");
            

            id.setText(Integer.toString(v.getId()));
            description1.setText(v.getDescription());
            titre1.setText(v.getTitle());
            url1.setText(v.getUrl());
            //url1.getEngine().load(formations.get(i).getUrl());
            
           
            
            url1.setPrefSize(100,150);
            
            

            domaine1.setText(formations.get(i).getDomaine());
           // publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
          
             btn1.setOnAction( e -> {
                 

                            FormationService sp = new FormationService();
                            System.out.println(v.getId());
                            sp.supprimerVideo(v);
                            System.out.println("deleted");
              
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Formation");
                 alert.setHeaderText("Formation supprimé !");
                 alert.setContentText("Go Back To The Homepage");
                 
                 alert.showAndWait();
            });
            
             
           
            url1.setOnAction( e -> {
                                    
                //Parent root = loader.load();
                System.out.println(((Hyperlink) e.getTarget()).getText());
                
                Node node = (Node) e.getSource();
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_formation/video_detail.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Video_detailController video_detailController = loader.getController();
                video_detailController.initData(((Hyperlink) e.getTarget()).getText());
                stage.setScene(scene);
                
                
            });
            
           
           
           
           
            VBox forma1=new VBox();
            //forma1.getChildren().addAll(id,titre1,url1,description1,publish_date1,domaine1,btn1);
            forma1.getChildren().addAll(id,titre1,url1,description1,domaine1,btn1);
            
            
            
            
            HBox formhor=new HBox();
            
            formhor.getChildren().addAll(forma1);
            
            VBox_formations.getChildren().add(formhor);
            
            
            
            
//           
            
            
            
            
         
            
             
             
              
        

  
          
            
       
    }
        

        
    
    }
    
   
    
   
    }


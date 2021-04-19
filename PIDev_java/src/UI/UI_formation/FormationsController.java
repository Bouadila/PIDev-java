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
        

            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            

            
            Hyperlink url1= new Hyperlink();
            TextField id= new TextField();
            Label description1= new Label();
            Label titre1= new Label();
            Label domaine1= new Label();
            Label publish_date1= new Label();
            
            id.setVisible(false);
            
            
            
            Button btn1=new Button("Like");
            

            id.setText(Integer.toString(formations.get(i).getId()));
            description1.setText(formations.get(i).getDescription());
            titre1.setText(formations.get(i).getTitle());
            url1.setText(formations.get(i).getUrl());
            //url1.getEngine().load(formations.get(i).getUrl());
            
           
            
            url1.setPrefSize(100,150);
            
            

            domaine1.setText(formations.get(i).getDomaine());
           // publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
          
            
            
           
           
            
            
            VBox forma1=new VBox();
            //forma1.getChildren().addAll(id,titre1,url1,description1,publish_date1,domaine1,btn1);
            forma1.getChildren().addAll(id,titre1,url1,description1,domaine1,btn1);
            
            
            
            
            HBox formhor=new HBox();
            
            formhor.getChildren().addAll(forma1);
            
            VBox_formations.getChildren().add(formhor);
            
            
            
            
             url1.setOnAction( e -> {
                                    
                /*try {
                        FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_formation/ModifierFormation.fxml"));
                        Parent root = loader.load();
                        ModifierFormationController mF = loader.getController();

                        mF.settf_ID_forma(Integer.toString(v.getId())); //A3mel TextField hidden ( fi properties visibility)
                        mF.settfUrlMod(v.getUrl());
                        mF.settfTitreMod(v.getTitle());
                        mF.settfDescriptionMod(v.getDescription());
                        mF.settfDomaineMod(v.getDomaine());*/
                        
                
                        System.out.println("test clicked");
                        System.out.println(v.getId());
                        String content_Url = "<iframe width=\"560\" height=\"315\" src=\"http://www.youtube.com/embed/9bZkp7q19f0\" frameborder=\"0\" allowfullscreen></iframe>";

                        
                        WebView webView = new WebView();
                        WebEngine webEngine = webView.getEngine();
                        webEngine.loadContent(content_Url);

                       
                        StackPane root = new StackPane();
                        root.getChildren().add(webView);

                        Scene scene = new Scene(root, 300, 250);

                       
                      
                          
                     
                      Stage Window = (Stage) url1.getScene().getWindow();
                      Window.setScene(new Scene(root));
                        

                  
                      
                    /*    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                        }*/
            });
       
            
            
            
            
         
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
             
             
              
        

  
          
            
       
    }
        

        
    
    }
    
   
    
   
    }


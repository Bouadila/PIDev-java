/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Entity.Votes;
import Services.FormationService;
import Services.VoteService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
     private Votes vo;
    @FXML
    private ImageView id_img;
     

    

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


           // id_img = new Image("C:\\Users\\User\\Downloads\\background.jpg");
            
           
            
            Hyperlink url1= new Hyperlink();
            url1.setStyle("-fx-font: normal bold 20px 'serif' ");
            url1.setAlignment(Pos.CENTER);
            url1.setTextFill(Color.web("#6e1010"));
            
            
            TextField id= new TextField();
            
            Label description1= new Label();
            description1.setStyle("-fx-font: normal 30px 'serif' ");
            
            Label titre1= new Label();
            titre1.setStyle("-fx-font: normal bold 70px 'serif' ");
            titre1.setTextFill(Color.web("#da0505"));
            
            
            Label domaine1= new Label();
            domaine1.setStyle("-fx-font: normal bold 40px 'serif' ");
            domaine1.setTextFill(Color.web("#ff1818"));
            
            
            Label votes= new Label();
            //Label publish_date1= new Label();
            
            id.setVisible(false);
            
            
            
            FontAwesomeIconView btnLiked=new FontAwesomeIconView(FontAwesomeIcon.THUMBS_UP);
            btnLiked.setStyle(" -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"); 
            
            
            FontAwesomeIconView btnUnliked=new FontAwesomeIconView(FontAwesomeIcon.THUMBS_ALT_UP);
            btnUnliked.setStyle(" -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"); 

            
            
//              UserSession s = UserSession.instance;
//            if (s.getU().getUsername().equals(v.getOwner().getUsername())) {
//
//                details.add(Delete, 2, 3);
//            }
//            if (vo.find(v, s.getU())) {
//                btnUnliked.setVisible(true);
//                btnUnliked.setVisible(false);
//            } else {
//                btnUnliked.setVisible(false);
//                btnUnliked.setVisible(true);
//            }
            
            
            
            
           
            id.setText(Integer.toString(v.getId()));
            description1.setText(v.getDescription());
            titre1.setText(v.getTitle());
            url1.setText(v.getUrl());
            url1.setPrefSize(700,800);

            domaine1.setText(formations.get(i).getDomaine());
            // publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
           // hedhy ata nchoufha maa firas :  votes.setText(Integer.toString(vo.getVideo_id()));

           
           
           
           
            
          
             btnUnliked.setOnMouseClicked( e -> {
                 

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
            
             
             
             
//              btnLiked.setOnMouseClicked(e -> {
//
//                    vo.Add(u.getU(), v);
//                    btnUnliked.setVisible(true);
//                    btnLiked.setVisible(false);
//                    getVideo_id.setText(Integer.toString(Integer.parseInt(getVideo_id.getText()) + 1));
//                   // updateRanks();
//
//                
//            });
//              
//            btnUnliked.setOnMouseClicked(e-> {
//
//                    vo.delete(v, u.getU());
//                    btnUnliked.setVisible(false);
//                    btnLiked.setVisible(true);
//                    getVideo_id.setText(Integer.toString(Integer.parseInt(getVideo_id.getText()) - 1));
//                   // updateRanks();
//                
//            });
             
             
           url1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
            url1.setScaleX(1.5);
            url1.setScaleY(1.5);
            url1.setTextFill(Color.web("#00E676"));

                    }
                    });
           
           url1.setOnMouseExited(new EventHandler<MouseEvent>() {
           @Override public void handle(MouseEvent e) {
           url1.setScaleX(1);
           url1.setScaleY(1);
           url1.setTextFill(Color.web("#6e1010"));
                    }
                    });
            
            
            url1.setOnAction( e -> {
                                    

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
           // final ScrollPane sp = new ScrollPane();
            //forma1.getChildren().addAll(id,titre1,url1,description1,publish_date1,domaine1,btn1);
            forma1.getChildren().addAll(id,titre1,url1,domaine1,description1);

            forma1.setAlignment(Pos.CENTER);
            
            
            

       
            
           
            
            HBox formhor=new HBox();
             HBox actions= new HBox();
             
            actions.getChildren().addAll(btnUnliked,votes);
            actions.setAlignment(Pos.CENTER);
            
            formhor.getChildren().addAll(forma1,actions);

            VBox_formations.getChildren().add(formhor);
            
            
            
            
         
            
            
            
            
         
            
             
             
              
        

  
          
            
       
    }
        

        
    
    }

    
    
   @FXML
    private void  btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        
        
        
        
    }
    
    
    
    
    
   
    }


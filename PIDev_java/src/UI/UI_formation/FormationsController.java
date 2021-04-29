/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Entity.Votes;
import Services.FormationService;
import Services.UserSession;
import Services.VoteService;
import UI.OffreUI.OffreFXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import utils.DataSource;


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
    private Button id_btn_voir_liste;
     

    

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
            
             VoteService vs=new VoteService();    
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
            votes.setTextFill(javafx.scene.paint.Color.BLACK);
            votes.setFont(Font.font("Cambria", 24));
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

            
            
            
            
            
            
           
            id.setText(Integer.toString(v.getId()));
            //System.out.println(v.getId());
            description1.setText(v.getDescription());
            titre1.setText(v.getTitle());
            url1.setText(v.getUrl());
            url1.setPrefSize(700,800);
            // publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            domaine1.setText(formations.get(i).getDomaine());
            
//            try {
//                
//                System.out.println(new VoteService().getVotes(v));
//            } catch (SQLException ex) {
//                Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                votes.setText(Integer.toString(new VoteService().getVotes(v)));
            } catch (SQLException ex) {
                Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
           
           
           
            
          
//             btnUnliked.setOnMouseClicked( e -> {
//                 
//
//                            VoteService vs = new VoteService();
//                            System.out.println(v.getId());
//                            
//                            vs.delete(v);
//                            System.out.println("deleted");
//              
//                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                 alert.setTitle("Formation");
//                 alert.setHeaderText("Formation supprimé !");
//                 alert.setContentText("Go Back To The Homepage");
//                 
//                 alert.showAndWait();
//            });
            
             


//
//                    UserSession s = UserSession.instance;
//            
//            if (VoteServices.find(v, s.getU())) {
//                votes.setSelected(true);
//                btnLiked.setVisible(true);
//                btnUnliked.setVisible(false);
//            } else {
//                votes.setSelected(false);
//                btnUnliked.setVisible(true);
//                btnLiked.setVisible(false);
//            }

                


             
             int x = formations.get(i).getId();
              btnLiked.setOnMouseClicked(e -> {

                  System.out.println(x);
                    VoteService vl = new VoteService();
                    Formation f = new Formation();
                    f.setId(x);
                try {
                    vl.Add(f);
                } catch (SQLException ex) {
                    Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    btnUnliked.setVisible(true);
                    btnLiked.setVisible(false);
                    votes.setText(Integer.toString(Integer.parseInt(votes.getText()) + 1));
                    //votes.setText(Integer.toString(new VoteService().getVotes(v)+1));
                
            });
              
              
            btnUnliked.setOnMouseClicked(e-> {

                    Formation f = new Formation();
                    f.setId(x);
                    
                    new VoteService().delete(f);
                    btnUnliked.setVisible(false);
                    btnLiked.setVisible(true);
                    votes.setText(Integer.toString(Integer.parseInt(votes.getText()) - 1));
                    //votes.setText(Integer.toString(new VoteService().getVotes(v)-1));
                
            });
             
             
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
            
           
            
           
           Rating id_rating = new Rating();
           Button id_button_rate = new Button("évaluer");
           Label space = new Label("");
           Label space1 = new Label("");
           
           Button reclamation = new Button("Réclamation");
           reclamation.setTextFill(Color.web("#6e1010"));
           reclamation.setBackground(new Background(new BackgroundFill(Color.web("#00E676"), CornerRadii.EMPTY, Insets.EMPTY)));
           
           id_rating.setRating(0);
           id_button_rate.setTextFill(Color.web("#6e1010"));
           id_button_rate.setBackground(new Background(new BackgroundFill(Color.web("#00E676"), CornerRadii.EMPTY, Insets.EMPTY)));
           
           
           
           reclamation.setOnAction(e-> {
                        
                   
     Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/UI/UI_Reclamation/ReclamationAjout.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage Window = (Stage) ((Node)e.getTarget()).getScene().getWindow();
        Window.setScene(new Scene(root));
                   
                
            });
           
           
      id_button_rate.setOnAction(e-> {
        
        if(e.getSource()==id_button_rate)
        {
            
            Formation f = new Formation();
            f.setVotes(Double.toString(id_rating.getRating()));
            f.setId(x);
                    
            new FormationService().ajouterRate(f);
          
           // new FormationService().ajouterRate(v);
            
        }
        
    });
          
           
            VBox forma1=new VBox();
//              final Pane sp = new Pane();
//              sp.setStyle(" -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#00E676;"); 
            //forma1.getChildren().addAll(id,titre1,url1,description1,publish_date1,domaine1,btn1);
            forma1.getChildren().addAll(id,titre1,url1,domaine1,description1,id_rating,space1,id_button_rate,space,reclamation);

            forma1.setAlignment(Pos.CENTER);
            
            
            

       
            
           
            
            HBox formhor=new HBox();
            HBox actions= new HBox();
             
            actions.getChildren().addAll(btnUnliked,votes,btnLiked);
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

    @FXML
    private void btn_voir_liste(ActionEvent event) throws IOException {
    
    
    
     Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AfficherFormation.fxml"));
        Stage Window = (Stage) id_btn_voir_liste.getScene().getWindow();
        Window.setScene(new Scene(root));
       
    }
    
   @FXML
    private void rendezVous(MouseEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/RendezVousFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

//     @FXML
//    private void offre(MouseEvent event) {
//        Node node = (Node) event.getSource();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
//                Stage stage = (Stage) node.getScene().getWindow();
//                Scene scene = null;  
//                try {
//                    scene = new Scene(loader.load());
//                } catch (IOException ex) {
//                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                 stage.setScene(scene);
//    }

    @FXML
    private void reclamation(ActionEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

    @FXML
    private void apropos(ActionEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/AboutUs.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

    @FXML
    private void formation(ActionEvent event) {
         Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_formation/Formations.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
        
    }
           Connection con = DataSource.getInstance().getCnx();

      @FXML
    private void goToProfil(ActionEvent event) throws IOException, SQLException {
        String role="";
         String request0 ="SELECT *,SUBSTR(roles,3,5) as rol from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next())
        {
            role = rs0.getString("rol");
        }
        
        if (role.equals("Candi")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserCandidatAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
        if (role.equals("Emplo")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
    }
    

    @FXML
    private void offre(ActionEvent event) throws SQLException {
        String role = "";
        String request0 = "SELECT * from `user` WHERE `user`.`id` = " + UserSession.getIdSession() + ";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next()) {
            role = rs0.getString("roles");
        }

        if (role.equals("[\"Candidat\"]")) {
                    Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreCandidatFXML.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        }
        if (role.equals("[\"Employeur\"]")) {
            Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
        }
    }
    }


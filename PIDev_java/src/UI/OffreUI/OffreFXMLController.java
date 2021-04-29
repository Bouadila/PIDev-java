/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
import Services.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import  javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class OffreFXMLController implements Initializable {

    @FXML
    private ListView<HBox> offreListe;
    private FontAwesomeIconView btnNewOffre;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        //addOffre();
       
    }    
    //

    private void loadDate() {
   
         for ( int i = 0; i < new OffreService().getAll().size(); i++){
            Offre offre = new OffreService().getAll().get(i);
            Label lb_post = new Label(offre.getPost());
            lb_post.setStyle("-fx-font: normal bold 15px 'serif'");
            Text lb_desc = new Text(offre.getDescription());
            VBox vBox = new VBox(lb_post, lb_desc);
            lb_post.setMaxWidth(Double.MAX_VALUE);
            lb_post.setAlignment(Pos.CENTER);
            HBox hb = new HBox();
//            ImageView imageView = new ImageView("/Images/Nous-recrutons.jpg");
//            imageView.setFitHeight(100);
//            imageView.setFitWidth(100);
//            imageView.setPreserveRatio(true);
            Circle circle = new Circle();
            circle.setCenterX(100.0f);
            circle.setCenterY(100.0f);
            circle.setRadius(70.0f);
            Image im = new Image("/Images/Offre-emploi.jpg");
            circle.setFill(new ImagePattern(im));
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(circle,vBox);
            offreListe.getItems().add(hb);
            offreListe.getItems().get(i).setOnMouseClicked(e ->{
                System.out.println(offre.getId());
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/DetailOffre.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    DetailOffreController detailController = loader.getController();
                    detailController.initData(offre);
                    stage.setScene(scene);
            });
        }
    }

    
    @FXML
    private void refreshData(MouseEvent event) {
        offreListe.getItems().clear();
        for ( int i = 0; i < new OffreService().getAll().size(); i++){
            Offre offre = new OffreService().getAll().get(i);
            Label lb_post = new Label(offre.getPost());
            lb_post .setStyle("-fx-font: normal bold 15px 'serif'");
            Text lb_desc = new Text(offre.getDescription());
            VBox vBox = new VBox(lb_post, lb_desc);
            lb_post.setMaxWidth(Double.MAX_VALUE);
            lb_post.setAlignment(Pos.CENTER);
            HBox hb = new HBox();
//            ImageView imageView = new ImageView("/Images/Nous-recrutons.jpg");
//            imageView.setFitHeight(100);
//            imageView.setFitWidth(100);
//            imageView.setPreserveRatio(true);
            Circle circle = new Circle();
            circle.setCenterX(50.0f);
            circle.setCenterY(50.0f);
            circle.setRadius(25.0f);
            Image im = new Image("/Images/Offre-emploi.jpg");
            circle.setFill(new ImagePattern(im));
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(circle,vBox);
            offreListe.getItems().add(hb);
            offreListe.getItems().get(i).setOnMouseClicked(e ->{
                System.out.println(offre.getId());
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/DetailOffre.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    DetailOffreController detailController = loader.getController();
                    detailController.initData(offre);
                    stage.setScene(scene);
            });
        }
    }
 @FXML
    private void addOffre(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/UI/OffreUI/AjouterOffreFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Node node = (Node) event.getSource();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/AjouterOffreFXML.fxml"));
//                Stage stage = (Stage) node.getScene().getWindow();
//                Scene scene = null;  
//                try {
//                    scene = new Scene(loader.load());
//                 } catch (IOException ex) {
//                    ex.getMessage();
//                }
//                stage.setScene(scene);
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

//    @FXML
//    private void offre(ActionEvent event) {
//    }
//
//    @FXML
//    private void reclamation(ActionEvent event) {
//    }

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

    @FXML
    private void offre(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/UI/OffreUI/AjouterOffreFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

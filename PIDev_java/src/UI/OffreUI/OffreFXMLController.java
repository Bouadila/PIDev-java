/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class OffreFXMLController implements Initializable {

    @FXML
    private ListView<HBox> offreListe;
    private FontAwesomeIconView btnNewOffre;

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
            Label lb_desc = new Label(offre.getPost());
            HBox hb = new HBox();
            ImageView imageView = new ImageView("/Images/9.jpg");
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(imageView, lb_desc);
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
    private void refreshData(MouseEvent event) {
        offreListe.getItems().clear();
        for ( int i = 0; i < new OffreService().getAll().size(); i++){
            Offre offre = new OffreService().getAll().get(i);
            Label lb_desc = new Label(offre.getPost());
            HBox hb = new HBox();
            ImageView imageView = new ImageView("/Images/9.jpg");
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(imageView, lb_desc);
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

    
}

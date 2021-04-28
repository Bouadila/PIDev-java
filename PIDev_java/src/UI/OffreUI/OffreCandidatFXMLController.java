/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class OffreCandidatFXMLController implements Initializable {

    @FXML
    private ListView<HBox> listOffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void offre(MouseEvent event) {
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

    @FXML
    private void rendezVous(MouseEvent event) {
    }

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
            listOffre.getItems().add(hb);
            listOffre.getItems().get(i).setOnMouseClicked(e ->{
                System.out.println(offre.getId());
                Node node = (Node) e.getSource();
                //
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/DetailOffreCandidat.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    DetailOffreCandidatController detailController = loader.getController();
                    detailController.initData(offre);
                    stage.setScene(scene);
            });
        }
        
    }
    
}

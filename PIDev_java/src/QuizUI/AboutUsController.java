/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizUI;

import UI.OffreUI.OffreFXMLController;
import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.javascript.event.GMapMouseEvent;
import com.dlsc.gmapsfx.javascript.event.UIEventType;
import com.dlsc.gmapsfx.javascript.object.GoogleMap;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.MapOptions;
import com.dlsc.gmapsfx.javascript.object.MapTypeIdEnum;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class AboutUsController implements Initializable {

    @FXML
    private Button login1;
    @FXML
    private Button profil;
    @FXML
    private GoogleMapView googleMapView;

    private GoogleMap map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        googleMapView.addMapInitializedListener(() -> configureMap());
    }

    @FXML
    private void offre(MouseEvent event) {
    }

    @FXML
    private void rendezVous(MouseEvent event) {
    }

    @FXML
    private void goToLogin(ActionEvent event) {
    }

    @FXML
    private void goToProfil(ActionEvent event) {
    }

    protected void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(36.8993187, 10.1930525)).mapMaker(true)
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(14);
        map = googleMapView.createMap(mapOptions, false);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(36.8993187, 10.1930525))
                .visible(Boolean.TRUE)
                .title("FHNW");

        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);

    }

    @FXML
    private void GoToOffre(MouseEvent event) {
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

}

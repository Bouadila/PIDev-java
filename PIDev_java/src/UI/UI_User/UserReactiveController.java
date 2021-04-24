/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserReactiveController implements Initializable {

    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Hyperlink offre;
    @FXML
    private Hyperlink demande;
    @FXML
    private Hyperlink annonce;
    @FXML
    private Hyperlink login1;
    @FXML
    private Hyperlink quiz;
    @FXML
    private Button OUI;
    @FXML
    private Button NON;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
            Connection con = DataSource.getInstance().getCnx();

    @FXML
    private void goToAcceuil(ActionEvent event) {
    }

    @FXML
    private void goToProfil(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goTooffre(ActionEvent event) {
    }

    @FXML
    private void goTodemande(ActionEvent event) {
    }

    @FXML
    private void goToannoce(ActionEvent event) {
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void quiz(ActionEvent event) {
    }

    @FXML
    private void OUI(MouseEvent event) throws SQLException, IOException {
         int etatDes= '0';

         String req2 ="UPDATE `user` SET `etat` = '"+'0'+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
       
       ps2.executeUpdate();
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void NON(MouseEvent event) throws IOException {
           Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}

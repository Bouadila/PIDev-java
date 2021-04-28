/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import Services.UserSession;
import static UI.UI_User.UserAddController.randomCodee;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.DataSource;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserActiveCodeController implements Initializable {

    @FXML
    private Label check;
    @FXML
    private TextField txtcodeActive;
    @FXML
    private Button verif;
           Connection con = DataSource.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActiveVerifier(javafx.scene.input.MouseEvent event) throws SQLException, IOException {
        if(txtcodeActive.getText().equals(""+randomCodee))
        {
      String req2 ="UPDATE `user` SET `activation_token` = NULL WHERE `user`.`id` = "+UserSession.getIdSession()+";";
      java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
      ps2.executeUpdate();
      JOptionPane.showMessageDialog(null, "code correct , Votre compte est activé");
      Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();           
        } else
        { JOptionPane.showMessageDialog(null, "code incorrect , Votre compte n'est pas activé");  }             
    }


    private void goToProfil(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }


    private void goToLogin(ActionEvent event) throws IOException {
          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    
}
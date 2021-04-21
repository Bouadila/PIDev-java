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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserResetController implements Initializable {

    @FXML
    private Label inscrireUser;
    @FXML
    private Label check;
    @FXML
    private Button log1;
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
    private TextField txtnvpwd;
    @FXML
    private PasswordField txtverifpwd;
 
    Connection con = DataSource.getInstance().getCnx();

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Register(MouseEvent event) {
    }


    @FXML
    private void goToAcceuil(ActionEvent event) {
    }

    @FXML
    private void goToProfil(ActionEvent event) {
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
    private void goToLogin(ActionEvent event) {
    }

    @FXML
    private void quiz(ActionEvent event) {
    }

    @FXML
    private void Actreset(MouseEvent event) throws SQLException, IOException {
      
        if (txtnvpwd.getText().equals(txtverifpwd.getText()))  {
            String passwordreset = BCrypt.hashpw(txtnvpwd.getText(),BCrypt.gensalt(13));
            passwordreset = passwordreset.replaceFirst("2a", "2y") ;
                String req2 ="UPDATE `user` SET `password` = '"+passwordreset+"' WHERE `user`.`id` = "+UserSession.getIdSession()+";";
                java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "mot de passe est changer");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                stage.setScene(scene);
                stage.show();           
        }
        else{
                  JOptionPane.showMessageDialog(null, "mot de passe  n'est pas changer");
        }       
    }
}

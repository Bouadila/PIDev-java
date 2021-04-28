/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import static Services.UserSession.setIdSession;
import java.io.IOException;
import java.net.URL;
import static java.nio.channels.spi.AsynchronousChannelProvider.provider;
import static java.nio.channels.spi.SelectorProvider.provider;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import static org.json.JSONObject.NULL;
import utils.DataSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private Label check;
    @FXML
    private Button log;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pw;
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
    private Label inscrireUser;
    @FXML
    private Hyperlink Accueil;
    @FXML
    private Hyperlink profil;
    @FXML
    private Label oublierPwd;
    Connection con = DataSource.getInstance().getCnx();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(javafx.scene.input.MouseEvent event) throws SQLException, IOException {
        String role = "";
        String token = "";
        String pp = "";
        int etats;
 
        String req = "SELECT * ,SUBSTR(roles,3,5) as rol  FROM user Where email = ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, user.getText());
        ResultSet rs = ps.executeQuery();
              
        if (!rs.next()) {
            check.setTextFill(Color.TOMATO);
            check.setText("Email incorrect");
      
        } else { 
              pp =rs.getString("password");    
              pp = pp.replaceFirst("2y", "2a");            
              if (BCrypt.checkpw(pw.getText(),pp))
             {                                                      
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            etats=rs.getInt("etat");
            role=rs.getString("rol");
            token=rs.getString("activation_token");
            int k = rs.getInt("id");
            setIdSession(k);
            
            if(token.equals("22"))     {   
                 check.setTextFill(Color.TOMATO);
            check.setText("ce compte est désactiver");
            }
            else {
            if(etats==0 )  {
            if (role.equals("Candi")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserCandidatAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
                        if (role.equals("Emplo")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
                
            }
             if (role.equals("admin")){
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAfficheBack.fxml")));
                    stage.setScene(scene);
                    stage.show();                
            } 
            }
            else if(etats==1)         {
                   check.setTextFill(Color.TOMATO);
                   check.setText("vous avez désactiver ce compte !! /n reactiver votre compte cliquer ici");
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserReactive.fxml")));
                    stage.setScene(scene);
                    stage.show();
            }          
            }                   
        }
              else {
                    check.setTextFill(Color.TOMATO);
            check.setText("Mot de passe incorrect");           }
    }
    }
        
  
    @FXML
  
 private void Register(javafx.scene.input.MouseEvent event) throws IOException {
             
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAdd.fxml")));
                    stage.setScene(scene);
                    stage.show();               
         
    }
    

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
    private void forgetPwd(MouseEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserResetPwd.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

   

   
    }
    


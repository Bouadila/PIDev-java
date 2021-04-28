/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Entity.User;
import Services.UserService;
import static Services.UserSession.setIdSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserAfficheBackController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox vbox;
    @FXML
    private Button logout;
    @FXML
    private Button acceuil;
    @FXML
    private Button comptes;
    @FXML
    private TextField rechercher;
    Connection con = DataSource.getInstance().getCnx();
    @FXML
    private ListView<HBox> UserList;
private final ImageView brandIcon = new ImageView(); 
     private final Label descriptionLabel = new Label(); 
    User useer = null ;

    private final Rectangle colorRect = new Rectangle(10, 10);
    @FXML
    private Label compte;
    @FXML
    private Button comptes1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
       
            for ( int i = 0; i < new UserService().getAllUser().size(); i++){
            User user = new UserService().getAllUser().get(i);
            int j=i+1;
            Label lb_desc = new Label("Compte num "+j+" :\n   "+user.getEmail());
//            colorRect.setStroke(Color.BLACK); 
//              descriptionLabel.setStyle("-fx-opacity: 0.75;"); 
//              descriptionLabel.setGraphic(colorRect);
lb_desc.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em; "); 
        GridPane.setConstraints(lb_desc, 1, 0); 
       
          ImageView imageView = new ImageView("/image/default (1).png");
          Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
        );
        clip.setArcWidth(10);
        clip.setArcHeight(20);
         
//          ImageView imageVie = new ImageView("\GUI\Icons\icons8_user_50px.png");
          HBox hb = new HBox();
   
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(  imageView,lb_desc ,descriptionLabel);
          UserList.getItems().add(hb);
            UserList.getItems().get(i).setOnMouseClicked(e ->{
                System.out.println(user.getEmail());
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_User/UserDetaiAffichBackl.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(UserAfficheBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    UserDetaiAffichBacklController detailController = loader.getController();
                try {
                    detailController.initData(user);
                } catch (SQLException ex) {
                    Logger.getLogger(UserAfficheBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    stage.setScene(scene);
            });
        
            }
                       
        
    }  
       

    @FXML
    private void acceuilClicks(ActionEvent event) {
    }

    @FXML
    private void CompteClicks(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAfficheBack.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void GoToAllContact(MouseEvent event) {
    }

    @FXML
    private void GoToAllComment(MouseEvent event) {
    }

    @FXML
    private void labelcompte(MouseEvent event) throws SQLException {
    }
   

    @FXML
    private void logout(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

//    @FXML
//    private void rechercher(ActionEvent event) {
//        UserService cs = new UserService();
//        ArrayList listcs = (ArrayList) cs.finduser(Integer.SIZE);
//        ObservableList OReservation = FXCollections.observableArrayList(listcs);
//        FilteredList<User> filteredData = new FilteredList<>(OReservation, p -> true);
//        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(myObject -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (String.valueOf(myObject.getEmail()).toLowerCase().contains(lowerCaseFilter)|| String.valueOf(myObject.getEtat()).toLowerCase().contains(lowerCaseFilter)||String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//
//                }
//                return false;
//            });
//        });
//        SortedList<User> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
//        tableview.setItems(sortedData);
//    
//    }

    @FXML
    private void rechercher(KeyEvent event) {
      
    }

    @FXML
    private void compte(MouseEvent event) throws IOException {
             
                   Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserStat.fxml")));
                    stage.setScene(scene);
                    stage.show();               
         
    
    }

    @FXML
    private void gotToFormation(ActionEvent event) {
        
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_formation/ListViewFormBack.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(UserCandidatAfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    stage.setScene(scene);
                    stage.show();
    }
}

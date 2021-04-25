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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            // TODO
//            fillGrid();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserAfficheController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            nom.setCellValueFactory(new PropertyValueFactory<>("name"));
//         prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//         mail.setCellValueFactory(new PropertyValueFactory<>("email"));
//         special.setCellValueFactory(new PropertyValueFactory<>("special"));
//         gover.setCellValueFactory(new PropertyValueFactory<>("gover"));
//         
//         etat.setCellValueFactory(new PropertyValueFactory<>("etatecrit"));
//         
//         role.setCellValueFactory(new PropertyValueFactory<>("roles"));
//
//         UserService cs= new UserService();
//         List<User> arrc=new ArrayList<>();
//       
//          try {
//            arrc=cs.readAll();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserAfficheBackController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       ObservableList<User> ovbservableList= FXCollections.observableArrayList(arrc);
//       tableview.setItems(ovbservableList);
//       
//       //Updatable
//        tableview.setEditable(true);
//    }
//     public void fillGrid() throws SQLException{
//        
//        
       
            for ( int i = 0; i < new UserService().getAllUser().size(); i++){
            User user = new UserService().getAllUser().get(i);
            int j=i+1;
            Label lb_desc = new Label("l'email du compte num "+j+" :"+user.getEmail());
//            colorRect.setStroke(Color.BLACK); 
//              descriptionLabel.setStyle("-fx-opacity: 0.75;"); 
//              descriptionLabel.setGraphic(colorRect);
lb_desc.setStyle("-fx-font-weight: bold; -fx-font-size: 1em; "); 
        GridPane.setConstraints(lb_desc, 1, 0); 
//          ImageView imageVie = new ImageView("\GUI\Icons\icons8_user_50px.png");
          HBox hb = new HBox();
   
            //Label lb_id = new Label(String.valueOf(offre.getId()));
            hb.getChildren().addAll(  lb_desc ,descriptionLabel);
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
           
//   ObservableList<User> listUser = (ObservableList<User>) new UserService().getAllUser();
//
//    FilteredList<User> filteredData = new FilteredList<>(listUser, b->true);
//         rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
//             filteredData.setPredicate(useer -> {
//         if(newValue == null || newValue.isEmpty() )
//                 {
//                       return true;
//                 }
//         
//         String lowerCaseFilter = newValue.toLowerCase();
//         
//         if(useer.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1)
//                 {
//                     return true;
//                 } 
//         else if(useer.getName().toLowerCase().indexOf(lowerCaseFilter) != -1)
//                 {
//                     return true;
//                 }
//         else return false;
//         
//         });
//         
//          });           
//
//         SortedList<User> sortedData = new SortedList<>(filteredData);
////         sortedData.comparatorProperty().bind(UserList.comparatorProperty());
////         UserList.setItems(sortedData);
//              
        
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
//          String fullName="";
//         String email="";
//         String specia="";
//         String gover="";
//
//         
//        String request0 ="SELECT * from `user`";
//        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
//        ResultSet rs0 = ps0.executeQuery();
//
//        if (rs0.next()) {
//            String a = rs0.getString("name");
//            String b = rs0.getString("prenom");
//            fullName = "Nom et prénom : "+b+" "+a;
//            String c = rs0.getString("gover");
//            String d = rs0.getString("special");
//            String e = rs0.getString("email");
//            
////                     tfmail.setText("email : "+e);
////                     gover("gover : "+c);
////                     tfspecialite.setText("special : "+d);
//
//        
//                ArrayList<User> TabB = new ArrayList<>();
//
//          ObservableList ViewRec = FXCollections.observableArrayList(request0);
//        tableview.setItems(ViewRec);
//        nom.setCellValueFactory(new PropertyValueFactory<>("name"));
//        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        special.setCellValueFactory(new PropertyValueFactory<>("special"));
//        this.role.setText(""+TabB.size());
//        }
    }
//    

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
}

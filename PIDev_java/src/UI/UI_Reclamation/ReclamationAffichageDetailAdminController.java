/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_Reclamation;

import Entity.Reclamation;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAffichageDetailAdminController implements Initializable {

    @FXML
    private Label taTitle;
    @FXML
    private Label taStatus;
    @FXML
    private Label taEmail;
    @FXML
    private Label taDate;
    @FXML
    private Label taDes;
    @FXML
    private Button btnApprove;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnChat;
    @FXML
    private Button btnBack;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void loadData( Reclamation rec){

        taTitle.setText(rec.getTitle());
        
        taStatus.setText(rec.getStatus());
        
        taEmail.setText(rec.getEmail());

        taDate.setText(rec.getDateRec());
        
        taDes.setText(rec.getDescRec());
    }

    @FXML
    private void handleButtonApprove(ActionEvent event) {
           
    }

    @FXML
    private void handleButtonDelete(ActionEvent event) throws IOException {
        
        
        Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichageAdmin.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ReclamationAffichageAdminController ReclamationListController = loader.getController();
                    
                    stage.setScene(scene);
    }

    @FXML
    private void handleButtonChat(ActionEvent event) {
        try {
                    Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/Chat.fxml"));
                    Stage stage = new Stage();

                    Scene scene = new Scene(loader.load());
                    ChatController chatController = loader.getController();
                    
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    //Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void showReclamation() {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> lr = rs.Lister(1);
        ObservableList<Reclamation> data = FXCollections.observableArrayList();
        data.addAll(lr);
               
        taTitle.setText(lr.get(0).getTitle());
        
        taStatus.setText(lr.get(0).getStatus());
        
        taEmail.setText(lr.get(0).getEmail());

        taDate.setText(lr.get(0).getDateRec());
        
        taDes.setText(lr.get(0).getDescRec());
    }

    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichageAdmin.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ReclamationAffichageAdminController ReclamationListController = loader.getController();
                    
                    stage.setScene(scene);                    
    }
}

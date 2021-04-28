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
import java.util.ResourceBundle;
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
public class ReclamationAffichageDetailController implements Initializable {

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
    private Button btnDelete;
    @FXML
    private Button btnChat;
    @FXML
    private Button btnBack;
    
    private ReclamationService rs = new ReclamationService();
    private Reclamation rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void loadData( Reclamation rec){

        this.rec = rec;
        taTitle.setText(rec.getTitle());
        
        taStatus.setText(rec.getStatus());
        
        taEmail.setText(rec.getEmail());

        taDate.setText(rec.getDateRec());
        
        taDes.setText(rec.getDescRec());
    }
    @FXML
    private void handleButtonDelete(ActionEvent event) throws IOException {
        rs.Supprimer(rec);
        Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ReclamationAffichageController ReclamationListController = loader.getController();
                    
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

    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ReclamationAffichageController ReclamationListController = loader.getController();
                    
                    stage.setScene(scene);   
    }
    
}

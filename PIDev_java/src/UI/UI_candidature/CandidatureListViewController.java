/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import Entity.Candidature;
import Services.CandidatureService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class CandidatureListViewController implements Initializable {

    @FXML
    private Button btn_refreshCandidature;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btn_searchCandidature;
    @FXML
    private Button btn_AjouterCandidature;
    @FXML
    private ListView<String> CandidatureList;
    ObservableList<String> list = FXCollections.observableArrayList("");
    @FXML
    private Button btn_ModifierCandidature;
    @FXML
    private Button btn_SuppCandidature;
    @FXML
    private TextField txt_offer;
    @FXML
    private TextField txt_num;
    @FXML
    private TextField txt_status;
    @FXML
    private TextField txt_diplome;
    @FXML
    private Button btn_cv;
    @FXML
    private Button btn_lettre_motiv;
    @FXML
    private TextField txt_dispo;
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    Connection connection = cnx ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Candidature candidature = null ;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadDate();
    }    
    
    private void loadDate(){
        CandidatureList.setItems(list); 
    }
    


    @FXML
    private void refreshCandidature(ActionEvent event) {
    }


    @FXML
    private void gotoModifierCandidature(ActionEvent event) {
    }
    
    @FXML
    private void gotoAjouterCandidature(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AjouterCandidature.fxml"));
        Stage Window = (Stage) btn_AjouterCandidature.getScene().getWindow();
        Window.setScene(new Scene(root));
    }
    
    @FXML
    private void gotoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AfficherCandidature.fxml"));
        Stage Window = (Stage) back.getScene().getWindow();
        Window.setScene(new Scene(root));
    
    }
}

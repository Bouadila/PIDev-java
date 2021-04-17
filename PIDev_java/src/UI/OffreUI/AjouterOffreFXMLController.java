/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Offre;
import Services.OffreDao.OffreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class AjouterOffreFXMLController implements Initializable {
    @FXML
    private TableView<Offre> offreTable;
    @FXML
    private TableColumn<Offre, String> postCol;
    @FXML
    private TableColumn<Offre, String> descriptionCol;
    @FXML
    private TableColumn<Offre, String> salaireCol;
    @FXML
    private TableColumn<Offre, String> expirationCol;
    @FXML
    private TableColumn<Offre, String> nbPlaceCol;
    @FXML
    private TableColumn<Offre, String> experienceCol;
    @FXML
    private TextField tfPost;
    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfObj;
    @FXML
    private TextField tfCompetence;
    @FXML
    private TextField tfMin;
    @FXML
    private TextField tfSalaire;
    @FXML
    private TextField tfContrat;
    @FXML
    private TextField tfMax;
   
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    

    private void loadDate() {
        ObservableList<Offre> listOffres = FXCollections.observableArrayList(new OffreService().getAll());
                //(ObservableList<Offre>) ;
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        salaireCol.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        expirationCol.setCellValueFactory(new PropertyValueFactory<>("dateExpiration"));
        nbPlaceCol.setCellValueFactory(new PropertyValueFactory<>("nombrePlace"));
        experienceCol.setCellValueFactory(new PropertyValueFactory<>("experienceMax"));
        offreTable.setItems(listOffres);
        
        
    }

    @FXML
    private void AddOffre(ActionEvent event) {
    }
    
}

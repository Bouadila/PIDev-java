/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Contrat;
import Services.OffreDao.ContratService;
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
public class ContratFXMLController implements Initializable {

    @FXML
    private TableView<Contrat> contratTable;
    @FXML
    private TableColumn<Contrat, String> descriptionCol;
    @FXML
    private TableColumn<Contrat, String> typeCol;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    

    @FXML
    private void ajouterContrat(ActionEvent event) {
        Contrat c = new Contrat();
        c.setDescription(tfDescription.getText());
        c.setType(tfType.getText());
        ContratService cs = new ContratService();
        cs.add(c);
        tfDescription.setText("");
        tfType.setText("");
    }

    @FXML
    private void modifirContrat(ActionEvent event) {
    }

    private void loadDate() {
        ObservableList<Contrat> listContrat = FXCollections.observableArrayList(new ContratService().getAll());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        contratTable.setItems(listContrat);
    }
        
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_Reclamation;

import Entity.Reclamation;
import Services.ReclamationService;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAffichageController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfDesc;
    @FXML
    private TableView<Reclamation> tvRec;
    @FXML
    private TableColumn<Reclamation, String> colTitle;
    @FXML
    private TableColumn<Reclamation, String> colDes;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, String> colStatus;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> lr = rs.Lister(1);
        ObservableList<Reclamation> data = FXCollections.observableArrayList();
        data.addAll(lr);
        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("title"));

        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        colDate.setCellValueFactory(
                new PropertyValueFactory<>("dateRec"));

        colDes.setCellValueFactory(
                new PropertyValueFactory<>("descRec"));

        colStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        tvRec.setItems(data);
    }    

    @FXML
    private void handleButtonInsert(ActionEvent event) {
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
           LocalDateTime now = LocalDateTime.now();  
           String date=String.valueOf(now).replace("T", " ");
        
        
        ReclamationService rs = new ReclamationService();
        Reclamation Rc=new Reclamation(tfTitle.getText(),tfType.getText(),date,tfDesc.getText(),"Non approuvé",rs.getEmail(1));
        rs.Ajouter(Rc);
         List<Reclamation> lr = rs.Lister(1);
        
          ObservableList<Reclamation> data =
                 FXCollections.observableArrayList(lr); 
          tvRec.setItems(data);
          showReclamation();
    }

    @FXML
    private void handleButtonDelete(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        Reclamation r = tvRec.getSelectionModel().getSelectedItem();
        rs.Supprimer(r);
          List<Reclamation> lr = rs.Lister(1);
        
          ObservableList<Reclamation> data =
                 FXCollections.observableArrayList(lr); 
          tvRec.setItems(data);
          showReclamation();
    }
    public ObservableList<Reclamation> getReclamationList() {
        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM reclamation";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Reclamation reclamation;
            while (rs.next()) {
                reclamation = new Reclamation(rs.getString("title"), rs.getString("type"), rs.getString("date_reclamation"), rs.getString("description_reclamation"), "Non approuvé", rs.getString("email"));
                reclamationList.add(reclamation);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reclamationList;
    }

    public void showReclamation() {
        ReclamationService rs = new ReclamationService();
        List<Reclamation> lr = rs.Lister(1);
        ObservableList<Reclamation> data = FXCollections.observableArrayList();
        data.addAll(lr);
       
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("descRec"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateRec"));

        tvRec.setItems(data);
    }
}

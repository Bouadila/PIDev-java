/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ReclamationAffichageController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfId1;
    @FXML
    private TextField tfId2;
    @FXML
    private TableView<Reclamation> tvRec;
    @FXML
    private TableColumn<Reclamation, String> colTitle;
    @FXML
    private TableColumn<Reclamation, String> colDes;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, Boolean> colEtat;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showReclamation();
    }    
    
    public ObservableList<Reclamation> getReclamationList(){
        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        Connection conn = DataSource.getInstance().getCnx();
        String query = "SELECT * FROM reclamation";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Reclamation reclamation;
            while(rs.next()){
                reclamation = new Reclamation(query, query, query, query, true, query);
                reclamationList.add(reclamation);
            }
                
        }catch(SQLException ex){ex.printStackTrace();}
        
        return reclamationList;
    }
    
     public void showReclamation(){
        ObservableList<Reclamation> list = getReclamationList();
        
        colTitle.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
        colDes.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("type"));
        colEtat.setCellValueFactory(new PropertyValueFactory<Reclamation, Boolean>("etat"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
        
        tvRec.setItems(list);
    }
    private void insertRecord(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date=String.valueOf(now).replace("T", " ");
        ReclamationService rs = new ReclamationService();
        Reclamation Rc=new Reclamation(tfId.getText(),tfId1.getText(),date,tfId2.getText(),true,"Pidevtestad@gmail.com");
        rs.Ajouter(Rc);
        List<Reclamation> lr = rs.Lister();
        
          ObservableList<Reclamation> data = FXCollections.observableArrayList(lr); 
          tvRec.setItems(data);
          initForm();
          showReclamation();
    }
    
    private void updateRecord() {
        String query = "UPDATE  books SET titre  = '" + tfId.getText() + "', description = '" + tfId2.getText() + "', type = " + tfId1.getText();
        executeQuery(query);
        showReclamation();    }
    
    private void deleteButton(){
        String query = "DELETE FROM books WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showReclamation();
    }
    
    private void executeQuery(String query) {
        Connection conn = DataSource.getInstance().getCnx();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){ex.printStackTrace();}
    }
        
     public void initForm(){
        tfId.setText("");
        tfId1.setText("");
        tfId2.setText("");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;


import Entity.Formation;
import Services.FormationService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.stage.StageStyle;
import javafx.util.Callback;





 



/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherFormationController implements Initializable {

    
     @FXML
    private Button btn_gotoAjoutForm;

    @FXML
    private Button btn_deleteFormation;

    @FXML
    private Button btn_gotoModifForm;
    
    
    @FXML
    private TableView<Formation> TableFormation;
    
    @FXML
    private TableColumn<Formation, String> Tab_titre_id;

    @FXML
    private TableColumn<Formation, String> tab_url_id;

    @FXML
    private TableColumn<Formation, String> tab_desc_id;

    @FXML
    private TableColumn<Formation, String> tab_domaine_id;


    @FXML
    private TableColumn<Formation, String> tab_delete_id;

    @FXML
    private TableColumn<Formation, String> tab_modif_id;
    
    ObservableList<Formation>  FormationList = FXCollections.observableArrayList();

    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Formation formation = null ;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadDate();
        
        
        
        
     /*   ArrayList<Formation> formations= new FormationService().getAll();
        
        //System.out.println(formations);
        
        for(int i = 0; i<formations.size();i++) {
        
             
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            
            
            Label url1= new Label();
            Label description1= new Label();
            Label titre1= new Label();
            Label domaine1= new Label();
            Label publish_date1= new Label();
            
            
            
            
            
            Button btn1=new Button("Like");
            
            
            
            description1.setText(formations.get(i).getDescription());
            titre1.setText(formations.get(i).getTitle());
            url1.setText(formations.get(i).getUrl());
            domaine1.setText(formations.get(i).getDomaine());
            publish_date1.setText(formatter.format(formations.get(i).getPublish_date()));
            
          
            
           
           
            
            
            VBox forma1=new VBox();
            forma1.getChildren().addAll(titre1,url1,description1,publish_date1,domaine1,btn1);
            
            
            HBox formhor=new HBox();
            
            formhor.getChildren().addAll(forma1);
            
            VBox_formations.getChildren().add(formhor);
            
            
            
            
            
         
             btn1.setOnAction((ActionEvent e) -> {
                 Formation v = new Formation();
                 new FormationService().supprimerVideo(v);
                 
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Formation");
                 alert.setHeaderText("Formation supprimé !");
                 alert.setContentText("Go Back To The Homepage");
                 
                 alert.showAndWait();
            });
             
             
             
            
        
             
             
             
             
     }*/
            
            
           
            
            
            
        }
        
        
    
    @FXML
        void btn_Refresh_Forma() {
        
        
         try {
            //FormationList.clear();
            
            query = "SELECT * FROM video";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                FormationList.add(new Formation(resultSet.getString("url"),
                        resultSet.getString("title"),
                        resultSet.getTimestamp("publish_date"),
                        resultSet.getString("description"),
                        resultSet.getString("domaine")));
                
                TableFormation.setItems(FormationList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        
        
   /* void btn_X_close(MouseEvent event) {


        
    }*/
    
    
    
 void btn_gotoAjouterForma(MouseEvent event) throws IOException {
     
    /* Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
        Stage Window = (Stage) btn_gotoAjoutForm.getScene().getWindow();
        Window.setScene(new Scene(root));*/
        
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
 
 
    
      
    

    
   /* @FXML
    void btn_gotoAjouterForma(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
        Stage Window = (Stage) btn_gotoAjoutForm.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }*/
    
    
    @FXML
    void btn_gotoModifierForma(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/ModifierFormation.fxml"));
        Stage Window = (Stage) btn_gotoModifForm.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }

    private void loadDate() {
        

        ObservableList<Formation> listForm = FXCollections.observableArrayList();
        Tab_titre_id.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tab_url_id.setCellValueFactory(new PropertyValueFactory<>("Url"));
        tab_desc_id.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tab_domaine_id.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
        FormationService fc = new FormationService();
        List old = fc.getAll();
        listForm.addAll(old);
         TableFormation.setItems(listForm);
        
         
         
         
        
        
    }

    @FXML
    private void btn_gotoAjouterForma(javafx.scene.input.MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
        Stage Window = (Stage) btn_gotoAjoutForm.getScene().getWindow();
        Window.setScene(new Scene(root));
    }


    @FXML
    private void btn_print_formation(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    private void btn_X_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    
    
    
    
    
    
    
}

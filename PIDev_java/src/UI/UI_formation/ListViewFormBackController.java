/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Services.FormationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ListViewFormBackController implements Initializable {

    
    @FXML
    private ListView<VBox> listViewForm;
    
    private final FormationService serviceFormation = new FormationService();
    
    private  Formation v;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            // TODO
            fillGrid();
        } catch (SQLException ex) {
            Logger.getLogger(ListViewFormBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

     public void fillGrid() throws SQLException{
        List<Formation> listForma = new ArrayList();
        
        listForma = serviceFormation.getAll();
        
        for(int i = 0; i < listForma.size(); i++){
            Hyperlink url = new Hyperlink(listForma.get(i).getUrl());
            String x = listForma.get(i).getUrl();
           
            
            
            
            
            url.setOnAction( e -> {
                                    

                System.out.println(((Hyperlink) e.getTarget()).getText());
                
                Node node = (Node) e.getSource();
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_formation/video_detail.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Video_detailController video_detailController = loader.getController();
                video_detailController.initData(((Hyperlink) e.getTarget()).getText());
                stage.setScene(scene);
                
                
            });
            
             url.setOnMouseClicked(e->{
                try {
                     Node node = (Node) e.getSource();
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_formation/FormationDetailBack.fxml")); 
                      FormationDetailBackController FormaBackController = loader.getController();
                    
                        FormaBackController.setlb_id_back(Integer.toString(v.getId()));
                        FormaBackController.setlb_url_back(v.getUrl());
                        FormaBackController.setlb_titre_back(v.getTitle());
                        FormaBackController.setlb_description_back(v.getDescription());
                        FormaBackController.setlb_domaine_back(v.getDomaine());
                        
                    Stage stage = (Stage) node.getScene().getWindow();
                   
                    Scene scene = new Scene(loader.load());      
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(ListViewFormBackController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            VBox hb = new VBox();
            
            hb.getChildren().add(new Label(""));
            hb.getChildren().add(url);
            hb.getChildren().add(new Label(""));

            listViewForm.getItems().add(hb);

            
        }
        
        
    }  

     @FXML
    private void btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}

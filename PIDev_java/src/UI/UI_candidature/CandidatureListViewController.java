/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import Entity.Candidature;
import Services.CandidatureService;
import Services.UserSession;
import UI.OffreUI.OffreFXMLController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class CandidatureListViewController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Button btn_AjouterCandidature;
    @FXML
    private ListView<VBox> listView;
    @FXML
    private Button btn_ModifierCandidature;
    @FXML
    private Button btn_SuppCandidature;
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
    
    private CandidatureService ServiceCandidature = new CandidatureService();
    @FXML
    private Button goToOffres;


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
            Logger.getLogger(CandidatureListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void fillGrid() throws SQLException{
        List<Candidature> listCand = new ArrayList();
        
        listCand = ServiceCandidature.getCandById();
               
        for(int i = 0; i < listCand.size(); i++){
            Label lb_offre_id = new Label("Offre test "+Integer.toString(listCand.get(i).getOffre_id()));
//            Label lb_num = new Label("Numéro: "+Integer.toString(listCand.get(i).getNum()));
//            Label lb_dip = new Label("Diplome: "+listCand.get(i).getDiplome());
//            Label lb_stat = new Label("Status: "+listCand.get(i).getStatus());
//            Label lb_cv = new Label("Voir CV: "+listCand.get(i).getCv());
//            Label lb_lettre = new Label("Voir lettre de motivation: "+listCand.get(i).getLettre_motiv());
//            
            Date dateCand = listCand.get(i).getDate_candidature();
//            Date dateDispo = listCand.get(i).getDispo(); 
                DateFormat dateFormat2 = new SimpleDateFormat("dd-mm-YYYY");
//                DateFormat dateFormat1 = new SimpleDateFormat("dd-mm-YYYY");  
                String strDateCand = dateFormat2.format(dateCand); 
//                String strDateDispo = dateFormat1.format(dateDispo);
//            
//            Label lb_dispo = new Label("Date de disponibilité: "+strDateDispo);
            Label lb_datecand = new Label("Date de postulation: "+strDateCand);
            
            lb_offre_id.setOnMouseClicked(e->{
                
                try {
                     Node node = (Node) e.getSource();
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_candidature/ModifierCandidature.fxml"));
                     
                    Stage stage = (Stage) node.getScene().getWindow();
                    Scene scene = new Scene(loader.load());      
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(CandidatureListViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
       
            });
            VBox hb = new VBox();
            hb.getChildren().add(lb_offre_id);
//            hb.getChildren().add(lb_num);
//            hb.getChildren().add(lb_dip);
//            hb.getChildren().add(lb_stat);
//            hb.getChildren().add(lb_cv);
//            hb.getChildren().add(lb_lettre);
//            hb.getChildren().add(lb_dispo);
            hb.getChildren().add(lb_datecand);
            

            listView.getItems().add(hb);
//             grid.getRowConstraints().add(new RowConstraints(50));
            
        }
        
               
        
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


    @FXML
    private void refreshData(MouseEvent event) throws SQLException {
        fillGrid();
        
    }
    Connection con = DataSource.getInstance().getCnx();

    @FXML
    private void goToOffres(ActionEvent event) throws SQLException {
        String role = "";
        String request0 = "SELECT * from `user` WHERE `user`.`id` = " + UserSession.getIdSession() + ";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next()) {
            role = rs0.getString("roles");
        }

        if (role.equals("[\"Candidat\"]")) {
                    Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreCandidatFXML.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        }
        if (role.equals("[\"Employeur\"]")) {
            Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
        }
    }

}

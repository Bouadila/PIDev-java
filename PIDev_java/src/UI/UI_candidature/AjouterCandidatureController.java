/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entity.Candidature;
import Services.CandidatureService;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class AjouterCandidatureController implements Initializable {


    @FXML
    private TextField txtfield_nom;
    @FXML
    private TextField txtfield_prenom;
    @FXML
    private ChoiceBox<String> choice_sexe;
    ObservableList<String> genders = FXCollections.observableArrayList("Homme","Femme");
    @FXML
    private TextField txtfield_email;
    @FXML
    private DatePicker date_date_naiss;
    @FXML
    private TextField txtfield_num;
    @FXML
    private ChoiceBox<String> choice_status;
    ObservableList<String> status = FXCollections.observableArrayList("Sans emploi","Employé(e)","Indépendant");
    @FXML
    private ChoiceBox<String> choice_diplome;
    ObservableList<String> diplomes = FXCollections.observableArrayList("Bac","Ingénieur","Master","Doctorat");
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_retour;
    @FXML
    private TextField txtfield_filename;
    @FXML
    private Button btn_upload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_sexe.setItems(genders);
        choice_status.setItems(status);
        choice_diplome.setItems(diplomes);
        
    }    
    
    @FXML
    void gotoAfficherCandidature(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AfficherCandidature.fxml"));
        Stage Window = (Stage) btn_retour.getScene().getWindow();
        Window.setScene(new Scene(root));
    }
    
    @FXML
    void ajouterCandidature(ActionEvent event) {
        
        btn_ajouter.setOnAction((ActionEvent e)->{
        Candidature c = new Candidature();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        c.setDate_candidature(date);
        c.setCandidat(1);
        c.setNom(txtfield_nom.getText());
        c.setPrenom(txtfield_prenom.getText());
        c.setSexe(choice_sexe.getValue());
        c.setEmail(txtfield_email.getText());
        //c.setDate_naiss(date_date_naiss.getValue());
        //c.setNum(txtfield_num.getInt());
        //c.setStatus(choice_status.getText());
        //c.setDiplome(choice_diplome.getText());
        //c.setCv();
        new CandidatureService().ajouterCandidature(c);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Candidature");
            alert.setHeaderText("Candidature ajouté !");
            alert.setContentText("retour");

            alert.showAndWait(); 
            Stage stage = (Stage) btn_retour.getScene().getWindow();
            stage.close();
        });

    }
    
}

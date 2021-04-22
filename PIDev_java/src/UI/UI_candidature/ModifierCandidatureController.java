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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class ModifierCandidatureController implements Initializable {

    @FXML
    private Button btn_modifier;
    @FXML
<<<<<<< HEAD
    private TextField txtfield_nomedit;
    @FXML
    private TextField txtfield_prenomedit;
    @FXML
    private ChoiceBox<String> choice_sexeedit;
    ObservableList<String> genders = FXCollections.observableArrayList("Homme","Femme");
    @FXML
    private TextField txtfield_emailedit;
    @FXML
    private DatePicker date_date_naissedit;
    @FXML
=======
>>>>>>> main
    private TextField txtfield_numedit;
    @FXML
    private ChoiceBox<String> choice_statusedit;
    ObservableList<String> status = FXCollections.observableArrayList("Sans emploi","Employé(e)","Indépendant");
    @FXML
    private ChoiceBox<String> choice_diplomeedit;
    ObservableList<String> diplomes = FXCollections.observableArrayList("Bac","Ingénieur","Master","Doctorat");
    @FXML
    private Button btn_retour;
    
    public int formCand;
    @FXML
    private TextField txtfield_idedit;
<<<<<<< HEAD
=======
    @FXML
    private DatePicker date_dispo;
    @FXML
    private TextField txtfield_cvedit;
    @FXML
    private Button btn_cvedit;
    @FXML
    private TextField txtfield_lettremotivedit;
    @FXML
    private Button btn_lettremotivedit;
>>>>>>> main

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
<<<<<<< HEAD
        choice_sexeedit.setItems(genders);
=======
>>>>>>> main
        choice_statusedit.setItems(status);
        choice_diplomeedit.setItems(diplomes);
    }    

    
    
     public int getFormCand() {
        return formCand;
    }

    public void setFormCand(int formCand) {
        this.formCand = formCand;
    }

<<<<<<< HEAD
    
    
     public TextField gettxtfield_nomedit() {
        return txtfield_nomedit;
    } 
    
    void settxtfield_nomedit(String txtfield_nomedit) {
        this.txtfield_nomedit.setText(txtfield_nomedit);
    }
    
    
    public TextField gettxtfield_prenomedit() {
        return txtfield_prenomedit;
    } 
    
    void settxtfield_prenomedit(String txtfield_prenomedit) {
        this.txtfield_prenomedit.setText(txtfield_prenomedit);
    }

    
    
    
    public ChoiceBox getchoice_sexeedit() {
        return choice_sexeedit;
    } 
    
    void setchoice_sexeedit(String choice_sexeedit) {
        this.choice_sexeedit.setValue(choice_sexeedit);
    }

    
    
    
    public TextField gettxtfield_emailedit() {
        return txtfield_emailedit;
    } 
    
    void settxtfield_emailedit(String txtfield_emailedit) {
        this.txtfield_emailedit.setText(txtfield_emailedit);
    }

   
    
=======
   
>>>>>>> main
    public TextField gettxtfield_numedit() {
        return txtfield_numedit;
    } 
    
    void settxtfield_numedit(String txtfield_numedit) {
        this.txtfield_numedit.setText(txtfield_numedit);
    }
    
     public ChoiceBox getchoice_statusedit() {
        return choice_statusedit;
    } 
    
    void setchoice_statusedit(String choice_statusedit) {
        this.choice_statusedit.setValue(choice_statusedit);
    }
    
     public ChoiceBox getchoice_diplomeedit() {
        return choice_diplomeedit;
    } 
    
    void setchoice_diplomeedit(String choice_diplomeedit) {
        this.choice_diplomeedit.setValue(choice_diplomeedit);
    }
    
    public TextField gettxtfield_idedit() {
        return txtfield_idedit;
    } 
    
    void settxtfield_idedit(String txtfield_idedit) {
        this.txtfield_idedit.setText(txtfield_idedit);
    }
<<<<<<< HEAD
    
    
    
    
    
=======

    public DatePicker getDate_dispo() {
        return date_dispo;
    }

    public void setDate_dispo(DatePicker date_dispo) {
        this.date_dispo = date_dispo;
    }

    public TextField getTxtfield_cvedit() {
        return txtfield_cvedit;
    }

    public void setTxtfield_cvedit(TextField txtfield_cvedit) {
        this.txtfield_cvedit = txtfield_cvedit;
    }

    public Button getBtn_cvedit() {
        return btn_cvedit;
    }

    public void setBtn_cvedit(Button btn_cvedit) {
        this.btn_cvedit = btn_cvedit;
    }

    public TextField getTxtfield_lettremotivedit() {
        return txtfield_lettremotivedit;
    }

    public void setTxtfield_lettremotivedit(TextField txtfield_lettremotivedit) {
        this.txtfield_lettremotivedit = txtfield_lettremotivedit;
    }

    public Button getBtn_lettremotivedit() {
        return btn_lettremotivedit;
    }

    public void setBtn_lettremotivedit(Button btn_lettremotivedit) {
        this.btn_lettremotivedit = btn_lettremotivedit;
    }
    

>>>>>>> main
    
    @FXML
    private void modifierCandidature(ActionEvent event) {
        btn_modifier.setOnAction(e->{
        Candidature c = new Candidature();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        c.setDate_candidature(date);
        System.out.println(date);
        //c.setCandidat(1);
        c.setId(Integer.parseInt(txtfield_idedit.getText()));
<<<<<<< HEAD
        c.setNom(txtfield_nomedit.getText());
        c.setPrenom(txtfield_prenomedit.getText());
        c.setSexe(choice_sexeedit.getValue());
        c.setEmail(txtfield_emailedit.getText());
        c.setDate_naiss(date);
        c.setNum(Integer.parseInt(txtfield_numedit.getText()));
        
        c.setStatus(choice_statusedit.getValue());
        c.setDiplome(choice_diplomeedit.getValue());
        //c.setCv();
=======
        c.setNum(Integer.parseInt(txtfield_numedit.getText()));
        c.setStatus(choice_statusedit.getValue());
        c.setDiplome(choice_diplomeedit.getValue());
        //c.setDispo(date);
        //c.setCv(txtfield_cvedit.getText());
        //c.setLettre_motiv(txtfield_lettremotivedit.getText());
>>>>>>> main
        new CandidatureService().modifierCandidature(c);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Candidature");
            alert.setHeaderText("Candidature modifié !");
            alert.setContentText("retour");

            alert.showAndWait(); 
            Stage stage = (Stage) btn_retour.getScene().getWindow();
            //stage.close();
        });
    }

    @FXML
    private void gotoAfficherCandidature(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AfficherCandidature.fxml"));
        Stage Window = (Stage) btn_retour.getScene().getWindow();
        Window.setScene(new Scene(root));
<<<<<<< HEAD
        
=======
>>>>>>> main
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import Entity.Candidature;
import Services.CandidatureService;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class ModifierCandidatureController implements Initializable {

    @FXML
    private Button btn_modifier;
    @FXML
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
    private TextField txtfield_cvedit;
    @FXML
    private Button btn_cvedit;
    private TextField txtfield_lettremotivedit;
    @FXML
    private Button btn_lettremotivedit;
    @FXML
    private Button btn_cvvoir;
    @FXML
    private Button btn_lettremotivvoir;
    @FXML
    private DatePicker date_dispoedit;
    
    private FileChooser fileChooser;
    private File file1;
    private File file2;
    private final Desktop desktop = Desktop.getDesktop();
    FtpUpload ftp1 = new FtpUpload();
    FtpUpload ftp2 = new FtpUpload();
    String url1,url2,name1,name2;
   
    
    String cvpath,lmpath;
    @FXML
    private Label labelcv;
    @FXML
    private Label labellm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_statusedit.setItems(status);
        choice_diplomeedit.setItems(diplomes);
        labelcv.setText(cvpath);
        labellm.setText(lmpath);
        System.out.println(cvpath);
        
        btn_cvedit.setOnAction(e ->{
            fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*pdf")
            );
            Window stage = null;
            String filename = "";
            //Single File Selection
            
            file1 = fileChooser.showOpenDialog(null);


            if(file1 != null){
                //desktop.open(file);
                
                url1 = file1.getAbsolutePath();
                name1 = file1.getName();
                //ftp.Upload(url1,name1);
                txtfield_cvedit.setText(file1.getName());

            }
        });
        
        btn_cvvoir.setOnAction(e ->{
            try {
                Desktop.getDesktop().open(new File("Backlog.pdf"));
            } catch (IOException ex) {
                Logger.getLogger(ModifierCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        );
        
        btn_lettremotivedit.setOnAction(e ->{
            Window stage = null;
            fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*pdf")
            );
            //Single File Selection

            file2 = fileChooser.showOpenDialog(null);

            if(file2 != null){

                
                url2 = file2.getAbsolutePath();
                name2 = file2.getName();
                txtfield_lettremotivedit.setText(file2.getName());

            }

        });
    }    

    public String getCvpath() {
        return cvpath;
    }

    public void setCvpath(String cvpath) {
        this.cvpath = cvpath;
    }

    public String getLmpath() {
        return lmpath;
    }

    public void setLmpath(String lmpath) {
        this.lmpath = lmpath;
    }

    
     public int getFormCand() {
        return formCand;
    }

    public void setFormCand(int formCand) {
        this.formCand = formCand;
    }

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

    public DatePicker getDate_dispoedit() {
        return date_dispoedit;
    }

    public void setDate_dispoedit(DatePicker date_dispo) {
        this.date_dispoedit = date_dispo;
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
    
    
    @FXML
    private void modifierCandidature(ActionEvent event) {
        btn_modifier.setOnAction(e->{
        Candidature c = new Candidature();
        c.setId(Integer.parseInt(txtfield_idedit.getText()));
        c.setNum(Integer.parseInt(txtfield_numedit.getText()));
        c.setStatus(choice_statusedit.getValue());
        c.setDiplome(choice_diplomeedit.getValue());
        LocalDate date = date_dispoedit.valueProperty().get();
        Date date2 = Date.valueOf(date);
        c.setDispo(date2);
        if(file1 != null){
            System.out.println("uploading url="+url1);
            ftp1.Upload(url1,name1);
            System.out.println("uploaded!!");
        }
        if(file2 != null){
            System.out.println("uploading url="+url2);
            ftp2.Upload(url2,name2);
            System.out.println("uploaded!!");
        }
        new CandidatureService().modifierCandidature(c);
        CandidatureService.notifsuccess("Candidature modifié");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Candidature");
            alert.setHeaderText("Retour à la list?");
            //alert.setContentText("retour");
            alert.showAndWait();
            Stage stage = (Stage) btn_retour.getScene().getWindow();
        });
    }

    @FXML
    private void gotoAfficherCandidature(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AfficherCandidature.fxml"));
        Stage Window = (Stage) btn_retour.getScene().getWindow();
        Window.setScene(new Scene(root));

    }
    
}

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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.sql.Date;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class AjouterCandidatureController implements Initializable {


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
    private DatePicker date_dispo;
    @FXML
    private TextField txtfield_cv;
    @FXML
    private Button btn_uploadcv;
    @FXML
    private TextField txtfield_lettremotiv;
    @FXML
    private Button btn_lettremotiv;
    
    private FileChooser fileChooser;
    private File file1;
    private File file2;
    private final Desktop desktop = Desktop.getDesktop();
    FtpUpload ftp1 = new FtpUpload();
    FtpUpload ftp2 = new FtpUpload();
    String url1,url2,name1,name2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_status.setItems(status);
        choice_diplome.setItems(diplomes);
        
              

        btn_uploadcv.setOnAction(e ->{
            fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*pdf")
            );
            Window stage = null;
            String filename = "";
            //Single File Selection
            
            file1 = fileChooser.showOpenDialog(null);


            if(file1 != null){
                //desktop.open(file);
                
                url1 = file1.getAbsolutePath().replace("\\", "\\\\");
                name1 = file1.getName();
                //ftp.Upload(url1,name1);
                txtfield_cv.setText(file1.getName());

            }

     

        });
        
        btn_lettremotiv.setOnAction(e ->{
            Window stage = null;
            fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*pdf")
            );
            //Single File Selection

            file2 = fileChooser.showOpenDialog(null);

            if(file2 != null){

                
                url2 = file2.getAbsolutePath().replace("\\", "\\\\");
                name2 = file2.getName();
                txtfield_lettremotiv.setText(file2.getName());

            }

        });
        
    }   


    
    @FXML
    void gotoAfficherCandidature(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AfficherCandidature.fxml"));
        Stage Window = (Stage) btn_retour.getScene().getWindow();
        Window.setScene(new Scene(root));
    }
    
    @FXML
    void ajouterCandidature(ActionEvent event) {
        
        btn_ajouter.setOnAction(e->{
            Candidature c = new Candidature();
            c.setNom("Anas");
            c.setPrenom("Bassoumi");
            c.setEmail("anas.bassoumi@gmail.com");
            String str="2015-03-31";
            Date daten=Date.valueOf(str);
            c.setDate_naiss(daten);
            c.setNum(Integer.parseInt(txtfield_num.getText()));
            c.setStatus(choice_status.getValue());
            c.setDiplome(choice_diplome.getValue());
            c.setCv(txtfield_cv.getText());
            c.setDate_candidature(new Date(System.currentTimeMillis()));
            
            LocalDate date = date_dispo.valueProperty().get();
            Date date2 = Date.valueOf(date);
            c.setDispo(date2);
            
            c.setLettre_motiv(txtfield_lettremotiv.getText()); 
            int cid = UserSession.getInstance().getLoggedUser().getId();
                    //(49);
            c.setCandidat_id(cid);
            int oid = (7);
            c.setOffre_id(oid);
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
            
            new CandidatureService().ajouterCandidature(c);
            CandidatureService.notifsuccess("Candidature ajouté");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Candidature");
            alert.setHeaderText("Retour à la list?");
            //alert.setContentText("retour");
            alert.showAndWait();
            Stage stage = (Stage) btn_retour.getScene().getWindow();
            
            //stage.close();

        });

    }
    

    
}

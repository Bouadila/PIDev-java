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
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import javax.mail.*;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class AjouterCandidatureController implements Initializable {

>>>>>>> main
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
<<<<<<< HEAD
    private TextField txtfield_filename;
    @FXML
    private Button btn_upload;
=======
    private DatePicker date_dispo;
    @FXML
    private TextField txtfield_cv;
    @FXML
    private Button btn_uploadcv;
    @FXML
    private TextField txtfield_lettremotiv;
    
    private FileChooser fileChooser;
    private Button browse;
    private File file;
    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button btn_lettremotiv;
>>>>>>> main

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
<<<<<<< HEAD
        choice_sexe.setItems(genders);
        choice_status.setItems(status);
        choice_diplome.setItems(diplomes);
        
    }    
=======
        choice_status.setItems(status);
        choice_diplome.setItems(diplomes);
        
        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*pdf")
        );

      

        btn_uploadcv.setOnAction(e ->{
            Window stage = null;

            //Single File Selection

            file = fileChooser.showOpenDialog(stage);

            if(file != null){

                try {

                    desktop.open(file);

                } catch (IOException ex) {

                    Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

     

        });
        
        btn_lettremotiv.setOnAction(e ->{
            Window stage = null;

            //Single File Selection

            file = fileChooser.showOpenDialog(stage);

            if(file != null){

                try {

                    desktop.open(file);

                } catch (IOException ex) {

                    Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        });
        
    }   

>>>>>>> main
    
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
<<<<<<< HEAD
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
=======
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
>>>>>>> main
        Date date = new Date(System.currentTimeMillis());
        c.setDate_candidature(date);
        System.out.println(date);
        //c.setCandidat(1);
<<<<<<< HEAD
        c.setNom(txtfield_nom.getText());
        c.setPrenom(txtfield_prenom.getText());
        c.setSexe(choice_sexe.getValue());
        c.setEmail(txtfield_email.getText());
        c.setDate_naiss(date);
        c.setNum(Integer.parseInt(txtfield_num.getText()));

        
        c.setStatus(choice_status.getValue());
        c.setDiplome(choice_diplome.getValue());
        //c.setCv();
        new CandidatureService().ajouterCandidature(c);
=======
        c.setDate_naiss(date);
        c.setNum(Integer.parseInt(txtfield_num.getText()));
        c.setStatus(choice_status.getValue());
        c.setDiplome(choice_diplome.getValue());
        //c.setCv();
        //c.setDispo();
        //c.setLettre_motiv();
        new CandidatureService().ajouterCandidature(c);
            try {
                sendMail("pidevtestad@gmail.com");
            } catch (MessagingException ex) {
                Logger.getLogger(AjouterCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
            }
>>>>>>> main
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Candidature");
            alert.setHeaderText("Candidature ajouté !");
            alert.setContentText("retour");

            alert.showAndWait(); 
            Stage stage = (Stage) btn_retour.getScene().getWindow();

            //stage.close();

        });

    }
    

    public void sendMail(String recepient) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "pidevtestad@gmail.com";
        String password ="pidevtestad123456";
        
        Session session = Session.getInstance(properties, new Authenticator(){
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
       
        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("Msg sent");
    }
    
    private Message prepareMessage(Session session, String myAccountEmail, String recepient){
        
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(myAccountEmail));
            message.setSubject("Votre candidature a été ajouté");
            message.setText("Votre candidature a été ajouté avec success");
            
        } catch (MessagingException e) {
            Logger.getLogger(CandidatureService.class.getName()).log(Level.SEVERE, null, e);
        }
          return null;  
    }

}

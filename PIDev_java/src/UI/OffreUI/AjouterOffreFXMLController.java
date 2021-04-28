/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Contrat;
import Entity.Offre;
import QuizUI.AddQuizController;
import QuizUI.QuizListController;
import QuizUI.ShowQuizController;
import Services.OffreDao.ContratService;
import Services.OffreDao.OffreService;
import Services.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class AjouterOffreFXMLController implements Initializable {

    @FXML
    private TextField tfPost;
    @FXML
    private TextField tfObjectif;
    @FXML
    private TextField tfCompetence;
    @FXML
    private ComboBox<String> cbDomaine;
    @FXML
    private TextArea tfDescription;
    @FXML
    private ComboBox<Contrat> cbContrat;
    @FXML
    private Spinner<Integer> tfSalaire;
    @FXML
    private Spinner<Integer> tfPlace;
    @FXML
    private Spinner<Integer> tfMinVal;
    @FXML
    private Spinner<Integer> tfMaxVal;
    @FXML
    private DatePicker datePickerExpiration;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        loadDate();
        }    

    @FXML
    private void newOffre(MouseEvent event) throws SQLException {
        String post = this.tfPost.getText();
        String objectif = this.tfObjectif.getText();
        String competence = this.tfCompetence.getText();
        String description =this.tfDescription.getText();
        String domaine = this.cbDomaine.getValue();
        int salaire = this.tfSalaire.getValue();
        int nbPlace = this.tfPlace.getValue();
        int minVal = this.tfMinVal.getValue();
        int maxVal= this.tfMaxVal.getValue();
        Contrat c = this.cbContrat.getValue();
        //Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        LocalDate expiration = this.datePickerExpiration.getValue();
//        System.out.println(post.isEmpty());
//        System.out.println(objectif.isEmpty());
//        System.out.println(competence.isEmpty());
//        System.out.println(description.isEmpty());
//        System.out.println(domaine.isEmpty());
//        System.out.println(salaire);
//        System.out.println(nbPlace == 0);
        if (post.isEmpty() || objectif.isEmpty() || competence.isEmpty() || description.isEmpty() || domaine.isEmpty()|| salaire == 0 || nbPlace == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("s\'il vous plaît remplir tous le formulaire"); 
            alert.showAndWait();

        }else{
            Date date = Date.from(expiration.atStartOfDay(defaultZoneId).toInstant());
            Offre offre = new Offre(post,objectif,competence,description,domaine,salaire,nbPlace,date,minVal,maxVal);
            OffreService os = new OffreService();
            int id = os.addOffreAndGetItsId(offre, c,UserSession.getIdSession());
            offre.setId(id);
            System.out.println("offre ajouter");
            this.tfPost.setText(null);
            this.tfObjectif.setText(null);
            this.tfDescription.setText(null);
            this.tfCompetence.setText(null);
            this.cbContrat.getSelectionModel().clearSelection();
            this.cbDomaine.getSelectionModel().clearSelection();
            this.tfSalaire.getValueFactory().setValue(0);
            this.tfPlace.getValueFactory().setValue(0);
            this.tfMinVal.getValueFactory().setValue(0);
            this.tfMaxVal.getValueFactory().setValue(0);
            this.datePickerExpiration.getEditor().clear();
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous ajouter un quizz ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                try {
                    //do stuff
                    Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/AddQuiz.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    AddQuizController addQuiz = loader.getController();
                    addQuiz.initData(offre);
                    
                    
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterOffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                }
                else{
            
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
                }
            
        }
}

    private void loadDate() {

        String elements [] = {"Aéronautique Et Espace",
                    "Agriculture - Agroalimentaire",
                    "Artisanat",
                    "Audiovisuel, Cinéma",
                    "Audit, Comptabilité, Gestion",
                    "Automobile",
                    "Banque, Assurance",
                    "Bâtiment, Travaux Publics",
                    "Biologie, Chimie, Pharmacie",
                    "Commerce, Distribution",
                    "Communication",
                    "Création, Métiers art",
                    "Culture, Patrimoine",
                    "Défense, Sécurité, Armée",
                    "Documentation, Bibliothèque",
                    "Droit",
                    "Edition, Livre",
                    "Enseignement",
                    "Environnement",
                    "Ferroviaire",
                    "Foires, Salons Et Congrès",
                    "Fonction Publique",
                    "Hôtellerie, Restauration",
                    "Humanitaire",
                    "Immobilier",
                    "Industrie",
                    "Informatique, Télécoms, Web",
                    "Jeu Vidéo",
                    "Journalisme",
                    "Langues",
                    "Marketing, Publicité",
                    "Médical",
                    "Mode-Textile",
                    "Paramédical",
                    "Propreté Et Services Associés",
                    "Psychologie",
                    "Ressources Humaines",
                    "Sciences Humaines Et Sociales",
                    "Secrétariat",
                    "Social",
                    "Spectacle - Métiers De La Scène",
                    "Sport",
                    "Tourisme",
                    "Transport-Logistique"};
        this.cbDomaine.getItems().addAll(elements);
        ContratService cs = new ContratService();
        this.cbContrat.getItems().addAll(cs.getAll());
        SpinnerValueFactory <Integer> salaireFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,7000,0);
        SpinnerValueFactory <Integer> nbPlaceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        SpinnerValueFactory <Integer> valMinFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        SpinnerValueFactory <Integer> valMaxFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        this.tfSalaire.setValueFactory(salaireFactory);
        this.tfSalaire.setEditable(true);
        this.tfPlace.setValueFactory(nbPlaceFactory );
        this.tfPlace.setEditable(true);
        this.tfMinVal.setValueFactory(valMinFactory);
        this.tfMinVal.setEditable(true);
        this.tfMaxVal.setValueFactory(valMaxFactory);     
        this.tfMaxVal.setEditable(true);
    
}

    @FXML
    private void tfGover(MouseEvent event) {
    }
}

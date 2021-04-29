/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Contrat;
import Entity.Offre;
import QuizUI.AddQuizController;
import QuizUI.ShowQuizController;
import Services.OffreDao.ContratService;
import Services.OffreDao.OffreService;
import Services.QuizService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
public class ModifirOffreFXMLController implements Initializable {

    

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
    private DatePicker datePickerExpiration;
    @FXML
    private Spinner<Integer> tfPlace;
    @FXML
    private Spinner<Integer> tfMinVal;
    @FXML
    private Spinner<Integer> tfMaxVal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    Offre of = new Offre();
    public void initData(Offre o) {
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
        SpinnerValueFactory <Integer> salaireFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,7000,o.getSalaire());
        SpinnerValueFactory <Integer> nbPlaceFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,o.getNombrePlace());
        SpinnerValueFactory <Integer> valMinFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,o.getExperienceMin());
        SpinnerValueFactory <Integer> valMaxFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,o.getExperienceMax());
        this.tfSalaire.setValueFactory(salaireFactory);
        this.tfSalaire.setEditable(true);
        this.tfPlace.setValueFactory(nbPlaceFactory );
        this.tfPlace.setEditable(true);
        this.tfMinVal.setValueFactory(valMinFactory);
        this.tfMinVal.setEditable(true);
        this.tfMaxVal.setValueFactory(valMaxFactory);     
        this.tfMaxVal.setEditable(true);
        System.out.println(o.toString());
        this.tfPost.setText(o.getPost());
        this.tfObjectif.setText(o.getObjectif());
        this.tfCompetence.setText(o.getCompetences());
        this.cbDomaine.getSelectionModel().select(o.getDomaine());
        this.cbContrat.getSelectionModel().select(o.getContrat());
        this.tfDescription.setText(o.getDescription());
        Date date = o.getDateExpiration();
        //System.out.println(o.getDateExpiration());
        LocalDate datelocal = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.datePickerExpiration.setValue(datelocal);
        of = o;
        System.out.println(o.getQuiz());
        
    }
    @FXML
    private void UpdateOffre(MouseEvent event) throws SQLException {
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
        if (post.isEmpty() || objectif.isEmpty() || competence.isEmpty() || description.isEmpty() || domaine.isEmpty()|| salaire == 0 || nbPlace == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("s\'il vous plaît remplir tous le formulaire"); 
            alert.showAndWait();

        }else{
            Date date = Date.from(expiration.atStartOfDay(defaultZoneId).toInstant());
            Offre offre = new Offre(post,objectif,competence,description,domaine,salaire,nbPlace,date,minVal,maxVal);
            offre.setId(of.getId());
            OffreService os = new OffreService();
            os.edite(offre, c);
            if(of.getQuiz()>0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez vous ajouter un quizz ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                try {
                    //do stuff
                    Node node = (Node) event.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/ShowQuiz.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ShowQuizController addQuiz = loader.getController();
                    
                    addQuiz.initData(new QuizService().getQuiz(of.getQuiz()));
                    
                    
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(AjouterOffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
           // System.out.println("offre modifir");
//            Node node = (Node) event.getSource();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/DetailOffre.fxml"));
//                Stage stage = (Stage) node.getScene().getWindow();
//                Scene scene = null;  
//                try {
//                    scene = new Scene(loader.load());
//                } catch (IOException ex) {
//                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                    DetailOffreController detailController = loader.getController();
//                    detailController.initData(offre);
//                    stage.setScene(scene);
//            
            
        }
    }

    @FXML
    private void inscrireUser(ActionEvent event) {
    }

    @FXML
    private void tfGover(MouseEvent event) {
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import Entity.Candidature;
import Services.CandidatureService;
import Services.UserSession;
import UI.OffreUI.AjouterRendezVousFXMLController;
import UI.OffreUI.DetailOffreController;
import UI.OffreUI.OffreFXMLController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.File;
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
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.sql.DataSource;


/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class AfficherCandidatureController implements Initializable {

    
    @FXML
    private TableView<Candidature> TableCandidature;
    @FXML
    private TableColumn<Candidature, String> tab_nom;
    @FXML
    private TableColumn<Candidature, String> tab_prenom;
    @FXML
    private TableColumn<Candidature, String> tab_email;
    @FXML
    private TableColumn<Candidature, String> tab_date_naiss;
    @FXML
    private TableColumn<Candidature, String> tab_num;
    @FXML
    private TableColumn<Candidature, String> tab_status;
    @FXML
    private TableColumn<Candidature, String> tab_diplome;
    @FXML
    private TableColumn<Candidature, String> tab_cv;
    private TableColumn<Candidature, String> tab_edit;
    @FXML
    private TableColumn<Candidature, String> tab_date_candidature;
    
    String query = null;
    Connection cnx = null;
    Connection connection = cnx ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Candidature candidature = null ;
    
    ObservableList<Candidature>  CandidatureList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Candidature, String> tab_id;

    @FXML
    private TableColumn<Candidature, String> tab_lettremotiv;
    @FXML
    private TableColumn<Candidature, String> tab_dispo;
    @FXML
    private TextField searchField;
    private Button front;
    @FXML
    private TableColumn<Candidature, String> tab_rv;
    
    String path = ("C:\\Users\\brahm\\Desktop\\3A27\\Semestre2\\PIDev\\ProjPiDev\\public\\uploads\\image\\");
    @FXML
    private Button profil;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();

    }    
        
         private void loadDate() 
         {

             
             
        ObservableList<Candidature> listCand = new CandidatureService().getAll();
        tab_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tab_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tab_date_naiss.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
        tab_num.setCellValueFactory(new PropertyValueFactory<>("num"));
        tab_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab_diplome.setCellValueFactory(new PropertyValueFactory<>("diplome"));
        tab_dispo.setCellValueFactory(new PropertyValueFactory<>("dispo"));
        tab_date_candidature.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        
       


         TableCandidature.setItems(listCand);
         
         
  

        
         
         
         
         
         
//         //add cell of button edit 
//         Callback<TableColumn<Candidature, String>, TableCell<Candidature, String>> cellFoctory = (TableColumn<Candidature, String> param) -> {
//            // make cell containing buttons
//            final TableCell<Candidature, String> cell = new TableCell<Candidature, String>() {
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    //that cell created only on non-empty rows
//                    if (empty) {
//                        setGraphic(null);
//                        setText(null);
//
//                    } else {
//
//                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
//
//                        deleteIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#ff1744;"
//                        );
//                        editIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#00E676;"
//                        );
//                        
//                        
//                        deleteIcon.setOnMouseClicked(e -> {
//                            
//                            Candidature c = TableCandidature.getSelectionModel().getSelectedItem();
//                             
//                           // TableFormation.getItems().remove(TableFormation.getSelectionModel().getSelectedItem());
//
//                            CandidatureService sp = new CandidatureService();
//                            System.out.println(c.getId());
//                            sp.supprimerCandidature(c);
//                            System.out.println("deleted");
//                            loadDate();
//                            
//                            
//                          
//                            
//                        });
//                        
//                        
//                        
//                        
//                        
//                        editIcon.setOnMouseClicked(e -> {
//                            
//                            
//                            
//                             Candidature c = TableCandidature.getSelectionModel().getSelectedItem();
//
//                            try {
//                                FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_candidature/ModifierCandidature.fxml"));
//                        Parent rooter = loader.load();
//                        ModifierCandidatureController mC = loader.getController();
//
//                        mC.settxtfield_idedit(Integer.toString(c.getId())); //A3mel TextField hidden ( fi properties visibility)
//
//                        mC.settxtfield_numedit(Integer.toString(c.getNum()));   
//                        mC.setchoice_statusedit(c.getStatus()); 
//                        mC.setchoice_diplomeedit(c.getDiplome());
//                        
//                        mC.setCvpath(c.getCv());
//                        mC.setLmpath(c.getLettre_motiv());
//                        System.out.println(c.getId());
//
//                        Stage stage = new Stage();
//                        stage.setTitle("Modifier évenement");
//                        stage.setScene(new Scene(rooter));
//                        stage.show();
//                        } catch (IOException ex) {
//                         System.out.println(ex.getMessage());
//
//                        }  
//
//                        });
//
//                        HBox managebtn = new HBox(editIcon, deleteIcon);
//                        managebtn.setStyle("-fx-alignment:center");
//                        setGraphic(managebtn);
//
//                        setText(null);
//
//
//                    }
//                }
//
//            };
//
//            return cell;
//        };
         
         Callback<TableColumn<Candidature, String>, TableCell<Candidature, String>> celllettremotiv = (TableColumn<Candidature, String> param) -> {
            // make cell containing buttons
            final TableCell<Candidature, String> cell = new TableCell<Candidature, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView showIcon = new FontAwesomeIconView(FontAwesomeIcon.CLIPBOARD);

                        showIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        showIcon.setOnMouseClicked(e -> {
                            
                            
                            
                             Candidature c = TableCandidature.getSelectionModel().getSelectedItem();
                             
                            try {
                                Desktop.getDesktop().open(new File(path+c.getLettre_motiv()));
                        } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                        }  
                        });
                        HBox managebtn1 = new HBox(showIcon);
                        managebtn1.setStyle("-fx-alignment:center");
                        setGraphic(managebtn1);

                        setText(null);


                    }
                }

            };

            return cell;
        };

         
        Callback<TableColumn<Candidature, String>, TableCell<Candidature, String>> cellcv = (TableColumn<Candidature, String> param) -> {
            // make cell containing buttons
            final TableCell<Candidature, String> cell = new TableCell<Candidature, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView showIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOUD_DOWNLOAD);

                        showIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        showIcon.setOnMouseClicked(e -> {
                            
                            
                            
                             Candidature c = TableCandidature.getSelectionModel().getSelectedItem();

                            try {  
                                Desktop.getDesktop().open(new File(path+c.getCv()));
                                
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        HBox managebtn2 = new HBox(showIcon);
                        managebtn2.setStyle("-fx-alignment:center");
                        setGraphic(managebtn2);

                        setText(null);

                    }
                }

            };

            return cell;
        }; 
        
         Callback<TableColumn<Candidature, String>, TableCell<Candidature, String>> cellrv = (TableColumn<Candidature, String> param) -> {
            // make cell containing buttons
            final TableCell<Candidature, String> cell = new TableCell<Candidature, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView rvIcon = new FontAwesomeIconView(FontAwesomeIcon.FILE);

                        rvIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        rvIcon.setOnMouseClicked(e -> {
                             Candidature c = TableCandidature.getSelectionModel().getSelectedItem();
                              Node node = (Node) e.getSource();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/AjouterRendezVousFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    AjouterRendezVousFXMLController AjouterRendezVousFXML = loader.getController();
                    AjouterRendezVousFXML.initData(c);
                    stage.setScene(scene);

                        });
                        HBox managebtn3 = new HBox(rvIcon);
                        managebtn3.setStyle("-fx-alignment:center");
                        setGraphic(managebtn3);

                        setText(null);


                    }
                }

            };

            return cell;
        };
         
        
        tab_cv.setCellFactory(cellcv);
        tab_lettremotiv.setCellFactory(celllettremotiv);
        tab_rv.setCellFactory(cellrv);
        //tab_edit.setCellFactory(cellFoctory);
        TableCandidature.setItems(listCand);
             
          FilteredList<Candidature> filteredData = new FilteredList<>(listCand, e -> true);
        searchField.setOnKeyReleased(e ->{
            searchField.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super Candidature>) cand->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(cand.getNom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(cand.getPrenom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Candidature> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableCandidature.comparatorProperty());
            TableCandidature.setItems(sortedData);
           
        });   
             
        
         }

   @FXML
    private void rendezVous(javafx.scene.input.MouseEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/RendezVousFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

//     @FXML
//    private void offre(MouseEvent event) {
//        Node node = (Node) event.getSource();
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
//                Stage stage = (Stage) node.getScene().getWindow();
//                Scene scene = null;  
//                try {
//                    scene = new Scene(loader.load());
//                } catch (IOException ex) {
//                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                 stage.setScene(scene);
//    }

    @FXML
    private void reclamation(ActionEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_Reclamation/ReclamationAffichage.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

    @FXML
    private void apropos(ActionEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/AboutUs.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

    @FXML
    private void formation(ActionEvent event) {
         Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/UI_formation/Formations.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
        
    }
           Connection con = utils.DataSource.getInstance().getCnx();

      @FXML
    private void goToProfil(ActionEvent event) throws IOException, SQLException {
         String role="";
         String request0 ="SELECT *,SUBSTR(roles,3,5) as rol from `user` WHERE `user`.`id` = "+UserSession.getIdSession()+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();
        if (rs0.next())
        {
            role = rs0.getString("rol");
        }
        
        if (role.equals("Candi")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserCandidatAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
        if (role.equals("Emplo")){
             Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/UI/UI_User/UserAffiche.fxml")));
                    stage.setScene(scene);
                    stage.show();
        }
    }
    

    @FXML
    private void offre(ActionEvent event) throws SQLException {
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
    

    
    private void gotoFront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/CandidatureListView.fxml"));
        Stage Window = (Stage) front.getScene().getWindow();
        Window.setScene(new Scene(root));
    
    }

    @FXML
    private void goToCalendar(javafx.scene.input.MouseEvent event) {
         Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/RendezVousFXML.fxml"));
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
    }

    @FXML
    private void refreshData(javafx.scene.input.MouseEvent event) {
        
        loadDate();
    }
         


    
        
    }
    

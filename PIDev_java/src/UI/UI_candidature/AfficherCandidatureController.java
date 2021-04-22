/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import Entity.Candidature;
import Services.CandidatureService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Insets;
import java.awt.event.MouseEvent;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.sql.DataSource;
;

/**
 * FXML Controller class
 *
 * @author A.L.I.C.E
 */
public class AfficherCandidatureController implements Initializable {

    
    @FXML
    private TextField searchBar;
    @FXML
    private Button btn_searchCandidature;
    @FXML
    private TableView<Candidature> TableCandidature;
    @FXML
    private TableColumn<Candidature, String> tab_nom;
    @FXML
    private TableColumn<Candidature, String> tab_prenom;
    @FXML
    private TableColumn<Candidature, String> tab_sexe;
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
    @FXML
    private TableColumn<Candidature, String> tab_edit;
    @FXML
    private TableColumn<Candidature, String> tab_date_candidature;
    @FXML
    private Button btn_AjouterCandidature;
    
    String query = null;
    Connection cnx = null;
    Connection connection = cnx ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Candidature candidature = null ;
    
    ObservableList<Candidature>  CandidatureList = FXCollections.observableArrayList();
    @FXML
    private Button btn_refreshCandidature;
    @FXML
    private TableColumn<Candidature, String> tab_id;

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
        tab_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        tab_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tab_date_naiss.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
        tab_num.setCellValueFactory(new PropertyValueFactory<>("num"));
        tab_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tab_diplome.setCellValueFactory(new PropertyValueFactory<>("diplome"));
        tab_cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
       // tab_date_candidature.setCellValueFactory(new PropertyValueFactory<>("date_candidature"));
        
       
        /*FormationService fc = new FormationService();
        List old = fc.getAll();
        listForm.addAll(old);*/
         TableCandidature.setItems(listCand);
         
         

        
         
         
         
         
         
         //add cell of button edit 
         Callback<TableColumn<Candidature, String>, TableCell<Candidature, String>> cellFoctory = (TableColumn<Candidature, String> param) -> {
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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        
                        deleteIcon.setOnMouseClicked(e -> {
                            
                            Candidature c = TableCandidature.getSelectionModel().getSelectedItem();
                             
                           // TableFormation.getItems().remove(TableFormation.getSelectionModel().getSelectedItem());

                            CandidatureService sp = new CandidatureService();
                            System.out.println(c.getId());
                            sp.supprimerCandidature(c);
                            System.out.println("deleted");
                            
                            
                          
                            
                        });
                        
                        
                        
                        
                        
                        editIcon.setOnMouseClicked(e -> {
                            
                            
                            
                             Candidature c = TableCandidature.getSelectionModel().getSelectedItem();

                            try {
                                FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_candidature/ModifierCandidature.fxml"));
                        Parent rooter = loader.load();
                        ModifierCandidatureController mC = loader.getController();

                        mC.settxtfield_idedit(Integer.toString(c.getId())); //A3mel TextField hidden ( fi properties visibility)
                        mC.settxtfield_nomedit(c.getNom());
                        mC.settxtfield_prenomedit(c.getPrenom());
                        mC.setchoice_sexeedit(c.getSexe());
                        mC.settxtfield_emailedit(c.getEmail());
                        mC.settxtfield_numedit(Integer.toString(c.getNum()));   
                        mC.setchoice_statusedit(c.getStatus()); 
                        mC.setchoice_diplomeedit(c.getDiplome());
                        
                        System.out.println(c.getId());


                        Stage stage = new Stage();
                        stage.setTitle("Modifier évenement");
                        stage.setScene(new Scene(rooter));
                        stage.show();
                        } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                        }
                        

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };

            return cell;
        };
         tab_edit.setCellFactory(cellFoctory);
         TableCandidature.setItems(listCand);
             
             
             
        
         }

    @FXML
    private void gotoAjouterCandidature(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/AjouterCandidature.fxml"));
        Stage Window = (Stage) btn_AjouterCandidature.getScene().getWindow();
        Window.setScene(new Scene(root));
        
        
    }

    void btn_refreshCandidature() {
        try {
           CandidatureList.clear();
            
            query = "SELECT * FROM candidature";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                CandidatureList.add(new Candidature(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("sexe"),
                        resultSet.getString("email"),
                        resultSet.getDate("date_naiss"),                       
                        resultSet.getInt("num"),
                        resultSet.getString("status"),
                        resultSet.getString("diplome"),                       
                        resultSet.getString("cv"),
                        resultSet.getDate("date_candidature")
                ));
                TableCandidature.setItems(CandidatureList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCandidatureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}

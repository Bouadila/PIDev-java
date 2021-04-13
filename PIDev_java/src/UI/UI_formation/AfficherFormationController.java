/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;


import Entity.Formation;
import Services.FormationService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;






 



/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherFormationController implements Initializable {

    


    @FXML
    private Button btn_gotoModifForm;
    
    
    @FXML
    private TableView<Formation> TableFormation;
    
    @FXML
    private TableColumn<Formation, String> Tab_titre_id;

    @FXML
    private TableColumn<Formation, String> tab_url_id;

    @FXML
    private TableColumn<Formation, String> tab_desc_id;

    @FXML
    private TableColumn<Formation, String> tab_domaine_id;

    @FXML
    private TableColumn<Formation, String> tab_gestion_id;

    @FXML
    private Button btn_gotoFormations;
    
    @FXML
    private TextField tfSearchForma;
    
    
    
    ObservableList<Formation>  FormationList = FXCollections.observableArrayList();

    String query = null;
    Connection cnx = null;
    Connection connection = cnx ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Formation formation = null ;
   
        
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadDate();
       
        
         
        }
        
     
    
    
    
    
    
    
    
    @FXML
        void btn_Refresh_Forma() {

        
         try {
           // FormationList.clean();
            
            query = "SELECT * FROM video";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                FormationList.add(new Formation(resultSet.getString("url"),
                        resultSet.getString("title"),
                        resultSet.getTimestamp("publish_date"),
                        resultSet.getString("description"),
                        resultSet.getString("domaine")));
                
                TableFormation.setItems(FormationList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        
        
   
    
    

    
 
 @FXML
    void  btn_gotoFormations(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/Formations.fxml"));
        Stage Window = (Stage) btn_gotoFormations.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }
 
 
 
 
 
    @FXML
    void btn_gotoModifierForma(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/ModifierFormation.fxml"));
        Stage Window = (Stage) btn_gotoModifForm.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }

    private void loadDate() {
        

        
        
        
        ObservableList<Formation> listForm = new FormationService().getAll();
        Tab_titre_id.setCellValueFactory(new PropertyValueFactory<>("Title"));
        tab_url_id.setCellValueFactory(new PropertyValueFactory<>("Url"));
        tab_domaine_id.setCellValueFactory(new PropertyValueFactory<>("Domaine"));
        tab_desc_id.setCellValueFactory(new PropertyValueFactory<>("Description"));
        
       
        /*FormationService fc = new FormationService();
        List old = fc.getAll();
        listForm.addAll(old);*/
         TableFormation.setItems(listForm);
         
         

        
         
         
         
         
         
         //add cell of button edit 
         Callback<TableColumn<Formation, String>, TableCell<Formation, String>> cellFoctory = (TableColumn<Formation, String> param) -> {
            // make cell containing buttons
            final TableCell<Formation, String> cell = new TableCell<Formation, String>() {
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
                            
                            Formation v = TableFormation.getSelectionModel().getSelectedItem();
                             
                           // TableFormation.getItems().remove(TableFormation.getSelectionModel().getSelectedItem());

                            FormationService sp = new FormationService();
                            System.out.println(v.getId());
                            sp.supprimerVideo(v);
                            System.out.println("deleted");
                            
                            
                          
                            
                        });
                        
                        
                        
                        
                        
                        editIcon.setOnMouseClicked(e -> {
                            
                            
                            
                             Formation v = TableFormation.getSelectionModel().getSelectedItem();

                            try {
                                FXMLLoader loader= new FXMLLoader(getClass().getResource("/UI/UI_formation/ModifierFormation.fxml"));
                        Parent root = loader.load();
                        ModifierFormationController mF = loader.getController();

                        mF.settf_ID_forma(Integer.toString(v.getId())); //A3mel TextField hidden ( fi properties visibility)
                        mF.settfUrlMod(v.getUrl());
                        mF.settfTitreMod(v.getTitle());
                        mF.settfDescriptionMod(v.getDescription());
                        mF.settfDomaineMod(v.getDomaine());
                        
                          
                     
                      Stage Window = (Stage) editIcon.getScene().getWindow();
                      Window.setScene(new Scene(root));
                        

                        Stage stage = new Stage();
                        stage.setTitle("Modifier évenement");
                        stage.setScene(new Scene(root));
                        stage.show();
                        } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                        }
                            
                            
                            
                            
                         /*   formation = TableFormation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AjouterFormationController AjouterFormationController = loader.getController();
                            AjouterFormationController.setUpdate(true);
                            AjouterFormationController.setTextField(formation.getTitle(), 
                                    formation.getUrl(),formation.getDescription(), formation.getDomaine());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();*/
                            

                           

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
         tab_gestion_id.setCellFactory(cellFoctory);
         TableFormation.setItems(listForm);
         
         
         
         
         
         
         FilteredList<Formation> filteredData = new FilteredList<>(listForm, b->true);
         tfSearchForma.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate(formation -> {
         if(newValue == null || newValue.isEmpty() )
                 {
                       return true;
                 }
         
         String lowerCaseFilter = newValue.toLowerCase();
         
         if(formation.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1)
                 {
                     return true;
                 } 
         else if(formation.getDomaine().toLowerCase().indexOf(lowerCaseFilter) != -1)
                 {
                     return true;
                 }
         else return false;
         
         });
         
          });           

         SortedList<Formation> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(TableFormation.comparatorProperty());
         TableFormation.setItems(sortedData);
         
         
         
         
         
         
         
         
         
         
         
         
    }

   
         
    
    @FXML
    private void btn_gotoAjouterForma(javafx.scene.input.MouseEvent event) throws IOException {
        
         try {
 
            
            Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AjouterFormation.fxml"));
        Stage Window = (Stage) btn_gotoModifForm.getScene().getWindow();
        Window.setScene(new Scene(root));
        

        } catch (IOException ex) {
            Logger.getLogger(AfficherFormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
    
    @FXML
    private void btn_print_formation(javafx.scene.input.MouseEvent event) {
        
        try {
            
            String file_name="C:\\Users\\User\\Desktop\\PIDEV\\Formations.pdf";
            Document document = new Document();
            
            //Simple paragraph
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
         /*  DBConnection obJDBConnection = new DBConnection();
            Connection connection = obJDBConnection.getConnection();
            
            
            String query="select * from video";*/
            
            
            Paragraph para = new Paragraph("Liste des Formations");
            
            document.add(para);
            
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            
            //add table formation
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell c1 = new PdfPCell(new Phrase("title"));
            table.addCell(c1);
            
            PdfPCell c2 = new PdfPCell(new Phrase("url"));
            table.addCell(c2);
            
            PdfPCell c3 = new PdfPCell(new Phrase("publish_date"));
            table.addCell(c3);
            
            PdfPCell c4 = new PdfPCell(new Phrase("domaine"));
            table.addCell(c4);
            
            PdfPCell c5 = new PdfPCell(new Phrase("description"));
            table.addCell(c5);
            
            table.addCell("0.1");
            table.addCell("1.1");
            table.addCell("1.2");
            table.addCell("2.1");
            table.addCell("2.2");
            table.addCell("2.3");
            table.addCell("3.3");
            table.addCell("3.4");
            table.addCell("4.4");
            table.addCell("4.5");
            
            document.add(table);
            
            
            //add image in pdf
            
            document.add(Image.getInstance("C:\\Users\\User\\Desktop\\PIDEV\\recruitini_logo.png"));
            
            document.close();
            
            System.out.println("finished");
            
            
        }catch(Exception e){
        
         System.err.println(e);
        }
            
        }
        
        
    

    
    
    
    
    
    
    @FXML
    private void btn_X_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    
    

    
    
    
    
}

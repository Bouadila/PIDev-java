/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import Entity.Formation;
import Services.FormationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierFormationController implements Initializable {

    
     @FXML
    private TextField tfUrlMod;

    @FXML
    private TextField tfTitreMod;

    @FXML
    private TextField tfDomaineMod;

    @FXML
    private TextArea tfDescriptionMod;

    @FXML
    private Button btn_gotoAfficherFormation;
    
    @FXML
    private Button btn_enregis_modif;
    
    public int form;
    
    @FXML
    private TextField tf_ID_forma;

    
    
    
    
    
    
    
    
    
    @FXML
    void btn_gotoAfficherFormaModif(ActionEvent event) throws IOException {
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_formation/AfficherFormation.fxml"));
        Stage Window = (Stage) btn_gotoAfficherFormation.getScene().getWindow();
        Window.setScene(new Scene(root));

      
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    
    
    
    
     public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    
    
     public TextField gettf_ID_forma() {
        return tf_ID_forma;
    } 
    
    void settf_ID_forma(String tf_ID_forma) {
        this.tf_ID_forma.setText(tf_ID_forma);
    }
    
    
    public TextField gettfUrlMod() {
        return tfUrlMod;
    } 
    
    void settfUrlMod(String tfUrlMod) {
        this.tfUrlMod.setText(tfUrlMod);
    }

    
    
    
    public TextField gettfTitreMod() {
        return tfTitreMod;
    } 
    
    void settfTitreMod(String tfTitreMod) {
        this.tfTitreMod.setText(tfTitreMod);
    }

    
    
    
    public TextField gettfDomaineMod() {
        return tfDomaineMod;
    } 
    
    void settfDomaineMod(String tfDomaineMod) {
        this.tfDomaineMod.setText(tfDomaineMod);
    }

   
    
    public TextArea gettfDescriptionMod() {
        return tfDescriptionMod;
    } 
    
    void settfDescriptionMod(String tfDescriptionMod) {
        this.tfDescriptionMod.setText(tfDescriptionMod);
    }

    
    
    
    private boolean verifchamps()
            
    {
         return false;
 
    }
    
    
    
  /*  @FXML
    private void btn_enregistrer_modif(ActionEvent event) {
        
        
        

   
    }*/
    
    
    @FXML
    void btn_enregistrer_modif(ActionEvent event) {
        
        
        btn_enregis_modif.setOnAction(e->{
            
        String url = "https://www.youtube.com/embed/";
        String code = tfUrlMod.getText().substring(tfUrlMod.getText().length() - 11);
        url = url + code;
        
        
        Formation v = new Formation();
        
        
        
        v.setId(Integer.parseInt(tf_ID_forma.getText()));
        v.setUrl(url);
        v.setTitle(tfTitreMod.getText());
        Timestamp time= new Timestamp(System.currentTimeMillis());
        v.setPublish_date(time);
        System.out.println(time); 
        v.setDomaine(tfDomaineMod.getText());
        v.setDescription(tfDescriptionMod.getText());
        
        new FormationService().modifierVideo(v);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Formation");
            alert.setHeaderText("Formation Modifier !");
            alert.setContentText("Go Back To The Homepage");

            alert.showAndWait(); 
            Stage stage = (Stage) btn_gotoAfficherFormation.getScene().getWindow();
            //stage.close();
        });
          
        
       

    }

  
    
    
    @FXML
    private void btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
       
}

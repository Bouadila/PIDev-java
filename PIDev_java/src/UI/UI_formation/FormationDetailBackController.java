/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_formation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FormationDetailBackController implements Initializable {

    
    @FXML
    private Label lb_id_back;
    @FXML
    private Label lb_url_back;
    @FXML
    private Label lb_titre_back;
    @FXML
    private Label lb_domaine_back;
    @FXML
    private Label lb_description_back;
    @FXML
    private Label lb_owner_back;

    public int formDet;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }



  public int getFormDet() {
        return formDet;
    }

    public void setForm(int formDet) {
        this.formDet = formDet;
    }

    
    
     public Label getlb_id_back() {
        return lb_id_back;
    } 
    
    void setlb_id_back(String lb_id_back) {
        this.lb_id_back.setText(lb_id_back);
    }
    
    
    public Label getlb_url_back() {
        return lb_url_back;
    } 
    
    void setlb_url_back(String lb_url_back) {
        this.lb_url_back.setText(lb_url_back);
    }

    
    
    
    public Label getlb_titre_back() {
        return lb_titre_back;
    } 
    
    void setlb_titre_back(String lb_titre_back) {
        this.lb_titre_back.setText(lb_titre_back);
    }

    
    
    public void show(){
        System.out.println("here");
    }
    
    public Label getlb_domaine_back() {
        return lb_domaine_back;
    } 
    
    void setlb_domaine_back(String lb_domaine_back) {
        this.lb_domaine_back.setText(lb_domaine_back);
    }

   
    
    public Label getlb_description_back() {
        return lb_description_back;
    } 
    
    void setlb_description_back(String lb_description_back) {
        this.lb_description_back.setText(lb_description_back);
    }

     @FXML
    private void btn_close(javafx.scene.input.MouseEvent event) {
        
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    
}

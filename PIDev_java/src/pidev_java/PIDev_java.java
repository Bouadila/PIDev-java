/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_java;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class PIDev_java extends Application {
    
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
             Application.launch(args);

}
    
    
    
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Recruitini");
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_candidature/CandidatureListView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

   
}

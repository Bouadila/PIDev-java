/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_java;

import utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/UI_Reclamation/Chat.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
        
//        Connection cnx;
//        cnx = DataSource.getInstance().getCnx();
//        String req =  "select * from demande";
//        
//        Statement ste;
//        ResultSet res;
//        
//        try {
//            ste = cnx.createStatement();
//            res = ste.executeQuery(req);
//            while (res.next())
//            {
//                System.out.println("nom"+res.getString("titre_demande"));
//                        }
//            //launch(args);
//        } catch (SQLException ex) {
//            Logger.getLogger(PIDev_java.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}

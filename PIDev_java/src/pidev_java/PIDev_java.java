/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDev_java;

import Entity.Contrat;
import Entity.Offre;
import Entity.RendezVous;
import Services.OffreDao.OffreService;
import Services.OffreDao.RendezVousService;
import java.sql.SQLException;
import java.util.Date;
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
        //\UI\OffreUI
        Parent root = FXMLLoader.load(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
         Application.launch(args);
//        Contrat c = new Contrat(4,"111111","fgvfjfufjkgv");
//        //ContratService cs = new ContratService() ;
//        //cs.add(c);
//        //cs.edite(c);
//        //System.out.println(cs.getAll());
//        //cs.delete(c);
//        /*
//        LocalDate localDate = LocalDate.now();
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//        System.out.println(date);
//        */
//        Date d1 = new Date();
//        Offre o = new Offre("livreur","gllgllg","glkkgkhh","dfggghgh","gggg",700,2,d1,1,2);
//        OffreService os = new OffreService();
//        //os.add(o, c);
//        System.out.println(os.getAll());
//        System.out.println("*************************");
//        System.out.println(os.getOne(1));
       /*
         ResultSet rs = os.getAll();
         while(rs.next())
            {
                System.out.println(rs.getString("contrat.description"));
            }
        */
        
        //LocalDate localDate = LocalDate.now();
        //o.setDateDepot(java.sql.Date.valueOf(localDate));
//        RendezVous rdv = new RendezVous();
//        RendezVousService rdvs = new RendezVousService();
//        System.out.println(rdvs.getAll());
    }
    
}

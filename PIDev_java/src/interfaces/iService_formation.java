/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entity.User;
import java.util.List;
import Entity.Formation;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface iService_formation <v> {
    
    
    
    public boolean ajouterVideo(Formation v);  
    public void modifierVideo(int id, String url, String title, Timestamp publish_date, String description, String domaine , User owner);
    public void supprimerVideo(Formation v);
   /* public ObservableList<formation v> getFormation();*/
    public String getVideoById();
    public ArrayList<v> afficherVideo();

    
}

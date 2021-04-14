/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author A.L.I.C.E
 */

import Entity.User;
import java.util.List;
import Entity.Candidature;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public interface iService_candidature <c>{
    
   public boolean ajouterCandidature(Candidature c);  
   public ObservableList<c> GetAll();
    public void modifierCandidature(int id, String nom, String prenom, String sexe, String email, Timestamp date_naiss, int num, String status, String diplome, String cv, Timestamp date_candidature, int offre, int candidat);
    public void supprimerCandidature(Candidature c);
   /* public ObservableList<formation v> getFormation();*/
   // public String getCandidatureById();
   public ArrayList<c> afficherCandidature();
    
}

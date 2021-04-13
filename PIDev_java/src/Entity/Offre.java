/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author brahm
 */
public class Offre {
 int id;
 String post;
 String objectif;
 String competences;
 String description;
 String domaine;
 Contrat contrat;
 int salaire;
 int nombrePlace;
 Date dateDepot;
 Date dateExpiration;
 int experienceMin;
 int experienceMax;
 boolean flagSupprimer;
 boolean flagExpirer;
}

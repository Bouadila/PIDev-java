/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author brahm
 */
public class Offre {
 private int id;
 private String post;
 private String objectif;
 private String competences;
 private String description;
 private String domaine;
 private Contrat contrat;
 private int salaire;
 private int nombrePlace;
 private Date dateDepot;
 private Date dateExpiration;
 private int experienceMin;
 private int experienceMax;
 private boolean flagSupprimer;
 private boolean flagExpirer;
 private int Quiz;

 LocalDate localDate = LocalDate.now();

    public Offre() {
    }

    public Offre(String post, String objectif, String competences, String description, String domaine, int salaire, int nombrePlace, Date dateExpiration, int experienceMin, int experienceMax) {
        this.post = post;
        this.objectif = objectif;
        this.competences = competences;
        this.description = description;
        this.domaine = domaine;
        this.salaire = salaire;
        this.nombrePlace = nombrePlace;
        this.dateDepot=java.sql.Date.valueOf(localDate);
        this.dateExpiration = dateExpiration;
        this.experienceMin = experienceMin;
        this.experienceMax = experienceMax;
        this.flagSupprimer=false;
        this.flagExpirer=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(int experienceMin) {
        this.experienceMin = experienceMin;
    }

    public int getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(int experienceMax) {
        this.experienceMax = experienceMax;
    }

    public boolean isFlagSupprimer() {
        return flagSupprimer;
    }

    public void setFlagSupprimer(boolean flagSupprimer) {
        this.flagSupprimer = flagSupprimer;
    }

    public boolean isFlagExpirer() {
        return flagExpirer;
    }

    public void setFlagExpirer(boolean flagExpirer) {
        this.flagExpirer = flagExpirer;
    }


    public int getQuiz() {
        return Quiz;
    }

    public void setQuiz(int Quiz) {
        this.Quiz = Quiz;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", post=" + post + ", objectif=" + objectif + ", competences=" + competences + ", description=" + description + ", domaine=" + domaine + ", contrat=" + contrat + ", salaire=" + salaire + ", nombrePlace=" + nombrePlace + ", dateDepot=" + dateDepot + ", dateExpiration=" + dateExpiration + ", experienceMin=" + experienceMin + ", experienceMax=" + experienceMax + ", flagSupprimer=" + flagSupprimer + ", flagExpirer=" + flagExpirer + '}';
    }

 
}

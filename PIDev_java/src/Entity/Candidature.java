/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;




/**
 *
 * @author A.L.I.C.E
 */
public class Candidature {
    
private int id;
private String nom;
private String prenom;
private String sexe;
private String email;
private Date date_naiss;
private int num;
private String status;
private String diplome;
private String cv;
private Date date_candidature;
private Date dispo;
private String lettre_motiv;
private int offre_id;
private int candidat_id;
private Offre offre;
private User candidat;

    public Candidature(int id, String nom, String prenom, String sexe, String email, Date date_naiss, int num, String status, String diplome, String cv, Date date_candidature, int offre_id, int candidat_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.date_naiss = date_naiss;
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
        this.offre_id = offre_id;
        this.candidat_id = candidat_id;
    }

    public Candidature(int id, String nom, String prenom, String sexe, String email, Date date_naiss, int num, String status, String diplome, String cv, Date date_candidature, Date dispo, String lettre_motiv, int offre_id, int candidat_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.date_naiss = date_naiss;
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
        this.dispo = dispo;
        this.lettre_motiv = lettre_motiv;
        this.offre_id = offre_id;
        this.candidat_id = candidat_id;
    }

    
    
    public Candidature() {
    }

    public Candidature(String nom, String prenom, String sexe, String email, Date date_naiss, int num, String status, String diplome, String cv, Date date_candidature) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.date_naiss = date_naiss;
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
    }

    public Candidature(int id, int num, String status, String diplome, String cv, Date date_candidature, Date dispo, String lettre_motiv) {
        this.id = id;
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
        this.dispo = dispo;
        this.lettre_motiv = lettre_motiv;
    }

    public Candidature(int num, String status, String diplome, String cv, Date date_candidature, Date dispo, String lettre_motiv, int offre_id, int candidat_id) {
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
        this.dispo = dispo;
        this.lettre_motiv = lettre_motiv;
        this.offre_id = offre_id;
        this.candidat_id = candidat_id;
    }

    public Candidature(int id, int num, String status, String diplome, String cv, Date date_candidature, Date dispo, String lettre_motiv, int offre_id, int candidat_id) {
        this.id = id;
        this.num = num;
        this.status = status;
        this.diplome = diplome;
        this.cv = cv;
        this.date_candidature = date_candidature;
        this.dispo = dispo;
        this.lettre_motiv = lettre_motiv;
        this.offre_id = offre_id;
        this.candidat_id = candidat_id;
    }


    
    

    public Date getDispo() {
        return dispo;
    }

    public void setDispo(Date dispo) {
        this.dispo = dispo;
    }

    public String getLettre_motiv() {
        return lettre_motiv;
    }

    public void setLettre_motiv(String lettre_motiv) {
        this.lettre_motiv = lettre_motiv;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public Date getDate_candidature() {
        return date_candidature;
    }

    public void setDate_candidature(Date date_candidature) {
        this.date_candidature = date_candidature;
    }

    
    public int getOffre_id() {
        return offre_id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public User getCandidat() {
        return candidat;
    }

    public void setCandidat(User candidat) {
        this.candidat = candidat;
    }

   
    
    
    
    @Override
    public String toString() {
        return "candidature{" +
                "id=" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe=" + sexe + '\'' +
                ", email=" + email + '\'' +
                ", date de naissance=" + date_naiss + '\'' +
                ", numero=" + num + '\'' +
                ", status=" + status + '\'' +
                ", diplome=" + diplome + '\'' +
                ", cv=" + cv + '\'' +
                ", date de postulation=" + date_candidature + '\'' +
                '}';
    }

    
}

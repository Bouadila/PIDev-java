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
private int offre;
private int candidat;

    public Candidature(int id, String nom, String prenom, String sexe, String email, Date date_naiss, int num, String status, String diplome, String cv, Date date_candidature, int offre, int candidat) {
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
        this.offre = offre;
        this.candidat = candidat;
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

    
    public int getOffre() {
        return offre;
    }

    public void setOffre(int offre) {
        this.offre = offre;
    }

    public int getCandidat() {
        return candidat;
    }

    public void setCandidat(int candidat) {
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

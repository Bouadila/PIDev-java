/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author User
 */
public class User {
    private int id ,etat ;
    private String email, password , name  ,prenom, gover
    , img , special, nom_entre,  activation_token, reset_token,etatecrit ,
            color ,roles;

    public User(int idc, String username, String email, int role, String nom, String prenom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(Integer id, int username, String email, String role, String nom, String prenom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEtatecrit() {
        return etatecrit;
    }

    public void setEtatecrit(String etatecrit) {
        this.etatecrit = etatecrit;
    }

    public User(String email, String name, String prenom, String gover, String img, String special, String etatecrit, String roles) {
        this.email = email;
        this.name = name;
        this.prenom = prenom;
        this.gover = gover;
        this.img = img;
        this.special = special;
        this.etatecrit = etatecrit;
        this.roles = roles;
    }

    public User(String email) {
        this.email = email;
    }

    

    private Date date_naiss;

    public User(String email, String password, String name, String prenom, String gover, String img, String special, String nom_entre, String activation_token, String reset_token, int etat, String color, String roles, Date date_naiss) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.gover = gover;
        this.img = img;
        this.special = special;
        this.nom_entre = nom_entre;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.etat = etat;
        this.color = color;
        this.roles = roles;
        this.date_naiss = date_naiss;
    }
    public User() {
    }

    public User(String email, String password, String name, String prenom, String gover, String img, String special, int etat, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.gover = gover;
        this.img = img;
        this.special = special;
        this.etat = etat;
        this.roles = roles;
    }




    public User(int id, String email, String password, String name, String prenom, String gover, String img, String special, String nom_entre, String activation_token, String reset_token, int etat, String color, String roles, Date date_naiss) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.prenom = prenom;
        this.gover = gover;
        this.img = img;
        this.special = special;
        this.nom_entre = nom_entre;
        this.activation_token = activation_token;
        this.reset_token = reset_token;
        this.etat = etat;
        this.color = color;
        this.roles = roles;
        this.date_naiss = date_naiss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGover() {
        return gover;
    }

    public void setGover(String gover) {
        this.gover = gover;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getNom_entre() {
        return nom_entre;
    }

    public void setNom_entre(String nom_entre) {
        this.nom_entre = nom_entre;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

   

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    @Override
    public String toString() {
        return "User{"  +  "id=" + id+ " email=" + email + ", password=" + password + ", name=" + name + ", prenom=" + prenom + ", gover=" + gover + ", img=" + img + ", special=" + special + ", nom_entre=" + nom_entre + ", activation_token=" + activation_token + ", reset_token=" + reset_token + ", etat=" + etat + ", color=" + color + ", roles=" + roles + ", date_naiss=" + date_naiss + '}';
    }

            
            
            

}

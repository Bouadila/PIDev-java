/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Djap_ii
 */
public class Reclamation {
    private int id;
    private String title;
    private String type;
    private String dateRec;
    private String descRec;
    private String status;
    private String email;

    public Reclamation() {
    }

    public Reclamation(String title, String type, String dateRec, String descRec, String status, String email) {
        this.title = title;
        this.type = type;
        this.dateRec = dateRec;
        this.descRec = descRec;
        this.status = status;
        this.email = email;
    }

    public Reclamation(int id, String title, String type, String dateRec, String descRec, String status, String email) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.dateRec = dateRec;
        this.descRec = descRec;
        this.status = status;
        this.email = email;
    }
        
/*-------------------------GETTER-------------------------*/
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
    
    public String getDateRec() {
        return dateRec;
    }

    public String getDescRec() {
        return descRec;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
    /*-------------------------SETTER-------------------------*/

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateRec(String dateRec) {
        this.dateRec = dateRec;
    }

    public void setDescRec(String descRec) {
        this.descRec = descRec;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

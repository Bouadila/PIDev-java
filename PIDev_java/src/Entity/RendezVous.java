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
public class RendezVous {
    private int id;
    private int idCandidature;
    private String titre;
    private Date start;
    private Date end;
    private String description;
    private boolean allDay;
    private String backgroundColor;
    private String borderColor;
    private String textColor;
    private boolean accepte;
    private String room;

    public RendezVous() {
    }

    public RendezVous(int idCandidature, String titre, Date start, Date end, String description, String backgroundColor, String borderColor, String textColor, String room) {
        this.idCandidature = idCandidature;
        this.titre = titre;
        this.start = start;
        this.end = end;
        this.description = description;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        this.allDay=false;
        this.accepte=false;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", titre=" + titre + ", start=" + start + ", end=" + end + ", description=" + description + ", allDay=" + allDay + ", backgroundColor=" + backgroundColor + ", borderColor=" + borderColor + ", textColor=" + textColor + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class Formation {
    private int id;
    private String url;
    private String title;
    private Timestamp publish_date;
    private String description;
    private String domaine;
    private String votes;
    private User owner;

    public Formation(int id, String url, String title, Timestamp publish_date, String votes, String description, String domaine , User owner) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.votes = votes;
        this.description = description;
        this.domaine = domaine;
        this.owner = owner;
    }
    
    public Formation(){
    
    }

    public Formation(String url, String title, Timestamp publish_date,  String description, String domaine, User owner) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.description = description;
        this.domaine = domaine;
        this.owner = owner;
    }

    public Formation(String url, String title, Timestamp publish_date, String description, String domaine) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.description = description;
        this.domaine = domaine;
    }
    
    
    public Formation(String url, String title, Timestamp publish_date, String votes, String description, String domaine) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.votes = votes;
        this.description = description;
        this.domaine = domaine;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Timestamp publish_date) {
        this.publish_date = publish_date;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
    
    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "video{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", publish_date=" + publish_date +
                ", votes=" + votes +
                ", description=" + description +
                ", domaine=" + domaine +
                ", id_cand_id=" + owner +
                '}';
    }

   
}

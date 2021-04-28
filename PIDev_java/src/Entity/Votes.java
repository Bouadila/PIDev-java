/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author User
 */
public class Votes {
    private int id;
    private User user_id;
    private Formation video_id;

    public Votes(int id ,User user_id, Formation video_id ) {
        this.id = id;
        this.user_id = user_id;
        this.video_id = video_id;
    }
    
     public Votes(User user_id, Formation video_id ) {
        
        this.user_id = user_id;
        this.video_id = video_id;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Formation getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Formation video_id) {
        this.video_id = video_id;
    }
    
    
    @Override
    public String toString() {
        return "post_like{" +
                "id=" + id +
                ", post_id='" + video_id + '\'' +
                ", user_id='" + user_id + '\'' +

                '}';
    }
    
    
}
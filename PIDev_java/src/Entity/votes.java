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
public class votes {
    private User user_id;
    private formation video_id;

    public votes(User user_id, formation video_id) {
        this.user_id = user_id;
        this.video_id = video_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public formation getVideo_id() {
        return video_id;
    }

    public void setVideo_id(formation video_id) {
        this.video_id = video_id;
    }
}
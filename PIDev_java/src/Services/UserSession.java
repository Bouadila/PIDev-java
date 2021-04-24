/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;

/**
 *
 * @author USER
 */
public class UserSession {
    private static int idSession;

    public static int getIdSession() {
        return idSession;
    }

    public static void setIdSession(int idSession) {
        UserSession.idSession = idSession;
    }

}

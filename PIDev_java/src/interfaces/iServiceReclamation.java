/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Djap_ii
 * @param <T>
 */
public interface iServiceReclamation<T> {

    public void Ajouter(T e);

    public void Supprimer(T e);

    public void Modifier(T e);
    
    public String getEmail(int iduser);

}

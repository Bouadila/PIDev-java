/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IService <T> {

    public boolean addUser(T entity);
    public ArrayList<T> getAllUser();
}

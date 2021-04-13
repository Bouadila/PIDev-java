/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.OffreDao;

import java.util.ArrayList;

/**
 *
 * @author brahm
 * @param <T>
 */
public interface IService <T> {
    public void add(T entity);
    public void  edite(T entity);
    public void delete(T entity);
    public ArrayList<T> getAll();
    public T getOne(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bou3dila
 */
public interface Service<o> {
    
    public void add()throws SQLException;
    public void  update()throws SQLException;
    public void delete()throws SQLException;
    public List<o> getAll()throws SQLException;
    public o get()throws SQLException;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Entity.Formation;
import Entity.Votes;

/**
 *
 * @author User
 */

public interface iService_votes <vo> {
   
    public void add(Votes vo);
    public void delete(Votes vo);
    public int getVotes(Votes vo);
    public Boolean find(Votes vo);
}

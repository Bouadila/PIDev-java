/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Reponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Bou3dila
 */
public class ReponseService {
    
      Connection conn;
    
    public ReponseService(){
        conn = DataSource.getInstance().getCnx();
    }
    
    
    public void addReponse(Reponse reponse){
        
        String sql="INSERT INTO reponse (nom_reponse, nomb_question) VALUES ('"+reponse.getNom_reponse()+"',"+reponse.getNomb_question()+")";
            
        Statement st;
            try
            {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Insertion reussite");
            }
            catch (SQLException ex)
            {
                System.out.println("Erreur d'insertion !");
                ex.getMessage();
            }
    }
    
    public void updateReponse ( Reponse reponse ){
        String sql="UPDATE reponse SET  nom_reponse='"+reponse.getNom_reponse()+"', nomb_question ="+reponse.getNomb_question()+" WHERE id="+reponse.getId();

        Statement st;
            try {
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Edition reussite");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                ex.getMessage();
            }
    }
    
    public void deleteReponse (Reponse reponse){
        String sql = "DELETE FROM reponse WHERE id ='"+reponse.getId()+"'";
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }
    
    
    
    
    
    public Reponse getReponse( int id ) throws SQLException{
        String sql="SELECT * FROM reponse where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        Reponse reponse = null;
        if (res.next())
        {
            String nom_reponse = res.getString("nom_reponse");
            int nomb_question = res.getInt("nomb_question");
            reponse = new Reponse (id,nom_reponse, nomb_question);
        }
            return reponse;
    }
    
    
    public List<Reponse> getAllReponse() throws SQLException{
        
        List<Reponse> listReponse = new ArrayList();
        String sql="SELECT * FROM reponse";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            String nom_reponse = res.getString("nom_reponse");
            int nomb_question = res.getInt("nomb_question");
            Reponse reponse = new Reponse (id,nom_reponse, nomb_question);
            listReponse.add(reponse);
        }
            return listReponse;
    }
}

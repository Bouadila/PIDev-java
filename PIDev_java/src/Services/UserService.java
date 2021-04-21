/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import interfaces.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author USER
 */
public class UserService implements IService<User> {
 private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
   
    public UserService() {
        
   
        try {
            cnx = DataSource.getInstance().getCnx();
            ste = cnx.createStatement();
           
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//    public void addUser(User entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public ArrayList<User> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUser(User u) {
              
          String email = "";
        String find = "select email from users where email = '" + u.getEmail()+ "'";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(find);
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (u.getEmail().equals(email)) {
            System.out.println("compte exitst !");
            return false;
        } else {
            //////
            String sql = "insert into users(email, password , name  ,prenom, gover, img , special ) values('" + u.getEmail()+ "','" + u.getPassword()+ "','" 
                    + u.getName()+ "','" + u.getPrenom()+ "','" + u.getGover()+ "','" + u.getImg()+ "','" + u.getSpecial()+ "')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public List<User> readAll()  throws SQLException {
  List<User> arr=new ArrayList<>();
    ste=cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from users");
     while (rs.next()) {     
         int etat =  rs.getInt("etat");
                  if (etat==1)
                  {
                                    String etatecrit="Désactivé";
    String nom=rs.getString("name");
               String prenom=rs.getString("prenom");
                String gover=rs.getString("gover");
               String special=rs.getString("special");
                String img=rs.getString("img");
               String email=rs.getString("email");
              String role=rs.getString("roles");
             
                            String pwd=rs.getString("password");

   
                
               User u =new User(email,nom,prenom,gover,img,special, etatecrit ,role);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(u);
                  }
                  else{
                                    String etatecrit="activé";
    String nom=rs.getString("name");
               String prenom=rs.getString("prenom");
                String gover=rs.getString("gover");
               String special=rs.getString("special");
                String img=rs.getString("img");
               String email=rs.getString("email");
              String role=rs.getString("roles");
             
                            String pwd=rs.getString("password");

   
                
               User u =new User(email,nom,prenom,gover,img,special, etatecrit ,role);
   
               //Courses c=new Courses(id,clients.get(0),partenaires.get(0),depart,destination,null,prix);
     arr.add(u);
                  }
           
     }
    return arr;
        }
    
    
  
}
    
    


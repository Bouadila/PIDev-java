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
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
               Connection con = DataSource.getInstance().getCnx();

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
        List <User> Users = new ArrayList <User>();
        try {
            String sql ="SELECT * FROM user ";
            PreparedStatement ps;
            ps = cnx.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next())
            {
                User u = new User();
                u.setName(rs.getString("name"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setEtat(rs.getInt("etat"));
                u.setGover(rs.getString("gover"));
                u.setSpecial(rs.getString("special"));
                u.setRoles(rs.getString("roles"));
                u.setImg(rs.getString("img"));
                Users.add(u);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (ArrayList<User>) Users;
        }    

    @Override
    public boolean addUser(User u) {
              
          String email = "";
        String find = "select email from user where email = '" + u.getEmail()+ "'";
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
            String sql = "insert into user(email, password , name  ,prenom, gover, img , special ) values('" + u.getEmail()+ "','" + u.getPassword()+ "','" 
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
    ResultSet rs=ste.executeQuery("select * from user");
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
    
    public boolean chercher(int id) throws SQLException {
        String req = "select * from user ";
        List<Integer> list = new ArrayList<>();

        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list.contains(id);
    }
    
        public User finduser(Integer id) throws SQLException
      {
          User u=null;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user where id='" + id + "' ");
     while (rs.next()) {                
             
               String nom=rs.getString("name");
               String prenom=rs.getString("prenom");
               String email=rs.getString("email");
               int username=rs.getInt("etat");
               String role=rs.getString("roles");
               
                u=new User(id,username,email,role,nom,prenom);
    
     }
    return u;
          
      }
        
        
        public User getOne(int id) {
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);

            if(rs.next())
            {
                User u = new User();
                u.setName(rs.getString("name"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setEtat(rs.getInt("etat"));
                u.setGover(rs.getString("gover"));
                u.setSpecial(rs.getString("special"));
                u.setRoles(rs.getString("roles"));
                u.setImg(rs.getString("img"));
                return u;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    return null;
    }
        
          public int TotalActiv() {
       int nb=0;
       String req = "SELECT count(id) from user where etat=0";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nb=resultSet.getInt(1);
        } catch (SQLException ex) {
                 Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  nb;
    }
           public int TotalRefused() {
       int nb=0;
       String req = "SELECT count(id) from user where etat=1";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nb=resultSet.getInt(1);
        } catch (SQLException ex) {
                      Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  nb;
    }
           
         
        
//   public int readTraited() throws SQLException {
//             int nb=0;
//       String req = "SELECT count(id) from user where etat='1'";
//       PreparedStatement preparedStatement;
//        try {
//            preparedStatement = con.prepareStatement(req);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            nb=resultSet.getInt(1);
//        } catch (SQLException ex) {
//                      Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return  nb;
//    }
//   
//    public List<reservation> readNottraited() throws SQLException {
//        String req = "select * from reservation where etat ='non traite'";
//        List<reservation> list = new ArrayList<>();
// List<User>CLS= new ArrayList<User>();
//        try {
//            ste = con.createStatement();
//            ResultSet rs = ste.executeQuery(req);
//            while (rs.next()) {
//   java.sql.Date sqlDate = java.sql.Date.valueOf(rs.getDate(6).toLocalDate());
//       PreparedStatement pre_partenaire=con.prepareStatement("select * from utilisateurs where id=? ");
//               pre_partenaire.setInt(1,rs.getInt(2));
//               ResultSet Clients=pre_partenaire.executeQuery();
//  while(Clients.next())
//               {
//                   int idc=Clients.getInt("id");
//                   String username=Clients.getString("username");
//                   String email=Clients.getString("email");
//                   String role=Clients.getString("roles");
//                   String nom=Clients.getString("nom");
//                   String prenom=Clients.getString("prenom");
//                   User Client= new User(idc,username,email,role,nom,prenom);
//                   CLS.add(Client);
//               }
//              //  System.out.println("test");
//       //CLS.forEach(System.out::println);
//                list.add(new reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),sqlDate, rs.getFloat(7), rs.getString(8), rs.getString(9), rs.getString(10),CLS.get(0)));
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return list;
//    }
}
    
    


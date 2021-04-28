/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class QuizService {
    
    Connection conn;
    
    public QuizService(){
        conn = DataSource.getInstance().getCnx();
    }
    
    public int addQuizAndGetItsId(Quiz quiz) throws SQLException{
        
        String sql="INSERT INTO quiz (nom_quiz, nomb_question) VALUES (? ,?)";
        String generatedColumns[] = { "ID" };
        PreparedStatement statement = conn.prepareStatement(sql, generatedColumns);
        statement.setString(1, quiz.getNom_quiz());
        statement.setInt(2, quiz.getNomb_question());
        statement.executeUpdate();
         try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating quiz failed, no ID obtained.");
            }
        }
    }
    public void addQuiz(Quiz quiz){
        
        String sql="INSERT INTO quiz (nom_quiz, nomb_question) VALUES ('"+quiz.getNom_quiz()+"',"+quiz.getNomb_question()+")";
            
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
    
    public void updateQuiz ( Quiz quiz ){
        String sql="UPDATE quiz SET  nom_quiz='"+quiz.getNom_quiz()+"', nomb_question ="+quiz.getNomb_question()+" WHERE id="+quiz.getId();

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
    
    public void deleteQuiz (Quiz quiz){
        String sql = "DELETE FROM quiz WHERE id ='"+quiz.getId()+"'";
        Statement st ;
            try{
                st=conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("Suppression reussite");
            }catch (SQLException ex){
                System.out.println("Probleme de suppression !!!");
                }
    }
    
    
    
    
    
    public Quiz getQuiz( int id ) throws SQLException{
        String sql="SELECT * FROM quiz where id ="+id;
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        Quiz quiz = null;
        if (res.next())
        {
            
            String nom_quiz = res.getString("nom_quiz");
            int nomb_question = res.getInt("nomb_question");
            quiz = new Quiz (id,nom_quiz, nomb_question);
        }
            return quiz;
    }
    
    
    public List<Quiz> getAllQuiz() throws SQLException{
        
        List<Quiz> listQuiz = new ArrayList();
        String sql="SELECT * FROM quiz";
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        while (res.next())
        {
            int id = res.getInt("id");
            String nom_quiz = res.getString("nom_quiz");
            int nomb_question = res.getInt("nomb_question");
            Quiz quiz = new Quiz (id,nom_quiz, nomb_question);
            listQuiz.add(quiz);
        }
            return listQuiz;
    }
}

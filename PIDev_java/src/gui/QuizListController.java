/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Quiz;
import Services.QuizService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class QuizListController implements Initializable {

    @FXML
    private GridPane grid;
    
    private QuizService serviceQuiz = new QuizService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillGrid();
        } catch (SQLException ex) {
            Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void fillGrid() throws SQLException{
        List<Quiz> listQuiz = new ArrayList();
        
        listQuiz = serviceQuiz.getAllQuiz();
        
        for(int i = 0; i < listQuiz.size(); i++){
            Label lb_quiz = new Label(listQuiz.get(i).getNom_quiz());
//            lb_quiz.setPrefHeight(50);
            grid.addRow(i,lb_quiz);
             grid.getRowConstraints().add(new RowConstraints(50));
//            grid.getChildren().get(i).prefHeight(30);
//System.out.println(i);
//        grid.getRowConstraints().get(i).setMinHeight(30);
//        grid.getRowConstraints().get(i).setPrefHeight(30);
//        grid.getRowConstraints().get(i).setMaxHeight(30);
            
        }
        
        grid.getRowConstraints().add(new RowConstraints(100));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Question;
import Entity.Quiz;
import Entity.Reponse;
import Services.QuestionService;
import Services.QuizService;
import Services.ReponseService;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class QuizController implements Initializable {
    
    @FXML
    private GridPane grid ;
    @FXML
    private VBox vbox ;
    @FXML
    private Button btn_add_reponse;
    
    @FXML
    private Button btn_add_question;
    
    @FXML
    private TextField tf_question;
    
    @FXML
    private TextField tf_quiz;
    
    @FXML
    private TextField tf_reponse;
    int nbQuestion = 0;
    
    private ArrayList<TextField> tfList = new ArrayList();    
    private ArrayList<Button> buttonList = new ArrayList();
    private ArrayList<TextField> labelList = new ArrayList();
    private ArrayList<GridPane> gridList = new ArrayList();
    

    
//    private HashMap<TextField, ArrayList<TextField>> questionList = new HashMap();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        grid = this.grid;
        gridList.add(grid);
//        al.add(tf_reponse);
//        questionList.put(question, al);
    }    
    
    @FXML
    private void addQuiz(ActionEvent event) throws SQLException{
//        System.out.println(tf_quiz.getText());
        Quiz quiz = new Quiz( tf_quiz.getText(), nbQuestion);
        QuizService service = new QuizService();
        int idQuiz = service.addQuizAndGetItsId(quiz);
        int x;
        
        for(Node node: vbox.getChildren()){
            x=0;
            Question question ;
            QuestionService serviceQuestion = new QuestionService();
            int idQuestion =0;
            for(Node n : ((GridPane)node).getChildren()){
                question = new Question();
                
                if(n instanceof TextField){
                    if(x == 0){
//                        System.out.println("question::"+((TextField) n).getText() );
                        question.setDuree(2);
                        question.setQuiz_id_id(idQuiz);
                        question.setContenu_ques(((TextField) n).getText());
                        question.setNomb_rep(0);
                        idQuestion = serviceQuestion.addQuestionAndGetItsId(question);
                    }
                    else{
//                    System.out.println("reponse: "+((TextField) n).getText());
                       Reponse reponse = new Reponse(idQuestion,((TextField) n).getText() );
                       ReponseService rs = new ReponseService();
                       rs.addReponse(reponse);
                    }
                    x=1;
                }
            }
        }
//        System.out.println(tf_quiz.getText());
    }
    
    
    @FXML
    private void addReponse(ActionEvent event){
        TextField tf = new TextField();
        Label lb = new Label("Reponse");
        Button btn = new Button("-");
        btn.setOnAction((e)->{
            grid.getChildren().removeAll(tf,lb,btn);
            
        });
        tfList.add(tf);
        grid.addRow(getRowCount(grid), lb ,tf , btn);
                
    }
    
    @FXML
    private void addQuestion(){
        nbQuestion++;
        GridPane grid1 = new GridPane();
        gridList.add(grid1);
        Button btn1 = new Button("+");
        btn1.setOnAction(e ->{
            TextField tf = new TextField();
        Label lb = new Label("Reponse");
        Button bt = new Button("-");
        bt.setOnAction((event)->{
            grid1.getChildren().removeAll(tf,lb,bt);
        });
        tfList.add(tf);
        grid1.addRow(getRowCount(grid1), lb ,tf, bt );
        });
        grid1.addRow(0, new Label("Question"), new TextField(), btn1);
        vbox.getChildren().add(vbox.getChildren().size(),grid1);
    }
    
    @FXML
    private void deleteReponse(ActionEvent event){
            grid.getChildren().remove((Node) event.getTarget());
    } 
    
    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }
}

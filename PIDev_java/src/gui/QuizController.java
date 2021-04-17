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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class QuizController implements Initializable {

    @FXML
    private TextField tf_text;

    @FXML
    private HBox hb_btns;

    @FXML
    private Label lb_error;

    @FXML
    private Button btn_next;

    @FXML
    private Label lb_text;

    @FXML
    private AnchorPane main_pane;

    @FXML
    private GridPane grid;

    private int nb_question = 0;
    private int nb_reponse;

    private int id_quiz;
    
    Quiz quiz;
    
    private QuizService quizService = new QuizService();
    private QuestionService questionService = new QuestionService();
    private ReponseService reponseService = new ReponseService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        grid = this.grid;
//        gridList.add(grid);
//        al.add(tf_reponse);
//        questionList.put(question, al);
        lb_text.setText("Titre De Quiz");
        grid.setAlignment(Pos.TOP_CENTER);
    }

    @FXML
    public void nextQuestion(ActionEvent event) throws SQLException {



        
        
        if (nb_question == 0) {
            if (tf_text.getText().length() > 4) {

                quiz= new Quiz(tf_text.getText(), 0);
                id_quiz = quizService.addQuizAndGetItsId(quiz);
                Button finish = new Button("Terminer");
                finish.setOnAction(e -> {
                    try {
                        nextQuestion(e);
                    } catch (SQLException ex) {
                        Logger.getLogger(QuizController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                hb_btns.getChildren().add(finish);

                main_pane.getChildren().removeAll(lb_error, lb_text, tf_text);
                fillGrid();

            } else {

                lb_error.setText("Le titre de quiz > 4 char");
                lb_error.setVisible(true);

            }
        } else if(testInput()){

            Question question = new Question(0, id_quiz, ((TextField)((VBox)getNodeByRowColumnIndex(0, 1, grid)).getChildren().get(0)).getText(), nb_reponse -1, 2);
            int id_ques = questionService.addQuestionAndGetItsId(question);
            question.setId(id_ques);
            for (Node node : grid.getChildren()) {
            if (grid.getColumnIndex(node) == 1 && grid.getRowIndex(node) != 0 ) {
                Reponse reponse = new Reponse(id_ques, ((TextField)((VBox)node).getChildren().get(0)).getText());
                int id_reponse = reponseService.addReponseAndGetItsId(reponse);
                if(((RadioButton)getNodeByRowColumnIndex(grid.getRowIndex(node), 0, grid)).isSelected()){
                    question.setRep_just_id(id_reponse);
                    questionService.updateQuestion(question);
                }
            }
        }
            if (((Button) event.getTarget()).getText().equals("Terminer")) {
                quiz.setId(id_quiz);
                quiz.setNomb_question(nb_question);
                quizService.updateQuiz(quiz);
                System.exit(0);
            }
            fillGrid();

        }
    }

    public void fillGrid() {

        nb_reponse = 2;
        grid.getChildren().clear();
        Button btn_add = new Button("+");
        btn_add.setOnAction(ev -> {
            Label lb_reponse = new Label("Reponse ");
            TextField tf_reponse = new TextField();
            VBox vb = new VBox();
            vb.getChildren().addAll(tf_reponse, new Label());
            Button btn_remove = new Button("-");
            RadioButton radio = new RadioButton();
            radio.setOnAction(e -> selectReponse(radio));
            grid.addRow(nb_reponse, radio, vb, btn_remove);
            btn_remove.setAlignment(Pos.TOP_CENTER);
            
            btn_remove.setOnAction(e -> {

                grid.getChildren().removeAll( radio, vb, btn_remove);
                nb_reponse--;
            });
            nb_reponse++;
            alignGrid();
        });

        RadioButton radio = new RadioButton();
        radio.setOnAction(e -> selectReponse(radio));
        VBox vb = new VBox();
        vb.getChildren().addAll(new TextField(), new Label());
        grid.addRow(0, new Label("Question"), vb, btn_add);
        vb = new VBox();
        vb.getChildren().addAll(new TextField(), new Label());
        grid.addRow(1, radio, vb, new Label());
        nb_question++;
        alignGrid();
        
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
    
    
    public boolean testInput(){
        
        boolean test = true;
        for (Node node : grid.getChildren()) {
            if (grid.getRowIndex(node) == 0 && grid.getColumnIndex(node) == 1) {
                if(((TextField)((VBox)node).getChildren().get(0)).getText().length() < 8){
                    ((Label)((VBox)node).getChildren().get(1)).setText("le question doit etre > 8 char");
                    test = false;
                }
                else {
                    ((Label)((VBox)node).getChildren().get(1)).setVisible(false);
                }
            }
            else if( grid.getColumnIndex(node) == 1){
                if(((TextField)((VBox)node).getChildren().get(0)).getText().length() < 1){
                    ((Label)((VBox)node).getChildren().get(1)).setText("le reponse doit etre > 1 char");
                    test = false;
                }else{
                    ((Label)((VBox)node).getChildren().get(1)).setVisible(false);
                }
            }
        }
        return test;
    }
    
    public void alignGrid(){
        for(Node node: grid.getChildren()){
            if(node instanceof RadioButton){
                grid.setHalignment(node, HPos.CENTER);
            }
            grid.setValignment(node, VPos.TOP);
        }
    }

    
    public void selectReponse(RadioButton radio){
        for (Node node : grid.getChildren()){
            if(node instanceof RadioButton){
                ((RadioButton)node).setSelected(false);
            }
        }
        radio.setSelected(true);
    }

}

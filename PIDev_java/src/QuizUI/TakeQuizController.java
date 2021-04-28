/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizUI;

import Entity.List_reponses_condidat;
import Entity.Question;
import Entity.Quiz;
import Entity.Reponse;
import Entity.Reponse_condidat;
import Services.ListReponseService;
import Services.QuestionService;
import Services.QuizService;
import Services.ReponseCondidatService;
import Services.ReponseService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class TakeQuizController implements Initializable {

    QuizService quizService = new QuizService();
    QuestionService questionService = new QuestionService();
    ReponseService reponseService = new ReponseService();
    ListReponseService listService = new ListReponseService();
    ReponseCondidatService rcService = new ReponseCondidatService();
    
    private int score = 0;

    @FXML
    private AnchorPane main_pane;

    @FXML
    private Label lb_quiz;
    @FXML
    private Label lb_question;

    @FXML
    private TextField tf_nbQuestion;

    @FXML
    private Button btn_next;

    @FXML
    private GridPane grid;
    
    @FXML
    private Label lb_text;
    Quiz quiz;
    List<Question> questions = new ArrayList();
    private int listId;
    
    List_reponses_condidat list;

    List<Reponse> reponses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lb_text.setStyle("-fx-font: normal bold 15px 'serif';");
        main_pane.setStyle("-fx-background-color: #0000;");
        btn_next.setStyle("-fx-background-color: #ad0505; -fx-text-fill: white;");
        btn_next.setPrefSize(89, 31);

        list = new List_reponses_condidat(25, 5, 0);
        try {
            listId = listService.addListAndGetItsId(list);
            list.setId(listId);
            list.setCondidtaure_id(0);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tf_nbQuestion.setText("0");
        quiz = new Quiz();
        try {
            quiz = quizService.getQuiz(25);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_quiz.setText(quiz.getNom_quiz());
        lb_quiz.setStyle("-fx-font: normal bold 20px 'serif'");

        try {
            questions = questionService.getQuestionByQuiz(quiz);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        fillGrid();

    }

    @FXML
    public void nextQuestion(ActionEvent event) {

        System.out.println(((Label)getNodeByRowColumnIndex(getSelectedIndex(), 1, grid)).getText());
        int id_reponse = Integer.parseInt(((Label)getNodeByRowColumnIndex(getSelectedIndex(), 1, grid)).getText());
        if(id_reponse == questions.get(Integer.parseInt(tf_nbQuestion.getText())).getRep_just_id()){
            score += 1;
        }
        
        Reponse_condidat rc = new Reponse_condidat();
        rc.setList_reponses_condidat_id(listId);
        rc.setQuestion_id(questions.get(Integer.parseInt(tf_nbQuestion.getText())).getId());
        for (Node node : grid.getChildren()) {
            if (node instanceof RadioButton) {
                if (((RadioButton) node).isSelected()) {
                    rc.setReponse_id(reponses.get(grid.getRowIndex(node)).getId());
                    break;
                }
            }
        }
        rcService.addList_reponses_condidat(rc);
        if (((Button) event.getTarget()).getText().compareTo("Next") == 0) {

            if (questions.size() == (Integer.parseInt(tf_nbQuestion.getText()) + 2)) {
                ((Button) event.getTarget()).setText("Finish");
            }
            grid.getChildren().clear();

            tf_nbQuestion.setText(String.valueOf((Integer.parseInt(tf_nbQuestion.getText())) + 1));
            fillGrid();

        } else {
            score = score *100 / questions.size();
            list.setScore(score);
            listService.updateList_reponses_condidat(list);
            System.out.println(score);
            System.exit(0);
        }

    }

    public void fillGrid() {
        lb_question.setText(questions.get(Integer.parseInt(tf_nbQuestion.getText())).getContenu_ques());
        lb_question.setStyle("-fx-font: normal bold 15px 'serif';");
        reponses = new ArrayList();

        try {
            reponses = reponseService.getReponseByQuestion(questions.get(Integer.parseInt(tf_nbQuestion.getText())));
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < reponses.size(); i++) {
            RadioButton chk = new RadioButton(reponses.get(i).getContenu_rep());
            chk.setMinWidth(400);
            chk.setMinHeight(40);
            grid.setMargin(chk, new Insets(2, 2, 2, 2));
            chk.setPadding(new Insets(20, 30, 20, 20));
            chk.setOnAction(event -> {
                for (Node node : grid.getChildren()) {
                    if (node instanceof RadioButton) {
                        ((RadioButton) node).setSelected(false);
                        ((RadioButton) node).setStyle("-fx-font: normal bold 15px 'serif'; -fx-background-size: 200 100;  -fx-border-radius: 18 18 18 18; -fx-border-color: red;");

                    }
                }
                ((RadioButton) event.getTarget()).setSelected(true);
                ((RadioButton) event.getTarget()).setStyle("-fx-font: normal bold 15px 'serif'; -fx-background-size: 200 100; -fx-background-radius: 18 18 18 18; -fx-border-radius: 18 18 18 18; -fx-background-color: #33cc33;");

            });
            Label lb_id_reponse = new Label(String.valueOf(reponses.get(i).getId()));
            lb_id_reponse.setVisible(false);
            chk.setStyle("-fx-font: normal bold 15px 'serif'; -fx-background-size: 200 100;  -fx-border-radius: 18 18 18 18; -fx-border-color: red;");

            grid.addRow(i, chk, lb_id_reponse);
        }
        alignGrid();
    }

    public int getSelectedIndex(){
        for(Node node : grid.getChildren()){
            if(node instanceof RadioButton){
                if(((RadioButton)node).isSelected())
                    return grid.getRowIndex(node);
            }
        }
        return -1;
    }
    
    public void alignGrid() {
        grid.setHgap(5);
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
}
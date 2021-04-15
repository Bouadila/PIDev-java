/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

    @FXML
    private Label lb_quiz;
    @FXML
    private Label lb_question;

    @FXML
    private TextField tf_nbQuestion;

    @FXML
    private VBox vbox;
    Quiz quiz;
    List<Question> questions = new ArrayList();
    private int listId;

    List<Reponse> reponses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List_reponses_condidat list = new List_reponses_condidat(7, 5, 0);
        try {
            listId = listService.addListAndGetItsId(list);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tf_nbQuestion.setText("0");
        quiz = new Quiz();
        try {
            quiz = quizService.getQuiz(7);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_quiz.setText(quiz.getNom_quiz());

        try {
            questions = questionService.getQuestionByQuiz(quiz);
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lb_question.setText(questions.get(0).getContenu_ques());

        reponses = new ArrayList();

        try {
            reponses = reponseService.getReponseByQuestion(questions.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(reponses.size());
        for (int i = 0; i < reponses.size(); i++) {
            RadioButton chk = new RadioButton(reponses.get(i).getContenu_rep());
            chk.setOnAction(event -> {
                for (Node node : vbox.getChildren()) {
                    if (node instanceof RadioButton) {
                        ((RadioButton) node).setSelected(false);
                    }
                }
                ((RadioButton) event.getTarget()).setSelected(true);
            });
            vbox.getChildren().add(chk);
        }

    }

    @FXML
    public void nextQuestion(ActionEvent event) {

        Reponse_condidat rc = new Reponse_condidat();
        rc.setList_reponses_condidat_id(listId);
        rc.setQuestion_id(questions.get(Integer.parseInt(tf_nbQuestion.getText())).getId());
        for (int i = 0; i < vbox.getChildren().size(); i++) {
            if (vbox.getChildren().get(i) instanceof RadioButton) {
                if (((RadioButton) vbox.getChildren().get(i)).isSelected()) {
                    rc.setReponse_id(reponses.get(i).getId());
                    break;
                }
            }
        }
        rcService.addList_reponses_condidat(rc);
        if (((Button) event.getTarget()).getText().compareTo("Next") == 0) {

            if (questions.size() == (Integer.parseInt(tf_nbQuestion.getText()) + 2)) {
                ((Button) event.getTarget()).setText("Finish");
            }
            vbox.getChildren().clear();

            tf_nbQuestion.setText(String.valueOf((Integer.parseInt(tf_nbQuestion.getText())) + 1));
            lb_question.setText(questions.get(Integer.parseInt(tf_nbQuestion.getText())).getContenu_ques());
            List<Reponse> reponses = new ArrayList();
            try {
                reponses = reponseService.getReponseByQuestion(questions.get(Integer.parseInt(tf_nbQuestion.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(TakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < reponses.size(); i++) {
                RadioButton chk = new RadioButton(reponses.get(i).getContenu_rep());
                chk.setOnAction(e -> {
                    for (Node node : vbox.getChildren()) {
                        if (node instanceof RadioButton) {
                            ((RadioButton) node).setSelected(false);
                        }
                    }
                    ((RadioButton) e.getTarget()).setSelected(true);
                });
                vbox.getChildren().add(chk);

            }

        } else {
            System.exit(0);
        }

    }
}

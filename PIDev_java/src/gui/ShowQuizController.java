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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class ShowQuizController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private Button btn_terminer;

    private Quiz quiz;
    TextField tf_quiz;

    private QuestionService questionService = new QuestionService();
    private QuizService quizService = new QuizService();
    private ReponseService reponseService = new ReponseService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Quiz quiz) throws SQLException {
        this.quiz = quiz;
        Label lb_quiz = new Label("Titre de quiz");
        Button btn_add = new Button("+");
        btn_add.setOnAction(e ->{
             Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowQuestion.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();

                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ShowQuestionController quizController = loader.getController();
                try {
                    quizController.initData(new Question(0, quiz.getId(), "", 0, 2));
                } catch (SQLException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                stage.setScene(scene);

        });
        tf_quiz = new TextField(quiz.getNom_quiz());
        grid.addRow(0, lb_quiz, tf_quiz, btn_add);
        List<Question> list_question = questionService.getQuestionByQuiz(quiz);

        for (int i = 0; i < list_question.size(); i++) {

            Label lb_question = new Label(list_question.get(i).getContenu_ques());
            VBox vb = new VBox();
            List<Reponse> reponseList = reponseService.getReponseByQuestion(list_question.get(i));
            for (Reponse reponse : reponseList) {
                Label lb_reponse = new Label(reponse.getContenu_rep());
                vb.getChildren().add(lb_reponse);
            }

            grid.addRow(i + 1, lb_question, vb);
            grid.setValignment(grid.getChildren().get(i + 1), VPos.TOP);
            Question question = list_question.get(i);
            lb_question.setOnMouseClicked(e -> {
                Node node = (Node) e.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowQuestion.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();

                Scene scene = null;
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ShowQuestionController quizController = loader.getController();
                try {
                    quizController.initData(question);
                } catch (SQLException ex) {
                    Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.setScene(scene);
            });
        }
    }

    @FXML
    public void terminer(ActionEvent e) {

        if (tf_quiz.getText().length() > 4) {
            quiz.setNom_quiz(tf_quiz.getText());
            quizService.updateQuiz(quiz);

            Node node = (Node) e.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/QuizList.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException ex) {
                Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage.setScene(scene);
        } else {
            System.out.println("errror");
        }

    }

}

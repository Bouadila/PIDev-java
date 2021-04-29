/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizUI;

import Entity.Question;
import Entity.Quiz;
import Entity.Reponse;
import Services.QuestionService;
import Services.QuizService;
import Services.ReponseService;
import Services.UserSession;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class ShowQuizController implements Initializable {

//    @FXML
//    private GridPane grid;
    @FXML
    private Button btn_terminer;

    private Quiz quiz;

    @FXML
    TextField tf_quiz;

    @FXML
    Label lb_quiz;

    @FXML
    private ListView list;

    @FXML
    private HBox hbox;

    private QuestionService questionService = new QuestionService();
    private QuizService quizService = new QuizService();
    private ReponseService reponseService = new ReponseService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_terminer.setText("Enregistrer");
        btn_terminer.setStyle("-fx-background-color: #ad0505; -fx-text-fill: white;");
        btn_terminer.setPrefSize(89, 31);
        tf_quiz.setPrefWidth(200);
        tf_quiz.setStyle("-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
    }

    public void initData(Quiz quiz) throws SQLException {

//        grid.setStyle("-fx-background-color: #0000;");
        this.quiz = quiz;
        lb_quiz.setText("Titre de quiz");
        tf_quiz.setText(quiz.getNom_quiz());
        FontAwesomeIconView btn_add = new FontAwesomeIconView();
        btn_add.setGlyphName("PLUS_SQUARE");
        btn_add.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 25.0; -fx-fill: #00af00;");
        btn_add.setOnMouseClicked(e -> {
            Node node = (Node) e.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/ShowQuestion.fxml"));
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
        if (hbox.getChildren().size() < 3) {
            hbox.getChildren().add(btn_add);
        }
        List<Question> list_question = questionService.getQuestionByQuiz(quiz);

        for (int i = 0; i < list_question.size(); i++) {

            Label lb_question = new Label(list_question.get(i).getContenu_ques());
            VBox vb = new VBox();
            List<Reponse> reponseList = reponseService.getReponseByQuestion(list_question.get(i));
            for (Reponse reponse : reponseList) {
                Label lb_reponse = new Label(reponse.getContenu_rep());
                if (reponse.getId() == list_question.get(i).getRep_just_id()) {
                    lb_reponse.setTextFill(Color.web("green", 0.8));
                }
                vb.getChildren().add(lb_reponse);
            }

            FontAwesomeIconView btn_remove = new FontAwesomeIconView();
            btn_remove.setGlyphName("TRASH");
            btn_remove.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 40.0; -fx-fill: #b45959; -fx-padding: 20 20 20 20;");
            Question question = list_question.get(i);
            btn_remove.setOnMouseClicked(event -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer  " + question.getContenu_ques() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    questionService.deleteQuestion(question);
                    try {
                        list.getItems().clear();;
                        initData(quiz);
                    } catch (SQLException ex) {
                        Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            vb.setPadding(new Insets(10, 10, 10, 10));
            lb_question.setPadding(new Insets(10, 10, 10, 10));
            HBox hb = new HBox();
            lb_question.setMinWidth(100);
            vb.setMinWidth(200);
            hb.getChildren().addAll(lb_question, vb, btn_remove);
            list.getItems().add(hb);
//            grid.addRow(i, lb_question, vb, btn_remove);

//            grid.setValignment(grid.getChildren().get(i), VPos.TOP);
        }
        list.setOnMouseClicked(e -> {
            Node node = (Node) e.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/QuizUI/ShowQuestion.fxml"));
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException ex) {
                Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ShowQuestionController quizController = loader.getController();
            try {

                quizController.initData(list_question.get(list.getSelectionModel().getSelectedIndex()));
            } catch (Exception ex) {

            }
            stage.setScene(scene);
        });

    }

    @FXML
    public void terminer(ActionEvent e) {

        if (tf_quiz.getText().length() > 4) {
            quiz.setNom_quiz(tf_quiz.getText());
            quizService.updateQuiz(quiz);

            Node node = (Node) e.getSource();
            FXMLLoader loader = null;
            if (UserSession.getIdSession() == 52) {
                loader = new FXMLLoader(getClass().getResource("/UI/UI_User/UserAfficheBack.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
            }
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

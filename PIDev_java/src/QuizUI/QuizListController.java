/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizUI;

import Entity.Quiz;
import Services.QuizService;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class QuizListController implements Initializable {

    @FXML
    private GridPane grid;
    
    @FXML
    private AnchorPane main_pane;

    private QuizService serviceQuiz = new QuizService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        main_pane.setStyle("-fx-background-color: white;");
        grid.setStyle("-fx-background-color: white;");
        try {
            // TODO
            
            fillGrid();
        } catch (SQLException ex) {
            Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillGrid() throws SQLException {
        List<Quiz> listQuiz = new ArrayList();

        listQuiz = serviceQuiz.getAllQuiz();

        for (int i = 0; i < listQuiz.size(); i++) {
            Label lb_quiz = new Label(listQuiz.get(i).getNom_quiz());
            Quiz quiz = listQuiz.get(i);
            int h = i;
            lb_quiz.setOnMouseClicked(e -> {
                try {
                    Node node = (Node) e.getSource();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowQuiz.fxml"));
                    Stage stage = (Stage) node.getScene().getWindow();

                    Scene scene = new Scene(loader.load());
                    ShowQuizController quizController = loader.getController();
                    quizController.initData(quiz);
                    stage.setScene(scene);
                } catch (IOException ex) {
                    Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            FontAwesomeIconView btn_remove = new FontAwesomeIconView();
            btn_remove.setGlyphName("TRASH");
            btn_remove.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 20.0; -fx-fill: #b45959;");
            btn_remove.setOnMouseClicked(event -> {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Supprimer  " + quiz.getNom_quiz() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //do stuff
                    serviceQuiz.deleteQuiz(quiz);
                    try {
                        grid.getChildren().clear();
                        fillGrid();
                    } catch (SQLException ex) {
                        Logger.getLogger(QuizListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            grid.addRow(i, lb_quiz, btn_remove);
            grid.getRowConstraints().add(new RowConstraints(50));

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entity.Question;
import Entity.Reponse;
import Services.QuestionService;
import Services.QuizService;
import Services.ReponseService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bou3dila
 */
public class ShowQuestionController implements Initializable {

    private int nb = 2;

    @FXML
    private GridPane grid;

    private int nb_reponse = 0;

    private Question question = null;

    @FXML
    private Button btn_terminer;

    private QuestionService questionService = new QuestionService();
    private QuizService quizService = new QuizService();
    private ReponseService reponseService = new ReponseService();
    List<Reponse> listReponse = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        grid.setAlignment(Pos.TOP_CENTER);
    }

    public void initData(Question question) throws SQLException {
        btn_terminer.setStyle("-fx-background-color: #ad0505; -fx-text-fill: white;");
        btn_terminer.setPrefSize(89, 31);
        this.question = question;
        if (question.getId() != 0) {

            listReponse = reponseService.getReponseByQuestion(question);
            nb_reponse = listReponse.size();
        }
        fillGrid();

    }

    public void fillGrid() throws SQLException {
        FontAwesomeIcon btn_add = new FontAwesomeIcon();
        btn_add.setGlyphName("PLUS_SQUARE");
        btn_add.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 25.0; -fx-fill: #00af00;");

        VBox vb = new VBox();
        TextField tf_question = new TextField(question.getContenu_ques());
        tf_question.setPrefWidth(300);
        tf_question.setStyle("-fx-background-color: #a9a9a9 , white , white; -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
        Label lb_error = new Label();
        vb.getChildren().addAll(tf_question, lb_error);
        lb_error.setTextFill(Color.web("#ff0000", 0.8));
        Label lb_question = new Label("Question");
grid.addRow(0, lb_question, vb, btn_add);
        
        lb_question.setPadding(new Insets(0, 0, 0, 0));
        if (listReponse.size() > 0) {
            vb = new VBox();
            RadioButton radio = new RadioButton();
            radio.setOnAction(e -> selectReponse(radio));
            Label error = new Label();
            error.setTextFill(Color.web("#ff0000", 0.8));
            vb.getChildren().addAll(new TextField(listReponse.get(0).getContenu_rep()), error);
            if (listReponse.get(0).getId() == question.getRep_just_id()) {
                radio.setSelected(true);
            }
            grid.addRow(1, radio, vb, new Label());

            for (int i = 1; i < listReponse.size(); i++) {
                TextField tf_reponse = new TextField(listReponse.get(i).getContenu_rep());
                error = new Label();
                error.setTextFill(Color.web("#ff0000", 0.8));
                VBox vb1 = new VBox();
                vb1.getChildren().addAll(tf_reponse, error);
                FontAwesomeIcon btn_remove = new FontAwesomeIcon();
                btn_remove.setGlyphName("MINUS_SQUARE");
                btn_remove.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 25.0; -fx-fill: #b45959;");
                RadioButton radio1 = new RadioButton();
                if (listReponse.get(i).getId() == question.getRep_just_id()) {
                    radio1.setSelected(true);
                }
                radio1.setOnAction(e -> selectReponse(radio1));
                grid.addRow(nb++, radio1, vb1, btn_remove);
//                btn_remove.setAlignment(Pos.TOP_CENTER);

                btn_remove.setOnMouseClicked(e -> {

                    grid.getChildren().removeAll(radio1, vb1, btn_remove);
                    nb_reponse--;
                    lb_error.setVisible(false);
                });
                alignGrid();
            }
        } else {
            Label error = new Label();
            error.setTextFill(Color.web("#ff0000", 0.8));
            vb = new VBox();
            RadioButton radio = new RadioButton();
            radio.setOnAction(e -> selectReponse(radio));
            vb.getChildren().addAll(new TextField(), error);
            grid.addRow(1, radio, vb, new Label());
        }

        alignGrid();

        btn_add.setOnMouseClicked(ev -> {

            if (nb_reponse < 4) {
                TextField tf_reponse = new TextField();
                VBox vb1 = new VBox();
                Label error = new Label();
                error.setTextFill(Color.web("#ff0000", 0.8));
                vb1.getChildren().addAll(tf_reponse, error);
                FontAwesomeIcon btn_remove = new FontAwesomeIcon();
                btn_remove.setGlyphName("MINUS_SQUARE");
                btn_remove.setStyle("-fx-font-family: FontAwesome; -fx-font-size: 25.0; -fx-fill: #b45959;");
                RadioButton radio = new RadioButton();
                radio.setOnAction(e -> selectReponse(radio));
                grid.addRow(nb++, radio, vb1, btn_remove);
//            btn_remove.setAlignment(Pos.TOP_CENTER);

                btn_remove.setOnMouseClicked(e -> {

                    grid.getChildren().removeAll(radio, vb1, btn_remove);
                    nb_reponse--;
                    lb_error.setVisible(false);
                });
                nb_reponse++;
                alignGrid();
            } else {
                lb_error.setText("Tu peut pas ajouter plus que 4 reponse");
                lb_error.setVisible(true);
            }
        });
        

    }

    @FXML
    public void terminer(ActionEvent e) throws SQLException {

        int x = 0;
        Reponse reponse;
        if (testInput()) {

            if (question.getId() == 0) {
                question.setContenu_ques(((TextField) ((VBox) getNodeByRowColumnIndex(0, 1, grid)).getChildren().get(0)).getText());
                question.setNomb_rep(nb_reponse);
                int id_quest = questionService.addQuestionAndGetItsId(question);
                question.setId(id_quest);
            } else {
                question.setContenu_ques(((TextField) ((VBox) getNodeByRowColumnIndex(0, 1, grid)).getChildren().get(0)).getText());

                questionService.updateQuestion(question);
            }
            for (Node node : grid.getChildren()) {
                if (grid.getColumnIndex(node) == 1 && grid.getRowIndex(node) != 0) {
                    if (listReponse.size() > x) {
                        reponse = listReponse.get(x);
                        x++;
                        reponse.setContenu_rep(((TextField) ((VBox) node).getChildren().get(0)).getText());
                        reponseService.updateReponse(reponse);
                    } else {
                        reponse = new Reponse(question.getId(), ((TextField) ((VBox) node).getChildren().get(0)).getText());
                        int id_reponse = reponseService.addReponseAndGetItsId(reponse);
                        reponse.setId(id_reponse);
                    }
                    if (((RadioButton) getNodeByRowColumnIndex(grid.getRowIndex(node), 0, grid)).isSelected()) {
                        question.setRep_just_id(reponse.getId());
                        questionService.updateQuestion(question);
                    }
                }
            }

            if (listReponse.size() > x) {
                for (int i = x; i < listReponse.size(); i++) {
                    reponse = listReponse.get(i);
                    reponseService.deleteReponse(reponse);
                }
            }
            Node node1 = (Node) e.getSource();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowQuiz.fxml"));
            Stage stage = (Stage) node1.getScene().getWindow();

            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException ex) {
                Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ShowQuizController quizController = loader.getController();
            try {
                quizController.initData(quizService.getQuiz(question.getQuiz_id_id()));
            } catch (SQLException ex) {
                Logger.getLogger(ShowQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(scene);
        }

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

    public boolean testInput() {

        boolean test = true;
        for (Node node : grid.getChildren()) {
            if (grid.getRowIndex(node) == 0 && grid.getColumnIndex(node) == 1) {
                if (((TextField) ((VBox) node).getChildren().get(0)).getText().length() < 8) {
                    ((Label) ((VBox) node).getChildren().get(1)).setText("le question doit etre > 8 char");
                    test = false;
                } else {
                    ((Label) ((VBox) node).getChildren().get(1)).setVisible(false);
                }
            } else if (grid.getColumnIndex(node) == 1) {
                if (((TextField) ((VBox) node).getChildren().get(0)).getText().length() < 1) {
                    ((Label) ((VBox) node).getChildren().get(1)).setText("le reponse doit etre > 1 char");
                    ((Label) ((VBox) node).getChildren().get(1)).setVisible(true);
                    test = false;
                } else {
                    ((Label) ((VBox) node).getChildren().get(1)).setVisible(false);
                }
            }
        }
        return test;
    }

    public void alignGrid() {
        for (Node node : grid.getChildren()) {
            if (node instanceof RadioButton) {
                grid.setHalignment(node, HPos.CENTER);
            }
            grid.setValignment(node, VPos.TOP);
        }
    }

    public void selectReponse(RadioButton radio) {
        for (Node node : grid.getChildren()) {
            if (node instanceof RadioButton) {
                ((RadioButton) node).setSelected(false);
            }
        }
        radio.setSelected(true);
    }

}

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

        if (((Button) event.getTarget()).getText().equals("Terminer")) {
            System.exit(0);
        }

        
        //check if the finish button exist
        //if not, it will be added next to the next button
        //also it means that we haven't added quiz yet
        if (nb_question == 0) {
            if (tf_text.getText().length() > 4) {

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

            Question question = new Question();
            System.out.println(((TextField)((VBox)getNodeByRowColumnIndex(0, 1, grid)).getChildren().get(0)).getText());
            for (Node node : grid.getChildren()) {
            if (grid.getColumnIndex(node) == 1 && grid.getRowIndex(node) != 0 ) {
                System.out.println(((TextField)((VBox)node).getChildren().get(0)).getText());
                if(((RadioButton)getNodeByRowColumnIndex(grid.getRowIndex(node), 0, grid)).isSelected()){
                    System.out.println("true");
                }
            }
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
//    @FXML
//    private GridPane grid ;
//    @FXML
//    private VBox vbox ;
//    @FXML
//    private Button btn_add_reponse;
//    
//    @FXML
//    private Button btn_add_question;
//    
//    @FXML
//    private TextField tf_question;
//    
//    @FXML
//    private TextField tf_quiz;
//    
//    @FXML
//    private TextField tf_reponse;
//    int nbQuestion = 0;
//    
//    private ArrayList<TextField> tfList = new ArrayList();    
//    private ArrayList<Button> buttonList = new ArrayList();
//    private ArrayList<TextField> labelList = new ArrayList();
//    private ArrayList<GridPane> gridList = new ArrayList();
//    private HashMap<TextField, ArrayList<TextField>> questionList = new HashMap();
//    @FXML
//    private void addQuiz(ActionEvent event) throws SQLException{
////        System.out.println(tf_quiz.getText());
//        Quiz quiz = new Quiz( tf_quiz.getText(), nbQuestion);
//        QuizService service = new QuizService();
//        i
//        int x;
//        
//        for(Node node: vbox.getChildren()){
//            x=0;
//            Question question ;
//            QuestionService serviceQuestion = new QuestionService();
//            int idQuestion =0;
//            for(Node n : ((GridPane)node).getChildren()){
//                question = new Question();
//                
//                if(n instanceof TextField){
//                    if(x == 0){
////                        System.out.println("question::"+((TextField) n).getText() );
//                        question.setDuree(2);
//                        question.setQuiz_id_id(idQuiz);
//                        question.setContenu_ques(((TextField) n).getText());
//                        question.setNomb_rep(0);
//                        idQuestion = serviceQuestion.addQuestionAndGetItsId(question);
//                    }
//                    else{
////                    System.out.println("reponse: "+((TextField) n).getText());
//                       Reponse reponse = new Reponse(idQuestion,((TextField) n).getText() );
//                       ReponseService rs = new ReponseService();
//                       rs.addReponse(reponse);
//                    }
//                    x=1;
//                }
//            }
//        }
////        System.out.println(tf_quiz.getText());
//    }
//    
//    
//    @FXML
//    private void addReponse(ActionEvent event){
//        TextField tf = new TextField();
//        Label lb = new Label("Reponse");
//        Button btn = new Button("-");
//        btn.setOnAction((e)->{
//            grid.getChildren().removeAll(tf,lb,btn);
//            
//        });
//        tfList.add(tf);
//        grid.addRow(getRowCount(grid), lb ,tf , btn);
//                
//    }
//    
//    @FXML
//    private void addQuestion(){
//        nbQuestion++;
//        GridPane grid1 = new GridPane();
//        gridList.add(grid1);
//        Button btn1 = new Button("+");
//        btn1.setOnAction(e ->{
//            TextField tf = new TextField();
//        Label lb = new Label("Reponse");
//        Button bt = new Button("-");
//        bt.setOnAction((event)->{
//            grid1.getChildren().removeAll(tf,lb,bt);
//        });
//        tfList.add(tf);
//        grid1.addRow(getRowCount(grid1), lb ,tf, bt );
//        });
//        grid1.addRow(0, new Label("Question"), new TextField(), btn1);
//        vbox.getChildren().add(vbox.getChildren().size(),grid1);
//    }
//    
//    @FXML
//    private void deleteReponse(ActionEvent event){
//            grid.getChildren().remove((Node) event.getTarget());
//    } 
//    
//    private int getRowCount(GridPane pane) {
//        int numRows = pane.getRowConstraints().size();
//        for (int i = 0; i < pane.getChildren().size(); i++) {
//            Node child = pane.getChildren().get(i);
//            if (child.isManaged()) {
//                Integer rowIndex = GridPane.getRowIndex(child);
//                if(rowIndex != null){
//                    numRows = Math.max(numRows,rowIndex+1);
//                }
//            }
//        }
//        return numRows;
//    }
}

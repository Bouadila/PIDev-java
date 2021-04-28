/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_User;

import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserStatController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Pane pane2;
    @FXML
    private Hyperlink retour;
    @FXML
    private Text nb;
    @FXML
    private VBox vbox;
    @FXML
    private Button acceuil;
    @FXML
    private Button comptes;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
loadData();
    }    
    
    private void loadData()
    {
        pane.getChildren().clear();
         pane2.getChildren().clear();
  ObservableList<PieChart.Data> graph=FXCollections.observableArrayList();
    ObservableList<PieChart.Data> graph2=FXCollections.observableArrayList();
  BorderPane root ;
 PieChart pieChart;
  PieChart pieChart2;
     UserService RV=new UserService();
int Accepted = RV.TotalActiv();
int Refused = RV.TotalRefused();
int Total= Accepted+Refused;
graph.addAll(new PieChart.Data("Les comptes activer", Accepted),
        new PieChart.Data("Les comptes désactiver", Refused)
        
        
);
//graph2.addAll( new PieChart.Data("Candi", Refused1),
//        new PieChart.Data("Employ", Refused2)
        
//);
root=new BorderPane();
pieChart2 =new PieChart();
pieChart =new PieChart();
pieChart.setData(graph);
pieChart2.setData(graph2);
nb.setText(("Totale des comptes :"+Integer.toString(Total)));
pieChart.setLegendSide(Side.BOTTOM);
pieChart.setLegendSide(Side.BOTTOM);
pieChart.setLabelsVisible(true);
pieChart2.setLegendSide(Side.BOTTOM);
pieChart2.setLabelsVisible(true);
final Label caption = new Label("");
caption.setTextFill(Color.BLACK);
caption.setStyle("-fx-font: 24 arial;");
final Label caption2 = new Label("");
caption2.setTextFill(Color.BLACK);
caption2.setStyle("-fx-font: 24 arial;");
int i = 0;
for (final PieChart.Data data : pieChart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
            e -> {
                double total = 0;
                for (PieChart.Data d : pieChart.getData()) {
                    total += d.getPieValue();
                }
                
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                caption.setText(text);
                caption.setVisible(true);
            }
           
    );
    
}
for (final PieChart.Data data : pieChart2.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
            e -> {
                double total = 0;
                for (PieChart.Data d : pieChart2.getData()) {
                    total += d.getPieValue();
                }
                
                caption2.setTranslateX(e.getSceneX());
                caption2.setTranslateY(e.getSceneY());
                String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                caption2.setText(text);
                caption2.setVisible(true);
            }
    );
}
pane.getChildren().addAll(pieChart,caption);
pane2.getChildren().addAll(pieChart2,caption2);
   
         
        retour.setOnAction((ActionEvent event) -> {
            Parent page2;
            try {
                page2 = FXMLLoader.load(getClass().getResource("UserAfficheBack.fxml"));
                Scene scene2 = new Scene(page2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene2);
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(UserAfficheBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    
    }

    @FXML
    private void acceuilClicks(ActionEvent event) {
    }
@FXML
    private void CompteClicks(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserAfficheBack.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
        
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.Candidature;
import Entity.RendezVous;
import Services.OffreDao.RendezVousService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class AjouterRendezVousFXMLController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfRoom;
    @FXML
    private JFXDatePicker tfDate;
    @FXML
    private JFXTimePicker tfTimeStart;
    @FXML
    private JFXTimePicker tfTimeEnd;
    private Candidature candidature;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODo
    }    

    @FXML
    private void newRendezvous(MouseEvent event) {
//        Date in = new Date();
//        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
//        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(out.toString());
//        System.out.println(this.tfTimeStart.getValue());
        String titre = this.tfTitre.getText();
        String description = this.tfDescription.getText();
        String room = this.tfRoom.getText();
        LocalDate dateRendezVous = this.tfDate.getValue();
        LocalTime start = this.tfTimeStart.getValue();
        LocalTime end = this.tfTimeEnd.getValue();
        //LocalDateTime dateStart = dateStart.atTime(start);
        LocalDateTime localDateTimeStart = dateRendezVous.atTime(start);
        LocalDateTime localDateTimeEnd = dateRendezVous.atTime(end);
        //Date dateStart = Date.from(localDateTimeStart.atZone(ZoneId.systemDefault()).toInstant());
        Date dateStart = new Date(localDateTimeStart.getYear(),localDateTimeStart.getMonthValue(),localDateTimeStart.getDayOfMonth(),localDateTimeStart.getHour(),localDateTimeStart.getMinute(),localDateTimeStart.getSecond());
//      dateStart.setMinutes(localDateTimeStart.getMinute());
//      dateStart.setHours(localDateTimeStart.getHour());
        dateStart.setYear(2021);
        System.out.println(dateStart.getYear());
        System.out.println(dateStart);
        Date dateEnd = new Date(localDateTimeEnd.getYear(),localDateTimeEnd.getMonthValue(),localDateTimeEnd.getDayOfMonth(),localDateTimeEnd.getHour(),localDateTimeEnd.getMinute(),localDateTimeEnd.getSecond());
        //Date dateEnd = Date.from(localDateTimeEnd.atZone(ZoneId.systemDefault()).toInstant());
        RendezVous rdv = new RendezVous(titre, dateStart, dateEnd, description, room);
        RendezVousService rdvs = new RendezVousService();
        rdvs.add(rdv,candidature.getId());
        this.tfTitre.setText(null);
        this.tfDescription.setText(null);
        this.tfRoom.setText(null);
        this.tfDate.getEditor().clear();
        this.tfTimeStart.getEditor().clear();
        this.tfTimeEnd.getEditor().clear();
//        System.out.println(dateStart);
//        System.out.println(dateEnd);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        
    }

    public void initData(Candidature c) {
        candidature = c;
    }

    
}

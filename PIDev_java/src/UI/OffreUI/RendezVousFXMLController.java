/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OffreUI;

import Entity.RendezVous;
import Services.OffreDao.RendezVousService;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author brahm
 */
public class RendezVousFXMLController implements Initializable {

    private CalendarView calendar;
    @FXML
    private GridPane pnlHost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCalendar();
    }    

    @FXML
    private void offre(MouseEvent event) {
        Node node = (Node) event.getSource();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/OffreUI/OffreFXML.fxml"));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = null;  
                try {
                    scene = new Scene(loader.load());
                } catch (IOException ex) {
                    Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 stage.setScene(scene);
    }

    

    @FXML
    private void refreshData(MouseEvent event) {
    }

    @FXML
    private void AddData(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/UI/OffreUI/AjouterRendezVousFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OffreFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@FXML
    private void rendezVous(MouseEvent event) {}
    private void loadCalendar() {
        calendar = new CalendarView();

        Calendar classes = new Calendar("Classes");
        Calendar meetings = new Calendar("Meetings");
        classes.setStyle(Calendar.Style.STYLE7);
        meetings.setStyle(Calendar.Style.STYLE2);
        CalendarSource myCalendarSource = new CalendarSource("Timetable");
        myCalendarSource.getCalendars().addAll(classes, meetings);
         for ( int i = 0; i < new RendezVousService().getAll().size(); i++){
            RendezVous rdv = new RendezVousService().getAll().get(i);
            Entry<String> destist = new Entry<>(rdv.getTitre());
             System.out.println(rdv.getStart());
            LocalDate date = LocalDate.of(2021, rdv.getStart().getMonth(), rdv.getStart().getDate());
            LocalTime time = LocalTime.of(rdv.getStart().getHours(), rdv.getStart().getMinutes());    
            destist.changeStartDate(date);
            destist.changeStartTime(time);
            time = LocalTime.of(rdv.getEnd().getHours(), rdv.getEnd().getMinutes());
            destist.changeEndTime(time);
            date = LocalDate.of(2021, rdv.getEnd().getMonth(), rdv.getEnd().getDate());
            destist.changeEndDate(date);
            classes.addEntries(destist);
                
////                destist = new Entry<>("b2 francais");
////                date = LocalDate.of(2021, Month.APRIL, 2);
////                destist.changeStartDate(date);
////                time = LocalTime.of(13, 15);
////                destist.changeStartTime(time);
////                time = LocalTime.of(15, 15);
////                destist.changeEndTime(time);
////                date = LocalDate.of(2021, Month.APRIL, 2);
////                destist.changeEndDate(date);
//                
//                
//               classes.addEntries(destist);
         }
                classes.setStyle(Style.STYLE1);
        calendar.getCalendarSources().addAll(myCalendarSource);

        calendar.setRequestedTime(LocalTime.now());


        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendar.setToday(LocalDate.now());
                        calendar.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };




        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        calendar.showWeekPage();
        pnlHost.getChildren().add(calendar);
    }

    
}

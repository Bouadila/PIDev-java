/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_Reclamation;

import Services.ClientService;
import Services.ServerService;
import interfaces.NetworkConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Djap_ii
 */
public class ChatController implements Initializable {

    @FXML
    private TextArea taMessages;
    @FXML
    private TextField tfInput;
    @FXML
    private Button btnSend;
    
    private boolean isServer = true;
    private NetworkConnection connection = isServer ? createServer() : createClient();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection.startConnection();
        } catch (Exception ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleButtonSend(ActionEvent event) {
        String message = isServer ? "Server : " : "Client : ";
        message += tfInput.getText();
        tfInput.clear();
        
        taMessages.appendText(message + "\n");
        
        try {
            connection.send(message);
        } catch (Exception ex) {
            taMessages.appendText("Failed To Send \n");
        }
    }

    private ServerService createServer() {
        return new ServerService(5555,data->{
            Platform.runLater(()-> {
                taMessages.appendText(data.toString() + "\n");
            });
        });
    }

    private ClientService createClient() {
        return new ClientService("127.0.0.1", 5555, data->{
            Platform.runLater(()-> {
                taMessages.appendText(data.toString() + "\n");
            });
        });
    }
    
    public void stop() throws Exception {
        connection.closeConnection();
    }

}

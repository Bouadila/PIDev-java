/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import interfaces.NetworkConnection;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 *
 * @author Djap_ii
 */
public class ClientService extends NetworkConnection {

    private String ip;
    private int port;
    
    public ClientService(String ip , int port , Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }
    
}
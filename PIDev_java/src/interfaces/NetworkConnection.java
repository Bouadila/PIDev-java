/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 *
 * @author Djap_ii
 */
public abstract class NetworkConnection {
    
    private ConnectionThread conThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;
    
    public NetworkConnection(Consumer<Serializable> onReceiveCallback){
        this.onReceiveCallback = onReceiveCallback;
        conThread.setDaemon(true);
    }
    
    public void startConnection() throws Exception{
        conThread.start();
    }
    
    public void send(Serializable data) throws Exception{
        conThread.out.writeObject(data);
    }
    
    public void closeConnection() throws Exception{
        conThread.socket.close();
    }
    
    protected abstract boolean isServer();
    protected abstract String getIP();
    protected abstract int getPort();
    
    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;
        
        @Override
        public void run(){
            try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null; 
                    Socket socket = isServer() ? server.accept() : new Socket(getIP(),getPort());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                
                this.socket = socket;
                this.out = out;
                socket.setTcpNoDelay(true);
                
                while (true){
                    Serializable data = (Serializable) in.readObject();
                    onReceiveCallback.accept(data);
                }
            }
            catch (Exception e){
                onReceiveCallback.accept("connection Closed");
            }
        }
    }
}
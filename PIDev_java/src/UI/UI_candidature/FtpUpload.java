/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.UI_candidature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author A.L.I.C.E
 */
public class FtpUpload {
    	String server = "172.20.10.12";
        int port = 21;
        String user = "user";
        String pass = "123456789";
        
        
        public void Upload(String url,String name){
            FTPClient ftpClient = new FTPClient();
            try{
                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                System.out.println("Connected to FTP Server");
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                File firstLocalFile = new File(url);
                
                String firstRemoteFile = name;
                boolean done;
                try (InputStream inputStream = new FileInputStream(firstLocalFile)) {
                    System.out.println("Start uploading PDF file");
                    done = ftpClient.storeFile(firstRemoteFile, inputStream);
                }
                if (done) {
                    System.out.println("The PDF file is uploaded successfully.");
                }
            }catch(IOException io){
                System.out.println("Not connected to FTP Server");
            }
        }
}

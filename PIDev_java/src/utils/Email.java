/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class Email extends Thread {

    public String destination;
    public int randomCodee1;

    @Override
    public void run() {
        try {
            super.run(); //To change body of generated methods, choose Tools | Templates.
            SendEmail(destination, randomCodee1);
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SendEmail(String destination, int randomCodee) {
        try {
            String host = "smtp.gmail.com";
            String from = "pidevtestad@gmail.com";
            String password = "pidevtestad123456";
            String to = destination;
            String sub = "Activer compte ";
            String msg = "Ton code est : " + randomCodee;

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            //get Session
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            System.out.println("message en cour successfully");

            //compose message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            message.setSubject(sub);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            String content = "<p>"+msg+"</p>";
            messageBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            System.out.println("message avant send");
            //send message
            Transport.send(message);
            System.out.println("message apres ");
        }  catch (MessagingException ex) {
            System.out.println("message didn't sent successfully");
            
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ghofrane
 */
public class JavaMailUtil {

public void sendMail (String recepient) throws MessagingException{
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
 String username = "adhunt1235@gmail.com";
 String password = "adminhunt1235";
Session session = Session.getInstance(props,new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
Message message =  prepareMessage ( session, username,recepient );
Transport.send(message);
System.out.println("Message_envoye");

}
public Message prepareMessage (Session session,String username,String recepient){
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(username));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(recepient));
message.setSubject("block");
message.setText("We've received complaints about your profile. You didn't respect our gidelines. As a result you are blocked");
return message ;
} catch (MessagingException e) {
throw new RuntimeException(e);
} }
public void unsendMail (String recepient) throws MessagingException{
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
 String username = "adhunt1235@gmail.com";
 String password = "adminhunt1235";
Session session = Session.getInstance(props,new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
Message message =  uprepareMessage ( session, username,recepient );
Transport.send(message);
System.out.println("Message_envoye");

}
public Message uprepareMessage (Session session,String username,String recepient){
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(username));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(recepient));
message.setSubject("unblock");
message.setText("You vowed to respect our gidelines . As a result we unblocked you");
return message ;
} catch (MessagingException e) {
throw new RuntimeException(e);
} }

}

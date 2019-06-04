package msg.user.mail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class Email {
   private String emailFrom;
   private String emaiTo;
   private String subject;
   private String message;

    public Email(String emailFrom, String emaiTo, String subject, String message) {
        this.emailFrom = emailFrom;
        this.emaiTo = emaiTo;
        this.subject = subject;
        this.message = message;
    }

    public void sendMail(){
//        // Assuming you are sending email from localhost
//        String host = "localhost";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", "127.0.0.1");
//        properties.put("mail.smtp.port", "8080");

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(emailFrom));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emaiTo));

            // Set Subject: header field
            mimeMessage.setSubject(subject);

            // Now set the actual message
            mimeMessage.setText(message);

            // Send message
            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    static Properties properties = new Properties();
    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

    }
    public static void main(String[] args) {
        String returnStatement = null;
        try {
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("domaintest2908@gmail.com", "password");
                }
            };
            Session session = Session.getInstance(properties, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("domaintest2908@gmail.com"));//From
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("purwar389@gmail.com"));//To
            message.setSentDate(new Date());
            message.setSubject("Test Mail");
            message.setText("Hi");
            returnStatement = "The e-mail was sent successfully";
            System.out.println(returnStatement);
            Transport.send(message);
        } catch (Exception e) {
            returnStatement = "error in sending mail";
            e.printStackTrace();
        }
    }

    public static void sendEmailToRecipient(String from, String to, String subject, String text, String password){

            String returnStatement = null;
            try {
                Authenticator auth = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("domaintest2908@gmail.com", "password");
                    }
                };
                Session session = Session.getInstance(properties, auth);
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("domaintest2908@gmail.com"));//From
                message.setRecipient(Message.RecipientType.TO, new InternetAddress("purwar389@gmail.com"));//To
                message.setSentDate(new Date());
                message.setSubject("Test Mail");
                message.setText("Hi");
                returnStatement = "The e-mail was sent successfully";
                System.out.println(returnStatement);
                Transport.send(message);
            } catch (Exception e) {
                returnStatement = "error in sending mail";
                e.printStackTrace();
            }

    }
}
package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendingMail {
    public static void sendingEmail(String receiver) throws Exception {
        System.out.println("Waiting. It's being sent.....");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        String UserName = "anaalcaza409@gmail.com";
        String Password = "12345ana!";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UserName, Password);
            }
        });
        Message message = createMessage(session, UserName, receiver);

        Transport.send(message);
        System.out.println("Message sent successfully!");
    }

    private static Message createMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Booking hotel offers");
            message.setText("HI, \n Do not reply to this message");
            return message;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }
}



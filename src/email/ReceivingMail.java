package email;

import com.sun.mail.pop3.POP3Store;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import java.util.Properties;

public class ReceivingMail {

    public static void receivingEmail(String pop3Host, String storeType, String UserName, String Password) {
        try {

            Properties properties = new Properties();


            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.pop3.socketFactory.fallback", "false");
            properties.put("mail.pop3.socketFactory.port", 995);
            properties.put("mail.pop3.port", 995);
            properties.put("mail.pop3.username", UserName);
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.ssl.protocols", "TLSv1.2");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.ssl.enable", true);
            properties.put("mail.pop3.ssl.trust", "*");


            Session emailSession = Session.getDefaultInstance(properties);

            //  create the POP3 store object and connect to the pop server
            POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
            emailStore.connect(UserName, Password);

            //create the folder object and open it
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //  take the messages from the folder into an array and print them
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("******************************");
                System.out.println("No. " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            emailFolder.close(false);
            emailStore.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
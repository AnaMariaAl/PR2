package email;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        String username = "anaalcaza409@gmail.com";
        String password = "12345ana!";
        String host = "pop.gmail.com";
        String mailStoreType = "pop3";

        System.out.println("Send or receive? ");

        Scanner scanner = new Scanner(System.in);

        String answer = scanner.nextLine();

        if (answer.equals("send")) {
            SendingMail.sendingEmail("anaalcaza409@gmail.com");
        } else {
            ReceivingMail.receivingEmail(host, mailStoreType, username, password);
        }
    }
}

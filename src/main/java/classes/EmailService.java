package classes;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

    public void sendEmail(String to, String subject, String content) {
        final String username = "vuzzeh@gmail.com"; // Sender email account
        final String password = "zaxf lmav tyrl sqxd"; // App-specific password

        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new org.apache.commons.mail.DefaultAuthenticator(username, password));
            email.setStartTLSRequired(true);
            email.setFrom(username);
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(to);
            email.send();

            System.out.println("Email sent.");

        } catch (EmailException e) {
            e.printStackTrace();
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}

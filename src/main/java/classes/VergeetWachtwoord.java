package classes;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class VergeetWachtwoord {

    public static void wachtwoordVergeten(String email) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");

        Document document = collection.find(eq("email", email)).first();
        if (document != null) {
            String subject = "Wachtwoord vergeten";
            String content = "Beste " + document.getString("gebruikersnaam") + ",\n\n" +
                    "U heeft onze wachtwoordherstelservice gebruikt om uw huidige wachtwoord op te vragen.\n" +
                    "Uw wachtwoord is: " + document.getString("password") + "\n\n" +
                    "Met vriendelijke groet,\n" +
                    "System42 Support Team";

            EmailService emailService = new EmailService();
            emailService.sendEmail(email, subject, content);
        }

    }
}

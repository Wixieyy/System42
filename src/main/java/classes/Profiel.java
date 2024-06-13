package classes;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.system42.ProfielController;

import java.util.Locale;

import static classes.Login.gebruikerID;
import static com.mongodb.client.model.Filters.eq;

public class Profiel implements VeranderTaal{


    public static void changeLanguage(Locale locale) {
        LocalizationHelper.setLocale(locale);
        ProfielController controller = new ProfielController();
        controller.setLanguage(locale);
    }

    public static void changeProfile(boolean checkPassword, String wachtwoordField, String herhaalWachtwoordField, String gebruikersnaamField, String emailadresField, String beroepField, String afdelingField) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");

        if (wachtwoordField.equals(herhaalWachtwoordField)) {
            Document document = collection.find(eq("_id", gebruikerID)).first();
            assert document != null;
            if (!gebruikersnaamField.isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("gebruikersnaam", gebruikersnaamField)));
            }
            if (!emailadresField.isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("email", emailadresField)));
            }
            if (!beroepField.isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("beroep", beroepField)));
            }
            if (!afdelingField.isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("afdeling", afdelingField)));
            }
            if (!wachtwoordField.isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("password", wachtwoordField)));
            } else {
                System.out.println("Passwords are not equal");
                checkPassword = false;
            }
        }
    }
    @Override
    public void updateTaal(){
        Locale locale = null;
        ProfielController profielController = new ProfielController();
        profielController.setLanguage(locale);
    }
}


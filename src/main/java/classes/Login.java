package classes;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class Login {
    public static ObjectId gebruikerID;

    public static void setGebruikerID(ObjectId gebruikerID) {
        Login.gebruikerID = gebruikerID;
    }

    public boolean login(String email, String password) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("email");
        if (collection != null) {
            Document document = collection.find(eq("email", email)).first();
            if (document != null) {
                boolean passwordMatches = password.equals(document.getString("password"));
                if (passwordMatches) {
                    setGebruikerID(document.getObjectId("_id"));
                }
                return passwordMatches;
            }
        }
        return false;
    }

    public void wachtwoordVergeten(String gebruikersWachtwoord) {

    }
}
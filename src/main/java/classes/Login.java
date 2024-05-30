package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Login {
    protected Gebruiker gebruiker;

    public void wachtwoordVergeten(String gebruikerWachtwoord) {
        // Your logic here
    }

    public boolean login(String email, String password) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
            Document document = collection.find(eq("email", email)).first();
            if (document != null) {
                return password.equals(document.getString("password"));
            }
        }
        return false;
    }
}

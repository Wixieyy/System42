package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class Login {
    public static ObjectId gebruikerID;

    public static void setGebruikerID(ObjectId gebruikerID) {
        Login.gebruikerID = gebruikerID;
    }

    public boolean login(String email, String password) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
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
}
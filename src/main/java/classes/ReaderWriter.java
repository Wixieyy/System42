package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class ReaderWriter {
    private static MongoClient mongoClient = null;

    /* Need to be on the same network (111.111.111.x) to be able to remotely read/write from database, I ain't setting up no port forwarding right now */
    public static MongoCollection<Document> establishDatabaseConnection() {
        String uri = "mongodb://localhost:27017";
        MongoCollection<Document> collection = null;
        if (mongoClient == null) {
            mongoClient = MongoClients.create(uri);
        }
        MongoDatabase database = mongoClient.getDatabase("login-gegevens");
        collection = database.getCollection("email");
        return collection;
    }

    public void LocalReader() {

    }

    public Scanner LocalWriter() {

        return null;
    }

    public void ExternReader() {

    }

    public Scanner ExternWriter() {

        return null;
    }
}
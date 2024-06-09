package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReaderWriter {
    private static JSONObject responses;
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

    public static boolean isDatabaseConnected() {
        try {
            MongoCollection<Document> collection = establishDatabaseConnection();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static void OfflineJsonLoader() {
        JSONParser parser = new JSONParser();
        try (InputStreamReader reader = new InputStreamReader(ReaderWriter.class.getResourceAsStream("/org/example/system42/responses.json"))) {
            responses = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String OfflineJsonReader(String userInput) {
        for (Object key : responses.keySet()) {
            String keyword = (String) key;
            if (userInput.toLowerCase().contains(keyword.toLowerCase())) {
                return (String) responses.get(keyword);
            }
        }
        return "Sorry, I don't have that in my database!";
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
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

import static com.mongodb.client.model.Filters.eq;

public class ReaderWriter {
    private static JSONObject responses;
    private static MongoClient mongoClient = null;

    public static MongoDatabase establishDatabaseConnection() {
        String uri = "mongodb://localhost:27017";
        MongoCollection<Document> collection = null;
        if (mongoClient == null) {
            mongoClient = MongoClients.create(uri);
        }
        return mongoClient.getDatabase("");
    }

    public static boolean isDatabaseConnected() {
        try {
            MongoCollection<Document> collection = establishDatabaseConnection().getCollection("email");
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
            if (userInput.toLowerCase().contains(keyword.toLowerCase())) {
                MongoCollection<Document> collection = establishDatabaseConnection().getCollection("externe-gegevens");
                Document document = collection.find(eq("term", userInput)).first();
                if (!document.isEmpty()) {
                    return document.getString("definition");
                }
            }
        }
        return "Sorry, I don't have that in my database!";
    }

    public Scanner LocalWriter() {

        return null;
    }

    public static MongoCollection<Document> externReader() {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("externe-gegevens");
        return collection;
    }

    public Scanner externWriter() {

        return null;
    }
}
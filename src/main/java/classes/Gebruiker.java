package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class Gebruiker {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private String gebruikerNaam;
    private String gebruikerEmail;
    private String gebruikerWachtwoord;
    private int gebruikerID;
    

    public Gebruiker(String gebruikerNaam, String gebruikerEmail, String gebruikerWachtwoord) {
        this.gebruikerNaam = gebruikerNaam;
        this.gebruikerEmail = gebruikerEmail;
        this.gebruikerWachtwoord = gebruikerWachtwoord;
    }

    public static void createAccount(String gebruikersNaam, String email, String wachtwoord, String herhaalWachtwoord){
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", email)).first();
            if (document == null) {
                if (wachtwoord.equals(herhaalWachtwoord)) {
                    collection.insertOne(new Document("gebruikersnaam", gebruikersNaam)
                            .append("email", email)
                            .append("password", wachtwoord)
                            .append("beroep", "Onbekend")
                            .append("afdeling", "Onbekend"));
                    System.out.println("account created");
                }
                else {
                    System.out.println("Passwords dont match");
                }
            }
            else {
                System.out.println("Account already exists");
            }
        }
    }

}

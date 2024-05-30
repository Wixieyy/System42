package classes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;

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


    public String getGebruikerNaam() {
        return gebruikerNaam;
    }

    public void setGebruikerNaam(String gebruikerNaam) {

    }

    public String getGebruikerEmail() {
        return gebruikerEmail;
    }

    public void setGebruikerEmail() {

    }

    public String getGebruikerWachtwoord() {
        return gebruikerWachtwoord;
    }

    public void setGebruikerWachtwoord(String gebruikerWachtwoord) {

    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(int gebruikerID) {

    }
}

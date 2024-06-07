package classes;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    public String getGebruikerNaam() {
        return "testing";
    }

    public void setGebruikerNaam(String gebruikerNaam) {

    }

    public String getGebruikerEmail() {
        return gebruikerEmail;
    }

    public void setGebruikerEmail(String email) {

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

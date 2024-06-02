package org.example.system42;

import classes.Gebruiker;
import classes.Login;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class AccountMakenController {
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField wachtwoordField;
    @FXML
    private PasswordField herhaalWachtwoordField;

    public void createAccount(ActionEvent event) throws IOException {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
            Document document = collection.find(eq("email", emailField.getText())).first();
            if (document == null) {
                if (wachtwoordField.getText().equals(herhaalWachtwoordField.getText())) {
                    collection.insertOne(new Document("gebruikersnaam", gebruikersnaamField.getText())
                            .append("email", emailField.getText())
                            .append("password", wachtwoordField.getText()));
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

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

            Parent newTemplate = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newTemplate, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
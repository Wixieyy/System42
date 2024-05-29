package org.example.system42;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

public class AccountMakenController {

    protected void createAccount() {
//        String uri = "mongodb://localhost:27017";
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
//            MongoCollection<Document> collection = database.getCollection("email");
//            if (!emailField.getText().equals(collection.find("email", emailField.getText()))) {
//                if (passwordField1.getText().equals(passwordField2.getText())) {
//                    collection.insertOne(new Document("gebruikersnaam", gebruikersnaamField.getText())
//                            .append("email", emailField.getText())
//                            .append("password", passwordField1.getText()));
//                }
//                else {
//                    /*passwords dont match*/
//                }
//            }
//            else {
//                /*account already exists or email already used*/
//            }
//        }
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

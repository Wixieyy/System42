package org.example.system42;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class HelloController {
    @FXML
    protected void onMaakAccountButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountaanmaken.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800 , 600));
        stage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }


    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    protected void checkAccount() {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
            Document document = collection.find(eq("email", emailField.getText())).first();
            if (document != null) {
                if (passwordField.getText().equals(document.getString("password"))) {
                    emailField.setText("nice");
                    passwordField.setText(document.getString("password"));
                }
            } else {
                emailField.setText("None found");
                passwordField.setText("");
            }
        }
    }

    @FXML
    AnchorPane rootPane;

    public void requestFocusOnRoot() {
        rootPane.requestFocus();
    }
}
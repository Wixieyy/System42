package org.example.system42;

import classes.EmailService;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

import static classes.Login.gebruikerID;
import static classes.ReaderWriter.establishDatabaseConnection;
import static com.mongodb.client.model.Filters.eq;

public class WachtwoordVergetenController {
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailField;

    @FXML
    protected void onLoginpaginaButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onOpslaanButtonClick (ActionEvent event) throws IOException {
        MongoCollection<Document> collection = establishDatabaseConnection();
        Document document = collection.find(eq("email", emailField.getText())).first();

        String to = emailField.getText();
        String subject = "Wachtwoord vergeten";
        String content = "Beste " + document.getString("gebruikersnaam") + ",\n\n" +
                "Uw wachtwoord is: " + document.getString("password") + "\n\n" +
                "Met vriendelijke groet,\n" +
                "Het support team";

        EmailService emailService = new EmailService();
        emailService.sendEmail(to, subject, content);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }
}

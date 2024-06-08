package org.example.system42;

import classes.Gebruiker;
import classes.Login;
import classes.ReaderWriter;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class ProfielController {
    @FXML
    private TextField accountIDField;
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField beroepField;
    @FXML
    private TextField afdelingField;
    @FXML
    private Button veranderProfielButton;

    @FXML
    public void initialize() {
        populateFields(Login.gebruikerID);
        accountIDField.setStyle("-fx-font-size: 16px;");
        gebruikersnaamField.setStyle("-fx-font-size: 16px;");
        emailField.setStyle("-fx-font-size: 16px;");
        beroepField.setStyle("-fx-font-size: 16px;");
        afdelingField.setStyle("-fx-font-size: 16px;");
        veranderProfielButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
    }

    public void populateFields(ObjectId gebruikerID) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection();
        Document document = collection.find(eq("_id", gebruikerID)).first();
        if (document != null) {
            accountIDField.setText(String.valueOf(document.get("_id")));
            gebruikersnaamField.setText(document.getString("gebruikersnaam"));
            emailField.setText(document.getString("email"));
            beroepField.setText(document.getString("beroep"));
            afdelingField.setText(document.getString("afdeling"));
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1000, 720));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onProfielBewerkenButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profielbewerken-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onLogoutButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }


    @FXML
    public void taalEnglish(ActionEvent actionEvent) {

        System.out.println("Language set to English");
    }

    @FXML
    public  void taalNederlands(ActionEvent actionEvent) {

        System.out.println("Taal veranderd naar Nederlands");
    }

}

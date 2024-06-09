package org.example.system42;

import classes.Login;
import classes.ReaderWriter;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

import static classes.Login.gebruikerID;
import static com.mongodb.client.model.Filters.eq;

public class ProfielBewerkenController {
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailadresField;
    @FXML
    private TextField beroepField;
    @FXML
    private TextField afdelingField;
    @FXML
    private PasswordField wachtwoordField;
    @FXML
    private PasswordField herhaalWachtwoordField;
    @FXML
    private TextField wachtwoordTextField;
    @FXML
    private TextField herhaalWachtwoordTextField;
    @FXML
    private CheckBox toonWachtwoordCheckBox;
    @FXML
    private Button veranderProfielButton;

    @FXML
    public void initialize() {
        populateFields(Login.gebruikerID);
        gebruikersnaamField.setStyle("-fx-font-size: 16px;");
        beroepField.setStyle("-fx-font-size: 16px;");
        afdelingField.setStyle("-fx-font-size: 16px;");
        wachtwoordField.setStyle("-fx-font-size: 16px;");
        herhaalWachtwoordField.setStyle("-fx-font-size: 16px;");
        wachtwoordTextField.setStyle("-fx-font-size: 16px;");
        herhaalWachtwoordTextField.setStyle("-fx-font-size: 16px;");
        toonWachtwoordCheckBox.setStyle("-fx-font-size: 14px;");
        veranderProfielButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
    }

    public void populateFields(ObjectId gebruikerID) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection();
        Document document = collection.find(eq("_id", gebruikerID)).first();
        assert document != null;
        gebruikersnaamField.setText(document.getString("gebruikersnaam"));
        emailadresField.setText(document.getString("email"));
        beroepField.setText(document.getString("beroep"));
        afdelingField.setText(document.getString("afdeling"));
        wachtwoordField.setText(document.getString("password"));
}

    @FXML
    protected void onOpslaanButton(ActionEvent event) throws IOException {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection();

        if (wachtwoordField.getText().equals(herhaalWachtwoordField.getText())) {
            Document document = collection.find(eq("_id", gebruikerID)).first();
            assert document != null;
            if (!gebruikersnaamField.getText().isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("gebruikersnaam", gebruikersnaamField.getText())));
            }
            if (!emailadresField.getText().isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("email", emailadresField.getText())));
            }
            if (!beroepField.getText().isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("beroep", beroepField.getText())));
            }
            if (!afdelingField.getText().isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("afdeling", afdelingField.getText())));
            }
            if (!wachtwoordField.getText().isEmpty()) {
                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("password", wachtwoordField.getText())));
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));
            Parent newTemplate = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newTemplate, 800, 600));
            stage.show();
            stage.centerOnScreen();
        }
        else {
            System.out.println("Passwords are not equal");
        }
    }

    @FXML
    protected void onToonWachtwoordCheckBoxClicked() {
        if (toonWachtwoordCheckBox.isSelected()) {
            wachtwoordTextField.setText(wachtwoordField.getText());
            wachtwoordField.setOpacity(0);
            wachtwoordField.setDisable(true);
            wachtwoordTextField.setDisable(false);
            wachtwoordTextField.setOpacity(1);

            herhaalWachtwoordTextField.setText(herhaalWachtwoordField.getText());
            herhaalWachtwoordField.setOpacity(0);
            herhaalWachtwoordField.setDisable(true);
            herhaalWachtwoordTextField.setDisable(false);
            herhaalWachtwoordTextField.setOpacity(1);
        } else {
            wachtwoordField.setText(wachtwoordTextField.getText());
            wachtwoordTextField.setOpacity(0);
            wachtwoordTextField.setDisable(true);
            wachtwoordField.setDisable(false);
            wachtwoordField.setOpacity(1);

            herhaalWachtwoordField.setText(herhaalWachtwoordTextField.getText());
            herhaalWachtwoordTextField.setOpacity(0);
            herhaalWachtwoordTextField.setDisable(true);
            herhaalWachtwoordField.setDisable(false);
            herhaalWachtwoordField.setOpacity(1);
        }
    }

    @FXML
    protected void onBackButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onVerwijderAccountButtonClick(ActionEvent event) throws IOException {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection();
        collection.deleteOne(eq("_id", gebruikerID));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }
}

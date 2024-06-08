package org.example.system42;

import classes.ReaderWriter;
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
import org.bson.types.ObjectId;

import java.io.IOException;

import static classes.Login.gebruikerID;
import static com.mongodb.client.model.Filters.eq;

public class ProfielBewerkenController {
    @FXML
    private TextField gebruikersnaamField;
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
    public void initialize() {
        populateFields(gebruikerID);
    }

    public void populateFields(ObjectId gebruikerID) {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection();
        Document document = collection.find(eq("_id", gebruikerID)).first();
        if (document != null) {
            gebruikersnaamField.setText(document.getString("gebruikersnaam"));
        }
    }

    @FXML
    protected void onOpslaanButton(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
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
    protected void onLogoutButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

}

package org.example.system42;

import classes.Login;
import classes.Profiel;
import classes.ReaderWriter;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import classes.LocalizationHelper;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
    private Text titelText;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label repeatPasswordLabel;
    @FXML
    private Label jobLabel;
    @FXML
    private Label passwordLabel;

    @FXML
    private Button back;
    @FXML
    private Button verwijderAccountButton;
    @FXML
    private Label afdeling;

    @FXML
    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());

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
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");
        Document document = collection.find(eq("_id", gebruikerID)).first();
        assert document != null;
        gebruikersnaamField.setText(document.getString("gebruikersnaam"));
        emailadresField.setText(document.getString("email"));
        beroepField.setText(document.getString("beroep"));
        afdelingField.setText(document.getString("afdeling"));
        wachtwoordField.setText(document.getString("password"));

    }

    @FXML
    public void setLanguage(Locale locale) {

        bundle = ResourceBundle.getBundle("languages.lan", locale);

        titelText.setText(bundle.getString("label.hoofdtext"));
        usernameLabel.setText(bundle.getString("label.gebruikersnaam"));
        emailLabel.setText(bundle.getString("label.emailLabel"));
        jobLabel.setText(bundle.getString("label.profession"));
        passwordLabel.setText(bundle.getString("label.password"));
        repeatPasswordLabel.setText(bundle.getString("label.repeatpassword"));
        veranderProfielButton.setText(bundle.getString("button.saveChanges"));
        back.setText(bundle.getString("button.back"));
        toonWachtwoordCheckBox.setText(bundle.getString("checkbox.show_password"));
        verwijderAccountButton.setText(bundle.getString("button.deleteAccount"));
        afdeling.setText(bundle.getString("label.afdeling"));

    }

    @FXML
    protected void onOpslaanButton(ActionEvent event) throws IOException {
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");

        boolean checkPassword = true;
        Profiel.changeProfile(checkPassword, wachtwoordField.getText(), herhaalWachtwoordField.getText(), gebruikersnaamField.getText(),emailadresField.getText(),beroepField.getText(),afdelingField.getText());

        if (checkPassword) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));
            Parent newTemplate = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newTemplate, 800, 600));
            stage.show();
            stage.centerOnScreen();
        } else {
            System.out.println("error");
        }
    }
//        else
//        {
//        System.out.println("Passwords are not equal");
//        break;
//    }



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
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");
        collection.deleteOne(eq("_id", gebruikerID));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }
}


//        if (wachtwoordField.getText().equals(herhaalWachtwoordField.getText())) {
//            Document document = collection.find(eq("_id", gebruikerID)).first();
//            assert document != null;
//            if (!gebruikersnaamField.getText().isEmpty()) {
//                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("gebruikersnaam", gebruikersnaamField.getText())));
//            }
//            if (!emailadresField.getText().isEmpty()) {
//                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("email", emailadresField.getText())));
//            }
//            if (!beroepField.getText().isEmpty()) {
//                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("beroep", beroepField.getText())));
//            }
//            if (!afdelingField.getText().isEmpty()) {
//                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("afdeling", afdelingField.getText())));
//            }
//            if (!wachtwoordField.getText().isEmpty()) {
//                collection.updateOne(eq("_id", gebruikerID), new Document("$set", new Document("password", wachtwoordField.getText())));
//            }
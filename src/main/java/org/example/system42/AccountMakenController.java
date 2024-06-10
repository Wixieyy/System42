package org.example.system42;

import classes.Gebruiker;
import classes.LocalizationHelper;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
    @FXML
    private TextField wachtwoordTextField;
    @FXML
    private TextField herhaalWachtwoordTextField;
    @FXML
    private CheckBox toonWachtwoordCheckBox;
    @FXML
    private Button maakAccountButton;
    @FXML
    private Label kopText;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label repeatPassword;

    @FXML
    private ResourceBundle bundle;


    @FXML
    private void initialize() {

        setLanguage(LocalizationHelper.getCurrentLocale());

        gebruikersnaamField.setStyle("-fx-font-size: 16px;");
        emailField.setStyle("-fx-font-size: 16px;");
        wachtwoordField.setStyle("-fx-font-size: 16px;");
        herhaalWachtwoordField.setStyle("-fx-font-size: 16px;");
        wachtwoordTextField.setStyle("-fx-font-size: 18px;");
        herhaalWachtwoordTextField.setStyle("-fx-font-size: 18px;");
        toonWachtwoordCheckBox.setStyle("-fx-font-size: 14px;");
        maakAccountButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
    }
    @FXML
    public void setLanguage(Locale locale) {

        bundle = ResourceBundle.getBundle("languages.lan", locale);

        passwordLabel.setText(bundle.getString("label.password"));
        toonWachtwoordCheckBox.setText(bundle.getString("checkbox.show_password"));
        repeatPassword.setText(bundle.getString("label.repeatpassword"));
        kopText.setText(bundle.getString("label.createaccount"));
        usernameLabel.setText(bundle.getString("label.gebruikersnaam"));
        toonWachtwoordCheckBox.setText(bundle.getString("checkbox.show_password"));
        maakAccountButton.setText(bundle.getString("button.maakaccount"));

    }

    public void createAccount(ActionEvent event) throws IOException {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", emailField.getText())).first();
            if (document == null) {
                if (wachtwoordField.getText().equals(herhaalWachtwoordField.getText())) {
                    collection.insertOne(new Document("gebruikersnaam", gebruikersnaamField.getText())
                            .append("email", emailField.getText())
                            .append("password", wachtwoordField.getText())
                            .append("beroep", "Onbekend")
                            .append("afdeling", "Onbekend"));
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
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
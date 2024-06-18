package org.example.system42;

import classes.EmailService;
import classes.ReaderWriter;
import classes.VergeetWachtwoord;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import classes.LocalizationHelper;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


import static classes.Login.gebruikerID;
import static classes.ReaderWriter.establishDatabaseConnection;
import static com.mongodb.client.model.Filters.eq;

public class WachtwoordVergetenController {
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailField;
    @FXML
    private Text forgotPasswordText;

    @FXML
    private TextField usernameField;

    @FXML
    private Button opslaanButton;

    @FXML
    private Button loginButton;

    @FXML
    private Text emailText;

    @FXML
    private Label gebruikersnaamLabel;


    private ResourceBundle bundle;

    public void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());
        gebruikersnaamField.setStyle("-fx-font-size: 16px;");
        emailField.setStyle("-fx-font-size: 16px;");
        emailField.setStyle("-fx-font-size: 16px;");
        opslaanButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff;");
    }
    @FXML
    public void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("languages.lan", locale);
        forgotPasswordText.setText(bundle.getString("text.titel"));
        emailText.setText(bundle.getString("text.instructions"));
        opslaanButton.setText(bundle.getString("button.send"));
        loginButton.setText(bundle.getString("button.login_page"));
        gebruikersnaamLabel.setText(bundle.getString("label.gebruikersnaam"));
    }

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
        VergeetWachtwoord.wachtwoordVergeten(emailField.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }
}

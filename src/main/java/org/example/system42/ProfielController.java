package org.example.system42;

import classes.LocalizationHelper;
import classes.TaalManager;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class ProfielController {
    @FXML
    private TextField gebruikersnaamField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField beroepField;
    @FXML
    private TextField afdelingField;
    @FXML
    private Text profielText;
    @FXML
    private TextField accountIDField;
    @FXML
    private Button veranderProfielButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private MenuButton taalSlider;
    @FXML
    private MenuItem itemNL;
    @FXML
    private MenuItem itemEN;
    @FXML
    private Label accountID;
    @FXML
    private Label gebruikersnaam;
    @FXML
    private Label emailAdres;
    @FXML
    private Label beroep;
    @FXML
    private Label afdeling;

    private ResourceBundle bundle;

    public void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());
        //populateFields(Login.gebruikerID);
    }

    private void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("languages.lan", locale);
        profielText.setText(bundle.getString("label.profile"));
        gebruikersnaam.setText(bundle.getString("label.gebruikersnaam"));
        beroep.setText(bundle.getString("label.profession"));
        afdeling.setText(bundle.getString("label.department"));
        veranderProfielButton.setText(bundle.getString("button.edit_profile"));
        backButton.setText(bundle.getString("button.back"));
        logoutButton.setText(bundle.getString("button.logout"));
        taalSlider.setText(bundle.getString("menubutton.taal"));
        itemNL.setText(bundle.getString("menuitem.dutch"));
        itemEN.setText(bundle.getString("menuitem.english"));
    }

    public void populateFields(ObjectId gebruikerID) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
            Document document = collection.find(eq("_id", gebruikerID)).first();
            if (document != null) {
                gebruikersnaamField.setText(gebruikerID.toString());
                gebruikersnaamField.setText(document.getString("gebruikersnaam"));
                emailField.setText(document.getString("email"));
                beroepField.setText("Onbekend");
                afdelingField.setText("Onbekend");
            }
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.lan", LocalizationHelper.getCurrentLocale()));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1000, 720));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onProfielbewerkenButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profielbewerken-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.lan", LocalizationHelper.getCurrentLocale()));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.lan", LocalizationHelper.getCurrentLocale()));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    public void taalEnglish() {
        System.out.println("Language set to English");
        changeLanguage(new Locale("en"));
    }

    @FXML
    public void taalNederlands() {
        System.out.println("Taal veranderd naar Nederlands");
        changeLanguage(new Locale("nl"));
    }

    private void changeLanguage(Locale locale) {
        LocalizationHelper.setLocale(locale);
        setLanguage(locale);
    }
}

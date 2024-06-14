package org.example.system42;

import classes.Profiel;
import classes.LocalizationHelper;
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
    private  Button veranderProfielButton;
    @FXML
    private  Text profielText;
    @FXML
    private  Button backButton;
    @FXML
    private  Button logoutButton;
    @FXML
    private  MenuButton taalSlider;
    @FXML
    private  MenuItem itemEN;
    @FXML
    private MenuItem itemNL;
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

    @FXML
    public void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());
        populateFields(Login.gebruikerID);
        accountIDField.setStyle("-fx-font-size: 16px;");
        gebruikersnaamField.setStyle("-fx-font-size: 16px;");
        emailField.setStyle("-fx-font-size: 16px;");
        beroepField.setStyle("-fx-font-size: 16px;");
        afdelingField.setStyle("-fx-font-size: 16px;");
        veranderProfielButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
    }

    public void setLanguage(Locale locale) {

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
        MongoCollection<Document> collection = ReaderWriter.establishDatabaseConnection().getCollection("login-credentials");
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
        Profiel.changeLanguage(new Locale("en"));
    }

    @FXML
    public  void taalNederlands(ActionEvent actionEvent) {
        System.out.println("Taal veranderd naar Nederlands");
        Profiel.changeLanguage(new Locale("nl"));
    }
//    private void changeLanguage(Locale locale) {
//        LocalizationHelper.setLocale(locale);
//        setLanguage(locale);
//    }
}

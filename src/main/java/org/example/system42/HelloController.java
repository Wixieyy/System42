package org.example.system42;

import classes.LocalizationHelper;
import classes.Login;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import org.bson.Document;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.mongodb.client.model.Filters.eq;

public class HelloController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox toonWachtwoordCheckBox;
    @FXML
    private Hyperlink wachtwoordVergetenLink;
    @FXML
    private Button loginButton;
    @FXML
    private Button signUpButton;
    @FXML
    private Rectangle color1;
    @FXML
    private Rectangle color2;
    @FXML
    private TextField passwordTextField;

    @FXML
    private Label loginTitleLabel;

    @FXML
    private Label helloLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button forgotPasswordButton;

    private ResourceBundle bundle;
    @FXML
    private ComboBox<String> languageSelector;

    private Locale currentLocale = Locale.getDefault();

    @FXML
    private Label titleLabel;

    @FXML
    private Label hallo;

    @FXML
    private Label welkom;


    @FXML
    private void initialize() {

        setLanguage(LocalizationHelper.getCurrentLocale());
        emailField.setStyle("-fx-font-size: 16px;");
        passwordField.setStyle("-fx-font-size: 16px;");
        toonWachtwoordCheckBox.setStyle("-fx-font-size: 14px;");
        wachtwoordVergetenLink.setStyle("-fx-font-size: 14px; -fx-text-fill: #c6c6c6");
        loginButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
        signUpButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ffffff; -fx-border-color: rgb(0,0,255); -fx-border-width: 2px");

    }

    @FXML
    public void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("languages/lan");

        hallo.setText(bundle.getString("label.hallo"));
        welkom.setText(bundle.getString("label.welkom"));
        passwordLabel.setText(bundle.getString("label.password"));
        toonWachtwoordCheckBox.setText(bundle.getString("checkbox.show_password"));
        signUpButton.setText(bundle.getString("label.signup"));
        wachtwoordVergetenLink.setText(bundle.getString("hyperlink.wachtwoordVergeten"));

    }

protected void onLanguageChange() {
    String selectedLanguage = languageSelector.getValue();
    if ("Nederlands".equals(selectedLanguage)) {
        currentLocale = new Locale("nl", "NL");
    } else {
        currentLocale = new Locale("en", "UK");
    }
    reloadSceneWithLocale(currentLocale);
}
    @FXML
    private void loadScene(ActionEvent event, String fxmlFile, String titleKey, int width, int height) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lan", currentLocale);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile), bundle);
        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(bundle.getString(titleKey));
        stage.setScene(new Scene(newTemplate, width, height));
        stage.show();
    }
    @FXML
    private void reloadSceneWithLocale(Locale locale) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("languages.lan", locale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/system42/hello-view.fxml"), bundle);
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMaakAccountButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountaanmaken.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        /* Need to be on the same network (111.111.111.x) to be able to remotely read/write from database, I ain't setting up no port forwarding right now */
//       if (new Login().login(emailField.getText(),passwordField.getText())) {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));
//            Parent newTemplate = fxmlLoader.load();
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(newTemplate, 1000, 720));
//            stage.show();
//        } else {
//            System.out.println("Wrong credentials");
//        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1000, 720));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onWachtwoordVergetenButtonClick(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wachtwoordvergeten-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onEmailFocused() {
        color1.setStyle("-fx-fill: #ff29ff");
        color2.setStyle("-fx-fill: #ffffff");
    }

    @FXML
    protected void onPasswordFocused() {
        color2.setStyle("-fx-fill: #ff29ff");
        color1.setStyle("-fx-fill: #ffffff");
    }

    @FXML
    protected void onToonWachtwoordCheckBoxClicked() {
        if (toonWachtwoordCheckBox.isSelected()) {
            passwordTextField.setText(passwordField.getText());
            passwordField.setOpacity(0);
            passwordField.setDisable(true);
            passwordTextField.setDisable(false);
            passwordTextField.setOpacity(1);
        } else {
            passwordField.setText(passwordTextField.getText());
            passwordTextField.setOpacity(0);
            passwordTextField.setDisable(true);
            passwordField.setDisable(false);
            passwordField.setOpacity(1);
        }
    }

    @FXML
    AnchorPane rootPane;

    public void requestFocusOnRoot() {
        rootPane.requestFocus();
    }
}

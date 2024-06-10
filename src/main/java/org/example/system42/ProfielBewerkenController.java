package org.example.system42;

import classes.LocalizationHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProfielBewerkenController {



    @FXML
    private void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());
    }

    @FXML
    public void setLanguage(Locale locale) {

        bundle = ResourceBundle.getBundle("languages.lan", locale);

        titelText.setText(bundle.getString("text.hoofdtext"));
        usernameLabel.setText(bundle.getString("text.gebruikersnaam"));
        emailLabel.setText(bundle.getString("text.emailLabel"));
        jobLabel.setText(bundle.getString("text.profession"));
        passwordLabel.setText(bundle.getString("text.password"));
        repeatPasswordLabel.setText(bundle.getString("text.repeatpassword"));
        saveButton.setText(bundle.getString("button.saveChanges"));
        uitlogButton.setText(bundle.getString("button.logout"));
        toonWachtwoordCheckbox.setText(bundle.getString("checkbox.show_password"));

    }



    @FXML
    protected void onOpslaanButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.lan", LocalizationHelper.getCurrentLocale()));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onLogoutButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("languages.lan", LocalizationHelper.getCurrentLocale()));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

}

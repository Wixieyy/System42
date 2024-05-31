package org.example.system42;

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

import java.io.IOException;

public class WachtwoordVergetenController {

        @FXML
        private CheckBox toonWachtwoord;

        @FXML
        private PasswordField nieuwWachtwoord;

        @FXML
        private PasswordField herhaalWachtwoord;

        @FXML
        private TextField wachtwoordTextField;

        @FXML
        private TextField herhaalWachtwoordTextField;

        @FXML
        protected void changeVisibility() {

            if(toonWachtwoord.isSelected()) {
                wachtwoordTextField.setText(nieuwWachtwoord.getText());
                nieuwWachtwoord.setOpacity(0);
                nieuwWachtwoord.setDisable(true);
                wachtwoordTextField.setOpacity(1);
                wachtwoordTextField.setDisable(false);

                herhaalWachtwoordTextField.setText(herhaalWachtwoord.getText());
                herhaalWachtwoord.setOpacity(0);
                herhaalWachtwoord.setDisable(true);
                herhaalWachtwoordTextField.setOpacity(1);
                herhaalWachtwoordTextField.setDisable(false);
            } else{

                nieuwWachtwoord.setText(wachtwoordTextField.getText());
                nieuwWachtwoord.setOpacity(1);
                nieuwWachtwoord.setDisable(false);
                wachtwoordTextField.setOpacity(0);
                wachtwoordTextField.setDisable(true);

                herhaalWachtwoord.setText(herhaalWachtwoordTextField.getText());
                herhaalWachtwoord.setOpacity(1);
                herhaalWachtwoord.setDisable(false);
                herhaalWachtwoordTextField.setOpacity(0);
                herhaalWachtwoordTextField.setDisable(true);
            }

        }

    @FXML
    protected void onLoginpaginaButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
    }

    @FXML
    protected void onOpslaanButtonClick (ActionEvent event) throws IOException {
       // create logic to save changes to the database
        //
        //
        /////////////////////////////////////////////


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
    }
}

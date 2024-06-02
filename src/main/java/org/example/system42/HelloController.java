package org.example.system42;

import classes.Login;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class HelloController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onMaakAccountButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountaanmaken.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
    }

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
//        if (new Login().login(emailField.getText(),passwordField.getText())) {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));
//
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
    }

    @FXML
    protected void onWachtwoordVergetenButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wachtwoordvergeten-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
    }

    @FXML
    AnchorPane rootPane;

    public void requestFocusOnRoot() {
        rootPane.requestFocus();
    }
}

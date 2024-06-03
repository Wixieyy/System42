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
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
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
    private void initialize() {
        emailField.setStyle("-fx-font-size: 16px;");
        passwordField.setStyle("-fx-font-size: 16px;");
        toonWachtwoordCheckBox.setStyle("-fx-font-size: 14px;");
        wachtwoordVergetenLink.setStyle("-fx-font-size: 14px; -fx-text-fill: #c6c6c6");
        loginButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ff29ff");
        signUpButton.setStyle("-fx-font-size: 22px; -fx-background-color:  #ffffff; -fx-border-color: rgb(0,0,255); -fx-border-width: 2px");
    }

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
    AnchorPane rootPane;

    public void requestFocusOnRoot() {
        rootPane.requestFocus();
    }
}

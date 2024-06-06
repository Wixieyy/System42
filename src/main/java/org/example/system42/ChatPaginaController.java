package org.example.system42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatPaginaController {
    @FXML
    private VBox sessieBox;

    @FXML
    private TextField chatBox;

    @FXML
    private TextArea chatArea;

    @FXML
    protected void onSessieButtonClicked(){
        sessieBox.setSpacing(5);
        Button button = new Button("Sessie " + (sessieBox.getChildren().size() + 1));
        button.setPrefWidth(212);
        button.setPrefHeight(40);
        sessieBox.setPadding(new Insets(6, 0, 0, 6));
        button.setStyle("-fx-background-color: #f2f2f2; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-cursor: hand;");
        button.setId("button" + (sessieBox.getChildren().size() + 1)); // maak een uniek ID voor elk knop
        sessieBox.getChildren().add(button);
        //System.out.println(button.getId());
    }

    @FXML
    protected void onVerwijderSessieButtonClick(){

        System.out.println("Sessie verwijderd");
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
    protected void onProfielButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));

        ProfielController profielController = new ProfielController();

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800,600 ));
        stage.show();
        stage.centerOnScreen();
    }


    @FXML
    protected void onVerstuurButtonClick (ActionEvent event) throws IOException {
        String message = chatBox.getText();
        System.out.println(message);

        chatArea.appendText("Gebruiker: " + message + "\n");
        AIresponse();
    }

    @FXML
    protected void AIresponse(){

        chatArea.appendText("A.I. Assistant: A.I test response\n\n");


    }



}

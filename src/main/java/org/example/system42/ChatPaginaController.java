package org.example.system42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ChatPaginaController {
    @FXML
    private VBox sessieBox;

    @FXML
    private TextField chatBox;

    @FXML
    private TextArea chatArea;

    private static JSONObject responses;
    private int sessionCounter = 0;
    private Map<Integer, VBox> sessions = new HashMap<>();
    private Map<Integer, Button> sessionButtons = new HashMap<>();
    private int currentSessionId = -1;

    @FXML
    public void initialize() {
        loadResponses();
        chatBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onVerstuurButtonClick(null);
            }
        });
    }

    @FXML
    protected void onSessieButtonClicked() {
        sessieBox.setSpacing(5);
        int newSessionId = ++sessionCounter;
        Button button = new Button("Sessie " + newSessionId);
        button.setPrefWidth(212);
        button.setPrefHeight(40);
        sessieBox.setPadding(new Insets(6, 0, 0, 6));
        button.setStyle("-fx-background-color: #f2f2f2; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-cursor: hand;");
        button.setId("sessionButton" + newSessionId); // Create a unique ID for each button

        VBox sessionBox = new VBox();
        sessions.put(newSessionId, sessionBox);
        sessionButtons.put(newSessionId, button);
        button.setOnAction(e -> switchToSession(newSessionId));

        sessieBox.getChildren().add(button);
        switchToSession(newSessionId); // Switch to the newly created session
    }

    @FXML
    protected void onWijzigenSessieButtonClicked() {
        if (currentSessionId != -1) {
            TextField renameField = new TextField();
            renameField.setPromptText("New session name");
            sessieBox.getChildren().add(renameField);
            renameField.setOnAction(event -> {
                String newName = renameField.getText();
                if (!newName.trim().isEmpty()) {
                    Button sessionButton = sessionButtons.get(currentSessionId);
                    sessionButton.setText(newName);
                    sessieBox.getChildren().remove(renameField);
                }
            });
        }
    }

    @FXML
    protected void onVerwijderSessieButtonClick() {
        if (currentSessionId != -1) {
            sessions.remove(currentSessionId);
            sessieBox.getChildren().removeIf(node -> node.getId().equals("sessionButton" + currentSessionId));
            sessionButtons.remove(currentSessionId);
            chatArea.clear();
            currentSessionId = -1;
            // Switch to the next available session if any
            if (!sessions.isEmpty()) {
                Integer nextSessionId = sessions.keySet().iterator().next();
                switchToSession(nextSessionId);
            }
        }
    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onProfielButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));
        Parent newTemplate = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    protected void onVerstuurButtonClick(ActionEvent event) {
        if (currentSessionId == -1) {
            chatArea.appendText("No session selected. Please create or select a session.\n");
            return;
        }
        String message = chatBox.getText();
        chatArea.appendText("Gebruiker: " + message + "\n");
        String response = getResponse(message);
        chatArea.appendText("Assistant: " + response + "\n\n");
        chatBox.clear();

        VBox currentSessionBox = sessions.get(currentSessionId);
        if (currentSessionBox != null) {
            TextArea textArea = new TextArea("Gebruiker: " + message + "\nA.I. Assistant: " + response + "\n");
            textArea.setEditable(false);
            textArea.setWrapText(true);
            currentSessionBox.getChildren().add(textArea);
        }
    }

    private void switchToSession(int sessionId) {
        currentSessionId = sessionId;
        VBox sessionBox = sessions.get(sessionId);
        chatArea.clear();
        if (sessionBox != null) {
            sessionBox.getChildren().forEach(node -> chatArea.appendText(((TextArea) node).getText()));
        }
    }

    private void loadResponses() {
        JSONParser parser = new JSONParser();
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/org/example/system42/responses.json"))) {
            responses = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static String getResponse(String userInput) {
        for (Object key : responses.keySet()) {
            String keyword = (String) key;
            if (userInput.toLowerCase().contains(keyword.toLowerCase())) {
                return (String) responses.get(keyword);
            }
        }
        return "Sorry, I don't have that in my database!";
    }
}

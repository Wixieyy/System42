package org.example.system42;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.text.Text;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChatPaginaController {
    @FXML
    private VBox sessieBox;

    @FXML
    private TextField chatBox;

    @FXML
    private Button wijzigenSessieButton;
    @FXML
    private TextArea chatArea;
    @FXML
    private Text sessiesText;
    @FXML
    private Text chatText;
    @FXML
    private Button verwijderSessieButton;
    @FXML
    private Button profielButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button nieuweSessieButton;
    private ResourceBundle bundle;

    private int sessionCounter = 0;
    private Map<Integer, Sessie> sessions = new HashMap<>();
    private int currentSessionId = -1;

    @FXML
    public void initialize() {
        setLanguage(LocalizationHelper.getCurrentLocale());
        Assistant.loadResponses();
        chatBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onVerstuurButtonClick(null);
            }
        });
    }
    @FXML
    private void setLanguage(Locale locale){
        bundle = ResourceBundle.getBundle("languages.lan", locale);
        sessiesText.setText(bundle.getString("text.sessie"));
        nieuweSessieButton.setText(bundle.getString("label.new_session"));
        logoutButton.setText(bundle.getString("label.logout"));
        profielButton.setText(bundle.getString("label.profile"));
        verwijderSessieButton.setText(bundle.getString("label.delete_session"));
        chatText.setText(bundle.getString("text.chat"));
        wijzigenSessieButton.setText(bundle.getString("button.changeSession"));

    }

    @FXML
    protected void onSessieButtonClicked() {

        String sessie;

        if(LocalizationHelper.getCurrentLocale().getLanguage().equals("nl")){
            sessie = "Sessie";
        }else{
            sessie = "Session";
        }
        sessieBox.setSpacing(5);
        int newSessionId = ++sessionCounter;
        Sessie newSession = new Sessie(newSessionId, "Sessie " + newSessionId);
        newSession.getSessionButton().setOnAction(e -> switchToSession(newSessionId));

        sessions.put(newSessionId, newSession);
        sessieBox.setPadding(new Insets(6, 0, 0, 6));
        sessieBox.getChildren().add(newSession.getSessionButton());
        switchToSession(newSessionId);
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
                    Sessie currentSession = sessions.get(currentSessionId);
                    currentSession.setName(newName);
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
            chatArea.clear();
            currentSessionId = -1;

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
       Message.completeMessage(sessions, chatBox, chatArea, currentSessionId);
//        String message = chatBox.getText();
//        chatArea.appendText("Gebruiker: " + message + "\n");
//        String response = Assistant.getResponse(message);
//        chatArea.appendText("Assistant: " + response + "\n\n");
//        chatBox.clear();
//
//        Sessie currentSession = sessions.get(currentSessionId);
//        if (currentSession != null) {
//            TextArea textArea = new TextArea("Gebruiker: " + message + "\nA.I. Assistant: " + response + "\n");
//            textArea.setEditable(false);
//            textArea.setWrapText(true);
//            currentSession.getSessionBox().getChildren().add(textArea);
//        }
    }

    private void switchToSession(int sessionId) {
        currentSessionId = sessionId;
        Sessie session = sessions.get(sessionId);
        chatArea.clear();
        if (session != null) {
            session.getSessionBox().getChildren().forEach(node -> chatArea.appendText(((TextArea) node).getText()));
        }
    }

//    private void loadResponses() {
//        ReaderWriter.OfflineJsonLoader();
//    }
//
//    private static String getResponse(String userInput) {
//        return ReaderWriter.OfflineJsonReader(userInput);
//    }
}

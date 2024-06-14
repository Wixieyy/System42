package classes;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Map;

public class Message {

    public static void completeMessage(Map<Integer, Sessie> sessions, TextField chatBox, TextArea chatArea, int currentSessionId){

        String message = chatBox.getText();
        chatArea.appendText("Gebruiker: " + message + "\n");
        String response = Assistant.getResponse(message);
        chatArea.appendText("Assistant: " + response + "\n\n");
        chatBox.clear();

        Sessie currentSession = sessions.get(currentSessionId);
        if (currentSession != null) {
            TextArea textArea = new TextArea("Gebruiker: " + message + "\nA.I. Assistant: " + response + "\n");
            textArea.setEditable(false);
            textArea.setWrapText(true);
            currentSession.getSessionBox().getChildren().add(textArea);
        }

    }
}

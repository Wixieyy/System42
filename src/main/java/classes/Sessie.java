package classes;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class Sessie {
    private int id;
    private VBox sessionBox;
    private Button sessionButton;
    private String name;

    public Sessie(int id, String name) {
        this.id = id;
        this.name = name;
        this.sessionBox = new VBox();
        this.sessionButton = new Button(name);
        setupButton();
    }

    public int getId() {
        return id;
    }

    public VBox getSessionBox() {
        return sessionBox;
    }

    public Button getSessionButton() {
        return sessionButton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.sessionButton.setText(name);
    }

    private void setupButton() {
        sessionButton.setPrefWidth(212);
        sessionButton.setPrefHeight(40);
        sessionButton.setStyle("-fx-background-color: #f2f2f2; -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-cursor: hand;");
        sessionButton.setId("sessionButton" + id);
    }
}

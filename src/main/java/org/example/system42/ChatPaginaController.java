package org.example.system42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatPaginaController {
    @FXML
    private VBox sessieBox;

    @FXML
    protected void onSessieButtonClicked(){
        sessieBox.setSpacing(5);
        Button button = new Button("Sessie");
        button.setPrefWidth(245);
        button.setPrefHeight(40);
        sessieBox.getChildren().add(button);
    }

    @FXML
    protected void onLogoutButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800, 600));
        stage.show();
    }
    @FXML
    protected void onProfielButtonClick (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiel-view.fxml"));

        ProfielController profielController = new ProfielController();

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 800,600 ));
        stage.show();
    }


    @FXML
    protected void onVerstuurButtonClick (ActionEvent event) throws IOException {


    }

    }

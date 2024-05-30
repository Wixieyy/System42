package org.example.system42;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class profielController {


    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatpagina.fxml"));

        Parent newTemplate = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newTemplate, 1000, 720));
        stage.show();
    }

    @FXML
    public void taalEnglish(ActionEvent actionEvent) {

        System.out.println("Language set to English");
    }

    @FXML
    public  void taalNederlands(ActionEvent actionEvent) {

        System.out.println("Taal veranderd naar Nederlands");
    }

}

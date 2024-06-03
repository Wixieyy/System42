package classes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sessie {

    @FXML
    private Button nieuweSessieButton;

    @FXML
    private VBox sessieBox;

    protected void onSessieButtonClicked(){

        System.out.println("test");
    }
}

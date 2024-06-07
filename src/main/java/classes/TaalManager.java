package classes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class TaalManager {
    public void start(Stage primaryStage) {
        Label greetingLabel = new Label(LocalizationHelper.getString("greeting"));
        Label farewellLabel = new Label(LocalizationHelper.getString("farewell"));

        ComboBox<Locale> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll(LocalizationHelper.getAvailableLocales());
        languageComboBox.setValue(Locale.getDefault());
        languageComboBox.setOnAction(e -> switchLanguage(languageComboBox.getValue(), greetingLabel, farewellLabel));

        VBox root = new VBox(10, greetingLabel, farewellLabel, languageComboBox);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("Multilingual JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchLanguage(Locale locale, Label greetingLabel, Label farewellLabel) {
        LocalizationHelper.setLocale(locale);
    }
}
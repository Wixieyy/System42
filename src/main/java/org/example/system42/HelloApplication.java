package org.example.system42;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import classes.LocalizationHelper;

import java.io.IOException;
import java.util.Locale;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        LocalizationHelper.setLocale(new Locale("en"));


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/system42/hello-view.fxml"));

        fxmlLoader.setResources(LocalizationHelper.getResourceBundle());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800,600 );
        scene.getStylesheets().add(getClass().getResource("/org/example/system42/css/hello-view.css").toExternalForm());
        stage.setTitle("Login page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();

        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

}
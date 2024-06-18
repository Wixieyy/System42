module org.example.system42 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires json.simple;
    requires com.google.gson;
    requires commons.email;

    opens org.example.system42 to javafx.fxml;
    exports org.example.system42;
    exports classes;
    opens classes to javafx.fxml;

}
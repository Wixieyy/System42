package org.example.system42;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AccountMakenController {

    protected void createAccount() {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("login-gegevens");
            MongoCollection<Document> collection = database.getCollection("email");
//            if (!emailField.getText().equals(collection.find("email", emailField.getText()))) {
//                if (passwordField1.getText().equals(passwordField2.getText())) {
//                    collection.insertOne(new Document("gebruikersnaam", gebruikersnaamField.getText())
//                            .append("email", emailField.getText())
//                            .append("password", passwordField1.getText()));
//                }
//                else {
//                    /*passwords dont match*/
//                }
//            }
//            else {
//                /*account already exists or email already used*/
//            }
        }
    }
}

package org.example.system42;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.Gebruiker;
import classes.Login;
import classes.Profiel;
import classes.ReaderWriter;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class TestTest {

    @BeforeAll
    static void setup() {
        initializeDatabaseForTesting();
    }

    @BeforeEach
    void loadData() {
        ReaderWriter.OfflineJsonLoader();
    }

    // Beslissingstabel test
    @Test
    void testCreateAccount() {
        // Case A: New account creation
        Gebruiker.createAccount("testUser", "newemail@example.com", "password123", "password123");
        assertTrue(accountExists("newemail@example.com"));

        // Case B: Account already exists
        Gebruiker.createAccount("testUser", "existingemail@example.com", "password123", "password123");
        assertTrue(accountCreationSuccess("existingemail@example.com"));
    }

    // Decision coverage test
    @Test
    void testOfflineJsonReaderDecisionCoverage() {
        String response = ReaderWriter.offlineJsonReader("existing term");
        assertEquals("expected response", response);

        response = ReaderWriter.offlineJsonReader("nonexistent term");
        assertEquals("Sorry, I don't have that in my database!", response);
    }

    // Condition coverage test
    @Test
    void testOfflineJsonReaderConditionCoverage() {
        String response = ReaderWriter.offlineJsonReader("inheritance");
        assertEquals("A mechanism where a new class inherits properties and behaviors from an existing class.", response);

        response = ReaderWriter.offlineJsonReader("existing term");
        assertEquals("expected response", response);

        response = ReaderWriter.offlineJsonReader("notinDB");
        assertEquals("Sorry, I don't have that in my database!", response);
    }

    // Response coverage test
    @Test
    void testOfflineJsonReaderResponse() {
        String response = ReaderWriter.offlineJsonReader("binair");
        assertEquals("Een systeem van numerieke notatie dat 2 in plaats van 10 als basis heeft.", response);
    }

    // Equivalentieklassen en randwaarden test
    @Test
    void testLoginWithEquivalentClasses() {
        Login login = new Login();

        // Case A: Correct login
        boolean resultA = login.login("shendrickwilliams@gmail.com", "1234");
        assertTrue(resultA, "Expected successful login with correct credentials");

        // Case B: Incorrect password
        boolean resultB = login.login("shendrickwilliams@gmail.com", "123");
        assertFalse(resultB, "Expected failed login with incorrect password");

        // Case C: Incorrect login
        boolean resultC = login.login("shendrick@gmail.com", "1234");
        assertFalse(resultC, "Expected failed login with incorrect email");

        // Case D: Incorrect login and password
        boolean resultD = login.login("shendrick@gmail.com", "123");
        assertFalse(resultD, "Expected failed login with incorrect email and password");
    }


    @Test
    void testUpdateAccountInfo() {
        Login login = new Login();
        boolean loginResult = login.login("shendrickwilliams@gmail.com", "1234");
        assertTrue(loginResult, "Expected successful login with correct credentials");

        Profiel.changeProfile(true, "newPassword123", "newPassword123", "newUsername", "newemail@example.com", "newJob", "newDepartment");
        assertTrue(profileUpdated("newemail@example.com"), "Expected successful profile update with new information");
    }

    private static void initializeDatabaseForTesting() {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");

            // Drop existing collections
            database.getCollection("login-credentials");
            database.getCollection("externe-gegevens");

            // Recreate collections with initial data
            MongoCollection<Document> loginCollection = database.getCollection("login-credentials");
            loginCollection.insertOne(new Document("gebruikersnaam", "testUser")
                    .append("email", "shendrickwilliams@gmail.com")
                    .append("password", "1234")
                    .append("beroep", "Onbekend")
                    .append("afdeling", "Onbekend"));

            MongoCollection<Document> externeGegevensCollection = database.getCollection("externe-gegevens");
            externeGegevensCollection.insertOne(new Document("term", "existing term").append("definition", "expected response"));
        }
    }

    private boolean accountExists(String email) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", email)).first();
            return document != null;
        }
    }

    private boolean accountCreationSuccess(String email) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", email)).first();
            return document != null && document.getString("email").equals(email);
        }
    }

    private boolean profileUpdated(String email) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", email)).first();
            return document != null && document.getString("gebruikersnaam").equals("newUsername") &&
                    document.getString("beroep").equals("newJob") &&
                    document.getString("afdeling").equals("newDepartment");
        }
    }

    private boolean profileUpdateSuccess(String email) {
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("System42DB");
            MongoCollection<Document> collection = database.getCollection("login-credentials");
            Document document = collection.find(eq("email", email)).first();
            return document != null && document.getString("email").equals(email);
        }
    }
}

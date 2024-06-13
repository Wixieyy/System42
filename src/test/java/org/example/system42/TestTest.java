package org.example.system42;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import classes.Gebruiker;
import classes.Login;
import classes.Profiel;
import classes.ReaderWriter;


public class TestTest {

    @Test
    void testCreateAccount() {
        Gebruiker.createAccount("testUser", "newemail@example.com", "password123", "password123"); // Case A
        // Assert account created

        Gebruiker.createAccount("testUser", "existingemail@example.com", "password123", "password123"); // Case B
        // Assert account already exists

        Gebruiker.createAccount("testUser", "newemail@example.com", "password123", "password321"); // Case C
        // Assert passwords don't match

        Gebruiker.createAccount("testUser", "newemail@example.com", "password123", "password123"); // Case D
        // Assert account created
    }

    @Test
    void testLogin() {
        Login login = new Login();

        boolean resultA = login.login("existingemail@example.com", "correctpassword"); // Case A
        assertTrue(resultA);

        boolean resultB = login.login("existingemail@example.com", "wrongpassword"); // Case B
        assertFalse(resultB);

        boolean resultC = login.login("nonexistentemail@example.com", "anyPassword"); // Case C
        assertFalse(resultC);

        boolean resultD = login.login("nonexistentemail@example.com", "anyPassword"); // Case D
        assertFalse(resultD);
    }

    @Test
    void testChangeProfileEquivalencePartitioning() {
        Profiel.changeProfile(true, "newpassword123", "newpassword123", "newUsername", "newemail@example.com", "newJob", "newDepartment");
        // Assert profile changed

        Profiel.changeProfile(true, "newpassword123", "newpassword123", "newUsername", "invalidemail", "newJob", "newDepartment");
        // Assert invalid email

        Profiel.changeProfile(true, "newpassword123", "differentpassword", "newUsername", "newemail@example.com", "newJob", "newDepartment");
        // Assert passwords don't match

        Profiel.changeProfile(true, "", "", "", "", "", "");
        // Assert empty fields
    }

    @Test
    void testChangeProfileBoundaryValueAnalysis() {
        Profiel.changeProfile(true, "p", "p", "u", "email@example.com", "j", "d");
        // Assert minimum length

        Profiel.changeProfile(true, "p12345678901234567890", "p12345678901234567890", "u12345678901234567890", "email@example.com", "j12345678901234567890", "d12345678901234567890");
        // Assert maximum length
    }

    @Test
    void testOfflineJsonReaderDecisionCoverage() {
        String response = ReaderWriter.offlineJsonReader("existing term");
        // Assert response found in JSON

        response = ReaderWriter.offlineJsonReader("nonexistent term");
        // Assert response not found, default response
    }

    @Test
    void testOfflineJsonReaderConditionCoverage() {
        String response = ReaderWriter.offlineJsonReader("term in JSON but not in DB");
        // Assert response found in JSON

        response = ReaderWriter.offlineJsonReader("term not in JSON but in DB");
        // Assert response found in DB

        response = ReaderWriter.offlineJsonReader("term not in JSON or DB");
        // Assert default response
    }

    @Test
    void testOfflineJsonReaderMultipleConditionCoverage() {
        String response = ReaderWriter.offlineJsonReader("term in both JSON and DB");
        // Assert response
        assertNotNull(response);
    }
}
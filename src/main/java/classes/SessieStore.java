package classes;

import classes.Sessie;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SessieStore {
    private static final String SESSIONS_FILE = "sessions.ser";
    private static SessieStore instance;
    private Map<Integer, Sessie> sessions;

    private SessieStore() {
        loadSessions();
    }

    public static SessieStore getInstance() {
        if (instance == null) {
            instance = new SessieStore();
        }
        return instance;
    }

    public Map<Integer, Sessie> getAllSessions() {
        return sessions;
    }

    public void addSession(Sessie session) {
        sessions.put(session.getId(), session);
        saveSessions();
    }

    private void loadSessions() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSIONS_FILE))) {
            sessions = (Map<Integer, Sessie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            sessions = new HashMap<>();
        }
    }

    private void saveSessions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSIONS_FILE))) {
            oos.writeObject(sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
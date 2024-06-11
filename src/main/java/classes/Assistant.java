package classes;

public class Assistant {

    public static void loadResponses() {
        ReaderWriter.OfflineJsonLoader();
    }

    public static String getResponse(String userInput) {
        return ReaderWriter.offlineJsonReader(userInput);
    }
}


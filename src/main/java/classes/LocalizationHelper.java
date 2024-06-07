package classes;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationHelper {
    private static ResourceBundle resourceBundle;
    private static Locale currentLocale = new Locale("nl");
    private static final String BUNDLE_BASENAME = "languages.lan";

    static {
        setLocale(currentLocale);
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        resourceBundle = ResourceBundle.getBundle(BUNDLE_BASENAME, locale);
    }

    public static String getString(String key) {
        return resourceBundle.getString(key);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static Locale[] getAvailableLocales() {
        return new Locale[]{
                new Locale("nl"),
                new Locale("en"),
        };
    }
}

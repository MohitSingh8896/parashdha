package project.namramuni.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalSharedprefrence {

    public static Context appContext;
    private static String PREFERENCE="UrlSet";

    // for username string preferences
    public static void setSharedPreference(Context context, String name,
                                           String value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        editor.putString(name, value);
        editor.commit();
    }

    // for username string preferences
    public static void setIntegerSharedPreference(Context context, String name,
                                                  int value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        editor.putInt(name, value);
        editor.commit();
    }

    //Drawable
    public static void setDrawableSharedPreference(Context context, String name,
                                                   int value) {
        appContext = context;
        SharedPreferences settings = context
                .getSharedPreferences(PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        editor.putInt(name, value);
        editor.commit();
    }

    public static String getSharedPreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
        return settings.getString(name, null);
    }
    public static String getUseridSharedPreferences(Context context, String name) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFERENCE, 0);
        return settings.getString(name, "0");
    }

    public static int getIngerSharedPreferences(Context context, String name) {
        SharedPreferences settings = context
                .getSharedPreferences(PREFERENCE, 0);
        return settings.getInt(name, 1);
    }

    public static void setSharedPreferenceBoolean(Context context, String name,
                                                  boolean value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public static boolean getSharedPreferencesBoolean(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE, 0);
        return settings.getBoolean(name, false);
    }

}// final class ends here

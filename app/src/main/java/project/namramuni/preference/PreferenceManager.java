package project.namramuni.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ashwini on 03-May-17.
 */

public class PreferenceManager {

    public Context context;
    public SharedPreferences.Editor editor;
    public final String MY_PREFS_NAME = "user";
    public SharedPreferences prefs;

    //User Details
    public PreferenceManager(Context conte) {
        context = conte;
        prefs = conte.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
        editor = conte.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
    }

    public PreferenceManager(Context context, String s) {
        this.context = context;
        this.prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);
    }

    public void putPreferenceBoolValues(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String putPreferenceValues(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        editor.apply();
        return key;
    }


    public void putPreferenceIntValues(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putPreferenceListValues(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
    }

    public Set<String> getPreferenceListValues(String key) {
        Set<String> set = new HashSet<String>();
        return prefs.getStringSet(key, set);
    }

    public boolean getPreferenceBoolValues(String key) {
        return prefs.getBoolean(key, false);
    }

    public int getPreferenceIntValues(String key) {
        return prefs.getInt(key, 0);
    }

    public String getPreferenceValues(String key) {
        return prefs.getString(key, "");
    }

    public void clearSharedPreferance() {
        prefs.edit().clear().commit();
    }

    public void clearSharedPreferance(String key) {
        prefs.edit().remove(key).commit();
    }


    public void putPreferencDoubleValues(String key, double value) {
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }

    public double getPreferencDoubleValues(String key, double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }

}

package project.namramuni;

import android.content.Context;

import androidx.multidex.MultiDexApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Application extends MultiDexApplication {
    public static Context masterContext;
    @Override
    public void onCreate() {
        super.onCreate();
        masterContext = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("cambria/FedraSerifPro A Medium.otf")
                .setFontAttrId(R.attr.font1)
                .build());

    }
        public static Context getAppContext() {
            return masterContext;
        }
        public static boolean isActivityVisible() {
            return activityVisible;
        }
        public static void activityResumed() {
            activityVisible = true;
        }
        public static void activityPaused() {
            activityVisible = false;
        }
        private static boolean activityVisible;
        private static Context context;
        public static Context getContext() {
            return context;
        }
}

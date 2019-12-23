package project.namramuni.Constant;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.Random;

public class ApplicationData {
    public static final String MainURL = "http://parasdham.skryptech.com/";
    public static final String URL = MainURL + "api/";





    public static final String add_user = URL + "register_user";
    public static final String update_user = URL + "update_profile";
    private static String[] liveVideoIds = {"UJwGsD4pbmQ"};
    private static Random random = new Random();
    public static String videoIds = "6JYIGclVQdwLvetJ9U_tVYS0Q4gqBUs7czOa-rSM4nms";
    private static String[] videoIds1 = {"6JYIGclVQdw", "LvetJ9U_tVY", "S0Q4gqBUs7c", "zOa-rSM4nms"};
    public static String getNextVideoId() {
        return videoIds1[random.nextInt(videoIds1.length)];
    }
    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
    public static String Deviceid(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            String imei = telephonyManager.getDeviceId();
            Log.e("imei", "=" + imei);
            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return Build.SERIAL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}

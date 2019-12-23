package project.namramuni.background;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Connectivity {
    public static Context conte;
    public static final int NETWORK_TYPE_EHRPD = 14; // Level 11
    public static final int NETWORK_TYPE_EVDO_B = 12; // Level 9
    public static final int NETWORK_TYPE_HSPAP = 15; // Level 13
    public static final int NETWORK_TYPE_IDEN = 11; // Level 8
    public static final int NETWORK_TYPE_LTE = 13; // Level 11

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static boolean isConnectedFast(Context context) {
        conte = context;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
//        return (info != null && info.isConnected() && Connectivity.isConnectionFast(info.getType(), info.getSubtype()));
        return true;
    }

    public static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            WifiManager wifiManager = (WifiManager) conte.getSystemService(Context.WIFI_SERVICE);
            int numberOfLevels = 5;
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
            switch (level) {
                case 0:
                    Toast.makeText(conte, "None", Toast.LENGTH_SHORT).show();
                    Log.d("LEVEL","None");
                    return false;
                case 1:
                    Toast.makeText(conte, "None", Toast.LENGTH_SHORT).show();
                    Log.d("LEVEL","None");
                    return false;
                case 2:
                    Toast.makeText(conte, "Poor", Toast.LENGTH_SHORT).show();
                    Log.d("LEVEL","Poor");
                    return false;
                case 3:
                    Log.d("LEVEL","Moderate");
                    Toast.makeText(conte, "Moderate", Toast.LENGTH_SHORT).show();
                    return true;
                case 4:
                    Toast.makeText(conte, "Good", Toast.LENGTH_SHORT).show();
                    Log.d("LEVEL","Good");
                    return true;
                case 5:
                    Toast.makeText(conte, "Excellent", Toast.LENGTH_SHORT).show();
                    Log.d("LEVEL","Excellent");
                    return true;
                default:
                    return false;
            }
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    Log.i("TYPE_MOBILE", "50-100 kbps");
                    Toast.makeText(conte, "50-100 kbps", Toast.LENGTH_SHORT).show();
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    Log.i("TYPE_MOBILE", "14-64 kbps");
                    Toast.makeText(conte, "14-64 kbps", Toast.LENGTH_SHORT).show();
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    Log.i("TYPE_MOBILE", "50-100 kbps");
                    Toast.makeText(conte, "50-100 kbps", Toast.LENGTH_SHORT).show();
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    Log.i("TYPE_MOBILE", "400-1400 kbps");
                    Toast.makeText(conte, "400-1400 kbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    Log.i("TYPE_MOBILE", "600-1400 kbps");
                    Toast.makeText(conte, "600-1400 kbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    Log.i("TYPE_MOBILE", "100 kbps");
                    Toast.makeText(conte, "100 kbps", Toast.LENGTH_SHORT).show();
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    Toast.makeText(conte, "2-14 Mbps", Toast.LENGTH_SHORT).show();
                    Log.i("TYPE_MOBILE", "2-14 Mbps");
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    Toast.makeText(conte, "700-1700 kbps", Toast.LENGTH_SHORT).show();
                    Log.i("TYPE_MOBILE", "700-1700 kbps");
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    Toast.makeText(conte, "1-23 Mbps", Toast.LENGTH_SHORT).show();
                    Log.i("TYPE_MOBILE", "1-23 Mbps");
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    Toast.makeText(conte, "400-7000 kbps", Toast.LENGTH_SHORT).show();
                    Log.i("TYPE_MOBILE", "400-7000 kbps");
                    return true; // ~ 400-7000 kbps
                // NOT AVAILABLE YET IN API LEVEL 7
                case Connectivity.NETWORK_TYPE_EHRPD:
                    Log.i("TYPE_MOBILE", "1-2 Mbps");
                    Toast.makeText(conte, "1-2 Mbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 1-2 Mbps
                case Connectivity.NETWORK_TYPE_EVDO_B:
                    Log.i("TYPE_MOBILE", "5 Mbps");
                    Toast.makeText(conte, "5 Mbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 5 Mbps
                case Connectivity.NETWORK_TYPE_HSPAP:
                    Log.i("TYPE_MOBILE", "10-20 Mbps");
                    Toast.makeText(conte, "10-20 Mbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 10-20 Mbps
                case Connectivity.NETWORK_TYPE_IDEN:
                    Log.i("TYPE_MOBILE", "25 kbps");
                    Toast.makeText(conte, "25 kbps", Toast.LENGTH_SHORT).show();
                    return false; // ~25 kbps
                case Connectivity.NETWORK_TYPE_LTE:
                    Log.i("TYPE_MOBILE", "10+ Mbps");
                    Toast.makeText(conte, "10+ Mbps", Toast.LENGTH_SHORT).show();
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    Toast.makeText(conte, "No network", Toast.LENGTH_SHORT).show();
                    Log.i("TYPE_MOBILE", "No internet");
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

}

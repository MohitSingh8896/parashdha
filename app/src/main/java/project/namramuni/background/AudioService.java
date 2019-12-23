package project.namramuni.background;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.RequiresApi;
import project.namramuni.Application;

public class AudioService extends Service {
    public Context context = this;
    public static String networkstatus = "";
    public static Boolean shilpaopenPlayer = false, shilpaopenPlayer1 = false, shilpaplaypouse = false;
    private AudioServiceBinder audioServiceBinder = new AudioServiceBinder();
    private final String LOG_TAG = "NotificationService";
    NotificationManager mNotifyManager;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static String shilpasongid = "",shilpasongcount = "1";
    public static int mantapoint = -1;
    public static String dbId = "", FId = "", dbOflineId = "";
    public static Handler audioProgressUpdateHandler = null;
    public static String shilpasetimag = "", shilpasettitle = "", shilpasetSongUrl = "";

    public AudioService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public IBinder onBind(Intent intent) {
        return audioServiceBinder;
    }

    @Override
    public void onDestroy() {
        Log.i("CheckonDestroy", "Distroye");
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == intent || null == intent.getAction()) {
            String source = null == intent ? "intent" : "action";
            Log.e("", source + " was null, flags=" + flags + " bits=" + Integer.toBinaryString(flags));
            return START_STICKY;
        } else {
            if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
//                STARTFOREGROUND_ACTION(context);
            } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
//                PREV_ACTION(context);
            } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
                PLAY_ACTION(context);
            } else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)) {
                PAUSE_ACTION(context);
            } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
//                NEXT_ACTION(context);
            } else if (intent.getAction().equals(Constants.ACTION.MAIN_ACTIONClick)) {
            }
            return START_STICKY;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void PLAY_ACTION(Context context) {
        if (Application.isActivityVisible()) {
            audioServiceBinder.startAudio();
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION.PLAY_ACTION);
            context.sendBroadcast(intent);
        } else {
            audioServiceBinder.startAudio1("");
        }
        shilpaplaypouse = true;
        Log.i(LOG_TAG, "Clicked Play");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void PAUSE_ACTION(Context context) {
        if (Application.isActivityVisible()) {
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION.PAUSE_ACTION);
            context.sendBroadcast(intent);
        } else {
            audioServiceBinder.pauseAudio();
        }
        shilpaplaypouse = false;
        Log.i(LOG_TAG, "Clicked Pause");
    }

}


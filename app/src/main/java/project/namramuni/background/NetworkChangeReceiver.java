package project.namramuni.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import project.namramuni.Application;

import static project.namramuni.background.AudioService.networkstatus;
import static project.namramuni.Activity.MainActivity.audioServiceBinder;
import static project.namramuni.background.AudioServiceBinder.audioPlayer;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (context != null) {
            networkstatus = ConnectionCheckActivity.getConnectivityStatusString(context);
            Toast.makeText(context, networkstatus, Toast.LENGTH_SHORT).show();
            if (networkstatus.equals("Not connected to Internet")) {
                if (audioPlayer != null) {
                    if (audioPlayer.isPlaying()) {
                        if (Application.isActivityVisible()) {
                            Intent intent1 = new Intent();
                            intent1.setAction(Constants.ACTION.PAUSE_ACTION1);
                            intent1.putExtra("status", "2");
                            context.sendBroadcast(intent1);
                        } else {
                            audioServiceBinder.pauseAudio();
                        }
                    }
                }
            } else {
                if (audioPlayer != null) {
                    if (audioPlayer.isPlaying()) {
                        if (networkstatus.equals("Mobile data enabled")) {
                            if (Application.isActivityVisible()) {
                                Intent intent1 = new Intent();
                                intent1.setAction(Constants.ACTION.PAUSE_ACTION1);
                                intent1.putExtra("status", "1");
                                context.sendBroadcast(intent1);
                            } else {
                                audioServiceBinder.pauseAudio();
                            }
                        }
                    } else if (networkstatus.equals("Wifi enabled")) {
                    }
                }
            }
        }
    }
}

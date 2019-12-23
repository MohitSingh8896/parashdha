package project.namramuni.background;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import project.namramuni.Activity.BhaktilistActivity;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Application;

import static project.namramuni.Activity.MainActivity.audioServiceBinder;
import static project.namramuni.background.AudioService.mantapoint;
import static project.namramuni.background.AudioService.networkstatus;
import static project.namramuni.background.AudioService.shilpaplaypouse;
import static project.namramuni.background.AudioService.shilpasetSongUrl;
import static project.namramuni.background.AudioService.shilpasetimag;
import static project.namramuni.background.AudioService.shilpasettitle;
import static project.namramuni.background.AudioService.shilpasongcount;
import static project.namramuni.background.AudioService.shilpasongid;

public class AudioServiceBinder extends Binder implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {
    private final String LOG_TAG = "NotificationService";
    NotificationManager mNotifyManager;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public final static String ALGO_SECRET_KEY_GENERATOR = "AES";

    // Save local audio file uri ( local storage file. ).
    private Uri audioFileUri = null;
    // Save web audio file url.
    private String audioFileUrl = "";

    // Check if stream audio.
    private boolean streamAudio = false;

    // Media player that play audio.
    public static int secondaryProgress;
    public static AudioService audioService = new AudioService();
    public static MediaPlayer audioPlayer = null;

    // Caller activity context, used when play local audio file.
    public Context context;

    // This Handler object is a reference to the caller activity's Handler.
    // In the caller activity's handler, it will update the audio play progress.
    public Handler audioProgressUpdateHandler;

    // This is the message signal that inform audio progress updater to update audio progress.
    public int UPDATE_AUDIO_PROGRESS_BAR = 1;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getAudioFileUrl() {
        return audioFileUrl;
    }

    public void setAudioFileUrl(String audioFileUrl) {
        if (audioFileUrl != null) {
            this.audioFileUrl = audioFileUrl.replaceAll(" ", "%20");
        } else {
            this.audioFileUrl = "";
        }
    }

    public boolean isStreamAudio() {
        return streamAudio;
    }

    public void setStreamAudio(boolean streamAudio) {
        this.streamAudio = streamAudio;
    }

    public Uri getAudioFileUri() {
        return audioFileUri;
    }

    public void setAudioFileUri(Uri audioFileUri) {
        this.audioFileUri = audioFileUri;
    }

    public Handler getAudioProgressUpdateHandler() {
        return audioProgressUpdateHandler;
    }

    public void setAudioProgressUpdateHandler(Handler audioProgressUpdateHandler) {
        this.audioProgressUpdateHandler = audioProgressUpdateHandler;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pouse(Context context) {
        if (context != null) {
            if (networkstatus.equals("Not connected to Internet")) {
                if (audioPlayer != null) {
                    if (audioPlayer.isPlaying()) {
                        if (Application.isActivityVisible()) {
                            Intent intent1 = new Intent();
                            intent1.setAction(Constants.ACTION.PAUSE_ACTION1);
                            intent1.putExtra("status", "2");
                            context.sendBroadcast(intent1);
                        } else {
                            pauseAudio();
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
                                pauseAudio();
                            }
                        }
                    }
                }
            }
        }
    }

    // Start play audio.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startAudio() {
        try {
            initAudioPlayer();
            if (Application.isActivityVisible()) {
                if (dataisActivityVisible.equals("")) {
                    ((MainActivity) context).getPlay();
                } else {
                    ((BhaktilistActivity) context).getPlay();
                }
            }
        } catch (Exception e) {
            Log.i("2GNetworkProblem2", e.getMessage());
        }
    }

    // Start play audio.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startAudio1(String s) {
        try {
            initAudioPlayer();
            if (audioPlayer != null) {
                audioPlayer.start();
                if (s.equals("1"))
                    if (Application.isActivityVisible()) {
                        if (dataisActivityVisible.equals("")) {
                            ((MainActivity) context).getPouse();
                        } else {
                            ((BhaktilistActivity) context).getPouse();
                        }
                    }
            }
            pouse(context);
        } catch (Exception e) {
            Log.i("2GNetworkProblem2", "" + e.getMessage());
        }
    }

    public void seekadjust(int v) {
        if (audioPlayer != null) {
            int currentPosition = audioPlayer.getDuration() * v / 100;
            audioPlayer.seekTo(currentPosition);
        }
    }

    // Pause playing audio.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pauseAudio() {
        if (audioPlayer != null) {
            audioPlayer.pause();
            if (Application.isActivityVisible()) {
                if (dataisActivityVisible.equals("")) {
                    ((MainActivity) context).getPouse();
                } else {
                    ((BhaktilistActivity) context).getPouse();
                }
            }
        }

    }

    public static String dataisActivityVisible = "";

    // Stop play audio.
    public void stopAudio() {
        if (audioPlayer != null) {
            audioPlayer.stop();
            destroyAudioPlayer();
        }
    }

    // Initialise audio player.
    private void initAudioPlayer() {
        try {
            if (audioPlayer == null) {
                audioPlayer = new MediaPlayer();
                audioPlayer.reset();
                audioPlayer.setOnCompletionListener(this);
                audioPlayer.setOnPreparedListener(this);
                if (!TextUtils.isEmpty(getAudioFileUrl())) {
                    if (isStreamAudio()) {
                        audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    }
                    try {
                        audioPlayer.setDataSource(getAudioFileUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopAudio();
                        return;
                    }
                } else {
                    setAudioFileUri(Uri.parse(getAudioFileUrl()));
                    audioPlayer.setDataSource(getContext(), getAudioFileUri());
                }
                audioPlayer.setOnErrorListener(this);
                setMpLooping(audioPlayer, true);
//                audioPlayer.setLooping(true);
                audioPlayer.setOnBufferingUpdateListener(this);
//                audioPlayer.prepare();
                audioPlayer.prepareAsync();

                // This thread object will send update audio progress message to caller activity every 1 second.
                Thread updateAudioProgressThread = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            // Create update audio progress message.
                            Message updateAudioProgressMsg = new Message();
                            updateAudioProgressMsg.what = UPDATE_AUDIO_PROGRESS_BAR;
                            audioProgressUpdateHandler.sendMessage(updateAudioProgressMsg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                };
                // Run above thread object.
                updateAudioProgressThread.start();

            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }

    }

    // Destroy audio player.
    private void destroyAudioPlayer() {
        if (audioPlayer != null) {
            if (audioPlayer.isPlaying()) {
                audioPlayer.stop();
            }
            audioPlayer.reset();
            audioPlayer.release();
            audioPlayer = null;
        }
    }

    // Return current audio play position.
    public int getCurrentAudioPosition() {
        int ret = 0;
        if (audioPlayer != null) {
            ret = audioPlayer.getCurrentPosition();
        }
        return ret;
    }

    // Return total audio file duration.
    public long getTotalAudioDuration() {
        long ret = 0;
        if (audioPlayer != null) {
            ret = audioPlayer.getDuration();
        }
        return ret;
    }

    // Return current audio player progress value.
    public long getAudioProgress() {
        long ret = 0;
        long currAudioPosition = getCurrentAudioPosition();
        long totalAudioDuration = getTotalAudioDuration();
        if (totalAudioDuration > 0) {
            ret = (currAudioPosition * 100) / totalAudioDuration;
        }
        return ret;
    }

    public int getAudioSeek() {
        float a = audioPlayer.getDuration();
        float a1 = audioPlayer.getCurrentPosition();
        float ret = a1 / a * 100;
        String point = "";
        try {
            point = String.valueOf(ret).substring(0, 4);
        } catch (Exception e) {
            point = String.valueOf(ret).substring(0, 3);
        }
        if (point.equals("99.9") || point.equals("100") || point.equals("100.")) {
//            if (MyApplication.isActivityVisible()) {
            try {
                if (Application.isActivityVisible()) {
                    if (removeStatic.equals("")) {
//                        Intent intent = new Intent();
//                        intent.setAction(Constants.ACTION.NEXT_ACTION);
//                        context.sendBroadcast(intent);
                    }
                } else {
                    if (removeStatic.equals("")) {
//                        performOnEnd();
                    }
                }
            } catch (Exception e) {
                e.getMessage();
//                performOnEnd();
            }
        }

        return (int) ret;
    }

    public static String removeStatic = "";
    public static int CCC = 1;

    public long totalAudioDuration() {
        long totalAudioDuration = getTotalAudioDuration();
        return totalAudioDuration;
    }

    public long currAudioPosition() {
        long currAudioPosition = getCurrentAudioPosition();
        return currAudioPosition;
    }

    String uri = "https://api.soundcloud.com/tracks/678978786/stream/?client_id=6aSX01kZxpetA85mf5R9Ezqs3ozjO2zc";

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        secondaryProgress = percent;
        Log.i("Buffering! ", " " + percent);
    }

    public void storekey(String id, String song_name, String image, String song_url, int point, String count) {
        shilpasetimag = image;
        shilpasettitle = song_name;
        shilpasongid = id;
        mantapoint = point;
        shilpasetSongUrl = song_url;
        shilpasongcount = count;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPrepared(MediaPlayer mp) {
        if (audioPlayer != null) {
            if (!firsttimesongs.equals("start")) {
                audioPlayer.seekTo(0);
                audioPlayer.start();
                pouse(context);
                if (Application.isActivityVisible()) {
                    if (dataisActivityVisible.equals("")) {
                        ((MainActivity) context).getPlay();
                    } else {
                        ((BhaktilistActivity) context).getPlay();
                    }
                }
            } else {
                firsttimesongs = "stop";
            }
        }
    }

    public static String firsttimesongs = "";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCompletion(MediaPlayer mp) {
        shilpaplaypouse = true;
        int c = Integer.parseInt(shilpasongcount);
        if (String.valueOf(CCC).equals(String.valueOf(c))) {
            CCC = 1;
            mantapoint = -1;
            shilpasongid = "0";
            shilpasongcount = "1";
            audioServiceBinder.stopAudio();
            if (Application.isActivityVisible()) {
                try {
                    ((MainActivity) context).stopMusic();
                } catch (Exception e) {
                    ((BhaktilistActivity) context).stopMusic();
                }
            }
        } else {
            CCC++;
            shilpasongcount = String.valueOf(c);
            audioPlayer.start();
            if (Application.isActivityVisible()) {
                if (((MainActivity) context).divadapter != null)
                    ((MainActivity) context).divadapter.notifyDataSetChanged();
            }
        }
    }

    public void setMpLooping(final MediaPlayer mp, final boolean isLooping) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            if (isLooping) {
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                            Toast.makeText(context, "Song Completed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mp.setOnCompletionListener(null);
            }
        } else {
            mp.setLooping(false);
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        try {
            //Handle error however you like i.e with custom message and dialog
            return true; //This indicates that your code handled the error and the OS will not handle the error further.
        } catch (Exception exception) {
            exception.getMessage();
            return false;// reutrning false indicates that your code did not handle the error and the OS will display whatever the default error message is.
        }
    }
}
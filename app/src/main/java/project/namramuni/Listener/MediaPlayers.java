package project.namramuni.Listener;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.IOException;

import project.namramuni.Activity.MainActivity;

import static project.namramuni.Activity.MainActivity.audioPlayer;
import static project.namramuni.background.AudioService.shilpasetSongUrl;

public class MediaPlayers {
    public static void getMusic(String uri) {
        if (audioPlayer == null) {
            audioPlayer = new MediaPlayer();
            audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                audioPlayer.setDataSource(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                audioPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            audioPlayer.start();
        } else {
            audioPlayer.stop();
            audioPlayer.reset();
            audioPlayer.release();
            audioPlayer = null;
            audioPlayer = new MediaPlayer();
            audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                audioPlayer.setDataSource(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                audioPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            audioPlayer.start();
        }
    }

    public static void getMusicPre(String uri) {
        if (audioPlayer == null) {
            audioPlayer = new MediaPlayer();
            audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                audioPlayer.setDataSource(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                audioPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (audioPlayer.isPlaying()) {
                audioPlayer.stop();
                audioPlayer.reset();
                audioPlayer.release();
                audioPlayer = null;
                audioPlayer = new MediaPlayer();
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    audioPlayer.setDataSource(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    audioPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    audioPlayer.setDataSource(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    audioPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getMusicStop() {
        if (audioPlayer != null)
            if (audioPlayer.isPlaying()) {
                audioPlayer.stop();
                audioPlayer.reset();
                audioPlayer.release();
                audioPlayer = null;
                audioPlayer = new MediaPlayer();
            }
    }
    public static String getPlaynowsongUrl;
    public static void getPlayingView(String url, String id, String img, String title, Context context) {
        try {
            if (audioPlayer != null) {
                if (audioPlayer.isPlaying()) {
                    getPlaynowsongUrl = shilpasetSongUrl;
                } else {
                    if (shilpasetSongUrl.equals("")) {
                        getPlaynowsongUrl = url;
                        Toast.makeText(context, "Online Audio", Toast.LENGTH_SHORT).show();
//                        audioServiceBinder.storekey(id, title, img, getPlaynowsongUrl);
                        ((MainActivity)context).getPlayPouse();
                    } else {
//                        ((MainActivity)context).setMusic(this);
                        ((MainActivity)context).getPlayPouse();
                    }
                }
            }
        } catch (Exception e) { }
    }

}

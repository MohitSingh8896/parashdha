package project.namramuni.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import project.namramuni.R;


/**
 * Created by James From CoderzHeaven on 5/2/18.
 */

public class Constants {

    // Files
  //  public static final String DOWNLOAD_AUDIO_URL = "http://www.noiseaddicts.com/samples_1w72b820/272.mp3";
    //public static final String FILE_NAME = "audio.mp3";
    //public static final String TEMP_FILE_NAME = "temp";
    public static final String FILE_EXT = ".mp3";
    public static final String FILE_EXT_VIDEO = ".mp4";

    //public static final String FILE_EXT_VIDEO = ".3gp";


    //  public static final String DIR_NAME = "Audio";

    public static final String DIR_NAME = "Subscriptionaudio";
    public static final int OUTPUT_KEY_LENGTH = 256;

    public static final String DIR_NAME_VIDEO="Subscriptionvideo";
    // Algorithm
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String KEY_SPEC_ALGORITHM = "AES";
    public static final String PROVIDER = "BC";

    public static final String SECRET_KEY = "SECRET_KEY";

    public interface ACTION {
        public static String MAIN_ACTIONClick = "com.marothiatechs.customnotification.action.click";
        public static String MAIN_ACTION = "com.marothiatechs.customnotification.action.main";
        public static String INIT_ACTION = "com.marothiatechs.customnotification.action.init";
        public static String PREV_ACTION = "com.marothiatechs.customnotification.action.prev";
        public static String PLAY_ACTION = "com.marothiatechs.customnotification.action.play";
        public static String PLAY_ACTION1 = "com.marothiatechs.customnotification.action.play";
        public static String PAUSE_ACTION = "com.marothiatechs.customnotification.action.pause";
        public static String PAUSE_ACTION1 = "com.marothiatechs.customnotification.action.pause1";
        public static String NEXT_ACTION = "com.marothiatechs.customnotification.action.next";
        public static String STARTFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.marothiatechs.customnotification.action.stopforeground";

    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }

    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.logo, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }


}

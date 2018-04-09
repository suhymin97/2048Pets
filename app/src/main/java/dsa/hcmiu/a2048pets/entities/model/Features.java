package dsa.hcmiu.a2048pets.entities.model;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.telecom.RemoteConnection;
import android.widget.ImageView;

import com.facebook.CallbackManager;

public class Features { //store biến static
    public static long uidCount = 0;
    public static int totalScore=0;
    public static int maxUndo=5;
    public static int maxKey=1;
    public static CallbackManager callbackManager;
    public static boolean Loggedfb;
    public static MediaPlayer mySong;
    public static boolean sound = true;
    public static ImageView AVA;

    public static int getMaxUndo() {
        return maxUndo;
    }

    public static void setMaxUndo(int maxUndo) {
        Features.maxUndo = maxUndo;
    }

    public static int getMaxKey() {
        return maxKey;
    }

    public static void setMaxKey(int maxKey) {
        Features.maxKey = maxKey;
    }
}

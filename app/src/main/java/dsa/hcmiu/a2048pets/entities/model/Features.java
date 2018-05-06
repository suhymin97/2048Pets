package dsa.hcmiu.a2048pets.entities.model;

import android.media.MediaPlayer;
import android.widget.ImageView;

import com.facebook.CallbackManager;

public class Features { //store biến static
    public static long uidCount = 0;
    public static int totalScore=2000;
    public static int maxUndo=5;
    public static int maxHammer =5;
    public static CallbackManager callbackManager;
    public static boolean Loggedfb;
    public static MediaPlayer mySong;
    public static boolean sound = true;
    public static ImageView AVA;
    public static User user = new User();

    public static int getMaxUndo() {
        return maxUndo;
    }

    public static void setMaxUndo(int maxUndo) {
        Features.maxUndo = maxUndo;
    }

    public static int getMaxHammer() {
        return maxHammer;
    }

    public static void setMaxHammer(int maxHammer) {
        Features.maxHammer = maxHammer;
    }
}

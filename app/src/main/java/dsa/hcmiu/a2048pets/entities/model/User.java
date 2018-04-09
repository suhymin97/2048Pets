package dsa.hcmiu.a2048pets.entities.model;

import android.media.Image;

public class User extends Features {
    private long UID = Features.uidCount++;
    private String name = "Anynomyous";
    private String IDFacebook = null;
    private Image avatar;
    private Long Score;
    private int Undo;
    private int Key;
}

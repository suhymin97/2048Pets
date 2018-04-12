package dsa.hcmiu.a2048pets.entities.model;

import android.app.Application;
import android.content.Context;
import android.media.Image;
import android.provider.Contacts;
import android.util.Log;

import dsa.hcmiu.a2048pets.R;


public class User extends Features {
    private long UID = 0;
    private String name = null;
    private String email = null;
    private String IDFacebook = null;
    private Image avatar;
    private Long Score;
    private int Undo;
    private int Key;

    public User() {
        name = "Anynomyous";
        UID = Features.uidCount++;
        Log.d("Add user", String.valueOf(UID));
    }

    public boolean isDefault() {
        return UID==0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIDFacebook() {
        return IDFacebook;
    }

    public void setIDFacebook(String IDFacebook) {
        this.IDFacebook = IDFacebook;
    }
}

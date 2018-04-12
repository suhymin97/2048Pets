package dsa.hcmiu.a2048pets.entities.model;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;

import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.MyApplication;


public class User extends Features {
    private long UID = 0;
    private String name = null;
    private String email = null;
    private String IDFacebook = null;
    private Bitmap avatar;
    private Long Score;
    private int Undo;
    private int Key;

    public User() {
        name = MyApplication.getContext().getResources().getString(R.string.default_name);
        UID = ++Features.uidCount;
        Log.d("Add user", String.valueOf(UID));
        setAvatar(R.drawable.default_ava);
    }

    public boolean isDefault() {
        return UID == 0 && name.equals(MyApplication.getContext().getResources().getString(R.string.default_name));
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public void setAvatar(int id) {
        avatar = BitmapFactory.decodeResource(MyApplication.getContext().getResources(), id);
    }

    public void setAvatar(String link) {
        Picasso.get().load(link).into(new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("USER","downloaded bitmap " + String.valueOf(bitmap == null));
                avatar = bitmap;
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("USER","Failed download image");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
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

package dsa.hcmiu.a2048pets;

import android.content.Context;

public class MyApplication extends android.app.Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getContext() {
        return MyApplication.context;
    }
}

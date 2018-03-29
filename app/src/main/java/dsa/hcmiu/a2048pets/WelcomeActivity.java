package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class WelcomeActivity extends Activity {

    private  int WelcomeInteval = 2000;

    @Override
    public void onBackPressed() {
        Intent i= new Intent(WelcomeActivity.this,MenuActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(WelcomeActivity.this,dsa.hcmiu.a2048pets.PlayActivity.class);
                startActivity(i);

                this.finish();            }

            private void finish() {
                //TODO
            }
        },WelcomeInteval);
        */


    }
}

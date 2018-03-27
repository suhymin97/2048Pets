package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MenuActivity extends Activity implements View.OnClickListener {

    Button bMenuPlay, bStore, bMenuSetting;
    MediaPlayer mySong,myClick;
    Button button1,button2,button3;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bMenuPlay = (Button) findViewById(R.id.bMenuPlay);
        bMenuSetting = (Button) findViewById(R.id.bRule);
        bStore = (Button) findViewById(R.id.bStore);

        bMenuSetting.setOnClickListener(this);
        bMenuPlay.setOnClickListener(this);
        bStore.setOnClickListener(this);

        //sound
        mySong= MediaPlayer.create(MenuActivity.this,R.raw.song);
        myClick= MediaPlayer.create(MenuActivity.this,R.raw.click);

        //callButton
        button1 = (Button) findViewById(R.id.bMenuPlay);
        button2 = (Button) findViewById(R.id.bStore);
        button3 = (Button) findViewById(R.id.bRule);
        //callAnimation

        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);

        button1.setAnimation(uptodown);
        button2.setAnimation(uptodown);
        button3.setAnimation(downtoup);

        //loopSound
        mySong.setLooping(true);
        mySong.start();


    }
    public void playIT(View view){
        myClick.start();
    }
    public void storeIT(View view){
        myClick.start();
    }
    public void settingIT(View view){
        myClick.start();
    }

    //diaglog
    @Override
    public void onBackPressed() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MenuActivity.this);
        a_builder.setMessage("Do you want to quit the game?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Quit game");
        alert.show();
        super.onBackPressed();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bMenuPlay:
                Intent iPlay = new Intent(this, PlayActivity.class);
                startActivity(iPlay);
                break;
            case R.id.bStore:
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MenuActivity.this);
                a_builder.setMessage("Coming Soon!!!")
                        .setCancelable(true);
                AlertDialog alert = a_builder.create();
                alert.setTitle("2048 Pets Features");
                alert.show();
                break;
            case R.id.bRule:
                Intent iRule = new Intent(this, RulesActivity.class);
                startActivity(iRule);
                break;
        }
    }
}

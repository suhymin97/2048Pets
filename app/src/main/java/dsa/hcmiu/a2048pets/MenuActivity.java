package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends Activity implements View.OnClickListener {

    Button bMenuPlay, bStore, bMenuSetting;
    MediaPlayer myClick;
    public static MediaPlayer mySong;
    Button btnPlay, btnStore, btnRule;
    Animation uptodown,downtoup;
    ImageView imgLoading;
    LinearLayout layMenu;

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
        btnPlay = (Button) findViewById(R.id.bMenuPlay);
        btnStore = (Button) findViewById(R.id.bStore);
        btnRule = (Button) findViewById(R.id.bRule);
        //callAnimation

        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);

        btnPlay.setAnimation(uptodown);
        btnStore.setAnimation(uptodown);
        btnRule.setAnimation(downtoup);

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
    /*
    public void loadingScreen(){
        imgLoading = (ImageView) findViewById(R.id.imgLoading);
        layMenu = (LinearLayout) findViewById(R.id.layMenu);
        layMenu.setVisibility(View.GONE);
        for (int i =0; i<20000;i++);
        imgLoading.setVisibility(View.GONE);
        layMenu.setVisibility(View.VISIBLE);
    } */
    @Override
    protected void onPause() {
        super.onPause();
        mySong.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySong.start();
    }

    @Override
    public void onBackPressed() {
        final Dialog MyDialog = new Dialog(MenuActivity.this,R.style.FullHeightDialog);
        LayoutInflater inflater = MenuActivity.this.getLayoutInflater();
        MyDialog.setContentView(R.layout.dialog);
        Button btnyes = (Button) MyDialog.findViewById(R.id.btnyes);
        Button btnno = (Button) MyDialog.findViewById(R.id.btnno);
        TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage) ;
        tvMess.setText("Do you like this game? Let's take a visit to our open source app.");
        btnyes.setText("Github");
        btnno.setText("Not now");
        Animation zoomin= AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoom_out);
        ImageView imgIcon = (ImageView) MyDialog.findViewById(R.id.icon_github);
        imgIcon.setAnimation(zoomin);
        imgIcon.setAnimation(zoomout);

        //btnyes.setEnabled(true);
        //btnno.setEnabled(true);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/suhymin97/2048Pets")));
                System.exit(0);
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        MyDialog.show();
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

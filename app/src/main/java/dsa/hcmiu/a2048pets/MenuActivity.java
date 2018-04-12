package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.login.LoginManager;

import dsa.hcmiu.a2048pets.entities.handle.HandleImage;
import dsa.hcmiu.a2048pets.entities.handle.HandleSound;
import dsa.hcmiu.a2048pets.entities.model.Features;

import static dsa.hcmiu.a2048pets.entities.model.Features.mySong;
import static dsa.hcmiu.a2048pets.entities.model.Features.sound;

public class MenuActivity extends Activity implements View.OnClickListener {

    MediaPlayer myClick;
    private ImageButton btnSound,btnQuit,btnPlay, btnStore, btnRule;
    Animation uptodown,downtoup;
    ImageView imgFb,ivCupCat,ivShadow;
    LinearLayout layMenu;
    private TextView tvTotalScore;
    MediaPlayer snd_singleKitty;
    private Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPlay = (ImageButton) findViewById(R.id.bMenuPlay);
        btnRule = (ImageButton) findViewById(R.id.bRule);
        btnStore = (ImageButton) findViewById(R.id.bStore);
        btnQuit = (ImageButton) findViewById(R.id.bQuit);
        btnSound = (ImageButton) findViewById(R.id.btnSound);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        imgFb = (ImageView) findViewById(R.id.ivAvaFb);
        ivCupCat = (ImageView) findViewById(R.id.ivCupCat);
        ivShadow = (ImageView) findViewById(R.id.ivCupCatShadow);
        tvTotalScore = (TextView) findViewById(R.id.tvTotalScore);

        update();
        btnRule.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnStore.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        cupCatSetup();
        soundSetup();
        }

    private void cupCatSetup() {
        Animation wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        Animation linear = AnimationUtils.loadAnimation(this, R.anim.linear_move);
        ivCupCat.setAnimation(wobble);
        ivShadow.setAnimation(linear);
        final HandleSound snd_Cats = new HandleSound(this, R.raw.bunch_cat);
        final MediaPlayer snd_singleKitty = MediaPlayer.create(this, R.raw.single_kitty);
        ivCupCat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        snd_Cats.getSong().seekTo(0);
                        snd_Cats.startFadeIn();
                        break;
                    case MotionEvent.ACTION_UP:
                        snd_Cats.startFadeOut();
                        break;
                }
                return true;
            }
        });
    }
    public void moewIT(View view){
        snd_singleKitty.start();
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

    @Override //Activity pause
    protected void onPause() {
        super.onPause();
        Features.mySong.pause();
    }

    @Override //Activity resume
    protected void onResume() {
        super.onResume();
        if (Features.sound) Features.mySong.start();
        update();
    }

    @Override
    public void onBackPressed() {
        quit();
    }

    private void quit() {
        final Dialog MyDialog = new Dialog(MenuActivity.this,R.style.FullHeightDialog);
        LayoutInflater inflater = MenuActivity.this.getLayoutInflater();
        MyDialog.setContentView(R.layout.dialog);
        Button btnyes = (Button) MyDialog.findViewById(R.id.btnyes);
        Button btnno = (Button) MyDialog.findViewById(R.id.btnno);
        TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage) ;

        tvMess.setText("Do you like this game? Let's take a visit to our open source app.");
        btnyes.setText("Github");
        btnno.setText("Not now");
        //set Anim for icon github
        Animation zoomin= AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoom_out);
        ImageView imgIcon = (ImageView) MyDialog.findViewById(R.id.icon_github);
        imgIcon.setAnimation(zoomin);
        imgIcon.setAnimation(zoomout);

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
            case R.id.bQuit:
                quit();
                break;
            case R.id.btnSound:
                if (sound) mySong.pause();
                else mySong.start();
                sound= !(sound || sound);
                break;
            case R.id.btnProfile:
                Intent iPro5 = new Intent(this, ProfileActivity.class);
                startActivity(iPro5);
        }
    }
    private void soundSetup() {
        //sound
        Features.mySong= MediaPlayer.create(MenuActivity.this,R.raw.song);
        myClick= MediaPlayer.create(MenuActivity.this,R.raw.click);

        //callAnimation
        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);

        btnPlay.setAnimation(uptodown);
        btnStore.setAnimation(uptodown);
        btnRule.setAnimation(downtoup);

        //loopSound
        mySong.setLooping(true);
        if (sound) mySong.start();
    }

    private void update() {
        tvTotalScore.setText(String.valueOf(Features.totalScore));
        if (HandleImage.get().checkIfImageExist(this))
            HandleImage.get().loadImageFromDisk(this,imgFb);
    }
}

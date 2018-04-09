package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import dsa.hcmiu.a2048pets.entities.handle.HandleGame;
import dsa.hcmiu.a2048pets.entities.model.Features;

import static android.widget.Toast.LENGTH_SHORT;
import static dsa.hcmiu.a2048pets.entities.model.Features.callbackManager;
import static dsa.hcmiu.a2048pets.entities.model.Features.mySong;
import static dsa.hcmiu.a2048pets.entities.model.Features.sound;

public class MenuActivity extends Activity implements View.OnClickListener {

    Button bMenuPlay, bStore, bMenuSetting;
    MediaPlayer myClick;
    Button btnPlay, btnStore, btnRule, btnSound, btnProfile;
    Animation uptodown,downtoup;
    ImageView imgFb;
    LinearLayout layMenu;
    String email,name,fname;
    private TextView tvTotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bMenuPlay = (Button) findViewById(R.id.bMenuPlay);
        bMenuSetting = (Button) findViewById(R.id.bRule);
        bStore = (Button) findViewById(R.id.bStore);
        btnSound = (Button) findViewById(R.id.btnSound);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        imgFb = (ImageView) findViewById(R.id.ivAvaFb);
        tvTotalScore = (TextView) findViewById(R.id.tvTotalScore);

        update();
        bMenuSetting.setOnClickListener(this);
        bMenuPlay.setOnClickListener(this);
        bStore.setOnClickListener(this);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound) mySong.pause();
                else mySong.start();
                sound= !(sound || sound);
            }
        });

        soundSetup();

        //login Fb - Dialog
        if (Features.Loggedfb = (AccessToken.getCurrentAccessToken() == null)) {
            btnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MenuActivity.this,"Login facebook!",LENGTH_SHORT).show();
                    final Dialog MyDialog = new Dialog(MenuActivity.this, R.style.FullHeightDialog);
                    LayoutInflater inflater = MenuActivity.this.getLayoutInflater();
                    MyDialog.setContentView(R.layout.login_facebook);
                    final LoginButton btnlogin = (LoginButton) MyDialog.findViewById(R.id.btnLogin);
                    final TextView tvNick = (TextView) MyDialog.findViewById(R.id.tvNick);
                    final ProfilePictureView ivAva = (ProfilePictureView) MyDialog.findViewById(R.id.ivAvaFb);
                    final Button btnLogout = (Button) MyDialog.findViewById(R.id.btnLogout);

                    //VISIBLE = Hiện; INVISIBLE = Tàng hình; GONE = Mất tích
                    btnlogin.setReadPermissions(Arrays.asList("public_profile", "email"));
                    ivAva.setVisibility(View.VISIBLE);
                    btnlogin.setVisibility(View.VISIBLE);
                    tvNick.setVisibility(View.GONE);
                    btnLogout.setVisibility(View.GONE);

                    btnlogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            btnlogin.setVisibility(View.GONE);
                            ivAva.setVisibility(View.VISIBLE);
                            tvNick.setVisibility(View.VISIBLE);
                            btnLogout.setVisibility(View.VISIBLE);

                            GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            Log.d("JSON", response.getJSONObject().toString());
                                            try {
                                                email = object.getString("email");
                                                name = object.getString("name");
                                                fname = object.getString("first_name");
                                                tvNick.setText(name);
                                                String userID = Profile.getCurrentProfile().getId();
                                                ivAva.setProfileId(userID);
                                                Picasso.get().load("https://graph.facebook.com/" + userID+ "/picture?type=large").into(imgFb);
                                            } catch (JSONException e) {
                                                Toast.makeText(MenuActivity.this, "Error JSON", LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "name,email,first_name,picture");
                            graphRequest.setParameters(parameters);
                            graphRequest.executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }

                    });
                    MyDialog.show();
                }
            });
        }
        //if (Features.AVA!= null) imgFb.setImageBitmap(Features.AVA);
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
        }
    }
    private void soundSetup() {
        //sound
        Features.mySong= MediaPlayer.create(MenuActivity.this,R.raw.song);
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
        if (sound) mySong.start();
    }

    @Override //Xin response từ fb: gửi 3 dữ liệu dưới cho fb
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Features.callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override //acti được bắt đầu
    protected void onStart() {
        super.onStart();
        LoginManager.getInstance().logOut();
    }

    private void update() {
        tvTotalScore.setText(String.valueOf(Features.totalScore));
    }

}

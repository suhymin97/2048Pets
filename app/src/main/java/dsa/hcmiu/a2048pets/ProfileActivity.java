package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import dsa.hcmiu.a2048pets.entities.handle.HandleImage;
import dsa.hcmiu.a2048pets.entities.model.Features;
import dsa.hcmiu.a2048pets.entities.model.User;

import static dsa.hcmiu.a2048pets.entities.model.Features.callbackManager;

public class ProfileActivity extends Activity {

    private LoginButton btnlogin;
    private TextView tvNick;
    private ImageView ivAva;
    private ProfileTracker mProfileTracker;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = new User();
        btnlogin = (LoginButton) findViewById(R.id.btnLogin);
        tvNick = (TextView) findViewById(R.id.tvNick);
        ivAva = (ImageView) findViewById(R.id.ivAvaFb);

        //VISIBLE = Hiện; INVISIBLE = Tàng hình; GONE = Mất tích
        btnlogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        btnlogin.setVisibility(View.VISIBLE);
        tvNick.setVisibility(View.GONE);
        //update();
        if (Features.Loggedfb = (AccessToken.getCurrentAccessToken() == null)) {
            Log.d("ACESS TOKEN", String.valueOf(Features.Loggedfb));
            btnlogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d("FACEBOOK LOGIN", loginResult.toString());
                    btnlogin.setVisibility(View.VISIBLE);
                    tvNick.setVisibility(View.VISIBLE);
                    Profile profile = Profile.getCurrentProfile();
                    if (profile == null) {
                        mProfileTracker = new ProfileTracker() {
                            @Override
                            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                                Log.v("facebook - profile", currentProfile.getId());
                                /*String userID = Profile.getCurrentProfile().getId();
                                ivWidFb.setProfileId(userID);
                                Picasso.get().load("https://graph.facebook.com/" + userID+ "/picture?type=large").into(ivAva);
                                tvNick.setText(currentProfile.getName());*/
                                getProfile(currentProfile.getId(), currentProfile.getName());
                                mProfileTracker.stopTracking();
                            }
                        };
                        // no need to call startTracking() on mProfileTracker
                        // because it is called by its constructor, internally.
                    } else {
                        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.d("JSON", response.getJSONObject().toString());
                                        try {
                                            user.setEmail(object.getString("email"));
                                            /*user.setName(object.getString("name"));
                                            tvNick.setText(user.getName());
                                            String userID = Profile.getCurrentProfile().getId();
                                            Picasso.get().load("https://graph.facebook.com/" + userID + "/picture?type=large").into(ivAva);*/
                                            getProfile(Profile.getCurrentProfile().getId(), object.getString("name"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,email,first_name,picture");
                        graphRequest.setParameters(parameters);
                        graphRequest.executeAsync();
                    }
                }

                @Override
                public void onCancel() {
                    Log.v("facebook - onCancel", "cancelled");
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.v("facebook - onError", exception.toString());
                }

            });
        }
    }

    private void getProfile(String id, String name) {
        Log.d("Save user" , name);
        user.setIDFacebook(id);
        user.setName(name);
        HandleImage.get().downloadSaveImageFromUrl(this,
                "https://graph.facebook.com/" + user.getIDFacebook() + "/picture?type=large");
        while (HandleImage.get().checkIfImageExist(this));
        update();
    }

    @Override //Activity resume
    protected void onResume() {
        super.onResume();
        if (user.isDefault()) update();
    }

    private void update() {
        Log.d("UPDATE user",user.getName());
        tvNick.setText(user.getName());
        if (!HandleImage.get().loadImageFromDisk(this, ivAva))
            ivAva.setImageResource(R.drawable.default_ava);
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
}

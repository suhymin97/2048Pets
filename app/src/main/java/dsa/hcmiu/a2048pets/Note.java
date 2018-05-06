package dsa.hcmiu.a2048pets;

public class Note {
}

/*
package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;

import dsa.hcmiu.a2048pets.entities.handle.FbConnectHelper;
import dsa.hcmiu.a2048pets.entities.handle.HandleFile;
import dsa.hcmiu.a2048pets.entities.handle.HandleImage;

public class ProfileActivity extends Activity implements FbConnectHelper.OnFbSignInListener {

private Button btnlogin;
private TextView tvNick;
private ImageView ivAva;
private ProfileTracker mProfileTracker;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        tvNick = (TextView) findViewById(R.id.tvNick);
        ivAva = (ImageView) findViewById(R.id.ivAvaFb);

        //VISIBLE = Hiện; INVISIBLE = Tàng hình; GONE = Mất tích
        //btnlogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        btnlogin.setVisibility(View.VISIBLE);
        tvNick.setVisibility(View.GONE);


        update();
        //login_facebook();
        //logout_facebook();
        }

private void logout_facebook() {
        LoginManager.getInstance().logOut();

        }

public void onFacebookClicked(View view) {
        //When Sign in with Facebook button is Clicked
        FbConnectHelper.with(this).connect();
        }

/*
 private void login_facebook() {
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
                                         HandleFile.get().getUser().setEmail(object.getString("email"));
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
     HandleFile.get().getUser().setIDFacebook(id);
     HandleFile.get().getUser().setName(name);
     //HandleFile.get().getUser().setAvatar("https://graph.facebook.com/" + HandleFile.get().getUser().getIDFacebook() + "/picture?type=large");
     HandleImage.get().downloadSaveImageFromUrl(MyApplication.getContext(),
             "https://graph.facebook.com/" + HandleFile.get().getUser().getIDFacebook() + "/picture?type=large");
     try {
         Thread.sleep(5000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
     update();

     //Picasso.get().load("https://graph.facebook.com/" + HandleFile.get().getUser().getIDFacebook() + "/picture?type=large").into(ivAva);
 }
    @Override //Activity resume
    protected void onResume() {
        super.onResume();
        if (HandleFile.get().getUser().isDefault()) update();
    }

    private void update() {
        Log.d("UPDATE user",HandleFile.get().getUser().getName());
        tvNick.setText(HandleFile.get().getUser().getName());
        if (!HandleImage.get().loadImageFromDisk(this, ivAva)) {
            ivAva.setImageResource(R.drawable.default_ava);
            //ivAva.setImageBitmap(HandleFile.get().getUser().getAvatar());
        }
    }
    @Override //Xin response từ fb: gửi 3 dữ liệu dưới cho fb
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FbConnectHelper.with(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override //acti được bắt đầu
    protected void onStart() {
        super.onStart();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void OnFbSuccess(GraphResponse graphResponse) {
        HandleFile.get().setUser(FbConnectHelper.with(this).getUserFromGraphResponse(graphResponse));
        if(HandleFile.get().getUser()!=null) {
            //SharedPreferenceManager.getSharedInstance().saveUserModel(HandleFile.get().getUser());
            startProfileActivity();
        }
    }

    @Override
    public void OnFbError(String errorMessage) {
        resetToDefaultView(errorMessage);
    }

    private void startProfileActivity()
    {
        /*
        Intent intent = new Intent(this, HomeActivity.class);
        //intent.putExtra(UserModel.class.getSimpleName(), userModel);
        startActivity(intent);
        this.finishAffinity();
        btnlogin.setVisibility(View.GONE);
        update();
    }

    private void resetToDefaultView(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        btnlogin.setVisibility(View.VISIBLE);
    }

        }

 */
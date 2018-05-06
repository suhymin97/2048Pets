package dsa.hcmiu.a2048pets;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import dsa.hcmiu.a2048pets.profile_shop.FragmentProfile;
import dsa.hcmiu.a2048pets.profile_shop.SendData;

import static dsa.hcmiu.a2048pets.entities.model.Features.mySong;
import static dsa.hcmiu.a2048pets.entities.model.Features.sound;

public class ProfileActivity extends Activity implements SendData {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (sound) mySong.start();
        setContentView(R.layout.activity_profile);
    }

    @Override
    public void data(boolean update, int ava) {
        FragmentProfile fragmentProfile = (FragmentProfile) getFragmentManager().
                findFragmentById(R.id.fragmentProfile);
        if (update) fragmentProfile.update();
        if (ava != -1) fragmentProfile.setIvAva(ava);
    }
}

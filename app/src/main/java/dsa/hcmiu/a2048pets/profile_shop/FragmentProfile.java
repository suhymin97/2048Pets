package dsa.hcmiu.a2048pets.profile_shop;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.ProfileTracker;

import de.hdodenhof.circleimageview.CircleImageView;
import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.entities.handle.FbConnectHelper;
import dsa.hcmiu.a2048pets.entities.model.Features;

public class FragmentProfile extends Fragment{

    TextView tvGold, tvHighscore, tvUndo, tvHammer;
    SendData sendData;
    CircleImageView ivAva;
    private Button btnlogin;
    private TextView tvNick;
    private ProfileTracker mProfileTracker;
    private FbConnectHelper fbConnectHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        tvHighscore = (TextView) view.findViewById(R.id.tvAchiveHighscore);
        tvUndo = (TextView) view.findViewById(R.id.tvAchiveUndo);
        tvHammer = (TextView) view.findViewById(R.id.tvAchiveHammer);
        ivAva = (CircleImageView) view.findViewById(R.id.ivAvaFb);
        update();
        btnlogin = (Button) view.findViewById(R.id.btnLogin);
        tvNick = (TextView) view.findViewById(R.id.tvNick);

        //VISIBLE = Hiện; INVISIBLE = Tàng hình; GONE = Mất tích
        //btnlogin.setReadPermissions(Arrays.asList("public_profile", "email"));
        btnlogin.setVisibility(View.VISIBLE);

        return view;
    }

    public void update() {
        tvHighscore.setText(String.valueOf(Features.totalScore));
        tvUndo.setText(String.valueOf(Features.getMaxUndo()));
        tvHammer.setText(String.valueOf(Features.getMaxHammer()));
    }

    public void setIvAva(int resid) {
        ivAva.setImageResource(resid);
    }
}

package dsa.hcmiu.a2048pets;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dsa.hcmiu.a2048pets.entities.model.Features;

public class FragmentProfile extends Fragment{

    TextView tvGold, tvHighscore, tvUndo, tvHammer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        tvHighscore = (TextView) view.findViewById(R.id.tvAchiveHighscore);
        tvUndo = (TextView) view.findViewById(R.id.tvAchiveUndo);
        tvHammer = (TextView) view.findViewById(R.id.tvAchiveHammer);
        update();
        return view;
    }

    private void update() {
        tvHighscore.setText(String.valueOf(Features.totalScore));
        tvUndo.setText(String.valueOf(Features.getMaxUndo()));
        tvHammer.setText(String.valueOf(Features.getMaxKey()));
    }
}

package dsa.hcmiu.a2048pets;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.entities.adapter.ShopAdapter;
import dsa.hcmiu.a2048pets.entities.model.ShopItem;

import static dsa.hcmiu.a2048pets.entities.model.Features.totalScore;

public class FragmentShopping extends Fragment {
    private ArrayList<ShopItem> array;
    ShopAdapter adapter;
    TextView tvGold;
    ListView listItem;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_store,container,false);
        init();
        adapter = new ShopAdapter(getActivity(), R.layout.item_shop, array);

        listItem = (ListView) view.findViewById(R.id.lvShopping);
        tvGold = (TextView) view.findViewById(R.id.tvAchiveGold);
        tvGold.setText(String.valueOf(totalScore));
        listItem.setAdapter(adapter);
        return view;
    }

    private void addItem(String name, long id, int picture, long price, boolean purchase) {
        array.add(new ShopItem(name, id, picture, price, purchase));
    }

    private void init() {
        array = new ArrayList<>();
        addItem("undo", 0, R.drawable.poke, 100, false);
        addItem("key", 1, R.drawable.poke, 500, false);
        addItem("avaDefault", 2, R.drawable.default_ava, 0,true);
    }
}

package dsa.hcmiu.a2048pets.profile_shop;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.MyApplication;
import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.entities.adapter.ShopAdapter;
import dsa.hcmiu.a2048pets.entities.model.Features;
import dsa.hcmiu.a2048pets.entities.model.ShopItem;

import static dsa.hcmiu.a2048pets.entities.model.Features.totalScore;

public class FragmentShopping extends Fragment {
    private ArrayList<ShopItem> array;
    ShopAdapter adapter;
    TextView tvGold,tvPrice;
    GridView listItem;
    View view;
    ImageView ivShopItem;
    ImageButton btnPurchase;
    ShopItem selectItem;
    SendData sendData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_store,container,false);
        init();
        selectItem = array.get(0);
        adapter = new ShopAdapter(getActivity(), R.layout.item_shop, array);

        listItem = (GridView) view.findViewById(R.id.lvShopping);
        tvGold = (TextView) view.findViewById(R.id.tvAchiveGold);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice) ;
        ivShopItem = (ImageView) view.findViewById(R.id.ivShopItem);
        btnPurchase = (ImageButton) view.findViewById(R.id.btnPurchase);
        sendData = (SendData) getActivity();

        showItem();
        update();
        listItem.setAdapter(adapter);
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout relativeLayout= (RelativeLayout) view;
                relativeLayout.setBackgroundColor(MyApplication.getContext().getResources().getColor(R.color.white));
                selectItem = array.get(position);
                showItem();
            }
        });
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalScore < selectItem.getPrice()) {
                    return;
                }
                int ava = -1;
                totalScore -= selectItem.getPrice();
                switch(selectItem.getId()) {
                    case 0:
                        Features.maxUndo++;
                        break;
                    case 1:
                        Features.maxHammer++;
                        break;
                    default:
                        if (selectItem.isPurchase()) ava = selectItem.getPicture();
                        selectItem.setPurchase(true);
                        selectItem.setPrice(0);
                        showItem();
                        break;
                }
                sendData.data(true, ava);
                update();
            }
        });

        return view;
    }

    private void addItem(String name, int id, int picture, long price, boolean purchase) {
        array.add(new ShopItem(name, id, picture, price, purchase));
    }

    private void init() {
        array = new ArrayList<>();
        addItem("undo", 0, R.drawable.undo, 100, false);
        addItem("hammer", 1, R.drawable.hammer, 1000, false);
        addItem("avaDefault", 2, R.drawable.default_ava, 0,true);
        addItem("avaPika", 2, R.drawable.ic_round, 50,false);
        addItem("avaPika", 2, R.drawable.pikachu2, 200,false);
        addItem("avaPoke", 2, R.drawable.poke, 300,false);

    }

    public void showItem() {
        tvPrice.setText(String.valueOf(selectItem.getPrice()));
        ivShopItem.setImageResource(selectItem.getPicture());
        if (selectItem.isPurchase()) btnPurchase.setImageResource(R.drawable.equip);
        else btnPurchase.setImageResource(R.drawable.purchase);
    }

    public void update() {
        tvGold.setText(String.valueOf(totalScore));
    }
}

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
import android.widget.Toast;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.MyApplication;
import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.entities.adapter.ShopAdapter;
import dsa.hcmiu.a2048pets.entities.handle.HandleGame;
import dsa.hcmiu.a2048pets.entities.model.Features;
import dsa.hcmiu.a2048pets.entities.model.ShopItem;

import static dsa.hcmiu.a2048pets.entities.model.Features.user;

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
                if (user.totalGold < selectItem.getPrice()) {
                    Toast.makeText(getActivity(),"Not enough gold. Play 2048 to earn more.",Toast.LENGTH_LONG).show();
                    return;
                }
                int ava = -1;
                user.totalGold -= selectItem.getPrice();
                switch((int) selectItem.getId()/100) {
                    case 0: //undo
                        user.undo++;
                        break;
                    case 1: //hammer
                        user.hammer++;
                        break;
                    case 2: //ava
                        if (selectItem.isPurchase()) {
                            ava = selectItem.getPicture();
                            break;
                        }
                        selectItem.setPurchased();
                        user.purchasedIdItem.add(selectItem.getId());
                        showItem();
                        break;
                    case 3: //theme
                        if (selectItem.isPurchase()) {
                            HandleGame.getInstance().setTheme(selectItem.getId()%100);
                            break;
                        }
                        selectItem.setPurchased();
                        user.purchasedIdItem.add(selectItem.getId());
                        showItem();
                        break;
                    case 4: //fb ava
                        if (!user.isLoggedFb()) {
                            Toast.makeText(getActivity(),"You haven't connect with facebook",Toast.LENGTH_LONG).show();
                            user.totalGold += selectItem.getPrice();
                            break;
                        }
                        if (selectItem.isPurchase()) {
                            ava = 0;
                            selectItem.setPurchase(false);
                            showItem();
                            break;
                        }
                        selectItem.setPurchase(true);
                        showItem();
                        break;
                }
                sendData.data(true, ava);
                update();
            }
        });

        return view;
    }

    private void addItem(String name, int id, int picture, long price) {
        array.add(new ShopItem(name, id, picture, price));
    }

    private void init() {
        array = new ArrayList<>();
        addItem("undo", 000, R.drawable.undo, 100);
        addItem("hammer", 100, R.drawable.hammer, 1000);
        addItem("avaDefault", 200, R.drawable.default_ava, 0);
        addItem("avaPika", 201, R.drawable.pikachu2, 200);
        addItem("avaPoke", 202, R.drawable.poke, 300);
        addItem("avaDog", 203, R.drawable.ic_round, 300);
        addItem("Facebook", 400, R.drawable.facebook, 1000);
        addItem("theme1", 300, R.raw.no2_1, 0);
        addItem("theme2", 301, R.drawable.theme2, 2000);
    }

    public void showItem() {
        tvPrice.setText(String.valueOf(selectItem.getPrice()));
        ivShopItem.setImageResource(selectItem.getPicture());
        if (selectItem.isPurchase()) btnPurchase.setImageResource(R.drawable.equip);
        else btnPurchase.setImageResource(R.drawable.purchase);
        adapter.notifyDataSetChanged();
    }

    public void update() {
        for(ShopItem item:array) {
            for(int idPurchased: user.purchasedIdItem) {
                if (idPurchased != item.getId()) continue;
                item.setPurchased();
                break;
            }
        }
        tvGold.setText(String.valueOf(user.totalGold));
    }
}

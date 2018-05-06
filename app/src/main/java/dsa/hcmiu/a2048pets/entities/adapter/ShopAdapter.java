package dsa.hcmiu.a2048pets.entities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.entities.model.ShopItem;

public class ShopAdapter extends ArrayAdapter<ShopItem> {

    private Context context;
    private int layout;

    private ArrayList<ShopItem> array;

    public ShopAdapter(@NonNull Context context, int resource, ArrayList<ShopItem> array) {
        super(context, resource, array);
        this.context = context;
        this.layout = resource;
        this.array= array;
    }

    private class ViewHolder{
        ImageView ivItem;
        TextView tvPrice;
        ImageButton btn;

        public ViewHolder(View view) {
            ivItem = (ImageView) view.findViewById(R.id.ivShopItem);
            tvPrice = (TextView) view.findViewById(R.id.tvPrice);
            btn = (ImageButton) view.findViewById(R.id.btnPurchase);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShopItem item = array.get(position);
        holder.ivItem.setImageResource(R.drawable.poke);
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        if (item.isPurchase()) holder.btn.setImageResource(R.drawable.equip);
            else holder.btn.setImageResource(R.drawable.purchase);
        return convertView;
    }
}

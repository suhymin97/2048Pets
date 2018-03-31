package dsa.hcmiu.a2048pets.entities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import dsa.hcmiu.a2048pets.entities.model.Pets;
import dsa.hcmiu.a2048pets.entities.model.Square;
import dsa.hcmiu.a2048pets.R;

/**
 * Created by Admin on 3/30/2018.
 */

public class ItemAdapter extends ArrayAdapter<Pets> {
    private Context context;
    private ArrayList<Pets> array;
    private int layout;


    public ItemAdapter(Context context, int resource, ArrayList<Pets> objects) {
        super(context, resource, objects);
        this.context = context;
        array = objects;
        layout = resource;
    }
 /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){  //Check NULL
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Chuyen layout thanh 1 item
            convertView = inflater.inflate(R.layout.item_pet,null);
        }
        if(array.size() > 0){
            Square sq = (Square)convertView.findViewById(R.id.square); // Take ID from "item_pet" //
            sq.textFormat(array.get(position));
        }
        return convertView;
    }
    */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater view = LayoutInflater.from(context);
            convertView = view.inflate(R.layout.item_pet,null);
            ImageView ivItem = (ImageView) convertView.findViewById(R.id.ivItem);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            //convertView.setOnHoverListener(); //motion drag
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.build(array.get(position).getPic());
        return convertView;
    }

    private class ViewHolder {
        private ImageView ivItem;

        private ViewHolder(View view) {
            ivItem = (ImageView) view.findViewById(R.id.ivItem);
        }

        void build(int pic) {
            ivItem.setImageResource(pic);
        }

    }
}

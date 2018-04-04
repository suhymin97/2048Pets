package dsa.hcmiu.a2048pets.entities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater view = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = view.inflate(layout, null);
        }
        if (array.size() > 0) {
            Pets currentPet = new Pets(array.get(position));
            ImageView ivItem = (ImageView) convertView.findViewById(R.id.ivItem);
            ivItem.setImageResource(currentPet.getPic());
            TextView tvValue = (TextView) convertView.findViewById(R.id.tvValue);
            tvValue.setText(currentPet.getValue());
        }
        return convertView;
    }
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

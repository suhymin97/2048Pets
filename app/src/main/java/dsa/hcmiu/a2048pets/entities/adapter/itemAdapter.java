package dsa.hcmiu.a2048pets.entities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import dsa.hcmiu.a2048pets.R;
import dsa.hcmiu.a2048pets.entities.model.square;


public class itemAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private ArrayList<Integer> array;


    public itemAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){  //Check NULL
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //Chuyen layout thanh 1 item
            convertView = inflater.inflate(R.layout.item_square,null);
        }
        if(array.size() > 0){
            square sq = (square)convertView.findViewById(R.id.square); // Take ID from "item_square" //
            sq.textFormat(array.get(position));
        }
        return convertView;
    }
}

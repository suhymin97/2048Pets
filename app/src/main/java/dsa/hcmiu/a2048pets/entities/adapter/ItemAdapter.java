package dsa.hcmiu.a2048pets.entities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import dsa.hcmiu.a2048pets.entities.model.Square;
import dsa.hcmiu.a2048pets.R;

/**
 * Created by Admin on 3/30/2018.
 */

public class ItemAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private ArrayList<Integer> array;


    public ItemAdapter(Context context, int resource, ArrayList<Integer> objects) {
        super(context, resource, objects);
        this.context = context;
        this.array = objects;
    }

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
}

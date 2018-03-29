package dsa.hcmiu.a2048pets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.entities.adapter.ItemAdapter;
import dsa.hcmiu.a2048pets.entities.model.Pets;
import dsa.hcmiu.a2048pets.entities.model.Data;

import static dsa.hcmiu.a2048pets.MenuActivity.mySong;
import static dsa.hcmiu.a2048pets.entities.model.Board.max;


public class PlayActivity extends Activity {
    private int[] arrId;
    private int[] arrImage;
    private String[] arrPet;
    private ArrayList<Pets> arrPets;
    private GridView gameplay;
    private ItemAdapter adapter;

    @Override
    public void onBackPressed() {
        final Dialog MyDialog = new Dialog(PlayActivity.this,R.style.FullHeightDialog);
        LayoutInflater inflater = PlayActivity.this.getLayoutInflater();
        MyDialog.setContentView(R.layout.dialog);
        Button btnyes = (Button) MyDialog.findViewById(R.id.btnyes);
        Button btnno = (Button) MyDialog.findViewById(R.id.btnno);
        TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage) ;
        tvMess.setText("The game will be unsaved. Are you sure want to quit?");
        Animation zoomin= AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoom_out);
        ImageView imgIcon = (ImageView) MyDialog.findViewById(R.id.icon_github);
        imgIcon.setAnimation(zoomin);
        imgIcon.setAnimation(zoomout);

        btnyes.setEnabled(true);
        btnno.setEnabled(true);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.cancel();
            }
        });
        MyDialog.show();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        TypedArray images = getResources().obtainTypedArray(R.array.arrImage);

        //get resources
        arrPet = getResources().getStringArray(R.array.arrNo);
        arrImage = new int[max];
        arrId = new int[max];
        int countNo = 2;
        for (int i = 0; i < max; i++) {
            arrImage[i] = images.getResourceId(i, -1);
            arrId[i] = countNo;
            countNo *= 2;
        }
        /*
        create();
        show();
        setData();
        */
    }

    private void create(){
        Data.getDatagame().value16(PlayActivity.this);
        adapter = new ItemAdapter(PlayActivity.this,0, Data.getDatagame().getnItems());
    }
    private void show(){
        gameplay = (GridView)findViewById(R.id.gameplay);
    }

    private void setData(){
        gameplay.setAdapter(adapter);
    }

}

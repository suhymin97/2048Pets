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
import dsa.hcmiu.a2048pets.entities.handle.HandleGame;
import dsa.hcmiu.a2048pets.entities.handle.OnSwipeTouchListener;
import dsa.hcmiu.a2048pets.entities.model.Pets;

import static dsa.hcmiu.a2048pets.entities.model.Board.max;


public class PlayActivity extends Activity {
    private int[] arrValue;
    private int[] arrImage;
    private ArrayList<Pets> matrixPet;
    private GridView gvMatrix;
    private ItemAdapter adapter;
    private static int maxValue = 8192;
    private static int numCount = 13;

    @Override
    public void onBackPressed() {
        final Dialog MyDialog = new Dialog(PlayActivity.this, R.style.FullHeightDialog);
        LayoutInflater inflater = PlayActivity.this.getLayoutInflater();
        MyDialog.setContentView(R.layout.dialog);
        Button btnyes = (Button) MyDialog.findViewById(R.id.btnyes);
        Button btnno = (Button) MyDialog.findViewById(R.id.btnno);
        TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage);
        tvMess.setText("The game will be unsaved. Are you sure want to quit?");
        Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        ImageView imgIcon = (ImageView) MyDialog.findViewById(R.id.icon_github);
        imgIcon.setAnimation(zoomin);
        imgIcon.setAnimation(zoomout);

        //btnyes.setEnabled(true);
        //btnno.setEnabled(true);
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

        create();
        show();
        setData();

        gvMatrix.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeUp() {
               HandleGame.getInstance().moveUp();
            }

            public void onSwipeRight() {
                HandleGame.getInstance().moveUp();
            }

            public void onSwipeLeft() {
                HandleGame.getInstance().moveUp();
            }

            public void onSwipeDown() {
                HandleGame.getInstance().moveUp();
            }
        });
    }

    private void create() {
        TypedArray images = getResources().obtainTypedArray(R.array.arrImage);

        //get resources
        arrImage = new int[numCount+1];
        arrValue = new int[numCount+1];
        int[] arrId = new int[maxValue+1];
        int countNo = 2;
        arrId[0] = 0;
        for (int i = 1; i < 14; i++) {
            arrImage[i] = images.getResourceId(i - 1, -1);
            arrValue[i] = countNo;
            arrId[countNo] = i;
            countNo *= 2;
        }

        //set item for layout
        if (matrixPet == null) matrixPet = new ArrayList<>();
        for (int i = 0; i < max * max; i++) {
            int value = HandleGame.getInstance().curBoard.getElement(i);
            Pets temp = new Pets(value);
            if (value > 0) temp.setPic(arrImage[arrId[value]]);
            temp.setId(arrId[value]);
            matrixPet.add(temp);
        }
    }

    private void show() {
        gvMatrix = (GridView) findViewById(R.id.gvMatrix);
    }

    private void setData() {
        adapter = new ItemAdapter(this, R.layout.item_pet, matrixPet);
    }

/*
    private void create(){
        Data.getDatagame().value16(PlayActivity.this);
        adapter = new ItemAdapter(PlayActivity.this,0, Data.getDatagame().getnItems());
    }
    private void show(){
        gvMatrix = (GridView)findViewById(R.id.gvMatrix);
    }

    private void setData(){
        gvMatrix.setAdapter(adapter);
    }
    */

}

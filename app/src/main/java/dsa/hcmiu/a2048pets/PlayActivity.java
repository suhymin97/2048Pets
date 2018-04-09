package dsa.hcmiu.a2048pets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import dsa.hcmiu.a2048pets.entities.model.Features;
import dsa.hcmiu.a2048pets.entities.model.Pets;

import static dsa.hcmiu.a2048pets.entities.model.Features.mySong;
import static dsa.hcmiu.a2048pets.entities.handle.HandleGame.arrId;
import static dsa.hcmiu.a2048pets.entities.handle.HandleGame.typePet;
import static dsa.hcmiu.a2048pets.entities.model.Board.max;


public class PlayActivity extends Activity {
    private ArrayList<Pets> matrixPet;
    private GridView gvMatrix;
    private ItemAdapter adapter;
    private View layout;
    private TextView tvScore, tvUndo, tvKey;
    private Button btnUndo, btnNew;

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
        mySong.start();
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvUndo = (TextView) findViewById(R.id.tvUndo);
        tvKey = (TextView) findViewById(R.id.tvKey);

        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnNew = (Button) findViewById(R.id.btnNewGame);
        create();
        setData();

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleGame.getInstance(PlayActivity.this).Undo();
                update();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleGame.getInstance(PlayActivity.this).newGame();
                update();
            }
        });

        gvMatrix.setOnTouchListener(new OnSwipeTouchListener(PlayActivity.this) {
            public void onSwipeUp() {
                HandleGame.getInstance(PlayActivity.this).moveUp();
                check();
            }

            public void onSwipeRight() {
                HandleGame.getInstance(PlayActivity.this).moveRight();
                check();
            }

            public void onSwipeLeft() {
                HandleGame.getInstance(PlayActivity.this).moveLeft();
                check();
            }

            public void onSwipeDown() {
                HandleGame.getInstance(PlayActivity.this).moveDown();
                check();
            }
        });

    }

    private void create() {
        //set item for layout
        if (matrixPet == null) matrixPet = new ArrayList<>();
        for (int i = 0; i < max * max; i++) {
            int value = HandleGame.getInstance(PlayActivity.this).curBoard.getEValue(i);
            matrixPet.add(new Pets(typePet[arrId[value]]));
        }
        gvMatrix = (GridView) findViewById(R.id.gvMatrix);
    }

    private void setData() {
        adapter = new ItemAdapter(this, R.layout.item_pet,
                HandleGame.getInstance(PlayActivity.this).curBoard.getMatrix());
        gvMatrix.setAdapter(adapter);
    }

    private void check() {
        update();
        if (HandleGame.getInstance(PlayActivity.this).gameOver()) {
            final Dialog MyDialog = new Dialog(PlayActivity.this, R.style.FullHeightDialog);
            LayoutInflater inflater = PlayActivity.this.getLayoutInflater();
            MyDialog.setContentView(R.layout.dialog_gameover);
            Button btnyes = (Button) MyDialog.findViewById(R.id.btnok);
            TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage);
            tvMess.setText("One more time?");
            btnyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HandleGame.getInstance(PlayActivity.this).newGame();
                    MyDialog.cancel();
                }
            });
            MyDialog.setCanceledOnTouchOutside(false);
            MyDialog.show();
        }
        update();
    }

    private void update() {
        adapter.notifyDataSetChanged();
        tvScore.setText(String.valueOf(HandleGame.getInstance(this).curBoard.getScoreBoard()));
        tvUndo.setText(String.valueOf(Features.getMaxUndo()));
        tvKey.setText(String.valueOf(Features.getMaxKey()));
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

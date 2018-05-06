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
import static dsa.hcmiu.a2048pets.entities.model.Features.sound;


public class PlayActivity extends Activity {
    private ArrayList<Pets> matrixPet;
    private GridView gvMatrix;
    private ItemAdapter adapter;
    private View layout;
    private TextView tvScore, tvUndo, tvHammer;
    private Button btnUndo, btnNew,btnSoundPlay;
    private Button btnHammer;

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
        if (sound) mySong.start();
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvUndo = (TextView) findViewById(R.id.tvUndo);
        tvHammer = (TextView) findViewById(R.id.tvHammer);

        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnHammer = (Button) findViewById(R.id.btnHammer);
        btnNew = (Button) findViewById(R.id.btnNewGame);
        btnSoundPlay = (Button) findViewById(R.id.btnSoundPlay);
        create();
        setData();

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleGame.getInstance().Undo();
                update();
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleGame.getInstance().newGame();
                update();
            }
        });

        btnSoundPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound) mySong.pause();
                else mySong.start();
                sound= !(sound || sound);
            }
        });

        btnHammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleGame.getInstance().hammer();
                update();
            }
        });
        gvMatrix.setOnTouchListener(new OnSwipeTouchListener(PlayActivity.this) { //extend class OnswipeTouch
            public void onSwipeUp() {
                HandleGame.getInstance().moveUp();
                check();
            }

            public void onSwipeRight() {
                HandleGame.getInstance().moveRight();
                check();
            }

            public void onSwipeLeft() {
                HandleGame.getInstance().moveLeft();
                check();
            }

            public void onSwipeDown() {
                HandleGame.getInstance().moveDown();
                check();
            }
        });

    }

    private void create() {
        gvMatrix = (GridView) findViewById(R.id.gvMatrix);
    }

    private void setData() {
        adapter = new ItemAdapter(this, R.layout.item_pet,
                HandleGame.getInstance().curBoard.getMatrix());
        gvMatrix.setAdapter(adapter);
        update();
    }

    private void check() {
        update();
        if (HandleGame.getInstance().gameOver()) {
            final Dialog MyDialog = new Dialog(PlayActivity.this, R.style.FullHeightDialog);
            LayoutInflater inflater = PlayActivity.this.getLayoutInflater();
            MyDialog.setContentView(R.layout.dialog_gameover);
            Button btnyes = (Button) MyDialog.findViewById(R.id.btnok);
            TextView tvMess = (TextView) MyDialog.findViewById(R.id.tvMessage);
            tvMess.setText("One more time?");
            btnyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HandleGame.getInstance().newGame();
                    update();
                    MyDialog.cancel();
                }
            });
            MyDialog.setCanceledOnTouchOutside(false);
            MyDialog.show();
        }
    }

    private void update() {
        adapter.notifyDataSetChanged();
        tvScore.setText(String.valueOf(HandleGame.getInstance().curBoard.getScoreBoard()));
        tvUndo.setText(String.valueOf(Features.getMaxUndo()));
        tvHammer.setText(String.valueOf(Features.getMaxHammer()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        HandleGame.getInstance().newGame();
        update();
    }
}

package dsa.hcmiu.a2048pets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

import dsa.hcmiu.a2048pets.entities.adapter.PetAdapter;
import dsa.hcmiu.a2048pets.entities.model.Board;
import dsa.hcmiu.a2048pets.entities.model.Pets;


public class PlayActivity extends Activity {
    private int[] arrId;
    private int[] arrImage;
    private String[] arrPet;
    private ArrayList<Pets> arrPets;
    private int max = 13;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(PlayActivity.this);
        a_builder.setMessage("Do you want to quit the game?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Quit game");
        alert.show();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        //gvMatrix = (DynamicGridView) findViewById(R.id.gridlayout);
        TypedArray images = getResources().obtainTypedArray(R.array.arrImage);

        //get resources
        arrPet = getResources().getStringArray(R.array.arrNo);
        arrImage = new int[max];
        arrId = new int[max];
        int countNo =2;
        for (int i = 0; i < max; i++) {
            arrImage[i] = images.getResourceId(i, -1);
            arrId[i] = countNo;
            countNo *=countNo;
        }

        //set item for layout
        if (arrPets == null) arrPets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pos = Board.getInstance().getElement(i, j);
                Pets temp = new Pets(arrId[pos]);
                temp.setPic(arrImage[pos]);
                arrPets.add(temp);
            }
        }

        //PetAdapter adapter = new PetAdapter();
        /*gvMatrix.setAdapter(adapter);
        gvMatrix.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                gvMatrix.startEditMode(position);

                return true;
            }
        });*/

        /*KeyListener listener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moveUp();
                        saveBest();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        saveBest();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeft();
                        saveBest();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        saveBest();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        saveBest();
                        newGame();
                        getBest();
                        break;
                    case KeyEvent.VK_SPACE:
                        saveBest();
                        stop();
                        break;
                    case KeyEvent.VK_DELETE:
                        best = 0;
                        break;
                    case KeyEvent.VK_CONTROL:
                        saveBest();
                        Undo();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
            }
        };
        setFocusable(true);
        addKeyListener(listener);*/
    }
}

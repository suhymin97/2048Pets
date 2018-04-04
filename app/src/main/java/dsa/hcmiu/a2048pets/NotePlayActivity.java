package dsa.hcmiu.a2048pets;

/**
 * Created by Admin on 3/30/2018.
 */

public class NotePlayActivity {
    /*
        //set item for layout
        if (arrPets == null) arrPets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pos = HandleGame.getInstance().curBoard.getEValue(i, j);
                Pets temp = new Pets(arrId[pos]);
                temp.setPic(arrImage[pos]);
                arrPets.add(temp);
            }
        }
        */

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

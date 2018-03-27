package dsa.hcmiu.a2048pets.entities.handle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import dsa.hcmiu.a2048pets.entities.model.Board;


/**
 * Created by Admin on 3/25/2018.
 */

public class HandleGame {
    ArrayList<Integer> List, ListUndo, ListHighScore;
    ArrayList<Integer> List1, List2;
    public static int highScore = 0;
    private static HandleGame instance;
    public static int countUndo;
    public static int best = 0;
    private static int score =0;
    private int countEmpty=0;
    public Board curBoard;
    private Stack<Board> boardStack = new Stack<>();
    int row;
    int col;
    int countMove;

    private Random random = new Random();

    public static HandleGame getInstance() {
        if (instance == null) {
            instance = new HandleGame();
        }
        return instance;
    }

    private HandleGame() {
        newGame();
    }

    private void init() {
        score = 0;
        countUndo = 0;
        int rCol1 = random.nextInt(Board.max-1);
        int rRow1 = random.nextInt(Board.max-1);
        int rCol2 = random.nextInt(Board.max-1);
        int rRow2 = random.nextInt(Board.max-1);
        curBoard = new Board(0);
        curBoard.setElement(rRow1,rCol1,2);
        curBoard.setElement(rRow2,rCol2,(random.nextInt(1)+1)*2);
    }

    public void newGame() {
        boardStack.clear();
        init();
    }

    public void saveHis() {
        boardStack.push(curBoard);
    }

    public void addRandomNumber(){
        if(countEmpty != 0)
        {
            int addPosition = random.nextInt(countEmpty);
            int count0 = 0 ;
            for (row = 0 ; row < Board.max; row++)
            {
                for (col = 0; col < Board.max; col ++)
                {
                    if (count0 == addPosition) //+1
                    {
                        curBoard.setElement(row,col,(random.nextInt(1)+1)*2);
                    }
                    if (curBoard.getElement(row,col) == 0) count0++;
                }
            }
        }
    }

    public boolean Undo() {
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();
        int countChange = 0;

        if (countUndo==5) return false;
        saveBoard();
        for (int row = 0; row < Board.max; row++) {
            for (int col = 0; col < Board.max; col++) {
                curBoard.setElement(row, col, ListUndo.get(row * Board.max + col));
                }
            }
            curBoard.score = curBoard.scoreUndo;

        for (row = 0; row < Board.max; row++) {
            for (col = 0; col < Board.max; col++) {
                List2.add(curBoard.getElement(row, col));
            }
        }

        for (int i = 0; i < List1.size(); i++) {
            if (List1.get(i).equals(List2.get(i))) {
                countChange++;
            }
        }

        if (countChange == List1.size()) {
            countChange = 0;
        } else {
            countUndo++;
        }
    }

    public void pushUp() {
        countEmpty=0;
        for (col = 0; col < Board.max; col++) {
            int count0 = 0;
            for (row = 0; row < Board.max; row++) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row + count0, col, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty+=count0;
        }
    }

    public void moveUp() { //3-1
        countMove=0;
        saveHis();
        pushUp();
        for (col = 0; col < Board.max; col++) {
            for (row = 1; row < Board.max; row++) {
                if (curBoard.getElement(row, col) == curBoard.getElement(row - 1, col)) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row - 1, col, t*t);
                    curBoard.setElement(row, col, 0);
                    curBoard.score += curBoard.getElement(row-1, col);
                }
            }
        }
        pushUp();
        if (countMove>0){
            countMove = 0;
            addRandomNumber();
        }
    }

    public void pushDown() { //row
        countEmpty=0;
        for (col = 0; col < Board.max; col++) {
            int count0 = 0;
            for (row = Board.max - 1; row >= 0; row--) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row + count0, col, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty+=count0;
        }
    }

    public void moveDown() { //0-3
        countMove=0;
        saveHis();
        pushUp();

        for (col = 0; col < Board.max; col++) {
            for (row = Board.max - 2; row >= 0; row--) {
                if (curBoard.getElement(row, col) == curBoard.getElement(row + 1, col)) {
                    int t = curBoard.getElement(row + 1, col);
                    curBoard.setElement(row + 1, col, t*t);
                    curBoard.setElement(row, col, 0);
                    curBoard.score += curBoard.getElement(row + 1, col);
                }
            }
        }
        pushDown();
        if (countMove >0 ) {
            countMove=0;
            addRandomNumber();
        }
    }

    public void pushRight() {
        countEmpty=0;
            for (row = 0; row < Board.max; row++) {
                int count0 = 0;
                for (col = Board.max-2; col >=0; col--) {
                    if (curBoard.getElement(row, col) == 0) count0++;
                    else {
                        int t = curBoard.getElement(row, col);
                        curBoard.setElement(row, col + count0, t);
                        curBoard.setElement(row, col, 0);
                        countMove++;
                    }
                }
                countEmpty+=count0;
            }
        }

    public void moveRight() { //0-3
        countMove=0;
        saveBoard();
        pushRight();

        for (row = 0; row < Board.max; row++) {
            for (col = Board.max - 2; col >= 0; col--) {
                if (curBoard.getElement(row, col) == curBoard.getElement(row, col + 1)) {
                    int t = curBoard.getElement(row, col + 1);
                    curBoard.setElement(row, col + 1, t*t);
                    curBoard.setElement(row, col, 0);
                    curBoard.score += curBoard.getElement(row, col + 1);
                }
            }
        }
        pushRight();
        if (countMove >0 ) {
            countMove=0;
            addRandomNumber();
        }
    }

    public void pushLeft() {
        countEmpty=0;
        for (row = 0; row < Board.max; row++) {
            int count0 = 0;
            for (col = 0; col <Board.max; col++) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row, col - count0, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty+=count0;
        }
    }

    public void moveLeft() { //3-0
        countMove=0;
        saveBoard();
        pushLeft();
        for (row = 0; row < Board.max; row++) {
            for (col = 1; col < Board.max; col++) {
                if (curBoard.getElement(row, col) == curBoard.getElement(row, col - 1)) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row, col - 1, t*t);
                    curBoard.setElement(row, col, 0);
                    curBoard.score += curBoard.getElement(row, col-1);
                }
            }
        }
        pushLeft();
        if (countMove>0){
            countMove = 0;
            addRandomNumber();
        }
    }

    public boolean gameOver() {
        return curBoard.fullCol() && curBoard.fullRow() && curBoard.fullSpecial();
    }

    public void saveBest() {
        ListHighScore = new ArrayList<Integer>();
        if (curBoard.score > best) {
            highScore = curBoard.score;
            best = curBoard.score;
        } else {
            highScore = best;
        }
        ListHighScore.add(highScore);
        HandleFile.writeFile(ListHighScore);

    }

    public int getBest() {
        ListHighScore = HandleFile.readFile();
        for (int i = 0; i < ListHighScore.size(); i++) {
            if (ListHighScore.get(i) > best) {
                best = ListHighScore.get(i);
            }
        }
        return best;
    }

}

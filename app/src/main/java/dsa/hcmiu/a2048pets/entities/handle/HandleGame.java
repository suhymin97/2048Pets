package dsa.hcmiu.a2048pets.entities.handle;

import java.util.ArrayList;
import java.util.Stack;

import dsa.hcmiu.a2048pets.entities.model.Board;


/**
 * Created by Admin on 3/25/2018.
 */

public class HandleGame {
    ArrayList<Integer> List, ListUndo, ListHighScore;
    ArrayList<Integer> List1, List2;
    public static int highScore = 0;
    public static int best = 0;
    private Stack<int[][]> move = new Stack<>();
    int row;
    int col;
    int countMove;

    public HandleGame() {
        Board.getInstance();
        getBest();
    }

    public void saveBoard() {
        List = new ArrayList<Integer>();
        for (row = 0; row < Board.max; row++) {
            for (col = 0; col < Board.max; col++) {
                List.add(Board.getInstance().getElement(row, col));
            }
        }
    }

    public void Undo() {
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();
        int countChange = 0;

        for (row = 0; row < Board.max; row++) {
            for (col = 0; col < Board.max; col++) {
                List1.add(Board.getInstance().getElement(row, col));
            }
        }
        if (Board.countUndo < 3 && Board.score > 100) {
            for (int row = 0; row < Board.max; row++) {
                for (int col = 0; col < Board.max; col++) {
                    Board.getInstance().setElement(row, col, ListUndo.get(row * Board.max + col));
                }
            }
            Board.score = Board.scoreUndo;
        }

        for (row = 0; row < Board.max; row++) {
            for (col = 0; col < Board.max; col++) {
                List2.add(Board.getInstance().getElement(row, col));
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
            Board.countUndo++;
        }

    }

    public void pushUp() {
        for (col = 0; col < Board.max; col++) {
            int count0 = 0;
            for (row = 0; row < Board.max; row++) {
                if (Board.getInstance().getElement(row, col) == 0) count0++;
                else {
                    int t = Board.getInstance().getElement(row, col);
                    Board.getInstance().setElement(row + count0, col, t);
                    Board.getInstance().setElement(row, col, 0);
                    countMove++;
                }
            }
        }
    }

    public void moveUp() { //3-1
        countMove=0;
        saveBoard();
        pushUp();
        for (col = 0; col < Board.max; col++) {
            for (row = 1; row < Board.max; row++) {
                if (Board.getInstance().getElement(row, col) == Board.getInstance().getElement(row - 1, col)) {
                    int t = Board.getInstance().getElement(row, col);
                    Board.getInstance().setElement(row - 1, col, t*t);
                    Board.getInstance().setElement(row, col, 0);
                    Board.score += Board.getInstance().getElement(row-1, col);
                }
            }
        }
        pushUp();
        if (countMove>0){
            countMove = 0;
            Board.getInstance().addRandomNumber();
            ListUndo.clear();
            ListUndo.addAll(List);
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushDown() { //row
        int count0;
        for (col = 0; col < Board.max; col++) {
            count0 = 0;
            for (row = Board.max - 1; row >= 0; row--) {
                if (Board.getInstance().getElement(row, col) == 0) count0++;
                else {
                    int t = Board.getInstance().getElement(row, col);
                    Board.getInstance().setElement(row + count0, col, t);
                    Board.getInstance().setElement(row, col, 0);
                    countMove++;
                }
            }
        }
    }

    public void moveDown() { //0-3
        countMove=0;
        saveBoard();
        pushUp();

        for (col = 0; col < Board.max; col++) {
            for (row = Board.max - 2; row >= 0; row--) {
                if (Board.getInstance().getElement(row, col) == Board.getInstance().getElement(row + 1, col)) {
                    int t = Board.getInstance().getElement(row + 1, col);
                    Board.getInstance().setElement(row + 1, col, t*t);
                    Board.getInstance().setElement(row, col, 0);
                    Board.score += Board.getInstance().getElement(row + 1, col);
                }
            }
        }
        pushDown();
        if (countMove >0 ) {
            Board.getInstance().addRandomNumber();
            ListUndo.clear();
            ListUndo.addAll(List);
            countMove = 0;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushRight() {
            for (row = 0; row < Board.max; row++) {
                int count0 = 0;
                for (col = Board.max-2; col >=0; col--) {
                    if (Board.getInstance().getElement(row, col) == 0) count0++;
                    else {
                        int t = Board.getInstance().getElement(row, col);
                        Board.getInstance().setElement(row, col + count0, t);
                        Board.getInstance().setElement(row, col, 0);
                        countMove++;
                    }
                }
            }
        }

    public void moveRight() { //0-3
        countMove=0;
        saveBoard();
        pushRight();

        for (row = 0; row < Board.max; row++) {
            for (col = Board.max - 2; col >= 0; col--) {
                if (Board.getInstance().getElement(row, col) == Board.getInstance().getElement(row, col + 1)) {
                    int t = Board.getInstance().getElement(row, col + 1);
                    Board.getInstance().setElement(row, col + 1, t*t);
                    Board.getInstance().setElement(row, col, 0);
                    Board.score += Board.getInstance().getElement(row, col + 1);
                }
            }
        }
        pushRight();
        if (countMove >0 ) {
            Board.getInstance().addRandomNumber();
            ListUndo.clear();
            ListUndo.addAll(List);
            countMove = 0;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushLeft() {
        for (row = 0; row < Board.max; row++) {
            int count0 = 0;
            for (col = 0; col <Board.max; col++) {
                if (Board.getInstance().getElement(row, col) == 0) count0++;
                else {
                    int t = Board.getInstance().getElement(row, col);
                    Board.getInstance().setElement(row, col - count0, t);
                    Board.getInstance().setElement(row, col, 0);
                    countMove++;
                }
            }
        }
    }

    public void moveLeft() { //3-0
        countMove=0;
        saveBoard();
        pushUp();
        for (row = 0; row < Board.max; row++) {
            for (col = 1; col < Board.max; col++) {
                if (Board.getInstance().getElement(row, col) == Board.getInstance().getElement(row, col - 1)) {
                    int t = Board.getInstance().getElement(row, col);
                    Board.getInstance().setElement(row, col - 1, t*t);
                    Board.getInstance().setElement(row, col, 0);
                    Board.score += Board.getInstance().getElement(row, col-1);
                }
            }
        }
        pushUp();
        if (countMove>0){
            countMove = 0;
            Board.getInstance().addRandomNumber();
            ListUndo.clear();
            ListUndo.addAll(List);
            Board.scoreUndo = Board.score - 100;
        }
    }

    public boolean gameOver() {
        return Board.getInstance().fullCol() && Board.getInstance().fullRow() && Board.getInstance().fullSpecial();
    }

    public void saveBest() {
        ListHighScore = new ArrayList<Integer>();
        if (Board.score > best) {
            highScore = Board.score;
            best = Board.score;
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

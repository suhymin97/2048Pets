package dsa.hcmiu.a2048pets.entities.handle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import dsa.hcmiu.a2048pets.entities.model.Board;
import dsa.hcmiu.a2048pets.entities.model.Features;

import static dsa.hcmiu.a2048pets.entities.model.Board.max;


/**
 * Created by Admin on 3/25/2018.
 */

public class HandleGame {
    ArrayList<Integer> ListHighScore;
    private static HandleGame instance;
    public static int highScore = 0;
    public static int best = 0;
    private int countEmpty = 0;
    public Board curBoard;
    private Stack<Board> boardStack;
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
        init();
    }

    private void init() {
        curBoard = new Board(0);
        boardStack = new Stack<>();
        int pos1 = random.nextInt(max*max - 1);
        int pos2 = random.nextInt(max*max - 1);
        while (pos1 == pos2) {
            pos2 = random.nextInt(max*max - 1);
        }
        curBoard = new Board(0);
        curBoard.setElement(pos1, 2);
        curBoard.setElement(pos2, (random.nextInt(1) + 1) * 2);
    }

    public void newGame() {
        init();
    }

    public void saveHis() {
        Board boardTemp = new Board(curBoard);
        boardStack.push(boardTemp);
    }

    public Board undoSaveHis() {
        return boardStack.pop();
    }

    public void addRandomNumber() {
        if (countEmpty > 0) {
            int addPosition = random.nextInt(countEmpty-1)+1;
            int count0 = 0;
            for (int i = 0; i < max * max - 1; i++) {
                if (curBoard.getElement(i)==0) {
                    if (count0 == addPosition) {
                        curBoard.setElement(i, (random.nextInt(1) + 1) * 2);
                        break;
                    }
                    count0++;
                }
            }
        }
    }


    public boolean Undo() {
        int u = Features.getMaxUndo();
        if (u == 0) return false;
        Board boardTemp = new Board(undoSaveHis());
        curBoard = new Board(boardTemp);
        Features.setMaxUndo(--u);
        return true;
    }

    public void pushUp() {
        countEmpty = 0;
        for (col = 0; col < Board.max; col++) {
            int count0 = 0;
            for (row = 0; row < Board.max; row++) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else if (count0!=0) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row - count0, col, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty += count0;
        }
    }

    public void moveUp() {
        countMove = 0;
        saveHis();
        pushUp();
        int merge=0;
        for (col = 0; col < Board.max; col++) {
            for (row = 1; row < Board.max; row++) {
                int t = curBoard.getElement(row, col);
                if (t == curBoard.getElement(row - 1, col) && t!=0) {
                    curBoard.setElement(row - 1, col, t * 2);
                    curBoard.setElement(row, col, 0);
                    t = curBoard.getScoreBoard() + curBoard.getElement(row - 1, col);
                    curBoard.setScoreBoard(t);
                    countMove++;
                    if (row>1) merge++;
                }
            }
        }
        if (merge>0) pushUp();
        if (countMove > 0) {
            countMove = 0;
            addRandomNumber();
        } else {
            undoSaveHis();
        }
    }

    public void pushDown() { //row
        countEmpty = 0;
        for (col = 0; col < Board.max; col++) {
            int count0 = 0;
            for (row = Board.max - 1; row >= 0; row--) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else if (count0!=0) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row + count0, col, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty += count0;
        }
    }

    public void moveDown() { //0-3
        countMove = 0;
        saveHis();
        pushDown();
        int merge=0;
        for (col = 0; col < Board.max; col++) {
            for (row = Board.max - 2; row >= 0; row--) {
                int t = curBoard.getElement(row, col);
                if (t>0 && t == curBoard.getElement(row + 1, col)) {
                    curBoard.setElement(row + 1, col, t * 2);
                    curBoard.setElement(row, col, 0);
                    t = curBoard.getScoreBoard() + curBoard.getElement(row + 1, col);
                    curBoard.setScoreBoard(t);
                    countMove++;
                    if (row<Board.max - 2) merge++;
                }
            }
        }
        if (merge>0) pushDown();
        if (countMove > 0) {
            countMove = 0;
            addRandomNumber();
        } else undoSaveHis();
    }

    public void pushRight() {
        countEmpty = 0;
        for (row = 0; row < Board.max; row++) {
            int count0 = 0;
            for (col = Board.max - 1; col >= 0; col--) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else if (count0!=0) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row, col + count0, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty += count0;
        }
    }

    public void moveRight() { //0-3
        countMove = 0;
        saveHis();
        pushRight();
        int merge=0;
        for (row = 0; row < Board.max; row++) {
            for (col = Board.max - 2; col >= 0; col--) {
                int t = curBoard.getElement(row, col);
                if (t>0 && t == curBoard.getElement(row, col + 1)) {
                    curBoard.setElement(row, col + 1, t * 2);
                    curBoard.setElement(row, col, 0);
                    t = curBoard.getScoreBoard() + curBoard.getElement(row, col + 1);
                    curBoard.setScoreBoard(t);
                    countMove++;
                    if (col<Board.max - 2) merge++;
                }
            }
        }
        if (merge>0) pushRight();
        if (countMove > 0) {
            countMove = 0;
            addRandomNumber();
        } else undoSaveHis();
    }

    public void pushLeft() {
        countEmpty = 0;
        for (row = 0; row < Board.max; row++) {
            int count0 = 0;
            for (col = 0; col < Board.max; col++) {
                if (curBoard.getElement(row, col) == 0) count0++;
                else if (count0!=0) {
                    int t = curBoard.getElement(row, col);
                    curBoard.setElement(row, col - count0, t);
                    curBoard.setElement(row, col, 0);
                    countMove++;
                }
            }
            countEmpty += count0;
        }
    }

    public void moveLeft() { //3-0
        countMove = 0;
        saveHis();
        pushLeft();
        int merge=0;
        for (row = 0; row < Board.max; row++) {
            for (col = 1; col < Board.max; col++) {
                int t = curBoard.getElement(row, col);
                if (t>0 && t == curBoard.getElement(row, col - 1)) {
                    curBoard.setElement(row, col - 1, t * 2);
                    curBoard.setElement(row, col, 0);
                    t = curBoard.getScoreBoard() + curBoard.getElement(row, col - 1);
                    curBoard.setScoreBoard(t);
                    countMove++;
                    if (col>1) merge++;
                }
            }
        }
        if (merge>0) pushLeft();
        if (countMove > 0) {
            countMove = 0;
            addRandomNumber();
        } else undoSaveHis();
    }

    public boolean gameOver() {
        return curBoard.fullBoard();
    }

    public void saveBest() {
        ListHighScore = new ArrayList<Integer>();
        if (curBoard.getScoreBoard() > best) {
            highScore = curBoard.getScoreBoard();
            best = curBoard.getScoreBoard();
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

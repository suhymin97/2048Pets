package dsa.hcmiu.a2048pets.entities.model;

import java.util.ArrayList;

/**
 * Created by Admin on 3/25/2018.
 */

public class Board {
    private int scoreBoard;
    public static int max = 4;
    private ArrayList<Integer> matrix;
    int row,col;

    private ArrayList<Integer> initBoard() {
        ArrayList<Integer> init = new ArrayList<>();
        for(int i = 0; i<max*max; i++) {
            init.add(0);
        }
        return init;
    }

    public Board(int scoreBoard, ArrayList<Integer> boardi) {
        this.scoreBoard = scoreBoard;
        if (!matrix.isEmpty()) matrix.clear();
        this.matrix.addAll(matrix);
    }

    public Board(int scoreBoard) {
        if (matrix.isEmpty()) matrix.addAll(initBoard());
        this.scoreBoard = scoreBoard;
    }

    public int getElement(int rowi, int col) {
        return matrix.get(rowi*4+col);
    }

    public void setElement(int rowi, int col, int value) {
        matrix.set(rowi*4+col,value);
    }

    public int getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(int scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public boolean fullRow(){
        int count = 0;
        for(row= 0; row <max-1;row++) {
            for (col = 0; col < max; col++) {
                if(matrix[row][col] == 0 || matrix[row][col] == matrix[row+1][col] )
                {
                    count++;
                }
            }
        }
        if(count == 0) return true;
        return false;
    }

    public boolean fullCol(){
        int count=0;
        for(row= 0; row <max;row++) {
            for (col = 0; col < max-1; col++) {
                if (matrix[row][col] == 0 || matrix[row][col] == matrix[row][col + 1]) {
                    count++;
                }
            }
        }
        if(count == 0) return true;
        return false;
    }

    public boolean fullSpecial()
    {
        boolean check = false;
        if(matrix[3][3] == 0 || matrix[3][3] == matrix[3][2]|| matrix[3][3] == matrix[2][3] ) return false;
        else return true;
    }

}
